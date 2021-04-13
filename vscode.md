## 기본 설치
- Installer Ver의 경우 Extension 설치 위치를 특정할 수 없음 -> VSCode Portable Ver 설치함
- 다운로경로: https://code.visualstudio.com/Download
- .zip으로 되어 있는 파일 받아서 압축해제
- 수동으로 data폴더/하위 user-data, extensions, tmp 생성 필요
- 참고설명: https://code.visualstudio.com/docs/editor/portable
- 백업 시 data폴더 이동
- GIT 경로 확인 필요 .gitcofig
```
...  
> editor = \"D:\\pgr\\VSCode\\Code.exe\" --wait  
...
```

## Spring 개발 환경 구축
- Java, Tomcat, Maven 별도 설치 후
- extenstion 설치 : Java Extension Pack (*추가작성)
- maven repository 변경  
  C:\Users\Administrator\.m2\repository
```
  <!-- localRepository
   | The path to the local repository maven will use to store artifacts.
   |
   | Default: ${user.home}/.m2/repository
  <localRepository>/path/to/local/repo</localRepository>
  -->
```
  여기로 이동함
```
  <localRepository>D:\src\mvn\repository</localRepository>
```


## 특이사항
```
*.java is a non-project file, only syntax errors are reported”  
```
- 기존 작업 공간에 java 파일 생성 후 빌드 시 발생, VSCode를 별도로 열어 가동하니 사라짐  
  말 그대로 project file이 아니라서 syntax errors만 보고하겠다는 것  
  *worksspace 정리하고 일단 지켜보는 걸로

- Dependency file 이동 필요하면... 
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
