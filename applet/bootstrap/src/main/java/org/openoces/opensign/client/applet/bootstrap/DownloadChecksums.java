package org.openoces.opensign.client.applet.bootstrap;

public class DownloadChecksums {
protected DownloadChecksums() {}
private static final byte[] opensignChecksum = {(byte) 0x64,(byte) 0x8e,(byte) 0xcc,(byte) 0xab,(byte) 0x36,(byte) 0x59,(byte) 0x82,(byte) 0xd3,(byte) 0x18,(byte) 0xe1,(byte) 0xc6,(byte) 0x5e,(byte) 0xb5,(byte) 0x0f,(byte) 0xec,(byte) 0x72,(byte) 0x0c,(byte) 0xe4,(byte) 0x1d,(byte) 0x28,};
public static byte[] getopensignChecksum() { return copyArray(opensignChecksum); }
private static final byte[] capiChecksum = {(byte) 0x36,(byte) 0x1a,(byte) 0x60,(byte) 0x32,(byte) 0xc7,(byte) 0x59,(byte) 0x0d,(byte) 0x47,(byte) 0xa6,(byte) 0x9b,(byte) 0xea,(byte) 0x90,(byte) 0x5c,(byte) 0x17,(byte) 0xf9,(byte) 0x93,(byte) 0xb9,(byte) 0x29,(byte) 0x3a,(byte) 0x36,};
public static byte[] getcapiChecksum() { return copyArray(capiChecksum); }
private static final byte[] cdcardChecksum = {(byte) 0x3b,(byte) 0x0e,(byte) 0xb4,(byte) 0xe6,(byte) 0xce,(byte) 0xfb,(byte) 0x12,(byte) 0x18,(byte) 0x08,(byte) 0x51,(byte) 0xb4,(byte) 0xe5,(byte) 0x5f,(byte) 0x10,(byte) 0x6e,(byte) 0x2a,(byte) 0xbe,(byte) 0xe3,(byte) 0x11,(byte) 0x02,};
public static byte[] getcdcardChecksum() { return copyArray(cdcardChecksum); }
private static final byte[] pkcs12Checksum = {(byte) 0x55,(byte) 0x70,(byte) 0x66,(byte) 0x7a,(byte) 0xc6,(byte) 0x14,(byte) 0xed,(byte) 0x9e,(byte) 0x13,(byte) 0x84,(byte) 0xfd,(byte) 0x15,(byte) 0x4a,(byte) 0xaf,(byte) 0x7c,(byte) 0xc1,(byte) 0x58,(byte) 0xe4,(byte) 0xa7,(byte) 0x1e,};
public static byte[] getpkcs12Checksum() { return copyArray(pkcs12Checksum); }
private static final byte[] ocesChecksum = {(byte) 0x6a,(byte) 0x24,(byte) 0x03,(byte) 0x76,(byte) 0x71,(byte) 0xa7,(byte) 0x67,(byte) 0x5d,(byte) 0x40,(byte) 0xc4,(byte) 0x77,(byte) 0xe3,(byte) 0x04,(byte) 0x5f,(byte) 0x35,(byte) 0x4c,(byte) 0x9a,(byte) 0x00,(byte) 0x16,(byte) 0xdb,};
public static byte[] getocesChecksum() { return copyArray(ocesChecksum); }
private static final byte[] jsseChecksum = {(byte) 0x32,(byte) 0xef,(byte) 0x79,(byte) 0x13,(byte) 0x2d,(byte) 0xa6,(byte) 0x91,(byte) 0xd4,(byte) 0xea,(byte) 0x0e,(byte) 0x18,(byte) 0xd7,(byte) 0x14,(byte) 0x69,(byte) 0xd5,(byte) 0x66,(byte) 0xa7,(byte) 0x1c,(byte) 0x7e,(byte) 0xfc,};
public static byte[] getjsseChecksum() { return copyArray(jsseChecksum); }
private static final byte[] attachmentChecksum = {(byte) 0x39,(byte) 0x40,(byte) 0x4c,(byte) 0x5e,(byte) 0x5d,(byte) 0x19,(byte) 0x03,(byte) 0x6d,(byte) 0x0f,(byte) 0x50,(byte) 0x3d,(byte) 0xad,(byte) 0x8a,(byte) 0xb3,(byte) 0xf8,(byte) 0x00,(byte) 0xd7,(byte) 0x83,(byte) 0x41,(byte) 0x36,};
public static byte[] getattachmentChecksum() { return copyArray(attachmentChecksum); }
private static final byte[] cryptokiChecksum = {(byte) 0x11,(byte) 0x0a,(byte) 0xcc,(byte) 0x25,(byte) 0x52,(byte) 0xda,(byte) 0x17,(byte) 0x98,(byte) 0xda,(byte) 0x13,(byte) 0x62,(byte) 0x00,(byte) 0x6f,(byte) 0xb8,(byte) 0x18,(byte) 0x4b,(byte) 0x42,(byte) 0x64,(byte) 0x77,(byte) 0xa7,};
public static byte[] getcryptokiChecksum() { return copyArray(cryptokiChecksum); }
private static final byte[] pdfChecksum = {(byte) 0xce,(byte) 0x42,(byte) 0x91,(byte) 0xc2,(byte) 0x7a,(byte) 0x20,(byte) 0x3c,(byte) 0x7d,(byte) 0x61,(byte) 0x99,(byte) 0x8d,(byte) 0x11,(byte) 0xf7,(byte) 0xc9,(byte) 0x4d,(byte) 0xd2,(byte) 0x03,(byte) 0x3b,(byte) 0x95,(byte) 0x18,};
public static byte[] getpdfChecksum() { return copyArray(pdfChecksum); }
public static int getopensignDataSize() { return 427777; }
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