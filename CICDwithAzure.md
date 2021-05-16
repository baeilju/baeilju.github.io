
## CI/CD
- Microservice의 Pipeline은 복잡하여 이를 자동화할 수 있는 Tool을 필요로 함
- CI : 최종 산출물은 Docker Image이고, Build Path에 따라 Package부터 Image/Container를 계층적으로 누적함  
- CD : Image를 이용하여 Pod를 구성하고, Platform 운영 환경에 올리는 작업을 수행함
- Orchestration Cluster(k8s) : 서비스 호출 대응, 로드 밸런스, 장애 복구, 자원 할당 등을 수행함
- CICD in Cloud Platform(AWS, Azure...) : 대부분 k8s를 기반으로 CICD pipeline이 구축됨. UI를 통해 간단히 셋팅함 

## CICD Java Springboot PJT
mvn -> docker -> kubectl

### Maven
```sh
mvn package
```

### Docker

### Kubernetes
- kubernetes object model
- deployment.yaml
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: _name
  labels:
    app: _app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: _app
template:
  metadata:
    labels:
      app: _app
spec:
  containers: 
    - name: _name
      image: _image
      ports: 
      - containerPort: 80
```

## Azure
### Portal : aks, acr 생성, 셋팅
필수적인 서비스 구조는 대략 아래와 같음.  
```
Azure AD
├─ Users
├─ Groups
├─ Apps
└─ Subscriptions: *임시 무료 계정 사용 가능, 장기 사용 시 구매 필요
   └─ Resource Groups
      ├─ Resource: Kubenetis Service (aks)
      ├─ Resource: Container Registry
      └─ Resource: etc.
```
- Workflow: Portal AD 가입 > Subscription 구매, 생성 > Resource 그룹 생성 > Resources 생성...  
- 필요한 Resource는 Market에서 무료/유료 구매해서 설치 (주의! 서비스 이용량에 따라 과금됨)
- AKS Cluster 생성  시 노드 사이즈, 개수 셋팅 필요하나 이에 대한 지식이 없음
- Portal 내 CLI 혹은 Local CLI에서 작업 가능함

```bash
// login 후 링크를 타고 계정 확인함
$ az login

// aks, acr은 portal에서 생성하거나 아래처럼 CLI에서 생성함
// aks 생성
$ az aks create --resource-group (RESOURCE-GROUP-NAME) --name (Cluster-NAME) --node-count 2 --enable-addons monitoring --generate-ssh-keys

// acr (Azure Container Registry) 생성
$ az acr create --resource-group (RESOURCE-GROUP-NAME) --name (REGISTRY-NAME) --sku Basic

// 자격증명 다운로드하고, 이를 사용할 수 있게 설정함 ?
$ az aks get-credentials --resource-group (RESOURCE-GROUP-NAME) --name (Cluster-NAME)

//Azure AKS에 ACR Attach 설정
$ az aks update -n (Cluster-NAME) -g (RESOURCE-GROUP-NAME) --attach-acr (REGISTRY-NAME)

// azure container 삭제
$ kubectl delete deploy --all
$ kubectl delete service --all

```
### Dev
- dev.azure.com
- 조직, 프로젝트 등 입력하고 들어감
2


## References
- Azure.AKS Quick Start: https://docs.microsoft.com/ko-kr/azure/aks/kubernetes-walkthrough
