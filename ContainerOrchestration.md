# Container Orchestration
## 1. MSA, Container, and Container Orchestration
Container: Image가 실행된 형상
Service를 잘게 쪼개서 항상성이 자동 유지되도록(Self-Healing) 운영 -> 목표

Key Features of Kubernetes

Principles of Container-based Application Design
- Image Immutability
- High Observability
- Process Disposability
- Lifecycle Conformance
- Runtime Confinement
- Single Concern
- Self-Containment

Workload Distribution Engine
desired node, actual node
node 할당 -> engine


## 2. Docker Hands-on Lab

Docker Image Path 규칙
harbor <- CNCF에서 제공하는 private image 저장소
Image Push, Pull, Run...

dockerfile : docker image를 만들기 위한 receipe

Docker Image Command 정리

### Lab : Orientation
명령어 설치
```bash
apt-get update
apt-get install <명령어>  
#e.g. apt-get install zip
```

포트 사용중 프로세스 확인과 삭제
```bash
netstat -ntlp | grep :80
kill -9 <ProcessId>
```

작업 결과 폴더 압축해서 다운받기
```bash
zip test.zip ./*
```

리다이렉션 & 파이프 (|)
스트림의 흐름을 콘솔이 아닌 파일로 제어
```bash
ls > ls.txt # ls 명령어의 결과를 콘솔이 아닌, 파일로 기록
head < ls.txt # ls.txt의 출력을 head의 입력 스트림으로, 10 lines 출력
head < ls.txt > ls2.txt
echo "hello, world 2" > deployment2.yaml   # 첫번째 명령의 결과를 두번째 파일로 저장
echo "hello, korea" >> deployment2.yaml   # 첫번째 명령의 결과를 두번째 파일의 끝에 추가
```

### Lab : Docker Basics
이미지와 컨테이너의 관계

```bash
docker images

docker image pull <ImageName> #<ImageName> nginx
docker image ls #docker image 목록 확인
#1st Container Creation
docker run --name my-nginx -d -p 80:80 nginx
#80:80 container port <-> ms port 연결
docker container ls
curl localhost:80

#2nd Container Creation
docker run --name my-new-nginx -d -p 8080:80 nginx
curl localhost:8080
```


Dockerfile 및 샘플 Static App. 생성
```bash
mkdir DockerLab
cd DockerLab
nano index.html
    Hi~ My name is Hong Gil Dong..    
save & quit (ctrl+x, y 입력)

nano Dockerfile
    FROM nginx
    COPY index.html /usr/share/nginx/html/
save & quit (ctrl+x, y 입력)
```

Docker 이미지 생성 및 푸시
```bash
nano Dockerfile
    FROM nginx
    COPY index.html /usr/share/nginx/html/ # Local -> docker image 위치로 복사
save & quit (ctrl+x, y 입력)
```

Docker 이미지 생성 및 푸시
```bash
docker build -t DOCKER-ACCOUNT/my-nginx:v1 .
#마지막 . dockerfile을 가리킴
docker images
docker login 
docker push DOCKER-ACCOUNT/my-nginx:v1
```

Docker Hub 이미지를 활용한 컨테이너 생성
```bash
docker container rm $(docker ps -a -q) 
docker image rm -f $(docker images -q)
docker images
docker run --name=my-nginx -d -p 80:80 DOCKER-ACCOUNT/my-nginx:v1
```

### Azure 환경 설정
```bash
az aks get-credentials --resource-group user11-rsrc-grp --name user11-aks-cluster

$ az aks update -n user11-aks-cluster -g user11-rsrc-grp --attach-acr user11azurecr

kubectl create deploy myhome --image=[DOCKER ID]/my-nginx:v1

kubectl expose deploy myhome --type=LoadBalancer --port=80

kubectl get all

#External IP를 찾아서 browser에서 열면 확인 가능
```

### KOM
- Service : 라우트
- Deployment, Replica, Pod 세트로 만듦
    yaml에서

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deployment
    labels:
      app: nginx
spec:
  replicas: 3
selector:
matchLabels:
app: nginx
template:
metadata:
labels:
app: nginx
spec:
  containers: 
  - name: nginx
    image: nginx:1.7.9
    ports:
    - containerPort: 80
```
yaml indentation -> space 2개, - array 여러개의 항목이 올 수 있다


kubectl get [객체타입]
kubectl delete [객체타입][객체이름]

kubectl delete svc,deploy --all

kubectl create deployment nginx --image=nginx

watch kubectl get pod
watch는 지속 모니터링 명령어

kubectl delete pod/nginx-6799fc88d8-g49vl
지워도 다시 살아남

### Pod Status에 따른 Trouble Shooting



### yaml
kind pod > pod로만 k8s에 올린다?
올라가긴하는 데 deploy, replica없으면 살아나지 않음

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: declarative-pod
spec:
  containers:
  - name: memory-demo-ctr
    image: nginx
```
kubectl apply -f declarative-pod.yaml

## 3. Kubernetes Basic Object Model



## 4. Kubernetes Advanced Lab


## 5. Real MSA Application Deployment


## 6. Kubernetes Architecture




## 8. Cource Teset

## Day 2

### 비정형 정보 저장을 위한 Persistent Volume

현재 연결된 kubernetes context 확인
```
kubectl config current-context
```
> user_account-aks-cluster

### Volume : emptyDir
yaml file로를 생성함
```bash
kubectl apply -f volume-emptydir.yaml
```

volume-emptydir.yaml
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: shared-volumes
spec:
  containers:
  - image: redis
    name: redis
    volumeMounts:
    - name: shared-storage
      mountPath: /data/shared
  - image: nginx
    name: nginx
    volumeMounts:
    - name: shared-storage
      mountPath: /data/shared
  volumes:
  - name: shared-storage
    emptyDir: {}
```
Pod/shared-volumes 생성 시 shared-storage를 생성하고,
이를 Pod 내 두 container에서 공유하여 사용함

### PersistentVolume & PersistentVolumeClaim

파일/블록/오브젝트 스토리지

```
kubectl apply -f volume-pvc.yaml
kubectl get pvc
kubectl describe pvc
```

```
kubectl apply -f pod-with-pvc.yaml
kubectl get pod
kubectl describe pod mypod
kubectl exec -it mypod -- /bin/bash
cd /mnt/azure
```


```
kubectl delete deploy,svc --all
kubectl delete pvc --all
```


### Lab. 환경설정 관리- ConfigMap/Secret

• Scenario
ㅇ ConfigMap 생성
ㅇ ConfigMap의 환경변수를 읽어 출력하는 NodeJS 어플리케이션 준비
ㅇ Dockerfile 생성
ㅇ Dockerizing & Azure Container Registry에 Push
ㅇ Deployment yaml, Service yaml 준비
ㅇ 배포 및 서비스 생성
ㅇ 브라우저를 통해 서비스 확인
ㅇ ConfigMap의 환경변수를 어플리케이션이 정상적으로 참조하여 출력하는지 여부

?? Nodejs 코드?


```bash
kubectl create configmap hello-cm --from-literal=language=java
kubectl get cm
kubectl get cm hello-cm -o yaml
```

docker build -t (uengineorg).azurecr.io/cm-sandbox:v1 .
docker build -t user11azurecr.azurecr.io/cm-sandbox:v1 .
docker push (uengineorg).azurecr.io/cm-sandbox:v1
docker push user11azurecr.azurecr.io/cm-sandbox:v1

### Ingress

```
curl https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3 > get_helm.sh
chmod 700 get_helm.sh
./get_helm.sh
```

```
helm repo add stable https://charts.helm.sh/stable
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
kubectl create namespace ingress-basic
```


```
helm install nginx-ingress ingress-nginx/ingress-nginx --namespace=ingress-basic
```

az acr login --name (Azure Container Registry 명) 


```bash
docker build -t user11azurecr.azurecr.io/user11-nginx-blue:latest .
# Amazon Cluster인 경우, 아래 명령어 실행
# aws ecr create-repository --repository-name user30-nginx-blue --region ap-northeast-2
docker push user11azurecr.azurecr.io/user11-nginx-blue:latest

docker build -t user11azurecr.azurecr.io/user11-nginx-green:latest .
docker push user11azurecr.azurecr.io/user11-nginx-green:latest


```


kubectl create -f nginx-blue-deployment.yaml
kubectl create -f nginx-green-deployment.yaml


kubectl apply -f path-based-ingress.yaml
kubectl get ingress -n ingress-basic


Cluster에서의 Web-URL 원리
service 이름을 줘야 한다?

curl http://nginx-blue-svc
curl http://nginx-blue-svc:80
curl http://nginx-blue-svc.ingress-basic:80

## Kubernetes Architectire

api-server = kubernetes cluster
Scheduler, Controller는 자기 일이면 가져간다

Kubelet은 지점장
Container Runtime : Docker
Scheduler는 Node에 Pod를 할당


# Day 3

## 7. Service Mesh: Istio for Advanced Services Control

Istio Install
```
curl -L https://istio.io/downloadIstio | ISTIO_VERSION=1.7.1 TARGET_ARCH=x86_64 sh -
# istio v1.7.1은 Kubernetes 1.16이상에서만 동작
cd istio-1.7.1
export PATH=$PWD/bin:$PATH

istioctl install --set profile=demo --set hub=gcr.io/istio-release
"note : there are other profiles for production or performance testing."
```

Istio 모니터링 툴(Telemetry Applications) 설치
```
vi samples/addons/kiali.yaml
```
4라인의
apiVersion: apiextensions.k8s.io/v1beta1 을
apiVersion: apiextensions.k8s.io/v1으로 수정

addon 실행
```
kubectl apply -f samples/addons

# kiali.yaml 오류발생시, 아래 명령어 실행
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.7/samples/addons/kiali.yaml
```

모니터링(Tracing & Monitoring) 툴 설정

Monitoring Server - Kiali
기본 ServiceType 변경 : ClusterIP를 LoadBalancer 로…

```
kubectl get all -n istio-system
```
Jaeger, prometheus, kiali 등등을 포함하고 있음

모니터링(Tracing & Monitoring) 툴 설정
Monitoring Server - Kiali
기본 ServiceType 변경 : ClusterIP를 LoadBalancer 로…
```
kubectl edit svc kiali -n istio-system
:%s/ClusterIP/LoadBalancer/g
:wq!
```

Tracing Server - Jaeger
기본 ServiceType 변경 : ClusterIP를 LoadBalancer 로…
```
kubectl edit svc tracing -n istio-system
:%s/ClusterIP/LoadBalancer/g
:wq!
```

How to enable Istio
전처리 작업을 통한 istio enabling...
#1. Whenever deploying to Cluster, Using pre-processing command “Istio kube-inject”
e.g. $> kubectl apply -f <(istioctl kube-inject -f Deployment.yml) -n istio-test-ns
yaml을 istio 포함을 위한 yaml로 만들고 container 생성함

#2. Using Istio-enabled Namespace.
e.g. $> kubectl label namespace tutorial istio-injection=enabled
kubectl apply -f Deployment.yml -n tutorial

### Traffic Mgmt & Canary 배포

- Istio Tutorial 셋업
  - Git repository에서 Tutorial 리소스 가져오기
```
cd /home/project
git clone https://github.com/redhat-developer-demos/istio-tutorial
cd istio-tutorial
```
- 네임스페이스 생성
```
kubectl create namespace tutorial
```
- Customer Service 배포 생성확인
```
kubectl apply -f <(istioctl kube-inject -f customer/kubernetes/Deployment.yml) -n tutorial
kubectl describe pod (Customer Pod) -n tutorial
kubectl create -f customer/kubernetes/Service.yml -n tutorial
```
- Istio Gateway 설치 및 Customer 서비스 라우팅(VirtualService) 설정
cat customer/kubernetes/Gateway.yml
kubectl create -f customer/kubernetes/Gateway.yml -n tutorial
Istio-IngressGateway를 통한 Customer 서비스 확인
kubectl get service/istio-ingressgateway -n istio-system
해당 EXTERNAL-IP가 Istio Gateway 주소
Customer 서비스 호출
"http://(istio-ingressgateway IP)/customer"
Preference, Recommendation-v1 Service 배포
kubectl apply -f <(istioctl kube-inject -f preference/kubernetes/Deployment.yml) -n tutorial
kubectl create -f preference/kubernetes/Service.yml -n tutorial
kubectl apply -f <(istioctl kube-inject -f recommendation/kubernetes/Deployment.yml) -n tutorial
kubectl create -f recommendation/kubernetes/Service.yml -n tutorial
Simple Routing
pwd 로 현 위치가 /istio-tutorial/ 인지 확인
recommendation 서비스 추가 배포: v2

kubectl apply -f <(istioctl kube-inject -f recommendation/kubernetes/Deployment-v2.yml) -n tutorial
서비스 호출
브라우저에서 Customer 서비스(http://Istio-Gateway-IP/customer) 호출

F5(새로고침)를 10회 이상 클릭하여 다수의 요청 생성

Routing 결과 확인 - Kiali(Externl-IP:20001)

접속 서비스의 v2 의 replica 를 2로 설정

kubectl scale --replicas=2 deployment/recommendation-v2 -n tutorial
kubectl get po -n tutorial
Customer 서비스를 10회 이상 F5(새로고침)하여 서비스 호출
Routing 결과 확인 - Kiali(Externl-IP:20001) 접속
Advanced Routing with Istio
정책(VirtualService, DestinationRule) 설정 확인
kubectl get VirtualService -n tutorial -o yaml
kubectl get DestinationRule -n tutorial -o yaml
사용자 선호도에 따른 추천 서비스 라우팅 정책 설정
version-2로 100% 라우팅
kubectl create -f istiofiles/destination-rule-recommendation-v1-v2.yml -n tutorial
kubectl create -f istiofiles/virtual-service-recommendation-v2.yml -n tutorial
정책 설정 확인
kubectl get VirtualService -n tutorial -o yaml
kubectl get DestinationRule -n tutorial -o yaml
서비스 확인
브라우저에서 Customer 서비스(http://Istio-Gateway-IP/customer) 호출
Kiali(Externl-IP:20001), Jaeger(External-IP:80) 에서 모니터링
가중치 기반 스마트 라우팅
recommendation 서비스 v1의 가중치를 100으로 변경
kubectl replace -f istiofiles/virtual-service-recommendation-v1.yml -n tutorial
서비스 호출 및 Kiali(Externl-IP:20001)에서 모니터링
VirtualService 삭제 시, Round-Robin 방식으로 동작

kubectl delete -f istiofiles/virtual-service-recommendation-v1.yml -n tutorial
Canary 라우팅 비율별 배포 정책 예시

(90 : 10)
kubectl apply -f istiofiles/virtual-service-recommendation-v1_and_v2.yml -n tutorial
(75 : 25)
kubectl replace -f istiofiles/virtual-service-recommendation-v1_and_v2_75_25.yml -n tutorial
Header 기반 라우팅(브라우저 유형별)
Firefox 브라우저로 접속 시, v2로 라우팅되도록 설정
kubectl apply -f istiofiles/destination-rule-recommendation-v1-v2.yml -n tutorial
kubectl apply -f istiofiles/virtual-service-firefox-recommendation-v2.yml -n tutorial
Firefox 브라우저와 다른 브라우저에서 접속 확인 Browser 환경이 지원되지 않을 경우
curl -A Safari Externl-IP:8080
curl -A Firefox Externl-IP:8080
삭제
kubectl delete dr recommendation -n tutorial
kubectl delete vs recommendation -n tutorial



## Istio Timeout & Retry

Timeout
카프카 설치 및 주문(Order) 서비스 배포
kubectl --namespace kube-system create sa tiller 
kubectl create clusterrolebinding tiller --clusterrole cluster-admin --serviceaccount=kube-system:tiller

helm repo add incubator https://charts.helm.sh/incubator
helm repo update
kubectl create ns kafka
helm install my-kafka --namespace kafka incubator/kafka

kubectl get svc my-kafka -n kafka
tutorial 네임스페이스에 Istio Activation
네임스페이스가 없을 시, 생성 후 실행
kubectl label namespace tutorial istio-injection=enabled --overwrite
Timeout : Fail-Fast를 통한 Caller 자원 보호
Order Aggregate(Order.java)에 저장전 Thread.sleep 삽입
@PrePersist
  public void onPrePersist(){
    try {
        Thread.currentThread().sleep((long) (800 + Math.random() * 220));
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
  }
Dockerizing (Image Build, and Push)
tutorial 네임스페이스에 Order 서비스 배포
kubectl apply -f - <<EOF
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: order
    namespace: tutorial
    labels:
      app: order
  spec:
    replicas: 1
    selector:
      matchLabels:
        app: order
    template:
      metadata:
        labels:
          app: order
      spec:
        containers:
          - name: order
            image: user11azurecr.azurecr.io/order:v2
            ports:
              - containerPort: 8080
            resources:
              limits:
                cpu: 500m
              requests:
                cpu: 200m
EOF
Order 서비스 생성
kubectl expose deploy order --port=8080 -n tutorial
Ingres Gateway에 Order Timeout 설정
customer/kubernetes/Gateway.yaml 파일 오픈
마지막 행 다음에 Order 타임아웃 추가
- match:
    - uri:
        prefix: /orders
    route:
    - destination:
        host: order
        port:
          number: 8080
    timeout: 3s
변경 내용 적용
kubectl apply -f customer/kubernetes/Gateway.yml -n tutorial
VritualService에 Order 서비스 Timeout 설정
kubectl apply -f - <<EOF
    apiVersion: networking.istio.io/v1alpha3
    kind: VirtualService
    metadata:
      name: vs-order-network-rule
      namespace: tutorial
    spec:
      hosts:
      - order
      http:
      - route:
        - destination:
            host: order
        timeout: 3s
EOF
Siege 설치 및 Order 서비스 부하 생성
kubectl run siege --image=apexacme/siege-nginx -n tutorial
kubectl exec -it siege -c siege -n tutorial -- /bin/bash
siege -c30 -t20S -v --content-type "application/json" 'http://order:8080/orders POST {"productId": "1001", "qty":5}'
Order 서비스에 설정된 Timeout 임계치를 초과하는 순간, Istio에서 서비스로의 연결을 차단하는 것을 확인
Retry
tutorial 네임스페이스에 Delivery 서비스 배포
kubectl apply -f - <<EOF
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: delivery
    namespace: tutorial
    labels:
      app: delivery
  spec:
    replicas: 1
    selector:
      matchLabels:
        app: delivery
    template:
      metadata:
        labels:
          app: delivery
      spec:
        containers:
          - name: delivery
            image: IMAGE_FULL_REPOSITORY_URL/delivery:v2
            ports:
              - containerPort: 8080
            resources:
              limits:
                cpu: 500m
              requests:
                cpu: 200m
EOF
Delivery 서비스 생성
kubectl expose deploy delivery --port=8080 -n tutorial
Order 서비스에 Retry Rule 추가
kubectl apply -f - <<EOF
  apiVersion: networking.istio.io/v1alpha3
  kind: VirtualService
  metadata:
    name: vs-order-network-rule
    namespace: tutorial
  spec:
    hosts:
    - order
    http:
    - route:
      - destination:
          host: order
      timeout: 3s
      retries:
        attempts: 3
        perTryTimeout: 2s
        retryOn: 5xx,retriable-4xx,gateway-error,connect-failure,refused-stream
EOF
Delivery 서비스를 정지하고, 이를 동기호출하는 Order 서비스 API 호출
kubectl scale deploy delivery --replicas=0 -n tutorial
kubectl exec -it siege -c siege -n tutorial --/bin/bash
http http://order:8080/orders/ productId=1001 qty=5
http DELETE http://order:8080/orders/1
Jaeger 접속(http://tracing svc EXTERNAL-IP :80) 후, Retry 확인
검색조건: Service : order.tutorial,
Operation: delivery.tutorial.svc.cluster.local:8080/*
검색결과 : 총 Retry 횟수 + 1 의 Requests 로깅

## Istio - Circuit Breaker
### Namespace 생성 및 Istio Activation
kubectl create namespace istio-cb-ns
kubectl label namespace istio-cb-ns istio-injection=enabled
Istio Retry 디폴트 동작 확인
테스트 어플리케이션 배포
hello-server 앱은 env:RANDOM_ERROR
값의 확률로 랜덤하게 503 에러를 발생하는
로직이 포함
```bash
kubectl apply -f - << EOF
    apiVersion: v1
    kind: Pod
    metadata:
      name: hello-server-1
      namespace: istio-cb-ns
      labels:
        app: hello
    spec:
      containers:
      - name: hello-server-1
        image: docker.io/honester/hello-server:latest
        imagePullPolicy: IfNotPresent
        env:
        - name: VERSION
          value: "v1"
        - name: LOG
          value: "1"
---
    apiVersion: v1
    kind: Pod
    metadata:
      name: hello-server-2
      namespace: istio-cb-ns
      labels:
        app: hello
    spec:
      containers:
      - name: hello-server-2
        image: docker.io/honester/hello-server:latest
        imagePullPolicy: IfNotPresent
        env:
        - name: VERSION
          value: "v2"
        - name: LOG
          value: "1"
        - name: RANDOM_ERROR
          value: "0.2"
---
    apiVersion: v1
    kind: Service
    metadata:
      name: svc-hello
      namespace: istio-cb-ns
      labels:
        app: hello
    spec:
      selector:
        app: hello
      ports:
      - name: http
        protocol: TCP
        port: 8080
EOF
```

클라이언트용 서비스 배포
kubectl create deploy siege --image=apexacme/siege-nginx -n  istio-cb-ns
Health Status 기반 Circuit Breaker
Hello 서비스의 인스턴스 상태기반 Circuit Breaker 설정

```
kubectl apply -f - << EOF
  apiVersion: networking.istio.io/v1alpha3
  kind: DestinationRule
  metadata:
    name: dr-hello-server-cb
    namespace: istio-cb-ns
  spec:
    host: svc-hello
    trafficPolicy:
      connectionPool:
        tcp:
          maxConnections: 1
        http:
          http1MaxPendingRequests: 1
          maxRequestsPerConnection: 1
      outlierDetection:
        consecutive5xxErrors: 1
        interval: 1s
        baseEjectionTime: 3m
        maxEjectionPercent: 100
EOF				
```
Circuit Breaker 동작 확인
클라이언트(siege)에 접속하여 svc-hello 호출하기

hello-server-2의 로그 모니터 걸기

```bash
# Service Console monitoring
kubectl logs -f hello-server-1 -c hello-server-1 -n istio-cb-ns
kubectl logs -f hello-server-2 -c hello-server-2 -n istio-cb-ns
# Client connect
kubectl exec -it pod/[객체] -n istio-cb-ns -- /bin/bash

kubectl exec -it pod/siege-88f7fdd8d-6kzf5 -n istio-cb-ns -- /bin/bash

# Load generate.
siege -c1 -t10S -v http://svc-hello.istio-cb-ns:8080
```
모니터링 시스템(Kiali) : EXTERNAL-IP:20001 에서 Circuit Breaker 뱃지 발생 확인
Clear Istio
kubectl delete ns tutorial istio-cb-ns istio-system



##
##
k8s DashBoard Install
Install k8s DashBoard for AWS
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0-beta8/aio/deploy/recommended.yaml
계정 생성 및 클러스터 롤 바인딩
cat <<EOF | kubectl create -f -
 apiVersion: v1
 kind: ServiceAccount
 metadata:
   name: eks-admin
   namespace: kube-system
---
 apiVersion: rbac.authorization.k8s.io/v1
 kind: ClusterRoleBinding
 metadata:
   name: eks-admin
 roleRef:
   apiGroup: rbac.authorization.k8s.io
   kind: ClusterRole
   name: cluster-admin
 subjects:
 - kind: ServiceAccount
   name: eks-admin
   namespace: kube-system
EOF
인증토큰 조회
kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep eks-admin | awk "{print $1}")
Proxy 설정 및 DashBoard 연결
kubectl proxy
http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/#!/login
로 접속
복사한 토큰 정보로 로그인
Install k8s DashBoard for Azure
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0-beta8/aio/deploy/recommended.yaml
계정 생성 및 클러스터 롤 바인딩
cat <<EOF | kubectl create -f -
 apiVersion: v1
 kind: ServiceAccount
 metadata:
   name: admin-user
   namespace: kube-system
---
 apiVersion: rbac.authorization.k8s.io/v1
 kind: ClusterRoleBinding
 metadata:
   name: admin-user
 roleRef:
   apiGroup: rbac.authorization.k8s.io
   kind: ClusterRole
   name: cluster-admin
 subjects:
 - kind: ServiceAccount
   name: admin-user
   namespace: kube-system
EOF
인증 토큰 조회
kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep admin-user | awk '{print $1}')
복사된 토큰값 붙여넣기 및 로그인
az aks browse --resource-group (resource-group-name) --name (cluster-name)




---
9쪽
Image Immutability

10문제

도커는 이미지와 컨테이너 관계
이미지 이름 룰
맞는 이름으로 푸쉬하는 거다

주관식 철

서비스 리자일런스를 위해.... 두가지?

Liveness Probes & Readiness Probes

도커 파일의

베이스 이미지에 탑재하는 
실행하는 명령어

서비스의 타입
풀기능을 가진
내부에서만 통용되는 서비스
LoadBalancer
116쪽


비정형 데이터를 위한 볼륨
PVC
PersistentVolumeClaim
azure storage


azure portal cloud shell??


