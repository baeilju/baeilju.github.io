*작성 중인 개인적 생각입니다.*

### Summary
Edge Computing은 데이터 전송 비용, 실시간 응답, 보안 이슈로 인하여 상위 IT Server단이 아닌 Edge(현장) 단에서 Computing을 위한 자원을 소모하여 서비스를 제공하기 위한 기술을 일컫습니다. Edge단에서 Cloud 기술을 적용하여 서비스를 하고자 한다면 배포/생성/운영/자원 관리를 위한 공통적인 Orchestration 기술 적용이 필요할 뿐 아니라, 추가적으로 Edge단이 가지고 있는 특수성을 돌파할 수 있는 기술이 필요합니다.  
  
Edge가 검사기와 같이 극단적인 소규모 Device 수준인지, Machine, 혹은 Floor나 Factory 수준인지는 상황에 따라 다르지만, 상위IT 단에 구축된 Cloud Service 환경보다는 Cloud 운영에 적합하지 않은 상태로 자원의 열세에 놓여있고, 이를 변경하기 쉽지 않은 것이 일반적인 현장의 상황입니다. 따라서 Edge Computing을 위한 Cloud Orchestration 기술에서는 (1) 기존 Edge/Legacy Resource를 Cloud 운영에 적합하게 쉽게 전환할 수 있는가, (2) 현장 Infra가 부족한 경우, Resource 추가/이동을 위한 이점을 제공하는가, (3) 무거운 Cloud 운영 환경을 어떻게 경량화할 수 있는가 검토되어야 할 것 같습니다.  
  
ZEDADA 솔루션에 대하여 공개된 소개 내용으로는 (1), (2), (3)번 항목에 대해 파학하기 어려우나, (1) 항목에 대해서 EVE-OS를 언급하고 있습니다. EVE-OS는 x86, Arm 등 다양한 HW 자원을 가진 시스템의 자원을 서비스/운영하기 위한 기술을 제공하고, VM을 통해 기존 시스템의 서비스와 병렬로 제공될 수 있는 환경을 제공하고자 하고 있습니다. LFEdge라는 곳에서 Edge를 위한 Open Source Framework 구축을 목표로 하는 진행하고 있고, ZEDADA는 회원사 중 하나입니다. ZEDADA에서는 EVE-OS 적용된 솔루션을 제공하는지, 적용 후 관리 환경만을 제공하는지는 추가 확인이 필요합니다.  
  
ZEDADA는 Orchestration 관점에서는 잘 구조화된 Framework를 가지고 있는 것으로 보이나, Edge단에서 가지는 차별화된 강점에 대해서는 명확히 파악하기 어렵습니다. 이 솔루션 도입을 검토한다면 우선 자사의 어느 Edge 환경에 적용하고자 하는지에 대해 파악하고, EVE-OS에 대한 적용 검토 이후에 검토를 진행하는 것이 적합할 것으로 보입니다.


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
