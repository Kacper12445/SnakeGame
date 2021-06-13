package Player;

import GamePanels.BoardPanel;
import Values.GameValues;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SnakePlayer implements Runnable{
    public final int[] x = new int[GameValues.BoardWidth];
    public final int[] y = new int[GameValues.BoardHeight];
    private Image headImage;
    private Image bodyImage;
    private int bodyLength;

    private final SnakeControls snakeControl = new SnakeControls();
    private final PropertyChangeSupport snakeChanges;

    public SnakePlayer()
    {
        snakeChanges = new PropertyChangeSupport(this);
        createSnake(GameValues.InitSnakeLength, 50, 50);
        loadImages("src/Assets/SnakeHead.png", "src/Assets/SnakeBody.png");
    }

    //Give access to position X of snake head
    public int getHeadX()
    {
        return x[0];
    }
    //Give access to position Y of snake head
    public int getHeadY()
    {
        return y[0];
    }

    //Returning length of the snake
    public int getBodyLength()
    {
        return bodyLength;
    }

    //Returning head position
    public Rectangle getHeadPos()
    {
        return new Rectangle(getHeadX(), getHeadY(), GameValues.SnakeElementSize, GameValues.SnakeElementSize);
    }

    public SnakeControls getSnakeEventControl(){
        return snakeControl;
    }

    //Loading snake body as a img
    public void loadImages(String headImgPath, String bodyImgPath)
    {
        ImageIcon tempBody = new ImageIcon(bodyImgPath);
        bodyImage = tempBody.getImage();

        ImageIcon tempHead = new ImageIcon(headImgPath);
        headImage = tempHead.getImage();
    }

    //Initializing snake
    public void createSnake(int snakeLength, int coordX, int coordY)
    {
        bodyLength = snakeLength;
        for(int i = 0; i < bodyLength; i++)
        {
            x[i] = coordX - i * snakeLength;
            y[i] = coordY;
        }
    }

    //Reports an bound property update to listeners that have been registered to track updates of all properties or a property with the specified name.
    public void setBodyLength(int bodyPartNumberParam)
    {
        int oldSnakeLength = this.bodyLength;
        this.bodyLength = bodyPartNumberParam;
        snakeChanges.firePropertyChange("bodyLength", oldSnakeLength, this.bodyLength);
    }

    //Checking if snake touches board border
    public boolean checkBoardCollision()
    {
        for(int i = bodyLength; i > 0; i--)
        {
            if((i > 4) && (getHeadX() == x[i]) && (getHeadY() == y[i]))
            {
                return false;
            }
        }
        if(getHeadY() >= GameValues.BoardHeight - GameValues.ScorePanelHeight){
            return false;
        }
        if (getHeadY() < 0) {
            return false;
        }
        if(getHeadX() >= GameValues.BoardWidth)
        {
            return false;
        }
        if(getHeadX() < 0)
        {
            return false;
        }
        return true;
    }


    //Simulating and controlling snake by player
    @Override
    public void run()
    {
        for(int i = bodyLength; i > 0; i--)
        {
            x[i] = x[(i-1)];
            y[i] = y[(i-1)];
        }
        if(snakeControl.getLeft())
        {
            x[0] -= GameValues.BodyPartSize;
        }
        if(snakeControl.getRight())
        {
            x[0] += GameValues.BodyPartSize;
        }
        if(snakeControl.getTop())
        {
            y[0] -= GameValues.BodyPartSize;
        }
        if(snakeControl.getBot())
        {
            y[0] += GameValues.BodyPartSize;
        }
    }


    //Drawing player snake
    public void drawSnake(Graphics g, BoardPanel board)
    {
        //Drawing snake head
        g.drawImage(headImage, getHeadX(), getHeadY(), board);
        //Executing for every element of snake body except head
        for(int i = 1; i < bodyLength; i++)
        {
            //drawing snake body
            g.drawImage(bodyImage, x[i], y[i], board);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener bean)
    {
        snakeChanges.addPropertyChangeListener(bean);
    }
}
