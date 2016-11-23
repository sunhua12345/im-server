@echo off&setlocal enabledelayedexpansion

set mulu="%cd%"

echo 当前目录%mulu%

for /r %mulu% %%i in (./*.proto) do (

set name=%%~nxi
set pname=%%~ni

echo 生成!name!

protoc.exe --descriptor_set_out=!pname!.protobin --include_imports !name!

)

echo 完成.

pause>nul