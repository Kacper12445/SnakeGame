package GameScreens;


import GamePanels.BoardPanel;
import GamePanels.ScorePanel;
import Values.GameValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameScreen extends JPanel {

    public final ScorePanel scorePanel;
    public final BoardPanel snakePanel;
    public final GridBagConstraints gbc;

    public GameScreen(){
        scorePanel = new ScorePanel();
        snakePanel = new BoardPanel();
        gbc = new GridBagConstraints();

        snakePanel.addSnakeChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                scorePanel.getScore().setText(String.valueOf(snakePanel.getScore()));
                scorePanel.getScoreAI().setText(String.valueOf(snakePanel.getAIScore()));
            }
        });

        setLayout(new GridBagLayout());
        initScorePanel();
        initSnakePanel();
    }

    //Initializing score panel
    private void initScorePanel()
    {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        //Setting score panel height
        gbc.ipady = GameValues.ScorePanelHeight;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //If we click pause button , we call pauseGame() which pauses te game
        scorePanel.listenPauseButton(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event) {
                snakePanel.pauseGame();
            }
        });

        //Adding to frame
        add(scorePanel, gbc);

    }

    private void initSnakePanel()
    {
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.BOTH;
        //Adding to frame
        add(snakePanel, gbc);
        snakePanel.initStartAdapter();
    }

    //Setting focus on snake panel
    public void setSnakePanelFocus(){
        snakePanel.requestFocusInWindow();
    }


}
