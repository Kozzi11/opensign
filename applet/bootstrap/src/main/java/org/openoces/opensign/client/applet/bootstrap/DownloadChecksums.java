package org.openoces.opensign.client.applet.bootstrap;

public class DownloadChecksums {
protected DownloadChecksums() {}
private static final byte[] opensignChecksum = {(byte) 0x90,(byte) 0x77,(byte) 0x54,(byte) 0x71,(byte) 0x51,(byte) 0x27,(byte) 0x5a,(byte) 0x7f,(byte) 0x8b,(byte) 0xac,(byte) 0x74,(byte) 0x0d,(byte) 0xf6,(byte) 0x82,(byte) 0x14,(byte) 0xe4,(byte) 0x96,(byte) 0x75,(byte) 0x25,(byte) 0x83,};
public static byte[] getopensignChecksum() { return copyArray(opensignChecksum); }
private static final byte[] capiChecksum = {(byte) 0x15,(byte) 0xa4,(byte) 0xfb,(byte) 0x69,(byte) 0xe1,(byte) 0xa4,(byte) 0xea,(byte) 0x01,(byte) 0x2f,(byte) 0x25,(byte) 0xf1,(byte) 0x17,(byte) 0x35,(byte) 0xd1,(byte) 0x78,(byte) 0x37,(byte) 0xea,(byte) 0xf2,(byte) 0xe2,(byte) 0xc2,};
public static byte[] getcapiChecksum() { return copyArray(capiChecksum); }
private static final byte[] cdcardChecksum = {(byte) 0x1d,(byte) 0xbd,(byte) 0xde,(byte) 0x72,(byte) 0x81,(byte) 0x0d,(byte) 0x71,(byte) 0x48,(byte) 0xc1,(byte) 0xe1,(byte) 0x99,(byte) 0xea,(byte) 0xed,(byte) 0xcd,(byte) 0xda,(byte) 0xd6,(byte) 0xdf,(byte) 0x98,(byte) 0x71,(byte) 0xe6,};
public static byte[] getcdcardChecksum() { return copyArray(cdcardChecksum); }
private static final byte[] pkcs12Checksum = {(byte) 0x5f,(byte) 0x84,(byte) 0xb1,(byte) 0x0a,(byte) 0xcc,(byte) 0x04,(byte) 0xde,(byte) 0x7c,(byte) 0x7d,(byte) 0x4e,(byte) 0xb4,(byte) 0xbe,(byte) 0xe6,(byte) 0xea,(byte) 0xa1,(byte) 0x37,(byte) 0xe6,(byte) 0x6c,(byte) 0xa4,(byte) 0xca,};
public static byte[] getpkcs12Checksum() { return copyArray(pkcs12Checksum); }
private static final byte[] ocesChecksum = {(byte) 0x40,(byte) 0x75,(byte) 0xce,(byte) 0x31,(byte) 0x6c,(byte) 0x9b,(byte) 0xfc,(byte) 0xf8,(byte) 0x10,(byte) 0xbb,(byte) 0xec,(byte) 0xeb,(byte) 0xb4,(byte) 0xd3,(byte) 0x42,(byte) 0x88,(byte) 0x18,(byte) 0xb4,(byte) 0x82,(byte) 0x33,};
public static byte[] getocesChecksum() { return copyArray(ocesChecksum); }
private static final byte[] jsseChecksum = {(byte) 0x72,(byte) 0x01,(byte) 0x88,(byte) 0xff,(byte) 0xe7,(byte) 0x8f,(byte) 0x2e,(byte) 0x3e,(byte) 0x09,(byte) 0x41,(byte) 0xcd,(byte) 0xec,(byte) 0x3d,(byte) 0x98,(byte) 0xce,(byte) 0xb7,(byte) 0x73,(byte) 0xdf,(byte) 0xfa,(byte) 0xfe,};
public static byte[] getjsseChecksum() { return copyArray(jsseChecksum); }
private static final byte[] attachmentChecksum = {(byte) 0x37,(byte) 0x35,(byte) 0x66,(byte) 0x97,(byte) 0x4d,(byte) 0xb4,(byte) 0x6c,(byte) 0x02,(byte) 0x4e,(byte) 0x2e,(byte) 0x8c,(byte) 0xfe,(byte) 0x90,(byte) 0x88,(byte) 0x3f,(byte) 0x5e,(byte) 0x3d,(byte) 0x6a,(byte) 0x3e,(byte) 0xd2,};
public static byte[] getattachmentChecksum() { return copyArray(attachmentChecksum); }
private static final byte[] cryptokiChecksum = {(byte) 0xd9,(byte) 0x5d,(byte) 0x41,(byte) 0xc7,(byte) 0xbd,(byte) 0x95,(byte) 0xb8,(byte) 0x01,(byte) 0xb3,(byte) 0x70,(byte) 0x8a,(byte) 0xef,(byte) 0xfc,(byte) 0x4e,(byte) 0xf7,(byte) 0x6f,(byte) 0x71,(byte) 0x34,(byte) 0xdd,(byte) 0xba,};
public static byte[] getcryptokiChecksum() { return copyArray(cryptokiChecksum); }
private static final byte[] pdfChecksum = {(byte) 0xce,(byte) 0x42,(byte) 0x91,(byte) 0xc2,(byte) 0x7a,(byte) 0x20,(byte) 0x3c,(byte) 0x7d,(byte) 0x61,(byte) 0x99,(byte) 0x8d,(byte) 0x11,(byte) 0xf7,(byte) 0xc9,(byte) 0x4d,(byte) 0xd2,(byte) 0x03,(byte) 0x3b,(byte) 0x95,(byte) 0x18,};
public static byte[] getpdfChecksum() { return copyArray(pdfChecksum); }
public static int getopensignDataSize() { return 427175; }
public static int getcapiDataSize() { return 27767; }
public static int getcdcardDataSize() { return 80237; }
public static int getpkcs12DataSize() { return 11343; }
public static int getocesDataSize() { return 7732; }
public static int getjsseDataSize() { return 27098; }
public static int getattachmentDataSize() { return 63382; }
public static int getcryptokiDataSize() { return 676628; }
public static int getpdfDataSize() { return 1978575; }
private static byte [] copyArray(byte [] src){
byte [] dest = new byte[src.length];
System.arraycopy(src, 0, dest, 0, src.length);
return dest;
}
}