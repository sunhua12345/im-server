@echo off&setlocal enabledelayedexpansion

set mulu="%cd%"

echo ��ǰĿ¼%mulu%

for /r %mulu% %%i in (./*.proto) do (

set name=%%~nxi

echo ����!name!

protoc.exe --java_out=D:/SourceTreeRepository/utouu-im/utouu-im-protobuf/src/main/java/./ !name!

)

echo ���.

pause>nul