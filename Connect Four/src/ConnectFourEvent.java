/*
Name: Daniel Araujo
Date: 21 Aug 18
Title: Connect Four Event
Purpose: to create a functioning Connect Four game
*/

import javax.swing.*; //imports classes needed for the GUI elements
import java.awt.event.*; //imports classes needed for ItemListener, ActionListener, and ActionEvent
import java.util.*; //imports classes needed for ArrayLists

/**
 * creates class that responds to mouse inputs by using ItemListener and ActionListener
 */
public class ConnectFourEvent implements ItemListener, ActionListener, Runnable {
    ConnectFourGUI gui; //associates the ConnectFourGUI class with the event so that the GUI components can be accessed
    ImageIcon r = new javax.swing.ImageIcon(getClass().getResource("/imageresources/Red Piece.jpg")); //declares ImageIcon variable r and stores the Red Piece.jpg icon - represents a game board square with a red disc
    ImageIcon y = new javax.swing.ImageIcon(getClass().getResource("/imageresources/Yellow Piece.jpg")); //declares ImageIcon variable y and stores the Yellow Piece.jpg icon - represents a game board square with a yellow disc
    ImageIcon rE = new javax.swing.ImageIcon(getClass().getResource("/imageresources/End Red Piece.jpg")); //declares ImageIcon variable rE and stores the End Red Piece.jpg icon - represents a game board square with a red disc that is part of a four in a row
    ImageIcon yE = new javax.swing.ImageIcon(getClass().getResource("/imageresources/End Yellow Piece.jpg")); //declares ImageIcon variable yE and stores the End Yellow Piece.jpg icon - represents a game board square with a yellow disc that is part of a four in a row
    
    int intTurn = 0; //declares integer variable intTurn and sets it equal to 0 - used to determine whether its the red disc or yellow discs turn (even number - red; odd number - yellow)
    int intR = 0, intY = 0, intTie = 0; //declares integer variables that will store the number of wins for red, the number of wins for yellow, and the number of ties, respectively
    int[][] intBoardValues = new int[6][7]; //declares 2d int array that corresponds with the boards red discs, yellow discs, and no discs position (red - 1; yellow - 2; no disc - 0)
    boolean blnStart = false; //declares boolean variable blnStart and sets it as false - represents if the game has started
    boolean blnEnd = false; //declares boolean variable blnEnd and sets it as false - represents if the game has ended
    boolean blnWin = false; //declares boolean variable blnWin and sets it as false - represents if someone has won
    
    String strPlayer1 = ""; //declares string variable strPlayer1 and sets it equal to "" - stores the first player's name
    String strPlayer2 = ""; //declares string variable strPlayer2 and sets it equal to "" - stores the second player's name
    String strGameName = ""; //declares string variable strGameName and sets it equal to "" - stores the game's name
    String strWinner = ""; //declares string variable strWinner and sets it equal to "" - stores the winner's name
    
    /**
     * associates the ConnectFourEvent and ConnectFourGUI files together
     */
    public ConnectFourEvent (ConnectFourGUI in) {
        gui = in; //sets gui equal to in
    }

    /**
     * a button on the game board is pressed
     * executes the code for the button
     * @param event - condition in which a button is pressed
     */
    public void actionPerformed (ActionEvent event) {
        String strCommand = event.getActionCommand(); //declares string variable strCommand and sets it equal to the button's command action of the button that was clicked
        //if statements run the code for the specific button - based on the button's command action
        if (strCommand.equals("Play")) //if statement that exectues if the Play button was pressed
            play(); //calls on the play() method
        if (strCommand.equals("Exit")) //if statement that exectues if the Exit button was pressed
            exit(); //calls on the exit() method
        if (strCommand.equals("Play Again")) //if statement that exectues if the Play Again button was pressed
            playAgain(); //calls on the playAgain() method
        if (strCommand.equals("New Game")) //if statement that exectues if the New Game button was pressed
            newGame(); //calls on the newGame() method
        if (strCommand.equals("Main Menu")) //if statement that exectues if the Main Menu button was pressed
            mainMenu(); //calls on the mainMenu() method
        //if statements the correspond to the spots on the connect four gameboard - each calls on the button() method and passes their index for the parameteres
        if (strCommand.equals("1"))
            button(0,0);
        if (strCommand.equals("2"))
            button(0,1);
        if (strCommand.equals("3"))
            button(0,2);
        if (strCommand.equals("4"))
            button(0,3);
        if (strCommand.equals("5"))
            button(0,4);
        if (strCommand.equals("6"))
            button(0,5);
        if (strCommand.equals("7"))
            button(0,6);
        if (strCommand.equals("8"))
            button(1,0);
        if (strCommand.equals("9"))
            button(1,1);
        if (strCommand.equals("10"))
            button(1,2);
        if (strCommand.equals("11"))
            button(1,3);
        if (strCommand.equals("12"))
            button(1,4);
        if (strCommand.equals("13"))
            button(1,5);
        if (strCommand.equals("14"))
            button(1,6);
        if (strCommand.equals("15"))
            button(2,0);
        if (strCommand.equals("16"))
            button(2,1);
        if (strCommand.equals("17"))
            button(2,2);
        if (strCommand.equals("18"))
            button(2,3);
        if (strCommand.equals("19"))
            button(2,4);
        if (strCommand.equals("20"))
            button(2,5);
        if (strCommand.equals("21"))
            button(2,6);
        if (strCommand.equals("22"))
            button(3,0);
        if (strCommand.equals("23"))
            button(3,1);
        if (strCommand.equals("24"))
            button(3,2);
        if (strCommand.equals("25"))
            button(3,3);
        if (strCommand.equals("26"))
            button(3,4);
        if (strCommand.equals("27"))
            button(3,5);
        if (strCommand.equals("28"))
            button(3,6);
        if (strCommand.equals("29"))
            button(4,0);
        if (strCommand.equals("30"))
            button(4,1);
        if (strCommand.equals("31"))
            button(4,2);
        if (strCommand.equals("32"))
            button(4,3);
        if (strCommand.equals("33"))
            button(4,4);
        if (strCommand.equals("34"))
            button(4,5);
        if (strCommand.equals("35"))
            button(4,6);
        if (strCommand.equals("36"))
            button(5,0);
        if (strCommand.equals("37"))
            button(5,1);
        if (strCommand.equals("38"))
            button(5,2);
        if (strCommand.equals("39"))
            button(5,3);
        if (strCommand.equals("40"))
            button(5,4);
        if (strCommand.equals("41"))
            button(5,5);
        if (strCommand.equals("42"))
            button(5,6);
    }
    
    /**
     * play button must be pressed
     * starts the game by making the connect four game board visible and setting the required variables
     */
    void play() {
        if (gui.txtPlayer1.getText().equals("") || gui.txtPlayer2.getText().equals("") || gui.txtGameName.getText().equals(""))
            JOptionPane.showMessageDialog(null, "Please fill in all the boxes!");
        else {
            gui.pnlMainMenu.setVisible(false); //sets the pnlMainMenu panel as not visible
            gui.splGame.setVisible(true); //sets the splGame panel as visible
            
            strPlayer1 = gui.txtPlayer1.getText(); //sets the strPlayer1 as the user's input in the txtPlayer1 text field
            strPlayer2 = gui.txtPlayer2.getText(); //sets the strPlayer1 as the user's input in the txtPlayer2 text field
            strGameName = gui.txtGameName.getText(); //sets the strGameName as the user's input in the txtGameName text field
        
            gui.txtTurn.setText(strPlayer1 + "'s turn"); //sets the txt informing the user who's turn it is
        
            blnStart = true; //sets the blnStart variable as true - game started
        }
    }
    
    /**
     * exit button is pressed
     * exits the application
     */
    void exit() {
        System.exit(0); //closes the application
    }
    
    /**
     * reset button is pressed
     * resets the board to its original condition
     */
    void reset() {
        //for loops that run through the btnGameBoard and intBoardValues arrays and reset them
        for (int x = 0; x < 6; x++)
            for (int y = 0; y < 7; y++) {
                gui.btnGameBoard[x][y].setIcon(gui.empty); //sets the icon to empty
                intBoardValues[x][y] = 0; //sets the value to 0
            }
        
        intTurn = 0; //sets intTurn variable as 0
        blnStart = true; //sets blnStart variable as true
        blnEnd = false; //sets blnEnd variable as false
        blnWin = false; //sets blnWin variable as false
        
        strWinner = ""; //sets strWinner as ""
    }
    
    /**
     * playAgain button is pressed
     * resets the board with the same players and game name
     */
    void playAgain() {
        reset(); //calls on the reset() method
    }
    
    /**
     * newGame button is pressed
     * resets the game board allowing for the input of new players and game name
     */
    void newGame() {
        mainMenu(); //calls on the mainMenu() method
    }
    
    /**
     * mainMenu button is pressed
     * brings the user back to the main menu
     */
    void mainMenu() {
        reset(); //calls on the reset() method
        
        //resets the values back to their original stats
        intR = 0;
        intY = 0;
        intTie = 0;
        //intIndex += 6;
        
        //clears the text fields so that the user can input information again
        gui.txtGameName.setText("");
        gui.txtPlayer1.setText("");
        gui.txtPlayer2.setText("");
        
        //sets all the panels as ont visible except for the pnlMainMenu panel
        gui.pnlMainMenu.setVisible(true);
        gui.splGame.setVisible(false);
    }
    
    /**
     * one of the buttons on the Connect Four game board is pressed
     * adds the appropriate disc to the column if possible
     * @param intR - integer variable that represents the row of the button
     * @param intC  - integer variable that represents the column of the button
     */
    public void button(int intR, int intC) {
        int intRow = 5; //declares intRow and sets it equal to 5 - bottom row
        
        //if statement that executes if the top spot in that column is not occupied and the game has started
        if (intBoardValues[0][intC] == 0 && blnStart) {
            for (int i = 0; i < 6; i++) { //runs through the selected column and finds the first occupied spot
                if (intBoardValues[i][intC] != 0) { //if condition that exectues if the spot is occupied
                    intRow = i - 1; //sets intRow as the row index of the spot above the occupied spot
                    break; //exits the loop
                }
            }
            if (intBoardValues[intRow][intC] == 0) { //if statement that executes if the spot at the index is free
                if (intTurn % 2 == 0) { //if statement that executes if intTurn is positive - red disc
                    gui.btnGameBoard[intRow][intC].setIcon(r); //sets the icon at the index as r
                    intBoardValues[intRow][intC] = 1; //sets the intBoardValue at the index as 1
                    gui.txtTurn.setText(strPlayer2 + "'s Turn"); //changes who's turn it is
                }
                else { //if statement that executes if intTurn is positive - yellow disc
                    gui.btnGameBoard[intRow][intC].setIcon(y); //sets the icon at the index as y
                    intBoardValues[intRow][intC] = 2; //sets the intBoardValue at the index as 2
                    gui.txtTurn.setText(strPlayer1 + " 's Turn"); //changes who's turn it is
                }
                intTurn++; //increases intTurn by 1
                winner(); //calls on the winner() method
            }
        }
        else if (blnEnd) //else statement that executes if the game has ended
            JOptionPane.showMessageDialog(null, "The game has ended. Please select Play Again to play again or press New Game to start a new game.");
        else if  (intBoardValues[0][intC] != 0) //else statement that executes if the column is occupied
            JOptionPane.showMessageDialog(null, "Column is full. Please select another.");
    }
    
    /**
     * winner method is called after a disc is placed
     * checks to see if there is a winner
     */
    void winner() {
        
        winnerCheck(0,6,4,0,1,0,2,0,3); //horizontal winner
        if (!blnWin) //if statement that executes if someone hasn't won yet
            winnerCheck(0,3,7,1,0,2,0,3,0); //vertical winner
        if (!blnWin) //if statement that executes if someone hasn't won yet
            winnerCheck(0,3,4,1,1,2,2,3,3); //diagonal winner downwards
        if (!blnWin) //if statement that executes if someone hasn't won yet
            winnerCheck(3,6,4,-1,1,-2,2,-3,3); //diagonal winner upwards
        
        if (intTurn == 42 && !blnWin) { //if statement that executes if someone hasn't won yet and all the spots are occupied - tie
            JOptionPane.showMessageDialog(null, "The game is a tie!"); //outputs to the user that the game is a tie
            intTie++;
            blnStart = false;
            blnEnd = true;
            output();
        }
    }
    
    /**
     * is called on by the winner() method
     * @param xs - min x value
     * @param xx - max x value
     * @param yy - max y value
     * @param ax - next x spot
     * @param ay - next y spot
     * @param bx - 2nd next x spot
     * @param by - 2nd next y spot
     * @param cx - 3rd next x spot
     * @param cy - 3rd next y spot
     */
    void winnerCheck(int xs, int xx, int yy, int ax, int ay, int bx, int by, int cx, int cy) {
        for (int x = xs; x < xx; x++)
            for (int y = 0; y < yy; y++)
                if (intBoardValues[x][y] == intBoardValues[x + ax][y + ay] && intBoardValues[x][y] == intBoardValues[x + bx][y + by] && intBoardValues[x][y] == intBoardValues[x + cx][y + cy]) {
                    if (intBoardValues[x][y] == 1) {
                        gui.btnGameBoard[x][y].setIcon(rE);
                        gui.btnGameBoard[x + ax][y + ay].setIcon(rE);
                        gui.btnGameBoard[x + bx][y + by].setIcon(rE);
                        gui.btnGameBoard[x + cx][y + cy].setIcon(rE);
                        
                        JOptionPane.showMessageDialog(null, strPlayer1 + " is the winner!");
                        blnWin = true;
                        intR++;
                        blnStart = false;
                        blnEnd = true;
                        strWinner = gui.txtPlayer1.getText();
                        output();
                        break;
                    }
                    else if (intBoardValues[x][y] == 2) {
                        gui.btnGameBoard[x][y].setIcon(yE);
                        gui.btnGameBoard[x + ax][y + ay].setIcon(yE);
                        gui.btnGameBoard[x + bx][y + by].setIcon(yE);
                        gui.btnGameBoard[x + cx][y + cy].setIcon(yE);
                        
                        JOptionPane.showMessageDialog(null, strPlayer2 + " is the winner!");
                        blnWin = true;
                        intY++;
                        blnStart = false;
                        blnEnd = true;
                        strWinner = gui.txtPlayer2.getText();
                        output();
                        break;
                    }
                }
    }
    
    void output() {
        gui.txtScore.setText(strPlayer1 + " wins: " + intR + "\n" + strPlayer2 + " wins: " + intY + "\nTies: " + intTie);
        gui.txtTurn.setText("Game Over");
    }
        
    @Override
    public void itemStateChanged(ItemEvent ie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
