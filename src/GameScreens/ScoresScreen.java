package GameScreens;

import Values.GameValues;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScoresScreen extends JPanel{

    private Image cupImg;
    private final ArrayList<ArrayList<String>> scoresBoard;


    public ScoresScreen(){
        /**Getting scores and nicks list*/
        scoresBoard = GameManager.getLeaderBoard();
        /**Absolute positioning*/
        setLayout(null);
        /**Assigning image to variable*/
        loadImages();
        /**Setting background color of the screen*/
        setBackground(new Color(70, 180, 190));
        buildScreen();
    }

    private void loadImages(){
        ImageIcon tmpCup = new ImageIcon("src/Assets/Cup.png");
        cupImg = tmpCup.getImage().getScaledInstance(GameValues.CupX, GameValues.CupY, Image.SCALE_DEFAULT);
    }

    private void buildScreen(){
        /**Adding cup picture to screen*/
        JLabel pic = new JLabel(new ImageIcon(cupImg));
        /**Setting size and position of img*/
        pic.setBounds(30,300 , GameValues.CupX, GameValues.CupY);
        /**Adding to panel*/
        add(pic);

        /**Creating text object*/
        JLabel boardTitle = new JLabel("Best score rank");
        /**Setting foreground color*/
        boardTitle.setForeground(Color.YELLOW);
        /**Setting font style of the text*/
        boardTitle.setFont(new Font("Roboto", Font.BOLD, 85));
        /**Setting size and position of the text*/
        boardTitle.setBounds(100,55,750,100);
        /**Adding to panel*/
        add(boardTitle);

        /**Showing players score*/
        for(int i = 0; i < scoresBoard.size(); ++i)
        {
            /**Showing players nick*/
            JLabel playerNick = new JLabel(scoresBoard.get(i).get(0));
            playerNick.setForeground(Color.GREEN);
            playerNick.setFont(new Font("Roboto", Font.BOLD, 40));
            playerNick.setBounds(310, 200 + 60 * i, 400, 150);
            add(playerNick);

            /**Showing players points*/
            JLabel playerPoints = new JLabel(scoresBoard.get(i).get(1));
            playerPoints.setForeground(Color.GREEN);
            playerPoints.setFont(new Font("Roboto", Font.BOLD, 40));
            playerPoints.setBounds(710, 200 + 60 *i, 150, 150);
            add(playerPoints);
        }

        /**Button which allows us to go back to previous card*/
        JButton startButton = new JButton("Go back");
        /**Setting position and size*/
        startButton.setBounds(240,625,320,100);
        /**Setting button background color*/
        startButton.setBackground(new Color(95, 70, 205));
        /**Don't focus*/
        startButton.setFocusable(false);
        /**Reacting on click if clicked go to game over screen*/
        startButton.addActionListener(event-> GameManager.goToGameOverScreen());
        /**Adding button to panel*/
        add(startButton);

    }
}
