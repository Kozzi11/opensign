#include <stdio.h>

#include "../crypto.h"
#include "../global.h"
#include "../utils.h"

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
  printf("Listing certificates found in MY store:\n");
  while ( e != NULL ) {
    int r;
    WCHAR* subjectDn;
    DWORD subjectDnLen;
    FILETIME ftNotBefore;

    printf(" certificate of length %d found\n", e->cbCertEncoded);
    
    r = getCertificateDn(e->pbCertEncoded,
			 e->cbCertEncoded,
			 0,
			 &subjectDn,
			 &subjectDnLen);
    if ( r != 0 ) {
      printf(" unable to get subject dn\n");
    } else {
      printf(" subject: %s\n",utf16string_convert(subjectDn));
    }

    e = e->prev;
  }

  freeCertificateList(l);
  if ( lastError != OPENSIGN_ERROR_NONE ) {
    printf("freeCertificateList() failed with error: %d", lastError);
  }
}
