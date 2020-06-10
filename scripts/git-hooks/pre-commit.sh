#!/bin/sh
echo "⚙️ Running static analysis..."

JAVA_HOME=$(/usr/libexec/java_home -v 1.8)
export JAVA_HOME

OUTPUT="/tmp/analysis-result"
./gradlew detekt lintDebug spotlessCheck --daemon > ${OUTPUT}
EXIT_CODE=$?
if [[ ${EXIT_CODE} -ne 0 ]]; then
    cat ${OUTPUT}
    rm ${OUTPUT}
    echo "*********************************************"
    echo "❌ Static analysis failed ❌                 "
    echo "Please fix the above issues before committing"
    echo "*********************************************"
    exit ${EXIT_CODE}
else
    rm ${OUTPUT}
    echo "*********************************************"
    echo "✅ Static analysis passed ✅                 "
    echo "*********************************************"
fi
