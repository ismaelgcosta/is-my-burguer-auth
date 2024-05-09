resource "kubectl_manifest" "is-my-burguer-auth-deployment" {
  depends_on = [
    data.aws_eks_cluster.cluster,
    kubernetes_secret.is-my-burguer-auth-db
  ]
  yaml_body = <<YAML
apiVersion: apps/v1
kind: Deployment
metadata:
  name: is-my-burguer-auth
  namespace: is-my-burguer
  labels:
    name: is-my-burguer-auth
    app: is-my-burguer-auth
spec:
  replicas: 1
  selector:
    matchLabels:
      app: is-my-burguer-auth
  strategy:
    type: Recreate
  template:
    metadata:
      labels:
        app: is-my-burguer-auth
    spec:
      containers:
        - name: is-my-burguer-auth
          resources:
            limits:
              cpu: "1"
              memory: "300Mi"
            requests:
              cpu: "300m"
              memory: "300Mi"
          env:
            - name: MONGODB_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-auth-db
                  key: password
            - name: MONGODB_USER
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-auth-db
                  key: username
            - name: MONGODB_HOST
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-auth-db
                  key: host
            - name: CLIENT_CREDENTIALS_ID
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-cognito
                  key: username
            - name: CLIENT_CREDENTIALS_SECRET
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-cognito
                  key: password
            - name: CLIENT_DOMAIN
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-cognito
                  key: cognito_domain
            - name: AWS_COGNITO_USER_POOL_ID
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-cognito
                  key: user-pool-id
            - name: AWS_REGION
              value: ${local.region}
            - name: AWS_API_GATEWAY_ID
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-cognito
                  key: api-gateway
            - name: SERVICE_DISCOVERY_USERNAME
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-sd
                  key: username
            - name: SERVICE_DISCOVERY_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-sd
                  key: password
          image: docker.io/ismaelgcosta/is-my-burguer-auth:${var.TF_VAR_IMAGE_VERSION}
          ports:
            - containerPort: 8943
      restartPolicy: Always
status: {}
YAML
}

resource "kubectl_manifest" "is-my-burguer-auth-svc" {
  depends_on = [
    data.aws_eks_cluster.cluster,
    kubectl_manifest.is-my-burguer-auth-deployment
  ]
  yaml_body = <<YAML
apiVersion: v1
kind: Service
metadata:
  name: is-my-burguer-auth-svc
  namespace: is-my-burguer
spec:
  selector:
    app: is-my-burguer-auth
  ports:
    - name: https
      protocol: TCP
      port: 8943
      targetPort: 8943
YAML
}

resource "kubectl_manifest" "is-my-burguer-auth-hpa" {
  depends_on = [
    data.aws_eks_cluster.cluster,
    kubectl_manifest.is-my-burguer-auth-deployment,
    kubectl_manifest.is-my-burguer-auth-svc
  ]
  yaml_body = <<YAML
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: is-my-burguer-auth-hpa
  namespace: is-my-burguer
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: is-my-burguer-auth
    namespace: is-my-burguer
  minReplicas: 1
  maxReplicas: 2
  behavior:
    scaleDown:
      stabilizationWindowSeconds: 0 # para forçar o kubernets a zerar a janela de tempo e escalar imediatamente
    scaleUp:
      stabilizationWindowSeconds: 0 # para forçar o kubernets a zerar a janela de tempo e escalar imediatamente
  metrics:
  - type: Resource
    resource:
      name: cpu
      target:
        type: Utilization
        averageUtilization: 10 # para forçar o kubernets escalar com 1% de cpu
status:
  observedGeneration: 0
  lastScaleTime:
  currentReplicas: 1
  desiredReplicas: 2
  currentMetrics:
  - type: Resource
    resource:
      name: cpu
YAML
}

resource "kubernetes_secret" "is-my-burguer-auth-db" {
  metadata {
    name      = "is-my-burguer-auth-db"
    namespace = "is-my-burguer"
  }

  immutable = false

  data = {
    host = "${data.terraform_remote_state.is-my-burguer-db.outputs.mongodb_endpoint_host}",
    username = "${var.TF_VAR_MONGODB_AUTH_USERNAME}",
    password = "${var.TF_VAR_MONGODB_AUTH_PASSWORD}"
  }

  type = "kubernetes.io/basic-auth"

}