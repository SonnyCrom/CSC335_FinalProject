# CSC335_FinalProject
CSC 335 - Final Project
### Authors:
- Eilon Weiner
- Sonny Crom
- Calvin Briscoe

All you need to do is Run Main branch as a java application. You can delete the .json file to simulate no save.

--Design--

The design uses the standard MVC with observers to run the UI.

--MODEL--
DbConnector - uses googles Gson file, which is found in the libraries folder, to make a json object of the board state when saveNewGameSave is called. Uses a final string for the path because the path will not change, so it shouldn't be able to be reassigned.

Difficulty.java - Standard difficulty enum

GameBoard.java - This is the board object,this is the main model class. It doesn't interact with the UI at all, thats reseved for different classes in the model. It uses a hash map to save the difficulties, which isn't really necissary since there are only 2, but it does give O(1) lookup. It uses collections.shuffle which reduces duplicate code. Pretty well encapuslated, as everything returns immutable types and only getters and setters are public. We uses a youtube video for the recusive backtracking algorithm that solves the board. 


GameBoardCell.java -
Pretty well encapuslated, as everything returns immutable types and only getters and setters are public. Uses enums as numbers to avoid primitve obsession.


SudokuModel.java - Model class that interfaces with the UI observers. Uses a hashmap to store the cell observers, allowing for O(1) lookup time. Uses composition for the gameBoard and DbCOnnector. Standard encapuslation. 


--View--
Lots of interfaces used for observers. Nothing breaks encapsulation. Uses jswing.

--Controller--
SudokuController.java - main controller class for the board. Everything is encapuslated well. uses composition and interfaces. No duplicate code. No ai code


