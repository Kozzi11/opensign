digest.o: digest.cpp
	g++ -c ${CFLAGS} digest.cpp

digest: digest.o ../utils.o ../conversion.o ../crypto.o
	g++ -mno-cygwin ../utils.o ../conversion.o ../crypto.o digest.o -o digest -L../lib -lcrypt32

sign.o: sign.cpp
	g++ -c ${CFLAGS} sign.cpp

sign: sign.o ../utils.o ../conversion.o ../crypto.o
	g++ -mno-cygwin ../utils.o ../conversion.o ../crypto.o sign.o -o sign -L../lib -lcrypt32

certificateList.o: certificateList.cpp
	g++ -c ${CFLAGS} certificateList.cpp

certificateList: certificateList.o ../utils.o ../conversion.o ../crypto.o
	g++ -mno-cygwin ../utils.o ../conversion.o ../crypto.o certificateList.o -o certificateList -L../lib -lcrypt32

keyUsage.o: keyUsage.cpp
	g++ -c ${CFLAGS} keyUsage.cpp

keyUsage: keyUsage.o ../utils.o ../conversion.o ../crypto.o
	g++ -mno-cygwin ../utils.o ../conversion.o ../crypto.o keyUsage.o -o keyUsage -L../lib -lcrypt32


check: certificateList digest sign keyUsage
	./certificateList
	./digest
	./sign
	./keyUsage
