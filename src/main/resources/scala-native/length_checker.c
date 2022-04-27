#include <string.h>
#include <stdio.h>

int get_length(char arr[]) {
  int len = strlen(arr);
  printf("Length of `%s` is : %d ", arr, len);
  printf("\n");
  return len;
}