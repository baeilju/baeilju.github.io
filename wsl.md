# WSL을 이용한 Window에서 Linux 구동 및 환경 설정
## WSL Install
- Linux용 Windows 하위 시스템 옵션 기능을 사용
```powershell
dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart
```
- Virtual Machine 플랫폼 옵션 기능을 사용하도록 설정
```powershell
dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart
```
- 재부팅
- [x64 머신용 최신 WSL2 Linux 커널 업데이트 패키지](https://wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi)
- WSL 2를 기본 버전으로 설정
```powershell
wsl --set-default-version 2
```
- [Linux 배포 설치](https://aka.ms/wslstore)  
  Linux Version 찾아서 설치할 것, 현재는 Ubuntu20.04.2 LTS 설치함

## 기본 Tools & 환경설정
### Windows Terminal
- Powershell, CMD, Linux bash, Azure 등 다양한 CLI 통합 툴  
- Ubuntu Shell 시작 위치 변경 : 설정 > Ubuntu > 시작위치  
```
\\\\wsl$\\Ubuntu-20.04\\home\\baeilju
```

### git  
- Window 설정
```bash
# 윈도에서는 CRLF 를 사용하므로 저장소에서 가져올 때 LF 를 CRLF 로 변경하고 저장소로 보낼 때는 CRLF 를 LF로 변경하도록 true로 설정
git config --global core.autocrlf true
# 윈도우 내 개행문자 지정
#git config --global core.eol lf #native 확인요
```
- Linux 설정
```bash
# Linux는 LF만 사용
git config --global core.autocrlf input
```

### vscode
- vscode는 window에 설치되어 있어야 함
- Extension / Remote WSL 설치  
  ※ Remote WSL vs. Remote Development: Remote Development는 SSH, Contain, WSL 포함
- linux home 폴더에 .code 생성됨
- 라이브러리 업데이트, 인증서 관련 항목 추가
```bash
sudo apt-get update
sudo apt-get install wget ca-certificates
```
- linux shell에서 code 실행
```bash
code .
```
- mnt 폴더에서 실행한 경우... wsl 최적 환경이 아니니 home 아래로 옮기라는 권고가 나옴
[Docs/Performance across OS file systems](https://docs.microsoft.com/en-us/windows/wsl/compare-versions#performance-across-os-file-systems)

## Java, Tomcat, Maven 개발환경 셋팅
### Java
- Install
```bash
sudo apt install openjdk-11-jdk
```
- JAVA_HOME 설정
```bash
cd /etc/profile.d
sudo touch java.sh
sudo vi java.sh

# java.sh 내 작성
export JAVA_HOME="/usr/lib/jvm/java-11-openjdk-apm64" # 경로는 확인요
```
- logout, restart

### Tomcat
apt install tomcat9

cd /usr/share/tomcat9/bin

 cannot create /usr/share/tomcat9/logs/catalina.out: Directory nonexistent
### Maven



## Trouble & Shooting
- Local drive mount 실패 시 wsl shutdown 후 재가동  
  발생 빈도가 높은 편 -> mnt script 추후 확인요 
```powershell
wsl --shutdonw
```

## References
- [Windows 10에 Linux용 Windows 하위 시스템 설치 가이드](https://docs.microsoft.com/ko-kr/windows/wsl/install-win10)

