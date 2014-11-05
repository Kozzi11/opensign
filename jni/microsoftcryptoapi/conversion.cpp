#ifndef _CONVERSION_CPP
#define _CONVERSION_CPP

#ifdef __cplusplus
extern "C" {
#endif

#include "conversion.h"

  int cryptintegerblob2jbyteArray(JNIEnv* env, CRYPT_INTEGER_BLOB src, jbyteArray* dst) {
    return pbyte2jbyteArray(env,src.pbData,src.cbData,dst);
  }

  int filetime2jlong(JNIEnv* env, FILETIME src, jlong* dst) {
    ULONGLONG millis = (((ULONGLONG) src.dwHighDateTime) << 32) + src.dwLowDateTime;
    millis = millis / 10000;
    // difference in milliseconds between
    // January 1, 1601 00:00:00 UTC (Windows FILETIME)
    // January 1, 1970 00:00:00 UTC (Java long)
    // = 11644473600000
    millis -= 11644473600000LL;
    *dst = millis;
    return 0;
  }

  int pchar2jstring(JNIEnv* env, char *src, jstring *dst) {
    *dst = env->NewStringUTF(src);
    if (*dst == NULL){
        return 1;
    }
    return 0;
  }

  int wchar2jstring(JNIEnv* env, WCHAR *src, DWORD srcLen, jstring* dst) {
    *dst = env->NewString((jchar*)src,srcLen);
    if (*dst == NULL){
        return 1;
    }
    return 0;
  }

  int pbyte2jbyteArray(JNIEnv* env, BYTE* src, DWORD srcLen, jbyteArray *dst) {
    *dst = env->NewByteArray(srcLen);
    if (*dst == NULL){
        return 1;
    }
    env->SetByteArrayRegion(*dst,0,srcLen,(jbyte*)src);
    return 0;
  }

  int jbyteArray2pbyte(JNIEnv* env, jbyteArray src, BYTE** dst, DWORD* dstLen) {
    *dstLen = env->GetArrayLength(src);
    *dst = (BYTE*)(env->GetByteArrayElements(src,NULL));
    return 0;
  }

  jobjectArray certificateList2jobjectArray(JNIEnv *env, struct certificate_data* list) {

    int sz;
    struct certificate_data *e;
    jbyteArray tmpl;
	jobjectArray chain_tmpl;
    jobjectArray rs;
	int j = 0;

    sz = 0;

    /* count the number of certificates in the list */
    e = list;
    while ( e != NULL ) {
      sz++;
      e = e->prev;
    }

    /* create the byte array template for the certificates*/ 
    tmpl = (jbyteArray)(env->NewByteArray(1));
    if (tmpl == NULL){
        return NULL;
    }
	/* crate the cert chain template*/
    chain_tmpl = env->NewObjectArray(1,env->GetObjectClass(tmpl),0);
    if (chain_tmpl == NULL){
        return NULL;
    }

	/* crate the object array*/
    rs = env->NewObjectArray(sz,env->GetObjectClass(chain_tmpl),0);
    if (chain_tmpl == NULL){
        return NULL;
    }

	e = list;
	while ( e != NULL ) {
	
		jobjectArray chain = env->NewObjectArray(e->count,env->GetObjectClass(tmpl), 0 );


	  for(int i = 0; i< e->count; ++i){
		  byte_array_t & cert = e->pbCertEncoded[i];
		  jbyteArray c;

		  c = env->NewByteArray(cert.size);
		  if (c == NULL){
			/* FIXME free allready allocated memory */
			return NULL;
		  }
		  env->SetByteArrayRegion(c,0,cert.size,(jbyte*)cert.data);
		  env->SetObjectArrayElement(chain,i,c);
		 
	  }

	  env->SetObjectArrayElement(rs,j++,chain);

      e = e->prev;
	}


   return rs;
  }

  int keyUsage2jint(JNIEnv *env, byte* keyUsage, jint* jKeyUsage) {
      int i = 0;

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

      *jKeyUsage = i;
      return 0;
  }

#ifdef __cplusplus
}
#endif
#endif

