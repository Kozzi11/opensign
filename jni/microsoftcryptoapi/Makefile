# Note:
# Make sure CLASSPATH is set to the correct place ie "../../applet/plugins/capi/target/classes"
# Make sure MINGW_HOME points to the correct root dir of your MINGW installation
# Remember to update VERSION_MAJOR and/or VERSION_MINOR

# To create 64 bit dll - uncomment the following line or call: make dll 64=true
#64=true
VERSION_MAJOR=0
VERSION_MINOR=7

# Edit the following to reflect your own build system - MINGW can be found at http://sourceforge.net/projects/mingwbuilds
MINGW_HOME=/cygdrive/c/mingw
CLASSPATH="../../applet/plugins/capi/target/classes"

GCC=${MINGW_HOME}/bin/g++
CFLAGS_DEFAULT=-static -municode -D_JNI_IMPLEMENTATION -I'${JAVA_HOME}'/include -I'${JAVA_HOME}'/include/win32 -Iinclude

ifeq (${64},true)
# create 64 bit dll
	CFLAGS=-m64 ${CFLAGS_DEFAULT}
	X64=_x64
	LIB=${MINGW_HOME}/x86_64-w64-mingw32/lib
else
# create 32 bit dll
	CFLAGS=-m32 -O2 ${CFLAGS_DEFAULT}
	LIB=${MINGW_HOME}/x86_64-w64-mingw32/lib32
endif

WRAPPERCLASS=MicrosoftCryptoApi
WRAPPERCLASSDIR=../../applet/plugins/capi/target/classes/org/openoces/opensign/wrappers/microsoftcryptoapi
WRAPPERCLASSWITHPATH=${WRAPPERCLASSDIR}/${WRAPPERCLASS}
WRAPPERCLASSFILENAME=org_openoces_opensign_wrappers_microsoftcryptoapi_MicrosoftCryptoApi

${WRAPPERCLASSFILENAME}.h: ${WRAPPERCLASSWITHPATH}.class
	'${JAVA_HOME}'/bin/javah -classpath ${CLASSPATH} -jni org.openoces.opensign.wrappers.microsoftcryptoapi.${WRAPPERCLASS} 

utils.o: utils.h utils.cpp
	${GCC} -c ${CFLAGS} utils.cpp

conversion.o: conversion.h conversion.cpp
	${GCC} -c ${CFLAGS} conversion.cpp

crypto.o: crypto.h crypto.cpp
	${GCC} -c ${CFLAGS} crypto.cpp
	
dll: clean utils.o conversion.o crypto.o ${WRAPPERCLASSFILENAME}.cpp ${WRAPPERCLASSFILENAME}.h 
	${GCC} ${CFLAGS} -L${LIB} -Wl,--kill-at -shared -o ${WRAPPERCLASS}${X64}_${VERSION_MAJOR}_${VERSION_MINOR}.dll utils.o conversion.o crypto.o ${WRAPPERCLASSFILENAME}.cpp -lcrypt32
	
test: dll 
	'${JAVA_HOME}'/bin/java ${WRAPPERCLASS}

clean: 
	rm -f *.o *.gch
	
check: utils.o conversion.o crypto.o
	make --directory=tests check
