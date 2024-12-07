# Connect Four V1

A connect four game written in Java.
* Original version: 11/24/2019 by Justin Jang

How to run (assuming you have Java JDK installed):
1. Compile: `javac ConnectFourGUI.java`
2. Run: `java ConnectFourGUI`

Implementation info:
* Uses the standard Swing library (javax.swing.*) for the GUI
* Uses the java.awt.Graphics class for drawing the board

File description:
* ConnectFourBoard.java - The game board and game logic.
* ConnectFourBoardComponent.java - A JComponent that draws a ConnectFourBoard using the Graphics class.
* ConnectFourFrame.java - The JFrame (window) with all the GUI controls to play the game.
* ConnectFourGUI.java - The main program that creates an instance of the GUI and makes it visible.
* ConnectFourBoardTest.java - A console-based interface to play-test the ConnectFourBoard class.
