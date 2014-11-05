#include <stdio.h>
#include <Windows.h>

#include "../crypto.h"
#include "../global.h"

int lastError;

int main(int argc, char** argv) {

  BYTE to_be_hashed[] = {'a','b','c'};
  DWORD to_be_hashed_length = 3;
  BYTE* digest_value;
  BYTE  digest_value_stored[] = { 0xA9, 0x99, 0x3E, 0x36, 0x47, 0x06, 0x81, 0x6A, 0xBA, 0x3E,
				  0x25, 0x71, 0x78, 0x50, 0xC2, 0x6C, 0x9C, 0xD0, 0xD8, 0x9D };
 
  DWORD digest_value_length;
  int rs;

  lastError = OPENSIGN_ERROR_NONE;

  printf("Digesting %4#0x %4#0x %4#0x\n",'a','b','c');
  rs = digest_sha1(to_be_hashed,to_be_hashed_length,&digest_value,&digest_value_length);
  if ( rs != 0 ) {
    printf("digest_sha1 failed with error code: %d\n",lastError);
  }

  for ( int i=0; i<digest_value_length; i++ ) {
    printf(" %4#0x",digest_value[i]);
    if ( (i+1) % 10 == 0 ) {
      printf("\n");
    }
  }
  printf("\n");

  if ( digest_value_length != 20 ) {
    printf("Invalid digest - wrong length\n");
  } else {
    int ok = 1;
    for ( int i=0; i<digest_value_length; i++ ) {
      if ( digest_value[i] != digest_value_stored[i] ) {
	printf("invalid digest - wrong value at position %d\n",i);
	ok = 0;
      }
    }
    if ( ok ) {
      printf("digest ok\n");
    }
  }
  return 0;
}
