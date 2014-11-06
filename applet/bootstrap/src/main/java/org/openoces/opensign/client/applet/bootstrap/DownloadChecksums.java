package org.openoces.opensign.client.applet.bootstrap;

public class DownloadChecksums {
protected DownloadChecksums() {}
private static final byte[] opensignChecksum = {(byte) 0x42,(byte) 0xe8,(byte) 0xd0,(byte) 0x9f,(byte) 0xc8,(byte) 0x69,(byte) 0x8b,(byte) 0xbd,(byte) 0xf4,(byte) 0xf4,(byte) 0x4a,(byte) 0x1d,(byte) 0x17,(byte) 0x93,(byte) 0x30,(byte) 0x02,(byte) 0x19,(byte) 0xe1,(byte) 0x2e,(byte) 0xab,};
public static byte[] getopensignChecksum() { return copyArray(opensignChecksum); }
private static final byte[] capiChecksum = {(byte) 0xe9,(byte) 0xa1,(byte) 0x20,(byte) 0x6c,(byte) 0xc9,(byte) 0x18,(byte) 0xd9,(byte) 0x3c,(byte) 0x0d,(byte) 0x04,(byte) 0x2b,(byte) 0xc7,(byte) 0x87,(byte) 0xed,(byte) 0xf6,(byte) 0x1c,(byte) 0xb4,(byte) 0x30,(byte) 0x6a,(byte) 0x92,};
public static byte[] getcapiChecksum() { return copyArray(capiChecksum); }
private static final byte[] cdcardChecksum = {(byte) 0xa1,(byte) 0xde,(byte) 0xf6,(byte) 0x3f,(byte) 0xe8,(byte) 0xe9,(byte) 0xc4,(byte) 0x07,(byte) 0xf2,(byte) 0x72,(byte) 0x78,(byte) 0x34,(byte) 0x90,(byte) 0x4a,(byte) 0x05,(byte) 0x23,(byte) 0xb6,(byte) 0xd8,(byte) 0xa9,(byte) 0x0f,};
public static byte[] getcdcardChecksum() { return copyArray(cdcardChecksum); }
private static final byte[] pkcs12Checksum = {(byte) 0x8b,(byte) 0x29,(byte) 0x65,(byte) 0xdd,(byte) 0x49,(byte) 0xab,(byte) 0xfc,(byte) 0xfc,(byte) 0x35,(byte) 0x77,(byte) 0x9b,(byte) 0xa1,(byte) 0x5c,(byte) 0xdd,(byte) 0x47,(byte) 0x5e,(byte) 0x3f,(byte) 0xe5,(byte) 0xc8,(byte) 0x54,};
public static byte[] getpkcs12Checksum() { return copyArray(pkcs12Checksum); }
private static final byte[] ocesChecksum = {(byte) 0xb9,(byte) 0xa3,(byte) 0x4c,(byte) 0x7b,(byte) 0xf6,(byte) 0x38,(byte) 0x40,(byte) 0xd4,(byte) 0x3c,(byte) 0x91,(byte) 0xee,(byte) 0x5d,(byte) 0x1c,(byte) 0xd0,(byte) 0xb4,(byte) 0x46,(byte) 0x16,(byte) 0x78,(byte) 0xd2,(byte) 0xfe,};
public static byte[] getocesChecksum() { return copyArray(ocesChecksum); }
private static final byte[] jsseChecksum = {(byte) 0x0b,(byte) 0x81,(byte) 0x3e,(byte) 0x3e,(byte) 0x16,(byte) 0xa4,(byte) 0x00,(byte) 0xab,(byte) 0xe4,(byte) 0x38,(byte) 0xa9,(byte) 0x78,(byte) 0x13,(byte) 0x2f,(byte) 0xca,(byte) 0x30,(byte) 0x81,(byte) 0xaf,(byte) 0x8a,(byte) 0x17,};
public static byte[] getjsseChecksum() { return copyArray(jsseChecksum); }
private static final byte[] attachmentChecksum = {(byte) 0x36,(byte) 0xe1,(byte) 0xb2,(byte) 0xbd,(byte) 0xd3,(byte) 0x28,(byte) 0x79,(byte) 0x46,(byte) 0x26,(byte) 0x13,(byte) 0xbe,(byte) 0x40,(byte) 0xfc,(byte) 0x5b,(byte) 0x16,(byte) 0x26,(byte) 0xe1,(byte) 0x62,(byte) 0xf4,(byte) 0x20,};
public static byte[] getattachmentChecksum() { return copyArray(attachmentChecksum); }
private static final byte[] cryptokiChecksum = {(byte) 0xea,(byte) 0x3a,(byte) 0x16,(byte) 0xe8,(byte) 0x1a,(byte) 0x5e,(byte) 0x76,(byte) 0x38,(byte) 0x5c,(byte) 0x70,(byte) 0x06,(byte) 0xa9,(byte) 0x28,(byte) 0x76,(byte) 0x32,(byte) 0xa6,(byte) 0xc5,(byte) 0x8e,(byte) 0x79,(byte) 0x41,};
public static byte[] getcryptokiChecksum() { return copyArray(cryptokiChecksum); }
private static final byte[] pdfChecksum = {(byte) 0xce,(byte) 0x42,(byte) 0x91,(byte) 0xc2,(byte) 0x7a,(byte) 0x20,(byte) 0x3c,(byte) 0x7d,(byte) 0x61,(byte) 0x99,(byte) 0x8d,(byte) 0x11,(byte) 0xf7,(byte) 0xc9,(byte) 0x4d,(byte) 0xd2,(byte) 0x03,(byte) 0x3b,(byte) 0x95,(byte) 0x18,};
public static byte[] getpdfChecksum() { return copyArray(pdfChecksum); }
public static int getopensignDataSize() { return 426697; }
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