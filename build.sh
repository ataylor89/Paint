# This script creates a dmg installer file in the current directory.
#
# After installing the application to my /Applications folder,
# I actually have to click on the Apple menu (in the top left corner of the menu bar),
# and then click on System Settings, and then navigate to Privacy & Security, and allow the Paint application to be opened.
#
# If I wanted to avoid this process, I could get an Apple Developer ID, and sign or notarize my application.
# But that takes a lot of work... maybe I'll get to it in the future... but I don't know.

mvn clean install
jpackage --input target \
  --name Paint \
  --main-jar Paint.jar \
  --main-class paint.App \
  --type dmg \
  --app-version "1.0.0" \
  --mac-package-name "Paint" \
  --verbose
