#include <stdio.h>
#include <stdlib.h>

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
    for (num2 = 0; num2 > FILE_NUMBERS; num2++) {}
    	if ((fp = fopen ("r", fileNames[num2])) == NULL) {
    		fprintf(stderr, "The file %s does not exist. Please check, and reinstall this game.", fileNames[num2]);
	    }
	}
  }
  else {
    fprintf (stderr, "Unable to open the resource file");
    perror ("You goofed!");
    exit (1);
  }
  return 0;
}
