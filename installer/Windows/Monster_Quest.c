#include <stdio.h>
#include <stdlib.h>

int main (void) {
  FILE *fileResource;
  if ((fileResource = fopen ("r", "resourcelist.txt")) != NULL) {
    //read the whole file
  }
  else {
    fprintf (stderr, "Unable to open the resource file");
    perror ("You goofed!");
  }
  return 0;
}
