#include <Windows.h>
#include <wincrypt.h>
#include <jni.h>
#include <stdio.h>


#include "org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi.h"

#include "global.h"
#include "crypto.h"
#include "conversion.h"
#include "utils.h"

int lastError;

JNIEXPORT void JNICALL Java_org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi_hello(JNIEnv *env, jobject obj) 
{
    printf("Hello world!\n");
    return;
}

JNIEXPORT jobjectArray JNICALL Java_org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi_getCertificatesInSystemStore(JNIEnv *env, jobject obj, jstring storeName) {
  struct certificate_data *certificate_list_head;
  jobjectArray rs;

  lastError = OPENSIGN_ERROR_NONE;

  certificate_list_head = getCertificatesInMyStore();
  if ( lastError != OPENSIGN_ERROR_NONE ) {
    return NULL;
  }

  rs = certificateList2jobjectArray(env,certificate_list_head);
  if ( lastError != OPENSIGN_ERROR_NONE ) {    
    rs = NULL;
  }

  freeCertificateList(certificate_list_head);

  return rs;
}

JNIEXPORT jbyteArray JNICALL Java_org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi_signMessage
(JNIEnv *env, jobject obj, jbyteArray toBeSigned, jbyteArray certificate, jstring digestAlgorithm) {
 
  BYTE* pbToBeSigned;
  DWORD dwToBeSigned;
  BYTE* pbSignature;
  DWORD dwSignature;
  BYTE* pbCertificate;
  DWORD dwCertificate;
  jbyteArray jSignature;

  if ( jbyteArray2pbyte(env,toBeSigned,&pbToBeSigned,&dwToBeSigned) != 0 ) {
    return NULL;
  }

  if ( jbyteArray2pbyte(env,certificate,&pbCertificate,&dwCertificate) != 0 ) {
    return NULL;
  }

  int rc;
  const char * alg = env->GetStringUTFChars(digestAlgorithm, 0);

  if( strcmp("sha1", alg) == 0){
	  rc = sign(pbCertificate,dwCertificate,pbToBeSigned,dwToBeSigned,&pbSignature,&dwSignature, CALG_SHA1);
  }else if( strcmp("sha256", alg) == 0){
	  rc = sign(pbCertificate,dwCertificate,pbToBeSigned,dwToBeSigned,&pbSignature,&dwSignature, CALG_SHA_256);
  }else{
	  return NULL;
  }

  if ( rc != 0 ) {
    return NULL;
  }

  /* FIXME: free the allocated memory for the signature */
 
  if ( pbyte2jbyteArray(env,pbSignature,dwSignature,&jSignature) != 0 ) {
    return NULL;
  }

  return jSignature;
}

JNIEXPORT jbyteArray JNICALL Java_org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi_digest(JNIEnv *env, jobject, jbyteArray data, jstring digestAlgorithm) {

  BYTE* pbDigestValue;
  DWORD dwDigestValueLen;
  BYTE* pbData;
  DWORD dwDataLen;
  jbyteArray jDigestValue;


  if ( jbyteArray2pbyte(env,data,&pbData,&dwDataLen) != 0 ) {
    return NULL;
  }

  int rc;
  const char * alg = env->GetStringUTFChars(digestAlgorithm, 0);	

  if( strcmp("sha1", alg) == 0){
	  rc = digest(pbData,dwDataLen,&pbDigestValue,&dwDigestValueLen, CALG_SHA1);
  }else if( strcmp("sha256", alg) == 0){
	  rc = digest(pbData,dwDataLen,&pbDigestValue,&dwDigestValueLen, CALG_SHA_256);
  }else{
	  return NULL;
  }

  if ( rc != 0 ) {
    return NULL;
  }

  if ( pbyte2jbyteArray(env,pbDigestValue,dwDigestValueLen,&jDigestValue) != 0 ) {
    return NULL;
  }

  return jDigestValue;
}

JNIEXPORT jint JNICALL Java_org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi_getLastErrorCode
(JNIEnv *, jobject) {
  return lastError;
}

JNIEXPORT jstring JNICALL getCertificateDn(JNIEnv *env, jobject, bool subjectDn, jbyteArray certificate) {

  BYTE* pbCertificate;
  DWORD cbCertificate;
  WCHAR *dn;
  DWORD dnLen;
  jstring jDn;

  if ( jbyteArray2pbyte(env, certificate, &pbCertificate, &cbCertificate) != 0 ) {
    return NULL;
  }

  if ( getCertificateDn(pbCertificate,cbCertificate,subjectDn ? 0 : CERT_NAME_ISSUER_FLAG,&dn,&dnLen) != 0 ) {
    return NULL;
  }
  
  if ( wchar2jstring(env,dn,dnLen,&jDn) != 0 ) {
    return NULL;
  }

  return jDn; 
}

JNIEXPORT jstring JNICALL Java_org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi_getIssuerDn
(JNIEnv *env, jobject o, jbyteArray certificate) {
  return getCertificateDn(env,o,false,certificate);
}

JNIEXPORT jstring JNICALL Java_org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi_getSubjectDn
(JNIEnv *env, jobject o, jbyteArray certificate) {
  return getCertificateDn(env,o,true,certificate);
}

JNIEXPORT jint JNICALL Java_org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi_getCertificateVersion
(JNIEnv *env, jobject, jbyteArray certificate) {

  BYTE* pbCertificate;
  DWORD cbCertificate;
  DWORD dwVersion;
  jint jVersion;

  if ( jbyteArray2pbyte(env, certificate, &pbCertificate, &cbCertificate) != 0 ) {
    return -1;
  }

  if ( getCertificateVersion(pbCertificate,cbCertificate,&dwVersion) != 0 ) {
    return -1;
  }
  
  jVersion = -1;

  switch ( dwVersion ) {
  case CERT_V1 :
    jVersion = 1;
    break;
  case CERT_V2 :
    jVersion = 2;
    break;
  case CERT_V3 :
    jVersion = 3;
    break;
  }

  return jVersion;
}

JNIEXPORT jlong JNICALL getCertificateTimestamp(JNIEnv *env, jobject, bool notBefore, jbyteArray certificate) {

  BYTE* pbCertificate;
  DWORD cbCertificate;
  FILETIME ftTimestamp;
  jlong jTimestamp;

  if ( jbyteArray2pbyte(env, certificate, &pbCertificate, &cbCertificate) != 0 ) {
    return -1;
  }

  if ( getCertificateTimestamp(pbCertificate,cbCertificate,notBefore,&ftTimestamp) != 0 ) {
    return -1;
  }

  if ( filetime2jlong(env, ftTimestamp,&jTimestamp ) != 0 ) {
    return -1;
  }

  return jTimestamp;
}

JNIEXPORT jlong JNICALL Java_org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi_getNotAfter
(JNIEnv *env, jobject o, jbyteArray certificate) {
  return getCertificateTimestamp(env,o, false, certificate);
}

JNIEXPORT jlong JNICALL Java_org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi_getNotBefore
(JNIEnv *env, jobject o, jbyteArray certificate) {
  return getCertificateTimestamp(env,o, true, certificate);
}

JNIEXPORT jbyteArray JNICALL Java_org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi_getSerialNumber(JNIEnv *env, jobject, jbyteArray certificate) {

  BYTE* pbCertificate;
  DWORD cbCertificate;
  CRYPT_INTEGER_BLOB blSerialNumber;
  jbyteArray jSerialNumber;
  
  if ( jbyteArray2pbyte(env, certificate, &pbCertificate, &cbCertificate) != 0 ) {
    return NULL;
  }

  if ( getCertificateSerialNumber(pbCertificate, cbCertificate,&blSerialNumber) != 0 ) {
    return NULL;
  }

  if ( cryptintegerblob2jbyteArray(env,blSerialNumber,&jSerialNumber) != 0 ) {
    return NULL;

  }
  free(blSerialNumber.pbData);
  return jSerialNumber;
}

JNIEXPORT jint JNICALL Java_org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi_getMajorVersion
(JNIEnv *, jobject) {
  return 0;
}

JNIEXPORT jint JNICALL Java_org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi_getMinorVersion
(JNIEnv *, jobject) {
  return 9;
}

JNIEXPORT jint JNICALL Java_org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi_getKeyUsage

(JNIEnv *env, jobject, jbyteArray certificate) {

  BYTE* pbCertificate;
  DWORD cbCertificate;
  BYTE keyUsage[2];
  jint jKeyUsage;

  if ( jbyteArray2pbyte(env, certificate, &pbCertificate, &cbCertificate) != 0 ) {
    return -1;
  }

  if ( getCertificateKeyUsage(pbCertificate,cbCertificate,keyUsage) != 0 ) {
    return -1;
  }

  if ( keyUsage2jint(env,keyUsage,&jKeyUsage) ) {
    return -1;
  }

  return jKeyUsage;
}


