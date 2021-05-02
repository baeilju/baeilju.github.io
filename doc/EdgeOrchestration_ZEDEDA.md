### Summary
Edge Computing은 데이터 전송 비용, 실시간 응답, 보안 이슈로 인하여 상위 IT Server단이 아닌 Edge(현장) 단에서 Computing을 위한 자원을 소모하여 서비스를 제공하는 기술을 일컫습니다. Edge단에서 Cloud 기술을 적용하여 서비스를 하고자 한다면 기존 Cloud Server 환경과 마찬가지로 배포/연결/운영/자원 관리를 위한 공통적인 Orchestration 기술 적용이 필요하고, 추가적으로 Edge단이 가지고 있는 운영 환경의 특수성을 돌파할 수 있는 기술이 필요합니다.  



### Overview
> ZEDEDA is a simple and scalable cloud-based orchestration solution that delivers visibility, control and security for the distributed edge with the freedom of deploying and managing any app on any hardware at scale and connecting to any cloud or on-premises systems.

### Key Features
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

### X-Notes
- Visibility, Control and Security for the Distributed Edge
- Edge단 클라우드 운영을 위한 고려사항 Scalability, Fexibility, Ease of Use

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
