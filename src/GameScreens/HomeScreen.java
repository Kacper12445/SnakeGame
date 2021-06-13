package GameScreens;

import Values.GameValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JPanel implements ActionListener {
    private JTextField nickArea;
    private Image homeImage;

    public HomeScreen(){
        //Absolute positioning
        setLayout(null);
        //Assigning image to variable
        loadImages();
        //Setting background color of the screen
        setBackground(new Color(75, 225, 155));
        //Adding elements to panel
        buildScreen();

    }

    private void loadImages()
    {
        ImageIcon tmpLogoGame = new ImageIcon("src/Assets/homePicture.jpg");
        homeImage = tmpLogoGame.getImage().getScaledInstance(GameValues.LogoX, GameValues.LogoY, Image.SCALE_DEFAULT);
    }

    public void buildScreen()
    {
        //Adding image to home screen
        JLabel logo = new JLabel(new ImageIcon(homeImage));
        //Setting size of the logo
        logo.setBounds(225, 50, GameValues.LogoX, GameValues.LogoY);
        add(logo);

        //Creating label
        JLabel nick = new JLabel("Enter Nickname");
        //Setting size and position of the label
        nick.setBounds((GameValues.BoardWidth/2)-100,440,200,50);
        //Setting font style to label text
        nick.setFont(new Font("Roboto", Font.PLAIN, 26));
        //Adding to panel
        add(nick);

        //Creating input
        nickArea = new JTextField();
        //Reacting on action
        nickArea.addActionListener(this);
        //Setting Field font type and size
        nickArea.setFont(new Font("Roboto", Font.PLAIN, 30));
        //Setting foreground color
        nickArea.setForeground(Color.BLACK);
        //Setting size and position of input
        nickArea.setBounds((GameValues.BoardWidth/2)-160,510,320,50);
        add(nickArea);

        //Creating button
        JButton startButton = new JButton("Start Game");
        //Specifying size and position
        startButton.setBounds((GameValues.BoardWidth/2)-125, 600,250,50);
        //Setting type and size of button font
        startButton.setFont(new Font("Roboto", Font.BOLD, 20));
        //Setting button background color
        startButton.setBackground(new Color(95, 250, 234));
        //Don't set focus on button
        startButton.setFocusable(false);
        //Reacting on action
        startButton.addActionListener(this);
        //Add to panel
        add(startButton);


    }

    @Override
    public void actionPerformed(ActionEvent event) {
        //checking if text area is empty
        if(nickArea.getText().isEmpty()){
            //if empty, make text field colored in red
            nickArea.setBackground(Color.RED);
            return;
        }
        //Assigning text to variable
        GameManager.playerNick = nickArea.getText();
        //Start the game
        GameManager.playGame();
    }
}

