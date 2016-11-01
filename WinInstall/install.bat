mkdir C:\Nylon

copy ..\jar\Nylon.jar C:\Nylon\Nylon.jar
copy ..\jar\Java-Argument-Parser.jar C:\Nylon\Java-Argument-Parser.jar
copy nylon.bat C:\Nylon\nylon.bat

mkdir C:\Nylon\stdl
xcopy ..\stdl C:\Nylon\stdl /F /Y

setx PATH "%PATH%;C:\Nylon" /M

pause