/*
Name: Daniel Araujo
Date: 21 Aug 18
Title: Connect Four Event
Purpose: to create a functioning Connect Four game that keeps track of score
* use several of the programming methods learned in class (arrays, external files, etc.)
*/

import javax.swing.*; //imports classes needed for the GUI elements
import java.awt.event.*; //imports classes needed for ItemListener, ActionListener, and ActionEvent
import java.io.IOException; //imports classes need for IOException
import java.io.*; //imports classes needed to read and write XML files
import java.util.*; //imports classes needed for ArrayLists
//import org.jdom.output.XMLOutputter; //import classes needed to output the XML file
//import org.jdom.input.SAXBuilder; //imports classes needed for SAXBuilder
//import org.jdom.JDOMException; //imports classes needed for JDOMException
//import org.jdom.Document; //imports classes needed for JDOM documents
//import org.jdom.Element; //imports classes needed for JDOM elements
import java.util.List; //imports classes needed for Lists

/**
 * creates class that responds to mouse inputs by using ItemListener and ActionListener
 */
public class ConnectFourEvent implements ItemListener, ActionListener, Runnable {
    ConnectFourGUI gui; //associates the ConnectFourGUI class with the event so that the GUI components can be accessed
    
    ImageIcon r = new ImageIcon("ICS4UE_U4A3A1_DanielAraujo - Connect Four (Red Piece).jpg"); //declares ImageIcon variable r and stores the ICS4UE_U4A3A1_DanielAraujo - Connect Four (Red Piece).jpg icon - represents a game board square with a red disc
    ImageIcon y = new ImageIcon("ICS4UE_U4A3A1_DanielAraujo - Connect Four (Yellow Piece).jpg"); //declares ImageIcon variable y and stores the ICS4UE_U4A3A1_DanielAraujo - Connect Four (Yellow Piece).jpg icon - represents a game board square with a yellow disc
    ImageIcon rE = new ImageIcon("ICS4UE_U4A3A1_DanielAraujo - Connect Four (End Red Piece).jpg"); //declares ImageIcon variable rE and stores the ICS4UE_U4A3A1_DanielAraujo - Connect Four (End Red Piece).jpg icon - represents a game board square with a red disc that is part of a four in a row
    ImageIcon yE = new ImageIcon("ICS4UE_U4A3A1_DanielAraujo - Connect Four (End Yellow Piece).jpg"); //declares ImageIcon variable yE and stores the ICS4UE_U4A3A1_DanielAraujo - Connect Four (End Yellow Piece).jpg icon - represents a game board square with a yellow disc that is part of a four in a row
    
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
    
    ArrayList<String> scoreList = new ArrayList(); //declares string ArrayList - used to store the score
    //int intIndex = index(); //declares integer variable intIndex - used to store the next available index in the scoreList ArrayList
    
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
        //if (strCommand.equals("Scores")) //if statement that exectues if the Scores button was pressed
        //    score(); //calls on the score() method
        //if (strCommand.equals("Search")) //if statement that exectues if the Search button was pressed
        //    search(); //calls on the search() method
        //if (strCommand.equals("View Best Scores")) //if statement that exectues if the View Best Score button was pressed
         //   highScore(); //calls on the highScore() method
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
        gui.pnlMainMenu.setVisible(false); //sets the pnlMainMenu panel as not visible
        gui.splGame.setVisible(true); //sets the splGame panel as visible
        
        strPlayer1 = gui.txtPlayer1.getText(); //sets the strPlayer1 as the user's input in the txtPlayer1 text field
        strPlayer2 = gui.txtPlayer2.getText(); //sets the strPlayer1 as the user's input in the txtPlayer2 text field
        strGameName = gui.txtGameName.getText(); //sets the strGameName as the user's input in the txtGameName text field
        
        gui.txtTurn.setText(strPlayer1 + "'s turn"); //sets the txt informing the user who's turn it is
        
        blnStart = true; //sets the blnStart variable as true - game started
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
        //if statement that adds the games information the the scoreList arraylist
        /*if (intR + intY + intTie < 2) {
            scoreList.add(intIndex, strGameName);
            scoreList.add(intIndex + 1, strPlayer1);
            scoreList.add(intIndex + 2, strPlayer2);
            scoreList.add(intIndex + 3, Integer.toString(intR));
            scoreList.add(intIndex + 4, Integer.toString(intY));
            scoreList.add(intIndex + 5, Integer.toString(intTie));
        } else { //else condition that sets the values in the arraylist to the games information given that it is the same game
            scoreList.set(intIndex, strGameName);
            scoreList.set(intIndex + 1, strPlayer1);
            scoreList.set(intIndex + 2, strPlayer2);
            scoreList.set(intIndex + 3, Integer.toString(intR));
            scoreList.set(intIndex + 4, Integer.toString(intY));
            scoreList.set(intIndex + 5, Integer.toString(intTie));
        }
        
        writingScore(); //calls on the writingScore() method*/
        reset(); //calls on the reset() method
    }
    
    /**
     * newGame button is pressed
     * resets the game board allowing for the input of new players and game name
     */
    void newGame() {
        //adds the games information to the scoreList array
        scoreList.add(strGameName);
        scoreList.add(strPlayer1);
        scoreList.add(strPlayer2);
        scoreList.add(Integer.toString(intR));
        scoreList.add(Integer.toString(intY));
        scoreList.add(Integer.toString(intTie));
        
        //writingScore(); //calls on the writingScore() method
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
        gui.pnlScores.setVisible(false);
    }
    
    /**
     * one of the buttons on the Connect Four game board is pressed
     * adds the appropriate disc to the column if possible
     * @param intR - integer variable that represents the row of the button
     * @param intC  - integer variable that represents the column of the butotn
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
        else if  (intBoardValues[0][intC] != 0) { //else statement that executes if the column is occupied
            JOptionPane.showMessageDialog(null, "Column is full. Please select another.");
        }
        else if (blnEnd = true) //else statement that executes if the game has ended
            JOptionPane.showMessageDialog(null, "The game has ended. Please select Reset to play again.");
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
        gui.txtScore.setText(strPlayer1 + " wins: " + intR + "\t\t" + strPlayer2 + " wins: " + intY + "\t\tTies: " + intTie);
    }
    /*
    void writingScore() {
        try {
            Element score = new Element("score");
            Document doc = new Document(score);
            doc.setRootElement(score);
            
            score.addContent(new Element("index").setText(Integer.toString(intIndex + 6)));
            
            for (int i = 0; i < scoreList.size(); i += 6) {
                
                Element game = new Element("game");
                game.addContent(new Element("gameName").setText(scoreList.get(i)));
                game.addContent(new Element("player1").setText(scoreList.get(i + 1)));
                game.addContent(new Element("player2").setText(scoreList.get(i + 2)));
                game.addContent(new Element("player1Wins").setText(scoreList.get(i + 3)));
                game.addContent(new Element("player2Wins").setText(scoreList.get(i + 4)));
                game.addContent(new Element("ties").setText(scoreList.get(i + 5)));
                
                doc.getRootElement().addContent(game);
                
                XMLOutputter output = new XMLOutputter(); //declares XMLOutputter
                output.output(doc, new FileWriter("Score.txt")); //outputs doc using FileWriter - calls file "overwatch.txt"
            }
        }
        catch (IOException io) {}
    }
    
    void score() {                
        gui.pnlMainMenu.setVisible(false);
        gui.pnlScores.setVisible(true);
        
        String strOutput = "";
        
        for (int i = 0; i < scoreList.size(); i += 6) {
            strOutput += "Game Name: " + scoreList.get(i) + " | ";
            strOutput += "Player 1: " + scoreList.get(i + 1) + " | ";
            strOutput += "Player 2: " + scoreList.get(i + 2) + " | ";
            strOutput += "Player 1 wins: " + scoreList.get(i + 3) + " | ";
            strOutput += "Player 2 wins: " + scoreList.get(i + 4) + " | ";
            strOutput += "Ties: " + scoreList.get(i + 5) + "\n";
            }
        gui.txtScores.setText(strOutput);
    }
    
    void search() {
        String strSearch = gui.txtSearch.getText();
        
        for (int i = 0; i < scoreList.size(); i += 6) {
            if (strSearch.equals(scoreList.get(i)))
                gui.txtScores.setText("Game Name: " + scoreList.get(i) + " | "
                        + "Player 1: " + scoreList.get(i + 1) + " | "
                        + "Player 2: " + scoreList.get(i + 2) + " | "
                        + "Player 1 wins: " + scoreList.get(i + 3) + " | "
                        + "Player 2 wins: " + scoreList.get(i + 4) + " | "
                        + "Ties: " + scoreList.get(i + 5) + ""
                );
        }
    }
    
    void highScore() {
        System.out.println("hello");
        for (int i = 3; i < scoreList.size(); i += 6) {
            for (int j = i + 6; j < scoreList.size(); j += 6)
                if (Math.max(Integer.parseInt(scoreList.get(i)),Integer.parseInt(scoreList.get(i+1))) < Math.max(Integer.parseInt(scoreList.get(j)),Integer.parseInt(scoreList.get(j+1)))) {
                    String strTemp = scoreList.get(i - 3);
                    scoreList.set(i - 3, scoreList.get(j - 3));
                    scoreList.set(j - 3, strTemp);
                    
                    strTemp = scoreList.get(i - 2);
                    scoreList.set(i - 2, scoreList.get(j - 2));
                    scoreList.set(j - 2, strTemp);
                    
                    strTemp = scoreList.get(i - 1);
                    scoreList.set(i - 1, scoreList.get(j - 1));
                    scoreList.set(j - 1, strTemp);
                    
                    strTemp = scoreList.get(i);
                    scoreList.set(i, scoreList.get(j));
                    scoreList.set(j, strTemp);
                    
                    strTemp = scoreList.get(i + 1);
                    scoreList.set(i + 1, scoreList.get(j + 1));
                    scoreList.set(j + 1, strTemp);
                    
                    strTemp = scoreList.get(i + 2);
                    scoreList.set(i + 2, scoreList.get(j + 2));
                    scoreList.set(j + 2, strTemp);
                }
        }
        score();
    }
    
    int index() {
        SAXBuilder builder = new SAXBuilder();
        File sFile = new File ("Score.txt");
        
        try {
            Document doc = (Document) builder.build(sFile);
            Element rootNode = doc.getRootElement();
            List list = rootNode.getChildren("game");
            
            int intIndexTemp = Integer.parseInt(rootNode.getChildText("index"));
            
            for (int i = 0; i < intIndexTemp; i += 6) {
                Element node = (Element) list.get(i / 6);
                
                scoreList.add(node.getChildText("gameName"));
                scoreList.add(node.getChildText("player1"));
                scoreList.add(node.getChildText("player2"));
                scoreList.add(node.getChildText("player1Wins"));
                scoreList.add(node.getChildText("player2Wins"));
                scoreList.add(node.getChildText("ties"));
            }
            
            return intIndexTemp;
        }
        catch (JDOMException jdomex) {}
        return 0;
    } */
    
    @Override
    public void itemStateChanged(ItemEvent ie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
