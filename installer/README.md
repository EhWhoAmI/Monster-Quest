### File system checks, launcher and Installer
These things are for checking all the files exist, and if it doesn't exist, it will have to stop the whole thing.

### How to use
Depening on the file system you are using, you will need to drag the executeable file into the the root file system.

So far, we only have the windows version. The mac and linux version will come soon.

To compile the Windows version, you will need a gcc or g++ compiler (a C compiler)
open a command prompt window, and enter:
'gcc -o MonsterQuest Monster_Quest.c'
or 
'g++ -o MonsterQuest Monster_Quest.c'
,whichever your compiler is. 
Then enter, 
'MonsterQuest'
It should execute, a file system check, which should take a bit of time.
Then, it will run the Monster Quest game.
