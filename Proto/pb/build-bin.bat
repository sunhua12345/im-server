@echo off&setlocal enabledelayedexpansion

set mulu="%cd%"

echo ��ǰĿ¼%mulu%

for /r %mulu% %%i in (./*.proto) do (

set name=%%~nxi
set pname=%%~ni

echo ����!name!

protoc.exe --descriptor_set_out=!pname!.protobin --include_imports !name!

)

echo ���.

pause>nul