### Sourcetree
- Sourcetree에서 Github 계정 등록  
  Github에서 정상 로그인 되는 계정을 입력해도... 계속 Fail

### Java
war 생성을 위해 아래 추가

```
<packaging>war</packaging>
```

아래와 같은 에러 발생
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-war-plugin:2.2:war (default-war) on project test005: Error assembling WAR: webxml attribute is required (or pre-existing WEB-INF/web.xml if executing in update mode) -> [Help 1]
```

- REST API 예제
https://tech.devgd.com/9


### Python
- 파일 절대 경로 읽어오기 실행안됨
```
path= os.path.dirname(os.path.abspath(__file__))
```

```
---------------------------------------------------------------------------
NameError                                 Traceback (most recent call last)
<ipython-input-12-71f511e1eaa4> in <module>
----> 1 path= os.path.dirname(os.path.abspath(__file__))
      2 print(path)

NameError: name '__file__' is not defined
```
