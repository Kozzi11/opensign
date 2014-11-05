#ifndef _CRYPTO_H
#define _CRYPTO_H
#if __GNUC__ >=3
#pragma GCC system_header
#endif

#include <Windows.h>
#include <wincrypt.h>

#ifdef __cplusplus
extern "C" {
#endif

  void freeCertificateList(struct certificate_data* head);

  struct certificate_data* getCertificatesInMyStore();

  int digest(BYTE *to_be_hashed, 
		  DWORD to_be_hashed_length, 
		  BYTE **digest_value, 
		  DWORD *digest_value_length, 
		  ALG_ID alg_id);

  int sign(BYTE *certificate, 
	   DWORD certificate_length, 
	   BYTE *to_be_signed, 
	   DWORD to_be_signed_length, 
	   BYTE **signature, 
	   DWORD *signature_length, 
	   ALG_ID alg_id);

  int getCertificateDn(BYTE *certificate,
		       DWORD certificate_length,
		       DWORD issuerFlag,     
		       WCHAR **issuerDn,
		       DWORD *dnLen);

  int getCertificateVersion(BYTE *certificate,
			    DWORD certificate_length,
			    DWORD *version);

  int getCertificateTimestamp(BYTE *certificate,
			      DWORD certificate_length,
			      BOOL notBefore,
			      FILETIME *timestamp);

  int getCertificateSerialNumber(BYTE* certificate,
				 DWORD certificate_length,
				 CRYPT_INTEGER_BLOB *blSerialNumber);

  int getCertificateKeyUsage(BYTE *certificate,
			     DWORD certificate_length,
			     BYTE* keyusage);


#ifdef __cplusplus
}
#endif
#endif
