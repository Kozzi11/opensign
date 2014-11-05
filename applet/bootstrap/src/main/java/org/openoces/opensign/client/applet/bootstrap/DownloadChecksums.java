package org.openoces.opensign.client.applet.bootstrap;

public class DownloadChecksums {
protected DownloadChecksums() {}
private static final byte[] opensignChecksum = {(byte) 0x1e,(byte) 0xd5,(byte) 0x14,(byte) 0x55,(byte) 0x6e,(byte) 0x1f,(byte) 0x88,(byte) 0xcb,(byte) 0xba,(byte) 0x43,(byte) 0xd8,(byte) 0xa1,(byte) 0xa9,(byte) 0xc0,(byte) 0xc8,(byte) 0x62,(byte) 0x52,(byte) 0xa4,(byte) 0x1f,(byte) 0x81,};
public static byte[] getopensignChecksum() { return copyArray(opensignChecksum); }
private static final byte[] capiChecksum = {(byte) 0x41,(byte) 0xe8,(byte) 0x80,(byte) 0x42,(byte) 0x8f,(byte) 0x5f,(byte) 0x90,(byte) 0x6d,(byte) 0xef,(byte) 0xc1,(byte) 0x1a,(byte) 0xfd,(byte) 0xb2,(byte) 0xef,(byte) 0xfb,(byte) 0x33,(byte) 0xbf,(byte) 0x29,(byte) 0xcc,(byte) 0xf6,};
public static byte[] getcapiChecksum() { return copyArray(capiChecksum); }
private static final byte[] cdcardChecksum = {(byte) 0xb4,(byte) 0xd5,(byte) 0x96,(byte) 0xe1,(byte) 0xe7,(byte) 0x4c,(byte) 0xef,(byte) 0x9f,(byte) 0x16,(byte) 0x9a,(byte) 0x10,(byte) 0xbb,(byte) 0xad,(byte) 0x52,(byte) 0xf4,(byte) 0x1f,(byte) 0x62,(byte) 0xd2,(byte) 0xb2,(byte) 0x58,};
public static byte[] getcdcardChecksum() { return copyArray(cdcardChecksum); }
private static final byte[] pkcs12Checksum = {(byte) 0x07,(byte) 0xb1,(byte) 0xda,(byte) 0x11,(byte) 0xb7,(byte) 0x19,(byte) 0xb3,(byte) 0xad,(byte) 0xc3,(byte) 0xe1,(byte) 0x57,(byte) 0x15,(byte) 0x20,(byte) 0xec,(byte) 0x85,(byte) 0x58,(byte) 0xac,(byte) 0xf7,(byte) 0x39,(byte) 0x7d,};
public static byte[] getpkcs12Checksum() { return copyArray(pkcs12Checksum); }
private static final byte[] ocesChecksum = {(byte) 0x26,(byte) 0x70,(byte) 0xa8,(byte) 0xf2,(byte) 0xd4,(byte) 0x71,(byte) 0xcb,(byte) 0x08,(byte) 0x36,(byte) 0x27,(byte) 0xa9,(byte) 0x0c,(byte) 0xf8,(byte) 0x20,(byte) 0x5c,(byte) 0xef,(byte) 0xc2,(byte) 0x91,(byte) 0x94,(byte) 0x46,};
public static byte[] getocesChecksum() { return copyArray(ocesChecksum); }
private static final byte[] jsseChecksum = {(byte) 0x49,(byte) 0xe7,(byte) 0xce,(byte) 0xd9,(byte) 0x54,(byte) 0x67,(byte) 0xcf,(byte) 0x49,(byte) 0x5a,(byte) 0x53,(byte) 0x2e,(byte) 0x48,(byte) 0x23,(byte) 0x1c,(byte) 0x1e,(byte) 0xeb,(byte) 0x3e,(byte) 0x7b,(byte) 0x74,(byte) 0x13,};
public static byte[] getjsseChecksum() { return copyArray(jsseChecksum); }
private static final byte[] attachmentChecksum = {(byte) 0x33,(byte) 0x4b,(byte) 0x30,(byte) 0x91,(byte) 0x69,(byte) 0x86,(byte) 0x8c,(byte) 0x87,(byte) 0x31,(byte) 0x1c,(byte) 0xc0,(byte) 0xd1,(byte) 0x0d,(byte) 0x1b,(byte) 0xf0,(byte) 0x39,(byte) 0xb1,(byte) 0x87,(byte) 0x2a,(byte) 0xe6,};
public static byte[] getattachmentChecksum() { return copyArray(attachmentChecksum); }
private static final byte[] cryptokiChecksum = {(byte) 0x69,(byte) 0xfd,(byte) 0x67,(byte) 0x53,(byte) 0x50,(byte) 0xea,(byte) 0x5d,(byte) 0xe1,(byte) 0x48,(byte) 0x7c,(byte) 0x50,(byte) 0x66,(byte) 0xaf,(byte) 0x18,(byte) 0x88,(byte) 0x93,(byte) 0x98,(byte) 0xf7,(byte) 0xae,(byte) 0xbd,};
public static byte[] getcryptokiChecksum() { return copyArray(cryptokiChecksum); }
private static final byte[] pdfChecksum = {(byte) 0xce,(byte) 0x42,(byte) 0x91,(byte) 0xc2,(byte) 0x7a,(byte) 0x20,(byte) 0x3c,(byte) 0x7d,(byte) 0x61,(byte) 0x99,(byte) 0x8d,(byte) 0x11,(byte) 0xf7,(byte) 0xc9,(byte) 0x4d,(byte) 0xd2,(byte) 0x03,(byte) 0x3b,(byte) 0x95,(byte) 0x18,};
public static byte[] getpdfChecksum() { return copyArray(pdfChecksum); }
public static int getopensignDataSize() { return 426545; }
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