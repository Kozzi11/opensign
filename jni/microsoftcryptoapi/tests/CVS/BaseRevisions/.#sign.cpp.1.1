#include <stdio.h>

#include "../crypto.h"
#include "../global.h"

int lastError;

int main(int argc, char** argv) {

  struct certificate_data *l, *e;

  lastError = OPENSIGN_ERROR_NONE;

  l = getCertificatesInMyStore();
  if ( lastError != OPENSIGN_ERROR_NONE ) {
    printf("getCertificatesInSystemStore() failed with error: %d", lastError);
    return -1;
  }

  e = l;
  printf("Using certificates found in MY store to sign:\n");
  while ( e != NULL ) {
      BYTE to_be_signed[] = {'a','b','c'};
      DWORD to_be_signed_length = 3;
      BYTE* signature_value;
      DWORD signature_value_length;
      int rs;

      lastError = OPENSIGN_ERROR_NONE;

      printf("Signing %4#0x %4#0x %4#0x\n",'a','b','c');
      rs = sign(e->pbCertEncoded,
		e->cbCertEncoded,
		to_be_signed,
		to_be_signed_length,
		&signature_value,
		&signature_value_length);
      if ( rs != 0 ) {
	printf("sign failed with error code: %d\n",lastError);
      } else {
      
	for ( int i=0; i<signature_value_length; i++ ) {
	  printf(" %4#0x",signature_value[i]);
	  if ( (i+1) % 10 == 0 ) {
	    printf("\n");
	  }
	}
	printf("\n");
      }
      
      e = e->prev;
  }

  freeCertificateList(l);
  if ( lastError != OPENSIGN_ERROR_NONE ) {
    printf("freeCertificateList() failed with error: %d", lastError);
  }
}
