@echo off&setlocal enabledelayedexpansion

set mulu="%cd%"

echo 当前目录%mulu%

for /r %mulu% %%i in (./*.proto) do (

set name=%%~nxi

echo 生成!name!

protoc.exe --java_out=D:/SourceTreeRepository/utouu-im/utouu-im-protobuf/src/main/java/./ !name!

)

echo 完成.

pause>nul