@echo off&setlocal enabledelayedexpansion

set mulu="%cd%"

echo ��ǰĿ¼%mulu%

for /r %mulu% %%i in (./*.proto) do (

set name=%%~nxi
set pname=%%~ni
echo ����!pname!

protogen -i:!pname!.proto -o:C:/Users/sunhua/Workspaces/newplat/ProtoMS/c#_pb/!pname!.cs
)

echo ���.

pause>nul