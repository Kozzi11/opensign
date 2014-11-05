@echo off
rem -------------------------------------------------------------------------
rem OpenOces PDF Validator bootstrap for Windows
rem -------------------------------------------------------------------------

@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT" setlocal

if "%OS%" == "Windows_NT" (
  set "DIRNAME=%~dp0%"
) else (
  set DIRNAME=.\
)

if "x%JAVA_HOME%" == "x" (
  set  JAVA=java
  echo JAVA_HOME is not set. Unexpected results may occur.
  echo Set JAVA_HOME to the directory of your local JRE to avoid this message.
) else (
  set "JAVA=%JAVA_HOME%\bin\java"
)

set "CLASSPATH="

set "VALIDATOR_CLIENT_HOME=%DIRNAME%"

set "JAVA_OPTS=-Xms64M -Xmx256M -XX:MaxPermSize=64M"

for /R %VALIDATOR_CLIENT_HOME%lib %%a in (*.jar) do call :ADD_TO_PATH %%a

echo ===============================================================================
echo.
echo   PDF Validator Environment
echo.
echo   VALIDATOR_CLIENT_HOME: %VALIDATOR_CLIENT_HOME%
echo.
echo   JAVA: %JAVA%
echo.
echo   JAVA_OPTS: %JAVA_OPTS%
echo.
echo ===============================================================================
echo.

javaw %JAVA_OPTS% -classpath "%CLASSPATH%" org.openoces.opensign.pdf.validator.client.PdfValidatorApplication

goto END

:ADD_TO_PATH
SET CLASSPATH=%1;%CLASSPATH%

:END
