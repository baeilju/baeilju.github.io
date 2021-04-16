jupyter 시작폴더 위치 변경
```
jupyter notebook --generate-config

```
C:\Users\사용자이름\.jupyter\jupyter_notebook_config.py
```
#c.NotebookApp.notebook_dir=''
```

Shotcut 경로를 아래처럼 변경하니, 시작 위치 변경됨
```
D:\pgr\Anaconda3\python.exe D:\pgr\Anaconda3\cwp.py D:\pgr\Anaconda3 D:\pgr\Anaconda3\python.exe D:\pgr\Anaconda3\Scripts\jupyter-notebook-script.py "D:\src\py"
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
