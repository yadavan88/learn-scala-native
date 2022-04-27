#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <curl/curl.h>
 
void function_pt(void *ptr, size_t size, size_t nmemb, void *stream){
    printf("\nResult from curl C: %d", atoi(ptr));
}

int base64_decode(char arr[])
{
  CURL *curl;
  CURLcode res;
 
  curl = curl_easy_init();
  if(curl) {
    char *url = "http://httpbin.org/base64/";
    char *combined = malloc(strlen(url)+strlen(arr)+1);
    strcpy(combined, url);
    strcat(combined, arr);
    printf("Hello: %s \n",combined);
    curl_easy_setopt(curl, CURLOPT_URL, combined);
    /* redirected, so we tell libcurl to follow redirection */
    curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
 
    /* Perform the request, res will get the return code */
    res = curl_easy_perform(curl);
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, function_pt);
    /* Check for errors */
    printf("\nGET API Status enum value: %d \n",res);

    if(res != CURLE_OK)
      fprintf(stderr, "curl_easy_perform() failed: %s\n",
              curl_easy_strerror(res));
 
    /* always cleanup */
    curl_easy_cleanup(curl);
  }
  return 0;
}