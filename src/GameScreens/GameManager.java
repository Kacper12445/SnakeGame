package GameScreens;

import CSV.FileCSV;
import Values.GameValues;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameManager extends JFrame {
    static JPanel gamePanel;
    static String playerNick;
    static FileCSV csv;

    public GameManager(){
        //Initializing file variable
        csv = new FileCSV();
        //Creating new panel as a card
        gamePanel = new JPanel(new CardLayout());
        //Starting the game with homeScreen
        gamePanel.add(new HomeScreen());
        add(gamePanel);

        //Setting game icon
        setGameIcon();
        //Setting game title
        setTitle("Snake");
        //Set const size
        setResizable(false);
        //Exiting frame when user click close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Setting window game size
        setPreferredSize(new Dimension(GameValues.BoardWidth, GameValues.BoardHeight));
        //Fitting to preferred size
        pack();
        //Starting window in the center of the screen
        setLocationRelativeTo(null);
    }

    //Function sets game Icon
    public void setGameIcon(){
        ImageIcon tmpIconImg = new ImageIcon("src/Assets/SnakeIcon.png");
        this.setIconImage(tmpIconImg.getImage());
    }

    //Changing card to board
    public static void playGame(){
        GameScreen gameBoard = new GameScreen();
        gamePanel.removeAll();
        gamePanel.add(gameBoard);
        gamePanel.revalidate();
        gamePanel.repaint();
        gameBoard.setSnakePanelFocus();
    }

    //Changing card to game over screen
    public static void goToGameOverScreen(){
        GameOverScreen gameOver = new GameOverScreen();
        gamePanel.removeAll();
        gamePanel.add(gameOver);
        gamePanel.revalidate();
        gamePanel.repaint();
        gameOver.requestFocusInWindow();
    }

    //Changing card to leader board
    public static void goToScoreScreen(){
        ScoresScreen leaderBoard = new ScoresScreen();
        gamePanel.removeAll();
        gamePanel.add(leaderBoard);
        gamePanel.revalidate();
        gamePanel.repaint();

        leaderBoard.requestFocusInWindow();
    }

    //Getting scores from file
    public static ArrayList<ArrayList<String>> getLeaderBoard() {
        return csv.getParsedFile();
    }
    //Saving scores to file
    public static void saveScore(int score){
        csv.saveScore(score, playerNick);
    }

    //Running the game
    public static void runGame(){
        EventQueue.invokeLater(()->{
            JFrame game = new GameManager();
            game.setVisible(true);
        });
    }
}
