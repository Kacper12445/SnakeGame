package GamePanels;

import GameObjects.GameTime;
import Values.GameValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ScorePanel extends JPanel{
    private final GameTime time;

    private JButton pauseButton;
    private Image scoreImg;
    private Image AiScoreImg;

    private JLabel score;
    private JLabel scoreAI;

    public ScorePanel(){
        time = new GameTime();
        //Setting background color
        setBackground(new Color(95, 70, 204));
        //Absolute positioning
        setLayout(null);
        //Loading images to variables
        loadImages();
        //Adding elements to screen
        buildScreen();
    }

    //Giving access to score variable
    public JLabel getScore() { return score; }
    public JLabel getScoreAI(){return scoreAI;}

    private void loadImages(){
        ImageIcon tmpScoreImg = new ImageIcon("src/Assets/ScoreImg.png");
        scoreImg = tmpScoreImg.getImage().getScaledInstance(GameValues.ScoreImgX, GameValues.ScoreImgY, Image.SCALE_DEFAULT);

        ImageIcon tmpAIScoreImg = new ImageIcon("src/Assets/ScoreImg.png");
        AiScoreImg = tmpAIScoreImg.getImage().getScaledInstance(GameValues.ScoreImgX, GameValues.ScoreImgY, Image.SCALE_DEFAULT);

    }

    //Adding elements to panel
    private void buildScreen(){
        //Creating score img label
        JLabel scorePic = new JLabel(new ImageIcon(scoreImg));
        //Setting size and position of img
        scorePic.setBounds(40, 10, GameValues.ScoreImgX, GameValues.ScoreImgY);
        //Adding to panel
        add(scorePic);

        JLabel scoreAIPic = new JLabel(new ImageIcon(AiScoreImg));
        scoreAIPic.setBounds(250, 10,GameValues.ScoreImgX, GameValues.ScoreImgY );
        add(scoreAIPic);

        //Setting score number label
        score = new JLabel("0");
        //Setting size and position
        score.setBounds(100, 10, 80 ,50 );
        //Styling font of the score number
        score.setFont(new Font("Roboto", Font.BOLD, 40));
        //Setting foreground color
        score.setForeground(Color.white);
        //Adding to panel
        add(score);

        //Setting score number label
        scoreAI = new JLabel("0");
        //Setting size and position
        scoreAI.setBounds(300, 10, 80 ,50 );
        //Styling font of the score number
        scoreAI.setFont(new Font("Roboto", Font.BOLD, 40));
        //Setting foreground color
        scoreAI.setForeground(Color.white);
        //Adding to panel
        add(scoreAI);

        time.setBounds(400, 10, 150, 50);
        add(time);

        //Creating pause game Button
        pauseButton = new JButton("Pause");
        //Setting size and position of button
        pauseButton.setBounds(650, 10, 130 ,50);
        //Don't focus
        pauseButton.setFocusable(false);
        //Setting foreground color
        pauseButton.setForeground(Color.black);
        //Styling font
        pauseButton.setFont(new Font("Roboto", Font.PLAIN, 20));
        //Setting background color
        pauseButton.setBackground(new Color(52, 203, 52));
        //Adding to panel
        add(pauseButton);
    }

    //Reacting on click on button
    public void listenPauseButton(ActionListener event)
    {
        pauseButton.addActionListener(event);
    }
}
