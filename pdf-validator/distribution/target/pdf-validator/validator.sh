#!/bin/sh

DIRNAME=`dirname "$0"`


# Setup the JVM
if [ "x$JAVA" = "x" ]; then
    if [ "x$JAVA_HOME" != "x" ]; then
        JAVA="$JAVA_HOME/bin/java"
    else
        JAVA="java"
    fi
fi

# Build classpath
CLASSPATH=""

for i in `find lib -name "*jar"`
do
  if [ "x$CLASSPATH" = "x" ]; then
    CLASSPATH=$i
  else
    CLASSPATH=$CLASSPATH:$i
  fi
done

# Set Java opts
JAVA_OPTS="-Xms64M -Xmx256M -XX:MaxPermSize=64M"

echo "==============================================================================="
echo ""
echo "  PDF Validator Environment"
echo ""
echo "  JAVA: $JAVA"
echo ""
echo "  JAVA_OPTS: $JAVA_OPTS"
echo ""
echo "  CLASSPATH: $CLASSPATH"
echo ""
echo "==============================================================================="
echo ""

# Execute the JVM
$JAVA $JAVA_OPTS -cp $CLASSPATH org.openoces.opensign.pdf.validator.client.PdfValidatorApplication



