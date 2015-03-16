#/bin/bash

cd flice-projects/flice-notification; mvn clean package ; cd ../../;
cd flice-projects/flice-core        ; mvn clean package ; cd ../../;
cd flice-projects/flice-security    ; mvn clean package ; cd ../../;