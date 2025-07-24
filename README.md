## Author: Dylan
## Date: 2024-06-10
## Version: 1.0.0
## Description: This program provides an interface for the Powerball program that utilizes a java 2D animation to display randomly selected powerball numbers for lottery tickets.

## Powerball Java Animation

This program provides a graphical interface for managing and simulating Powerball lottery draws. Users can generate random numbers, view previous results, and interact with the lottery system using a simple Java-based GUI that has a java animation of lottery balls.

## Getting Started

Follow these steps to run the Powerball Interface in Visual Studio Code:

1. **Clone or download the repository** to your local machine.
2. Open the project folder in VS Code.
3. Ensure you have the Java Extension Pack installed.
4. Build and run the project using the `Run` or `Debug` options in VS Code.

1. Open the project folder in VS Code.
2. Compile the project from the command line by navigating to the `src` directory and running:
    ```
    javac Main.java
    ```
    Or, to compile all files:
    ```
    javac *.java
    ```
3. Run the compiled program with:
    ```
    java Main
    ```
## OR
1. Locate the main class file (e.g., `Main.java`).
2. Right-click the file and select **Run Java** to start the application.

## Java Animation Folder Structure

The `animation` folder contains the following files for the lottery ball animation:

- `Main.java`  
    The entry point of the application; initializes and launches the GUI.

- `MyFrame.java`  
    Sets up the main window and manages layout for the animation interface.

- `MyPanel.java`  
    Handles drawing and animating the lottery balls within the GUI.

- `Powerball.java`  
    Represents a standard lottery ball, including its number and visual properties.

- `PowerNum.java`  
    Represents the special Powerball number and its animation.

- `Random_Number.java`  
    Generates random numbers for the lottery draw and animation.

These files work together to create and display the animated lottery balls during each draw.

## Dependency Management

Manage your dependencies using the `JAVA PROJECTS` view in VS Code. For more details, see [VS Code Java Dependency Management](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
## Java Version

This project is using **Java 17** in Visual Studio Code. Make sure your environment is set to Java 17 for compatibility.

## License

This project is licensed under the Creative Commons Attribution-NonCommercial 4.0 International License. You may use, modify, and share the code for non-commercial purposes, provided you give appropriate credit. For commercial use, please contact the author for permission.

See [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/) for details.
