
### CI/CD
- Cloud App/Microservice의 Pipeline은 복잡하여 이를 자동화할 수 있는 Tool을 필요로 함
- CI의 최종 산출물은 Docker Image이고, Build Path에 따라 Package부터 Image/Container를 계층적으로 누적함  
  ※ Image를 pull/run하면 Container임. Class, Instance와 같은 관계
- CD는 Image를 이용하여 Pod를 구성하고, Platform 운영 환경에 올리는 작업을 수행함
- Orchestration Tool(k8s)에서는 서비스 호출 대응, 로드 밸런스, 장애 복구, 자원 할당 등을 수행하고 있음
- Cloud Platform에 따라 github와 같은 repository의 src 변동과 연계되어 배포까지 자동 수행하고 있음(Azure 등)

### Azure
Azure AD +- Users
         +- Groups

.
+-- data
        +-- train
        +-- test
+-- jupyter

### Pipeline


