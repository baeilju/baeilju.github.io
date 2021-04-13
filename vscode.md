## 기본 설치
- Installer Ver의 경우 Extension 설치 위치를 특정할 수 없음 -> VSCode Portable Ver 설치함
- 다운로경로: https://code.visualstudio.com/Download
- .zip으로 되어 있는 파일 받아서 압축해제
- 수동으로 data폴더/하위 user-data, extensions, tmp 생성 필요
- 참고설명: https://code.visualstudio.com/docs/editor/portable
- 백업 시 data폴더 이동
- GIT 경로 확인 필요 .gitcofig
> editor = \"D:\\pgr\\VSCode\\Code.exe\" --wait

## Spring 개발 환경 구축
- Java, Tomcat, Maven 별도 설치 후
- extenstion 설치 : Java Extension Pack (*추가작성)

## 테스트
### 특이사항
- *.java is a non-project file, only syntax errors are reported”
- java 파일 생성 후 빌드 시 발생
