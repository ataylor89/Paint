mvn clean install
jpackage --input target/ \
  --name Paint \
  --main-jar Paint.jar \
  --main-class paint.App \
  --type dmg \
  --app-version "1.0.0" \
  --vendor "Andrew's software" \
  --copyright "Copyright 2023" \
  --mac-package-name "Paint" \
  --verbose
