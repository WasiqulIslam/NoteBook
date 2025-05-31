# Note Book v1.0.0.4

An efficient encrypted notebook that is convenient to save daily notes and special notes.
Everytime you need to enter your password/code after running the application unless you save your password/code in the settings.txt file.

General usage:
Just open the notebook, write something and save and close. It will save the note for today's date, you don't need to mention the date.

Special usage:
Open the notebook, enter passord/code twice. write something and save and close. It will save the note for today's date, you don't need to mention the date. You have to remember the password otherwise you will lose you notes. For the first time decide about the pasword you will use and memorize it or write it elsewhere in a secured location.

![image](https://github.com/user-attachments/assets/7050fbec-6a59-490b-9d8b-a1a20f0bc9d6)

![image](https://github.com/user-attachments/assets/91cbef0a-1bba-4e29-bd28-91f4df15b7e2)

To run this program, please install JAVA development edition.
Example location:
https://www.oracle.com/java/technologies/downloads/ .
After you have installed JAVA you may have to add the java development location to the "path" environment variable unless the installer sets it.
Then double click on the Run.bat file to run the program(in windows). To run in Max or Linux you may need to use the Run.sh file.
for more referance see: "Readme(How To Run).txt" file.

To keep notes is one step to keep life organized, one step for success and find
errors of ownself.

This software is used to keep track of daily notes
ensuring security of the notes. Also special files can be saved as a quick referance.
After you have completed writing your note
press Ctrl+S to save.
Press F5 to get time.
Press F1, F2, F3 or F4 to view corresponding special files.
Press F9 to view today's note.
You can navigate to the upper "Browse" TAB to view old files.
When you are on the Browse tab, double click on the tree nodes(FILES)
repeatedly to expand the nodes.


ADVANCED:

You can customize your copy of this program
using the Settings.txt file.
Before modifying the settings file keep a backup copy
of the original file; as invalid setting file may cause this
program to not work.
In the settings file, every first line is a key
and second line is the value of that key.
Do not change the key, only change the value
(the 2nd, 4th, 6th, 8th line, and so on )
Encryption technique is used in the program.
This makes your files ambiguous to others.
Change the encryption password if needed
but change the password only once
(before you have started to use this program).
Or you can change the value of the key: "useDefaultEncryptionPassword"
to "no".
In that case, this program will always
prompt you for entering password(key) every time this
program is run.
It is upto you whether you keep your password in
settings file or provide it every time
this program runs.
If you do not change the settings file at all
this program will use the key "password"
as a password(according to the default settings).
You can also change the date/time format if you like.


WARNING:
If password(key) is invalid then the letters will be
in a format which is not readable by you.
In that case, do not save the document;
Otherwise that document will be corrupted.
So, if needed keep a backup of your notes.

Password Hint: If you are sure that your pc is secure,
you can keep your password in Settings.txt file.

Sample date/time format:
[%1$Td-%1$Tb-%1$TY %1$tI:%1$tM%1$Tp %1$Ta(GMT+6)]
or
[%1$Td/%1$Tm/%1$Ty %1$tH:%1$tM:%1$tS %1$TA(+6GMT)]
or
%1$tA, %1$tB %1$Td %1$TY %1$tH:%1$tM:%1$tS:%1$tL

Cloud Saving: If you change your note saving location to a drive that is already synced by Dropbox or Onedrive or somethig similar than the notes are saved in the cloud automatically.

Programmed by Wasiqul Islam.
Email: 
islam.wasiqul@gmail.com
LinkedIn:
https://www.linkedin.com/in/wasiqul-islam/

