del /s /q bin
del /s /q WEB-INF\classes
cmd /c c:\apache-ant-1.8.2-bin\apache-ant-1.8.2\bin\ant
xcopy /e bin\* WEB-INF\classes