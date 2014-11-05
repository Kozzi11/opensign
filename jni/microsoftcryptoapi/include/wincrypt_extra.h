#ifndef _WINCRYPT_EXTRA_H
#define _WINCRYPT_EXTRA_H
#if __GNUC__ >=3
#pragma GCC system_header
#endif

#ifdef __cplusplus
extern "C" {
#endif


#define CERT_SYSTEM_STORE_LOCATION_SHIFT 16

#define CERT_SYSTEM_STORE_CURRENT_USER_ID 1
#define CERT_SYSTEM_STORE_CURRENT_USER (CERT_SYSTEM_STORE_CURRENT_USER_ID << CERT_SYSTEM_STORE_LOCATION_SHIFT)

#define CERT_NAME_SIMPLE_DISPLAY_TYPE 4

#define CERT_KEY_PROV_INFO_PROP_ID 2

#define CERT_STORE_OPEN_EXISTING_FLAG 0x00004000
#define CERT_STORE_READONLY_FLAG 0x00008000

PCCERT_CONTEXT WINAPI CertEnumCertificatesInStore(HCERTSTORE,PCCERT_CONTEXT);

DWORD WINAPI CertGetNameStringA(PCCERT_CONTEXT,DWORD,DWORD,void*,LPSTR,DWORD);
DWORD WINAPI CertGetNameStringW(PCCERT_CONTEXT,DWORD,DWORD,void*,LPWSTR,DWORD);

#ifdef UNICODE
#define CertGetNameString CertGetNameStringW
#else
#define CertGetNameString CertGetNameStringA
#endif

typedef struct _CRYPT_SIGN_MESSAGE_PARA {
  DWORD cbSize;
  DWORD dwMsgEncodingType;
  PCCERT_CONTEXT pSigningCert;
  CRYPT_ALGORITHM_IDENTIFIER HashAlgorithm;
  void *pvHashAuxInfo;
  DWORD cMsgCert;
  PCCERT_CONTEXT *rgpMsgCert;
  DWORD cMsgCrl;
  PCCRL_CONTEXT *rgpMsgCrl;
  DWORD cAuthAttr;
  PCRYPT_ATTRIBUTE rgAuthAttr;
  DWORD cUnauthAttr;
  PCRYPT_ATTRIBUTE rgUnauthAttr;
  DWORD dwFlags;
  DWORD dwInnerContentType;

  // #ifdef CRYPT_SIGN_MESSAGE_PARA_HAS_CMS_FIELDS
  CRYPT_ALGORITHM_IDENTIFIER HashEncryptionAlgorithm;
  void *pvHashEncryptionAuxInfo;
  // #endif
} CRYPT_SIGN_MESSAGE_PARA, *PCRYPT_SIGN_MESSAGE_PARA;

#define szOID_RSA_SHA1RSA "1.2.840.113549.1.1.5"

BOOL WINAPI CertGetCertificateContextProperty(PCCERT_CONTEXT pCertContext,DWORD dwPropId,void *pvData,DWORD *pcbData);

#define szOID_RSA_MD5           "1.2.840.113549.2.5"

#define CERT_CLOSE_STORE_CHECK_FLAG         0x00000002

#define CERT_NAME_RDN_TYPE              2

#define CERT_NAME_ISSUER_FLAG           0x1

#define ALG_SID_SHA1                    4
#define ALG_SID_SHA_256                 12

#define CALG_SHA1               (ALG_CLASS_HASH | ALG_TYPE_ANY | ALG_SID_SHA1)
#define CALG_SHA_256            (ALG_CLASS_HASH | ALG_TYPE_ANY | ALG_SID_SHA_256)

BOOL WINAPI CertGetIntendedKeyUsage(DWORD dwCertEncodingType,PCERT_INFO pCertInfo,BYTE *pbKeyUsage,DWORD cbKeyUsage);

  /* 1st byte of the keyusage */
#define CERT_DIGITAL_SIGNATURE_KEY_USAGE     0x80
#define CERT_NON_REPUDIATION_KEY_USAGE       0x40
#define CERT_KEY_ENCIPHERMENT_KEY_USAGE      0x20
#define CERT_DATA_ENCIPHERMENT_KEY_USAGE     0x10
#define CERT_KEY_AGREEMENT_KEY_USAGE         0x08
#define CERT_KEY_CERT_SIGN_KEY_USAGE         0x04
#define CERT_OFFLINE_CRL_SIGN_KEY_USAGE      0x02
#define CERT_CRL_SIGN_KEY_USAGE              0x02
#define CERT_ENCIPHER_ONLY_KEY_USAGE         0x01
  /* 2nd byte of the key usage */
#define CERT_DECIPHER_ONLY_KEY_USAGE         0x80

#ifdef __cplusplus
}
#endif
#endif
