# assignment-2-GUI-ChanonSiripuchaka

This program is developed using eclipse with JDK 17 using JavaFx 15 and it must be imported in the eclipse by following the below way.

File > Open projects from File system > Click on the Directory Button in the opened window > Select the project folder > Click Select Folder > Click Finish.

Program will be imported to the eclipse ide.


Setting up the JavaFx classpaths:
Download the JavaFX SDK for your Windows from the following link and unzip it to a desired location, for instance /Users/your-user/Downloads/javafx-sdk-11.
https://download2.gluonhq.com/openjfx/15.0.1/openjfx-15.0.1_windows-x64_bin-sdk.zip

Create a new User Library under Eclipse -> Window -> Preferences -> Java -> Build Path -> User Libraries -> New.
Name it JavaFX15 and include the jars under the lib folder from JavaFX 15.

Now add this user library to the project by right clicking the project > Build Path > Configur Build Path > Select ClassPath > Add Library > User Library > Select JavaFx Library created in the previous step. Click Apply.

Follow these exact steps to add the Junit library to the class path.

Now, Add VM Arguments to the project by Right clicking the project > Run As > Run configurations > Click Aruguments tab > Add the following line to the VM Arguments Text Area (change the path to the directory where you downloaded the JavaFx libraries)

--module-path "[path]\lib" --add-modules javafx.controls,javafx.fxml

Now add the program arguments in the same Arguments tab by entering "test_data.txt test_data.txt" with out quotes in the Program Arguments tab.

Setup is complete now, you can run the program.

To run the program, follow the below steps:

Rigth click the project > Select Run As > Java Application > Program will run in a new window.

You need jdk 17 and JUnit 5 to run this program.
