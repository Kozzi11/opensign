# Vytvoreni appletu
## Priprava klicenky
1. je treba vytvorit pkcs format certifikatu a klice

    `openssl pkcs12 -export -in client.pem -inkey client.key -name importkey -out client.p12 -CAfile ca.pem`
    
    bude se ptat na heslo, zapmatovat nebo idealne dat `importkey` jelikoz stim pocita buildovaci skript pro maven
    
2. vytvoreni java keystore (klicenky):

    2. `keytool -importkeystore -deststorepass importkey -destkeypass importkey  -destkeystore .keystore -srckeystore client.p12 -srcstoretype PKCS12 -srcstorepass importkey -alias importkey`
    
    2. vznikly soubor `.keystore` ulozit do domovskeho adresare 
    
## Kompilace apletu

1. priprava prostredi
    
    pro kompilace je potreba jdk6 nebo jdk7, v pripade jdk6 je problem ze je treba pouzit starsi verzi nastroje maven (vrze 2) (mvn) v pripade pouziti jdk7 je treb dohrat plugin.jar, napriklad skopirovanim s instalace jdk6 nebo pres projekt icetead

2. v hlavnim adresari projektu zadat prikaz:

    `mvn clean install` 
    