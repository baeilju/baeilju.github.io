
### CI/CD
- Cloud App/Microservice의 Pipeline은 복잡하여 이를 자동화할 수 있는 Tool을 필요로 함
- CI의 최종 산출물은 Docker Image이고, Build Path에 따라 Package부터 Image/Container를 계층적으로 누적함  
  ※ Image를 pull/run하면 Container임. Class, Instance와 같은 관계
- CD는 Image를 이용하여 Pod를 구성하고, Platform 운영 환경에 올리는 작업을 수행함
- Orchestration Tool(k8s)에서는 서비스 호출 대응, 로드 밸런스, 장애 복구, 자원 할당 등을 수행하고 있음
- Cloud Platform에 따라 github와 같은 repository의 src 변동과 연계되어 배포까지 자동 수행하고 있음(Azure 등)

### Azure
아래와 같은 구조로 서비스 되고 있음
```
+-- Azure AD
  +-- Users
  +-- Groups
  +-- Apps
  +-- Subscriptions
    +-- Resource Groups
      +-- Resource : Kubenetis Service
      +-- Resource : Container Registry
```
AD 가입 후 구매 정보 입력해야 Subscription 생성됨  

- Resource Groups 생성  
  Subscrioptn이 있어야 만들 수 있음  
  Subscription과 Sevice Region만 등록하면 간단히 생성됨
  
- Container Registry 생성  
  기본 Contioner (Ubuntu...)  
  Market에서 서비스 찾아서 이름 정하고, 등록하면 끝  
  
- AKS Cluster 생성  
  K8S version : 일단 최신 버전 진행함, 다른 시스템과 연계가 필요한 경우 호환성 확인할 것
  노드 사이즈, 개수 : *셋팅에 대한 지식 없음*

- Client 환경  
  Cloud Client, K8S Client가 먼저 설치되어 있어야 함  
  (Azure Portal에서 Cloud Shell 지원함)

```bash
$ az login
// login 후 링크를 타고 계정 확인함

// K8s Client 에 Target Context 설정
$ az aks get-credentials --resource-group (RESOURCE-GROUP-NAME) --name (Cluster-NAME)

//Azure AKS에 ACR Attach 설정
$ az aks update -n (Cluster-NAME) -g (RESOURCE-GROUP-NAME) --attach-acr (REGISTRY-NAME)


$ kubectl delete deploy --all
$ kubectl delete service --all

```

### Pipeline


