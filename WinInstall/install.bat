mkdir C:\Nylon

copy ..\jar\Nylon.jar C:\Nylon\Nylon.jar
copy nylon.bat C:\Nylon\nylon.bat
copy nylond.bat C:\Nylon\nylond.bat

mkdir C:\Nylon\stdl
xcopy ..\stdl C:\Nylon\stdl /F /Y

setx PATH "%PATH%;C:\Nylon" /M

pause