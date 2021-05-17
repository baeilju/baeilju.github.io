
## CICD
- Microservice의 Pipeline은 복잡하여 이를 자동화할 수 있는 Tool을 필요로 함
- CI : 최종 산출물은 Docker Image이고, Build Path에 따라 Package부터 Image/Container를 계층적으로 누적함  
- CD : Image를 이용하여 Pod를 구성하고, Platform 운영 환경에 올리는 작업을 수행함
- Orchestration Cluster(k8s) : 서비스 호출 대응, 로드 밸런스, 장애 복구, 자원 할당 등을 수행함
- CICD in Cloud Platform(AWS, Azure...) : 대부분 k8s를 기반으로 CICD pipeline이 구축됨. UI를 통해 간단히 셋팅함 

## CICD for Springboot/Docker/K8S
- Workflow : mvn -> docker -> kubectl

### Maven
```java
mvn package
```

### Docker
- .dockerfile
```bash
FROM openjdk:8-jdk-alpine
RUN apk --no-cache add tzdata && cp/usr/share/zoneinfo/Asia/Seoul /etc/localtime
WORKDIR /app
COPY hello.jar hello.jar
COPY entrypoint.sh run.sh
RUN chmod 774 run.sh
ENV PROFILE=local
ENTRYPOINT ["./run.sh"]
```

### Kubernetes
- kubernetes object model -> "deployment.yaml" 정의  

> deployment.yaml
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
- yaml 생성 및 등록은 azure service를 이용함
- 상세 내용은 다음 강의에서 다룸

## Actions in Azure/Portal
필수적인 서비스 구조는 대략 아래와 같음.  
```tree
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

```powershell
# login 후 링크를 타고 계정 확인함
$ az login

# aks, acr은 portal에서 생성하거나 아래처럼 CLI에서 생성함
# aks 생성
$ az aks create --resource-group (RESOURCE-GROUP-NAME) --name (Cluster-NAME) --node-count 2 --enable-addons monitoring --generate-ssh-keys

# acr (Azure Container Registry) 생성
$ az acr create --resource-group (RESOURCE-GROUP-NAME) --name (REGISTRY-NAME) --sku Basic

# 자격증명 다운로드하고, 이를 사용할 수 있게 설정함 ?
$ az aks get-credentials --resource-group (RESOURCE-GROUP-NAME) --name (Cluster-NAME)

# Azure AKS에 ACR Attach 설정
$ az aks update -n (Cluster-NAME) -g (RESOURCE-GROUP-NAME) --attach-acr (REGISTRY-NAME)

# azure container 삭제
$ kubectl delete deploy --all
$ kubectl delete service --all

```
## Actions in Azure/Dev
### Overview, Scenario
- dev.azure.com
- 조직, 프로젝트 등 입력하고 들어감

CICD Scenario
```powershell
# github에서 원본 소스 가져오기
git clone https://github.com/<MY_ACCOUT>/<REPOSIOTY>.git

# 소스코드 빌드, 패키징 → output : jar 파일
mvn package

# Dockerizing -> output : 컨테이너 이미지
docker build -t <AZURE_CONTAINER_REGISTRY>.azurecr.io/<IMAGE>:<TAG> .
docker push <AZURE_CONTAINER_REGISTRY>.azurecr.io/<IMAGE>:<TAG>

# 배포 YAML 파일을 사용하여 클러스터에 배포
# deployment.yaml 내 acr image path 확인할 것
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

### CI Pipeline
- Pipeline Task 추가 
  - Get Sources
    - Pipeline > Where is your code? > Use the classic editor
    - Select a source, Repository, Branch
    - Agent job : Display name, Agent pool ??, Agent Spec(maybe Ubuntu-xx)
  - Maven
    - pom.xml, package 설정
  - Copy files to:
    - Source Folder: $(system.defaultworkingdirectory)
    - Contents: azure/*
    - Target: $(build.artifactstagingdirectory)
  - Publish Artifact: drop
  - Docker (build and push)
    - Container Registry: "Web UI에서 선택"
- Trigger 설정
  - Enable Continous Integration 옵션 선택
- Save & Que, Run Pipeline
- Test : github에서 소스 변경 후 image build 완료하는지 확인

### CD Pipeline
- 생성
  - Releases > New release pipeline > Empty job
  - Artifacts : CI Build 선택
  - Trigger : Enable 선택, CI 완료 시 자동으로 CD 진행함
- Stage 셋팅
  - Agent job 설정(Stage 1 > job, task 선택) : ubuntu 선택
  - Task 추가
    - Bash script
```
sed -i "s/latest/$(Build.BuildId)/g" $(System.DefaultWorkingDirectory)/<USER_PJT_CI_DEF>/drop/azure/deploy.yaml
```
    - kubectl 등록(deploy)
      - Kubernetes service connection > +New > Subscription 셋팅
      - Use configuration > ... > azure/deploy.xml  
        CI 시 생성되었고, 이를 CD 단계로 옮겨줌
    - kubectl 등록(service)
      - Kubernetes service connection > +New > Subscription 셋팅
      - Use configuration > ... > azure/service.xml  
        CI 시 생성되었고, 이를 CD 단계로 옮겨줌

## Q
- azure에서 bash 명령어 처리할 때 path 경로 잡는 내용 모르겠음
- service.yaml, deploy.yaml 용도 확인 필요
```
$(System.DefaultWorkingDirectory)/<USER_CI_DEF>/drop/azure/service.yaml
sed -i "s/latest/$(Build.BuildId)/g" $(System.DefaultWorkingDirectory)/<USER_PJT_CI_DEF>/drop/azure/deploy.yaml
```

## References
- [Azure.AKS Quick Start](https://docs.microsoft.com/ko-kr/azure/aks/kubernetes-walkthrough)
