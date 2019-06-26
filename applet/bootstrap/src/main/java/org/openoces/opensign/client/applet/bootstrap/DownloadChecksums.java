package org.openoces.opensign.client.applet.bootstrap;

public class DownloadChecksums {
protected DownloadChecksums() {}
private static final byte[] opensignChecksum = {(byte) 0xde,(byte) 0x29,(byte) 0x1e,(byte) 0xf0,(byte) 0x50,(byte) 0x21,(byte) 0x00,(byte) 0x90,(byte) 0x6f,(byte) 0xe1,(byte) 0xd5,(byte) 0x3c,(byte) 0x60,(byte) 0x80,(byte) 0x41,(byte) 0x7c,(byte) 0xfc,(byte) 0xfe,(byte) 0xb7,(byte) 0x0e,};
public static byte[] getopensignChecksum() { return copyArray(opensignChecksum); }
private static final byte[] capiChecksum = {(byte) 0x8c,(byte) 0x31,(byte) 0xc3,(byte) 0xec,(byte) 0xc3,(byte) 0x07,(byte) 0xa8,(byte) 0x72,(byte) 0xc3,(byte) 0x69,(byte) 0x7d,(byte) 0x12,(byte) 0x87,(byte) 0x82,(byte) 0x8f,(byte) 0xf8,(byte) 0xbb,(byte) 0xd6,(byte) 0x50,(byte) 0xc3,};
public static byte[] getcapiChecksum() { return copyArray(capiChecksum); }
private static final byte[] cdcardChecksum = {(byte) 0xfa,(byte) 0xf5,(byte) 0x51,(byte) 0x97,(byte) 0x0e,(byte) 0x7f,(byte) 0x72,(byte) 0xe7,(byte) 0xe0,(byte) 0x76,(byte) 0x20,(byte) 0x29,(byte) 0x6e,(byte) 0x09,(byte) 0xcc,(byte) 0x3d,(byte) 0x7a,(byte) 0x4d,(byte) 0x15,(byte) 0x77,};
public static byte[] getcdcardChecksum() { return copyArray(cdcardChecksum); }
private static final byte[] pkcs12Checksum = {(byte) 0x61,(byte) 0xa0,(byte) 0x74,(byte) 0x11,(byte) 0x4c,(byte) 0x78,(byte) 0xa2,(byte) 0x7b,(byte) 0xbb,(byte) 0x8c,(byte) 0xdc,(byte) 0x5f,(byte) 0x01,(byte) 0x40,(byte) 0x33,(byte) 0xe8,(byte) 0x19,(byte) 0x92,(byte) 0x30,(byte) 0x27,};
public static byte[] getpkcs12Checksum() { return copyArray(pkcs12Checksum); }
private static final byte[] ocesChecksum = {(byte) 0x37,(byte) 0xdc,(byte) 0x4b,(byte) 0x07,(byte) 0x37,(byte) 0x1c,(byte) 0x1e,(byte) 0x09,(byte) 0xcd,(byte) 0x90,(byte) 0x76,(byte) 0x45,(byte) 0xb6,(byte) 0x26,(byte) 0x90,(byte) 0x50,(byte) 0xa9,(byte) 0x58,(byte) 0x48,(byte) 0x85,};
public static byte[] getocesChecksum() { return copyArray(ocesChecksum); }
private static final byte[] jsseChecksum = {(byte) 0x04,(byte) 0x6e,(byte) 0xef,(byte) 0xef,(byte) 0x16,(byte) 0x69,(byte) 0x23,(byte) 0x2f,(byte) 0xd1,(byte) 0x66,(byte) 0xf9,(byte) 0x4a,(byte) 0xdd,(byte) 0xcc,(byte) 0x36,(byte) 0x64,(byte) 0x21,(byte) 0x28,(byte) 0x07,(byte) 0x46,};
public static byte[] getjsseChecksum() { return copyArray(jsseChecksum); }
private static final byte[] attachmentChecksum = {(byte) 0x42,(byte) 0x74,(byte) 0xa0,(byte) 0x6c,(byte) 0xc4,(byte) 0x0f,(byte) 0xed,(byte) 0xe3,(byte) 0x3f,(byte) 0xa3,(byte) 0x13,(byte) 0x0a,(byte) 0x26,(byte) 0x19,(byte) 0x7f,(byte) 0xb9,(byte) 0xfc,(byte) 0xb4,(byte) 0x8e,(byte) 0x0b,};
public static byte[] getattachmentChecksum() { return copyArray(attachmentChecksum); }
private static final byte[] cryptokiChecksum = {(byte) 0x55,(byte) 0x97,(byte) 0x86,(byte) 0x77,(byte) 0x9f,(byte) 0xaa,(byte) 0x5b,(byte) 0xf5,(byte) 0xa2,(byte) 0x4b,(byte) 0xef,(byte) 0xad,(byte) 0x89,(byte) 0x81,(byte) 0x28,(byte) 0x9a,(byte) 0xd8,(byte) 0x13,(byte) 0x5c,(byte) 0xdf,};
public static byte[] getcryptokiChecksum() { return copyArray(cryptokiChecksum); }
private static final byte[] pdfChecksum = {(byte) 0xce,(byte) 0x42,(byte) 0x91,(byte) 0xc2,(byte) 0x7a,(byte) 0x20,(byte) 0x3c,(byte) 0x7d,(byte) 0x61,(byte) 0x99,(byte) 0x8d,(byte) 0x11,(byte) 0xf7,(byte) 0xc9,(byte) 0x4d,(byte) 0xd2,(byte) 0x03,(byte) 0x3b,(byte) 0x95,(byte) 0x18,};
public static byte[] getpdfChecksum() { return copyArray(pdfChecksum); }
public static int getopensignDataSize() { return 428888; }
public static int getcapiDataSize() { return 27757; }
public static int getcdcardDataSize() { return 80247; }
public static int getpkcs12DataSize() { return 11344; }
public static int getocesDataSize() { return 7732; }
public static int getjsseDataSize() { return 27073; }
public static int getattachmentDataSize() { return 63165; }
public static int getcryptokiDataSize() { return 676839; }
public static int getpdfDataSize() { return 1978575; }
private static byte [] copyArray(byte [] src){
byte [] dest = new byte[src.length];
System.arraycopy(src, 0, dest, 0, src.length);
return dest;
}
}