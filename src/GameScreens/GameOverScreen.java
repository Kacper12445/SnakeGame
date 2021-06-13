package GameScreens;

import Values.GameValues;

import javax.swing.*;
import java.awt.*;

public class GameOverScreen extends JPanel{

    private Image deadSnake;

    public GameOverScreen(){
        /**Absolute positioning*/
        setLayout(null);
        /**Assigning image to variable*/
        loadImages();
        /**Setting background color of game over screen*/
        setBackground(new Color(210, 110, 70));
        /**Adding elements to screen*/
        buildScreen();
    }

    private void loadImages()
    {
        ImageIcon tmpSnakeImg = new ImageIcon("src/Assets/DeadSnake.png");
        deadSnake = tmpSnakeImg.getImage().getScaledInstance(GameValues.RipSnakeX, GameValues.RipSnakeY, Image.SCALE_DEFAULT);
    }

    private void buildScreen(){
        /**Adding game over img to screen*/
        JLabel snake_pic = new JLabel(new ImageIcon(deadSnake));
        /**Setting size and position of the img*/
        snake_pic.setBounds(215, 50, GameValues.RipSnakeX, GameValues.RipSnakeY);
        /**Adding to panel*/
        add(snake_pic);

        /**Creating button to try again after lose*/
        JButton tryAgainButton = new JButton("Try again");
        /**Setting size and position of the button*/
        tryAgainButton.setBounds(255, 350, 300, 100);
        /**Don't get focus*/
        tryAgainButton.setFocusable(false);
        /**Creating button*/
        tryAgainButton.setFont(new Font("Roboto", Font.BOLD, 25));
        /**Reacting on click (if clicked call function)*/
        tryAgainButton.addActionListener(e -> GameManager.playGame());
        /**Setting button background color*/
        tryAgainButton.setBackground(Color.GREEN);
        /**Adding to panel*/
        add(tryAgainButton);

        /**Creating button which bring us to leader board*/
        JButton leaderBoardButton = new JButton(" Go to Leader Board");
        /**Setting size and position*/
        leaderBoardButton.setBounds(255,480,300,100);
        /**Don't focus*/
        leaderBoardButton.setFocusable(false);
        /**Setting font style of button text*/
        leaderBoardButton.setFont(new Font("Roboto", Font.BOLD, 25));
        /**Reacting on click (if clicked call function)*/
        leaderBoardButton.addActionListener(event -> GameManager.goToScoreScreen());
        /**Setting background color*/
        leaderBoardButton.setBackground(new Color(180,170,190));
        /**Adding to panel*/
        add(leaderBoardButton);

        /**Creating exit game button*/
        JButton exitGameButton = new JButton("Exit the game");
        /**Setting size and position*/
        exitGameButton.setBounds(255,610,300,100);
        /**Setting background color of the button*/
        exitGameButton.setBackground(new Color(150, 90, 110));
        /**Setting button text font style*/
        exitGameButton.setFont(new Font("Roboto", Font.BOLD, 25));
        /**Don't focus*/
        exitGameButton.setFocusable(false);
        /**Reacting on click (if clicked call function)*/
        exitGameButton.addActionListener(e -> System.exit(0));
        /**Adding to panel*/
        add(exitGameButton);
    }
}
