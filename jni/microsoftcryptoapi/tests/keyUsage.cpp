#include <stdio.h>

#include "../crypto.h"
#include "../global.h"
#include "../utils.h"
#include "../include/wincrypt_extra.h"

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
  while ( e != NULL ) {
    int r;
    WCHAR* subjectDn;
    DWORD subjectDnLen;
    FILETIME ftNotBefore;

    printf(" certificate of length %d found\n", e->cbCertEncoded);
    
    BYTE keyUsage[2];

    r = getCertificateKeyUsage(e->pbCertEncoded,
			 e->cbCertEncoded,
			 keyUsage);

    if ( r != 0 ) {
      printf(" unable to get key usage dn\n");
    } else {
      int i = 0;

      printf("Key usages:\n");
      printf("  digital signature: %s\n", CERT_DIGITAL_SIGNATURE_KEY_USAGE & keyUsage[0] ? "Yes" : "No");
      printf("  non repudiation: %s\n", CERT_NON_REPUDIATION_KEY_USAGE & keyUsage[0] ? "Yes" : "No");
      printf("  key encipherment: %s\n", CERT_KEY_ENCIPHERMENT_KEY_USAGE & keyUsage[0] ? "Yes" : "No");
      printf("  data encipherment: %s\n", CERT_DATA_ENCIPHERMENT_KEY_USAGE & keyUsage[0] ? "Yes" : "No");
      printf("  key agreement: %s\n", CERT_KEY_AGREEMENT_KEY_USAGE & keyUsage[0] ? "Yes" : "No");
      printf("  key certificate signing: %s\n", CERT_KEY_CERT_SIGN_KEY_USAGE & keyUsage[0] ? "Yes" : "No");
      printf("  crl signing: %s\n", CERT_CRL_SIGN_KEY_USAGE & keyUsage[0] ? "Yes" : "No");
      printf("  encipher only: %s\n", CERT_ENCIPHER_ONLY_KEY_USAGE & keyUsage[0] ? "Yes" : "No");
      printf("  decipher only: %s\n", CERT_DECIPHER_ONLY_KEY_USAGE & keyUsage[1] ? "Yes" : "No");

      /*
      byte usages_0[] = {CERT_DIGITAL_SIGNATURE_KEY_USAGE,
			 CERT_NON_REPUDIATION_KEY_USAGE,
			 CERT_KEY_ENCIPHERMENT_KEY_USAGE,
			 CERT_DATA_ENCIPHERMENT_KEY_USAGE,
			 CERT_KEY_AGREEMENT_KEY_USAGE,
			 CERT_KEY_CERT_SIGN_KEY_USAGE,
			 CERT_CRL_SIGN_KEY_USAGE,
			 CERT_ENCIPHER_ONLY_KEY_USAGE};
      byte usages_1[] = {CERT_DECIPHER_ONLY_KEY_USAGE};

      for ( int j=0;j<8;j++) {
	if ( usages_0[j] & keyUsage[0] ) {
	  i = i | (1<<j);
	}
      }
      for (int j=0;j<1;j++) {
	if ( usages_1[j] & keyUsage[1] ) {
	  i = i | (1<<(j+8));
	}
      }

      printf(" key usage: %x %x %i\n",keyUsage[0],keyUsage[1],i);
      */
    }

    e = e->prev;
  }

  freeCertificateList(l);
  if ( lastError != OPENSIGN_ERROR_NONE ) {
    printf("freeCertificateList() failed with error: %d", lastError);
  }
}
