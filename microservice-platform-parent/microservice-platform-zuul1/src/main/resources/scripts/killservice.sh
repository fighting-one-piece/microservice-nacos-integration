@echo off  
echo kill client service start
for /f "tokens=5" %%I in ('netstat -ano ^| findstr 18888') do taskkill /t /f /pid %%I
echo kill client service end