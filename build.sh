mvn clean install
jpackage --input target \
  --name Paint \
  --main-jar Paint.jar \
  --main-class paint.App \
  --type dmg \
  --app-version "1.0.0" \
  --mac-package-name "Paint" \
  --verbose
