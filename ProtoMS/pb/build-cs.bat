@echo off&setlocal enabledelayedexpansion

set mulu="%cd%"

echo 当前目录%mulu%

for /r %mulu% %%i in (./*.proto) do (

set name=%%~nxi
set pname=%%~ni
echo 生成!pname!

protogen -i:!pname!.proto -o:C:/Users/sunhua/Workspaces/newplat/ProtoMS/c#_pb/!pname!.cs
)

echo 完成.

pause>nul