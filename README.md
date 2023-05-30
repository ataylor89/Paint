# Paint

## Building, installing, and running the application

The application can be packaged as an executable JAR file with the command:

    mvn clean install

It can be run with the command:

    java -jar target/Paint.jar

The application can also be packaged as a MacOS app.

First, we make the "build.sh" script executable.

    chmod u+x build.sh

This grants the "execute" permission to the owner of the "build.sh" file.

Now we can run the "build.sh" file with the command:

    ./build.sh

If the script runs successfully, it should create a DMG installer for the application.

The DMG file can be run by double clicking on its icon.

The application can be installed into a destination like /Applications. It can also be added to the Dock.

The application can be launched by double clicking on its icon.
