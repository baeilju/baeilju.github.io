## VS Code
- System / User / Portable 3가지 Version 있음
- Downloads: https://code.visualstudio.com/Download
- Portable Ver.
  - 압축풀고 직접 data 폴더 만들고, 하위에 user-data, extensions, tmp 만들어야 함  
    참고설명: https://code.visualstudio.com/docs/editor/portable
  - 탐색기 파일, 폴더 우클릭 시 연결하려면 Registry 직접 수정해야 함  
    -> 이 부분이 귀찮아서 System Version 설치함
- System Install Version
  - extensions, user-data folder 위치 변경은 아래와 같이 함
```
code --extensions-dir="C:\Program Files\Microsoft VS Code\data\extensions"
code --user-data-dir="C:\Program Files\Microsoft VS Code\data\user-data"
```

## Spring 개발 환경 구축
- JDK 11 install version 설치
- Tomcat, Maven은 최신 버전 binary Downloads
- 설치 설정
  - https://devpad.tistory.com/8
  - https://devpad.tistory.com/19?category=773041
- Maven .m2 repository 위치
  - 기본 위치는 user/.m2/repository
  - maven/conf/setting.xml 내 수정하여 변경 가능
  - 변경 시 vscode 내 setting에서 mvn setting.xml 잡아주어야 함
  - vscode extension 중 setting 위치를 못잡는 것이 있는 것으로 추정  
    -> 이 문제 해결못함, 그래서 위치 변경하치 않고, 기본 위치 사용
  - Project 별로 dependency 별도 다운로드가 하거나,  
    타 시스템에서 로컬 repo. 필요하다면 설정 변경 필요함
MVN Repository 이동 시 아래 주석 해제하고 위치 지정하면 됨
```
  <!-- localRepository
   | The path to the local repository maven will use to store artifacts.
   |
   | Default: ${user.home}/.m2/repository
  <localRepository>/path/to/local/repo</localRepository>
  -->
```
- vscode mvn repository setting  
  Preperence > Setting > 'maven' > global, user setting에 settings.xml 경로 추가 (global은 생략 가능?)
```
"java.configuration.maven.globalSettings": "D:\\pgr\\apache-maven\\conf\\settings.xml"
"java.configuration.maven.userSettings": "D:\\pgr\\apache-maven\\conf\\settings.xml"
```




- Tomcat 한글깨짐
  - 레지스트리 키 추가할 것 (참고: https://steven-life-1991.tistory.com/91)
> 컴퓨터\HKEY_CURRENT_USER\Console\Tomcat  
> CodePage : 65001


## Python 개발 환경
- jupyter 시작폴더 위치 변경
config file 생성
```
jupyter notebook --generate-config

```
config file 내 경로 수정
```
# C:\Users\사용자이름\.jupyter\jupyter_notebook_config.py
# 주석 제거 후 원하는 위치 작성
#c.NotebookApp.notebook_dir=''
```
- Shotcut 실행 경로 변경 (이것만 하면 되는 듯)
```
D:\pgr\Anaconda3\python.exe D:\pgr\Anaconda3\cwp.py D:\pgr\Anaconda3 D:\pgr\Anaconda3\python.exe D:\pgr\Anaconda3\Scripts\jupyter-notebook-script.py "D:\src\py"
```

## 특이사항
```
*.java is a non-project file, only syntax errors are reported”  
```
- 기존 작업 공간에 java 파일 생성 후 빌드 시 발생, VSCode를 별도로 열어 가동하니 사라짐  
  말 그대로 project file이 아니라서 syntax errors만 보고하겠다는 것  
  *worksspace 정리하고 일단 지켜보는 걸로

-  ".\target\dependency" 폴더에 pom.xml에서 의존성을 가진 패키지들을 생성
```
<plugin>
    <artifactId>maven-dependency-plugin</artifactId>
    <executions>
        <execution>
            <phase>process-sources</phase>

            <goals>
                <goal>copy-dependencies</goal>
            </goals>

            <configuration>
                <outputDirectory>${targetdirectory}</outputDirectory>
            </configuration>
        </execution>
    </executions>
</plugin>
```

- jar 파일에 dependency 포함시키기
```
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>3.1.1</version>
    <configuration>
        <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
    </configuration>
    <executions>
        <execution>
            <id>make-assembly</id>                        <!-- this is used for inheritance merges -->
            <phase>package</phase>                        <!-- bind to the packaging phase -->
            <goals>
                <goal>single</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
- 참고사이트 : https://www.sysnet.pe.kr/Default.aspx?mode=2&sub=0&pageno=13&detail=1&wid=11980


### HTTPIE 설치
```
# Install
pip install -U httpie
# Test
http GET http://www.example.com
```

- JAVA Version 문제로 인한 Warning
```
Build path specifies execution environment JavaSE-1.7. There are no JREs installed in the workspace that are strictly compatible with this environment. 
The compiler compliance specified is 1.7 but a JRE 11 is used
```

- JDK 11인 경우 아래와 같이 1.11로 변경할 것
```
 <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.11</maven.compiler.source>
    <maven.compiler.target>1.11</maven.compiler.target>
  </properties>
```
