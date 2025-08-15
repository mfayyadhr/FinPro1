pipeline {
  agent any
  options { timestamps() }

  environment {
    // --- Repo & layout ---
    REPO_URL      = 'https://github.com/mfayyadhr/FinPro1.git'
    BRANCH       = 'main'                     // branch yang akan di-deploy
    PROJECT_DIR   = '.'                       // root project
    MANIFEST_FILE = 'test-deployment.yaml'    // path manifest yang di-apply

    // --- Image & resource names ---
    IMAGE_NAME  = 'mfayyadhr/dev-finpro-backend'
    DEPLOY_NAME = 'dev-finpro-backend'
    ROUTE_NAME  = 'dev-finpro-backend'
    OC_PROJECT  = 'rmuhammadfayyadh-dev'           // namespace/project tujuan
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: "${env.BRANCH}", url: "${env.REPO_URL}", credentialsId: 'github-pat'
        script {
          env.GIT_SHA      = sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
          env.IMAGE_TAG    = "${env.GIT_SHA}-${env.BUILD_NUMBER}"
          env.DOCKER_IMAGE = "${env.IMAGE_NAME}:${env.IMAGE_TAG}"
          echo "Image tag: ${env.DOCKER_IMAGE}"
        }
      }
    }

    stage('Build Docker Image') {
      steps {
        dir("${env.PROJECT_DIR}") {
          sh "docker build -t ${env.DOCKER_IMAGE} ."
        }
      }
    }

    stage('Push Docker Image') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'docker-hub',
                                          usernameVariable: 'DOCKER_USER',
                                          passwordVariable: 'DOCKER_PASS')]) {
          sh """
            echo "\$DOCKER_PASS" | docker login -u "\$DOCKER_USER" --password-stdin
            docker push ${env.DOCKER_IMAGE}
            # opsional: jaga 'latest' untuk dev
            docker tag  ${env.DOCKER_IMAGE} ${env.IMAGE_NAME}:latest || true
            docker push ${env.IMAGE_NAME}:latest || true
            docker logout
          """
        }
      }
    }

    stage('Update Manifest (ganti image)') {
      steps {
        dir("${env.PROJECT_DIR}") {
          script {
            def txt = readFile(env.MANIFEST_FILE)
            // Ganti baris image: ... PERTAMA saja (aman untuk single container)
            txt = txt.replaceFirst(/(?m)^[ \\t]*image:[ \\t]*\\S+/, "image: ${env.DOCKER_IMAGE}")
            writeFile(file: env.MANIFEST_FILE, text: txt)
          }
          sh "echo '--- Patch manifest selesai'; grep -n 'image:' ${env.MANIFEST_FILE} || true"
        }
      }
    }

    stage('Deploy to OpenShift') {
      steps {
        withCredentials([
          string(credentialsId: 'OC_TOKEN',  variable: 'OC_TOKEN'),
          string(credentialsId: 'OC_SERVER', variable: 'OC_SERVER')
        ]) {
          sh '''
            set -e
            oc login --token="$OC_TOKEN" --server="$OC_SERVER" --insecure-skip-tls-verify=true
          '''
          sh "oc project ${env.OC_PROJECT}"
          sh "oc apply -f ${env.MANIFEST_FILE} -n ${env.OC_PROJECT}"
          sh "oc rollout status deployment/${env.DEPLOY_NAME} -n ${env.OC_PROJECT} --timeout=180s"

          script {
            def host = sh(
              script: "oc get route ${env.ROUTE_NAME} -n ${env.OC_PROJECT} -o jsonpath='{.spec.host}' || true",
              returnStdout: true
            ).trim()
            env.ROUTE_URL = host ? "https://${host}" : '(no route)'
            echo "Route URL: ${env.ROUTE_URL}"
          }
        }
      }
    }
  }

  post {
    success {
      echo "✅ Deploy sukses. Aplikasi: ${env.ROUTE_URL}"
    }
    failure {
      echo "❌ Deploy gagal. Cek log pipeline."
    }
    always {
      sh 'docker image prune -f || true'
    }
  }
}
