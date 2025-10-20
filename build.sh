# This script creates a dmg installer file in the current directory.
#
# After installing the application to my /Applications folder,
# I actually have to click on the Apple menu (in the top left corner of the menu bar),
# and then click on System Settings, and then navigate to Privacy & Security, and allow the Paint application to be opened.
#
# If I wanted to avoid this process, I could get an Apple Developer ID, and sign or notarize my application.
# But that takes a lot of work... maybe I'll get to it in the future... but I don't know.
#
# ------
#
# The comments below explain the commands used in the build process
#
# ------
#
# The command "mvn clean install" reads our pom.xml file and looks for build instructions in the pom.xml file
#
# Specifically, it downloads every necessary dependency, compiles the source code into bytecode, and packages the bytecode into a jar file, which it saves in the target folder
#
# In addition, the jar file is configured to be executable, per the instructions contained within the pom.xml file
#
mvn clean install
#
# The command "jpackage" accepts a jar file as its argument and outputs a dmg file
#
jpackage --input target \
  --name Paint \
  --main-jar Paint.jar \
  --main-class paint.App \
  --type dmg \
  --app-version "1.0.0" \
  --mac-package-name "Paint" \
  --verbose
