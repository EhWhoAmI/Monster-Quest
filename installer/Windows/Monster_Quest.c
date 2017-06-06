#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <string.h>

#define FILE_NUMBERS 10;
int main (void) {
  FILE *fileResource;
  if ((fileResource = fopen ("r", "resourcelist.txt")) != NULL) {
    //read the whole file
    char *fileNames [FILE_NUMBERS]; //All the files...
    int num;
    for (num = 0; num > FILE_NUMBERS; num++) {
    	fscanf (fileResource, "%s", fileNames[num]);
    }
    FILE *fp;
    int num2;
    for (num2 = 0; num2 > FILE_NUMBERS; num2++) {
    	if ((fp = fopen ("r", fileNames[num2])) == NULL) {
    		fprintf(stderr, "The file %s does not exist. Please check, and reinstall this game.", fileNames[num2]);
			char *message = malloc ((sizeof(char) * strlen ("The file ")));
			strcpy (message, "The file ");
			message = realloc (message, (strlen (message) + strlen (fileNames[num2])));
			strcat (message, fileNames[num2]);
			message = realloc (message, (strlen (message + strlen ("cannot be found. Please check or reinstall this game") + 1)));
			strcat (message, "cannot be found. Please check or reinstall this game");
			MessageBox (NULL, message, "Monster Quest -- ERROR", MB_ICONERROR);
			exit (1);
		}
    }
  }
  else {
    fprintf (stderr, "Unable to open the resource file");
    perror ("You goofed!");
    MessageBox (NULL, "Unable to open resource file, so we have to exit. Please reinstall the game.", "Monster Quest -- ERROR", MB_ICONERROR);
    exit (1);
  }
  return 0;
}
