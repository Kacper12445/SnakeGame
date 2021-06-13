package GamePanels;

import GameObjects.*;
import GameScreens.GameManager;
import Player.SnakeAI;
import Player.SnakePlayer;
import ThreadManagement.ThreadPool;
import Values.GameValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;

public class BoardPanel extends JPanel implements ActionListener {

    private final ThreadPool threadPool;
    private final Timer timer;
    private final FruitGenerator fruit;
    private final Frog frog;
    private final SnakePlayer player;
    private final SnakeAI snakeAI;
    private final MapBuilder map;
    private final KeyAdapter startAdapter;
    private final KeyAdapter resumeAdapter;
    private boolean gameIsRunning;

    private JLabel controllerImgLabel;
    private JLabel controllerText;
    private JLabel pauseHint;
    private JLabel pauseText;
    private Image controllerImg;

    public BoardPanel(){
        //Creating objects
        fruit = new FruitGenerator();
        frog = new Frog();
        map = new MapBuilder();

        player = new SnakePlayer();
        snakeAI = new SnakeAI(map.getObstacles());

        timer = new Timer(GameValues.TimerDelay, this);
        threadPool = new ThreadPool(5);


        //Starting the game when arrow keys are clicked
        startAdapter = new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {

                int event = e.getKeyCode();
                if(event == KeyEvent.VK_UP || event == KeyEvent.VK_DOWN || event == KeyEvent.VK_LEFT || event == KeyEvent.VK_RIGHT)
                {
                    removeKeyListener(startAdapter);
                    remove(controllerImgLabel);
                    remove(controllerText);
                    addKeyListener(player.getSnakeEventControl());
                    fruit.putOnBoard(map.getObstacles());
                    frog.putOnBoard(map.getObstacles());

                    timer.start();
                    GameTime.startTimer(false);
                    gameIsRunning = true;
                }
            }
        };

        //Resuming the game (pause situation, to resume we need to click "r"
        resumeAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e)
            {
                int event = e.getKeyCode();
                if(event == KeyEvent.VK_R)
                {
                    resumeGame();
                    addKeyListener(player.getSnakeEventControl());
                    removeKeyListener(resumeAdapter);
                }
            }
        };
        setFocusable(true);
        setBackground(Color.black);
        setLayout(null);
        loadImages();
        buildScreen();
    }

    public int getScore()
    {
        return  player.getBodyLength() - GameValues.InitSnakeLength;
    }
    public int getAIScore()
    {
        return snakeAI.getBodyLength() - GameValues.InitSnakeAILength;
    }

    private void loadImages(){
        ImageIcon tempControllerImg = new ImageIcon("src/Assets/ArrowKeys.png");
        controllerImg = tempControllerImg.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT);
    }

    private void buildScreen()
    {
        controllerImgLabel = new JLabel(new ImageIcon(controllerImg));
        controllerImgLabel.setBounds((GameValues.BoardWidth/2)-GameValues.ControllerImgX/2, 300, GameValues.ControllerImgX, GameValues.ControllerImgY);
        add(controllerImgLabel);

        controllerText = new JLabel("Press any arrow key to start");
        controllerText.setBounds(200,425, 700, 40);
        controllerText.setFont(new Font("Roboto", Font.BOLD, 30));
        controllerText.setForeground(Color.white);
        add(controllerText);
    }

    //Drawing objects on the map
    private void drawObjects(Graphics g)
    {
        if(gameIsRunning)
        {
            map.drawBoard(g, this);
            fruit.DrawObject(g, this);
            frog.DrawObject(g, this);

            snakeAI.drawSnake(g, this);
            player.drawSnake(g, this);
            Toolkit.getDefaultToolkit().sync();
        }
    }
    //Checking snake collision with the obstacles and with another snake head
    //If conditions retunr true -> there is a collision
    //The game is lost
    private void checkCollisions()
    {
        Rectangle snake = player.getHeadPos();
        for(BoardObjects obstacle : map.getObstacles())
        {
            Rectangle obstacleRect = obstacle.getRectProps();
            if(snake.intersects(obstacleRect) || snake.intersects(snakeAI.getHeadPos()))
            {
                gameIsRunning = false;
            }
        }
    }

    //Game over text
    private void gameOver(){
        JLabel gameOverText = new JLabel("Game Over");
        gameOverText.setBounds(220, 225, 700, 80);
        gameOverText.setFont(new Font("Roboto", Font.BOLD, 70));
        gameOverText.setForeground(Color.white);
        add(gameOverText);

        JLabel scoreText = new JLabel(("Your Score: "+ getScore()));
        scoreText.setBounds(320, 325, 700, 30);
        scoreText.setFont(new Font("Roboto", Font.BOLD, 30));
        scoreText.setForeground(Color.white);
        add(scoreText);

        JLabel scoreAIText = new JLabel(("AI Snake score: "+ getAIScore()));
        scoreAIText.setBounds(290, 365, 700, 30);
        scoreAIText.setFont(new Font("Roboto", Font.BOLD, 30));
        scoreAIText.setForeground(Color.white);
        add(scoreAIText);

        //After loosing the game, we can see points and game over text for 1.5 sec
        Timer gameOverTimer = new Timer(1500, null);
        gameOverTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOverTimer.stop();
                GameManager.saveScore(getScore());
                GameManager.goToGameOverScreen();
            }
        });
        gameOverTimer.start();
    }

    //Pausing the game
    public void pauseGame(){
        if(!timer.isRunning())
        {
            return;
        }
        //Stopping the time while it's paused
        timer.stop();
        GameTime.stopTimer();
        removeKeyListener(player.getSnakeEventControl());

        pauseText = new JLabel("Game Paused");
        pauseText.setBounds(200, 200, 600, 60);
        pauseText.setFont(new Font("Roboto", Font.BOLD, 50));
        pauseText.setForeground(Color.BLUE);
        add(pauseText);

        pauseHint = new JLabel("Press r key to resume");
        pauseHint.setBounds(225,280,700,30);
        pauseHint.setFont(new Font("Roboto", Font.BOLD, 30));
        pauseHint.setForeground(Color.BLUE);
        add(pauseHint);

        addKeyListener(resumeAdapter);
        repaint();
    }


    //Resuming the game
    public void resumeGame(){
        //Starting stopped time
        timer.start();
        GameTime.startTimer(true);
        //Removing some pause game objects from the screen
        remove(pauseHint);
        remove(pauseText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameIsRunning)
        {
            fruit.fruitEaten(player,snakeAI, map.getObstacles());
            frog.fruitEaten(player,snakeAI ,map.getObstacles());
            threadPool.startThreadTask(frog.createMovement(player.getHeadPos()));
            threadPool.startThreadTask(snakeAI.createRunnable(fruit.getPosX(), fruit.getPosY()));
            threadPool.startThreadTask(player);
            gameIsRunning = player.checkBoardCollision();
            this.checkCollisions();
            if(!gameIsRunning)
            {
                timer.stop();
                GameTime.stopTimer();
                gameOver();
            }
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawObjects(g);
    }

    public void addSnakeChangeListener(PropertyChangeListener listener)
    {
        player.addPropertyChangeListener(listener);
        snakeAI.addPropertyChangeListener(listener);
    }

    public void initStartAdapter(){
        addKeyListener(startAdapter);
    }

}
