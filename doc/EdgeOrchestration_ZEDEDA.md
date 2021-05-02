*작성 중인 개인적 생각입니다.*

### Summary
Edge Computing은 데이터 전송 비용, 실시간 응답, 보안 이슈로 인하여 상위 IT Server단이 아닌 Edge(현장) 단에서 Computing을 위한 자원을 소모하여 서비스를 제공하기 위한 기술을 일컫습니다. Edge단에서 Cloud 기술을 적용하여 서비스를 하고자 한다면 기존 Cloud Server 환경과 마찬가지로 배포/연결/운영/자원 관리를 위한 공통적인 Orchestration 기술 적용이 필요할 뿐 아니라, 추가적으로 Edge단이 가지고 있는 특수성을 돌파할 수 있는 기술이 필요합니다.  
Edge가 검사기와 같이 극단적으로 Unit 수준인지, Machine, 혹은 Floor나 Factory 수준인지 명확하지 않으나, 기존 상위IT 단에 구축된 Cloud Service 환경보다는 자원의 열세에 놓여있고, 기 구축된 HW, OS 등 Infra가 Cloud 운영에 적합하지 않은 상태에 놓여 있는 것이 일반적입니다. 따라서 Edge Computing을 위한 Cloud Orchestration 기술에서는 (1) 기존 Edge Resource를 Cloud 운영에 적합하게 어떻게 쉽게 전환해 줄 수 있는가, (2) 부가적인 Resource가 필요한 경우, 이를 기존 현장과 쉽게 융화하여 운영할 수 있는가, (3) 무거운 Cloud 운영 환경을 어떻게 경량화할 수 있는가 검토되어야 할 것 같습니다.  
ZEDADA 솔루션에 대하여 공개된 소개 내용으로는 (1), (2), (3)번 항목에 대해 파학하기 어려우나, (1) 항목에 대해서 일부 언급하고 있습니다.


### ZEDADA / Overview
> ZEDEDA is a simple and scalable cloud-based orchestration solution that delivers visibility, control and security for the distributed edge with the freedom of deploying and managing any app on any hardware at scale and connecting to any cloud or on-premises systems.

### ZEDADA / Key Features
Freedom of Choice
- ARM, x86, GPU등 HW Resource Type 제약 없음
- Docker & Kubernetes 운영 지원
- AWS, Microsoft Azure, Google Cloud Platform 지원
- Cloud와 VPN 연결 단순화
- Overlay network for intra-edge compute node connectivity
- Policy-based network failover; ethernet, LTE, satellite & Wif

Remote & Centralized Deployment and Management at Scale
- Deploy or upgrade apps and base OS of hardware
- Visibility, reports and status of all hardware and apps
- Alerting, events, resource utilization, and analytics 

Security and Privacy
- Hardware root of trust (e.g., TPM)
- Measured boot and remote attestation
- Crypto-based identification (no username/passwords)
- Data encryption at rest and in-flight
- Distributed firewall for every app
- Physical security - port isolation
- Role-based access control (RBAC)

Open Edge Ecosystem of Apps and Solutions
- Marketplace for ZEDEDA and partner-certified apps
- BYO-Apps in branded/private store
- Single-click bulk deployment (or updates) of apps

### References  
- ZEDADA Homepage: 
- https://zededa.com/
- https://zededa.com/wp-content/uploads/2021/01/Zededa_datasheet_agility_January2021-v2.pdf
- https://zededa.com/wp-content/uploads/2021/03/ZEDEDA-Architecture-WP-Feb2021.pdf
- https://zededa.com/wp-content/uploads/2021/03/ZEDEDA-Security-WP-Feb2021.pdf

- https://www.businesswire.com/news/home/20210428006164/en/
- https://cloudify.co/blog/birth-of-edge-orchestrator-cloudify/
- https://assets.ext.hpe.com/is/content/hpedam/a50002477enw
- https://www.intel.com/content/dam/www/public/us/en/documents/white-papers/simplifying-edge-orchestration.pdf
