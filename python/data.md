jupyter 시작폴더 위치 변경
```
jupyter notebook --generate-config

```
C:\Users\사용자이름\.jupyter\jupyter_notebook_config.py
```
#c.NotebookApp.notebook_dir=''
```

파일 절대 경로 읽어오기 실행안됨
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
