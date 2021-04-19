### jupyter 시작폴더 위치 변경
- config file 생성
```
jupyter notebook --generate-config

```
- config file 내 경로 수정
```
# C:\Users\사용자이름\.jupyter\jupyter_notebook_config.py
# 주석 제거 후 원하는 위치 작성
#c.NotebookApp.notebook_dir=''
```
- Shotcut 실행 경로 변경 (이것만 하면 되는 듯)
```
D:\pgr\Anaconda3\python.exe D:\pgr\Anaconda3\cwp.py D:\pgr\Anaconda3 D:\pgr\Anaconda3\python.exe D:\pgr\Anaconda3\Scripts\jupyter-notebook-script.py "D:\src\py"
```

### 미해결
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
