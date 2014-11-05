Step-by-step HOW-TO create a new dll version:

Prerequisites:
See http://www.openoces.org/opensign/howtos/howto_cryptoapi_build.html
Please note that the CYGWIN packaged MINGW for creating a 32 bit dll version, currently does not support unicode! 
- instead use a standalone mingw install - http://sourceforge.net/projects/mingwbuilds - The Makefile is currently set for standalone MINGW
make is assumed to be GNU make.

Change dll version numbers (Major & Minor) in the following files:
opensign\applet\plugins\capi\src\main\java\org\openoces\opensign\certificate\plugin\capi\JniCapiInitializer.java
opensign\applet\plugins\capi\src\main\java\org\openoces\opensign\certificate\plugin\capi\LibraryHashUtil.java
opensign\applet\bootstrap\pom.xml
opensign\jni\microsoftcryptoapi\org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi.cpp
opensign\jni\microsoftcryptoapi\Makefile

Build opensign in opensign root dir:
#> mvn clean install

Create both 32-bit and 64-bit versions of the dll:
#> cd jni/microsoftcryptoapi
	
#> make dll
	
#> make dll 64=true

To test new dlls, copy them to your local windows home dir in .oces/opensign/lib

Update opensign\applet\plugins\capi\src\main\java\org\openoces\opensign\certificate\plugin\capi\JniCapiInitializer.java with new digests:
run java org.openoces.opensign.certificate.plugin.capi.LibraryHashUtil to get lines to copy into JniCapiInitializer.java

Do a new opensign build in opensign root dir:
#> mvn clean install

run the bootapplet and check CAPI certificates are loaded and useable for login using the correct dll version.