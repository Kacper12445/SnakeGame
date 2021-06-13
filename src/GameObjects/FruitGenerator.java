package GameObjects;

import Player.SnakeAI;
import Player.SnakePlayer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FruitGenerator extends BoardObjects{

    //Loading fruit as a image
    public FruitGenerator(){
        loadImage("src/Assets/BoardFruit.png");
    }


    @Override
    public void loadImage(String path)
    {
        ImageIcon loadedImage = new ImageIcon(path);
        image = loadedImage.getImage().getScaledInstance(10,10,Image.SCALE_DEFAULT);
    }

    //Checking collision with obstacles
    public boolean checkObstacleCollision(List<BoardObjects> obstacles)
    {
        for(BoardObjects obstacle: obstacles)
        {
            Rectangle obstacleRect = obstacle.getRectProps();
            if(obstacleRect.intersects(this.getRectProps()))
            {
                return true;
            }
        }
        return false;
    }

    //Function which locates objects on the board with in random area
    public void putOnBoard(List<BoardObjects> obstacles)
    {
        positionX = randNumber(0, 61);
        positionY = randNumber(0, 61);

        //Setting position only when it's not in a collision with obstacle
        while(checkObstacleCollision(obstacles))
        {
            positionX = randNumber(0, 61);
            positionY = randNumber(0, 61);
        }
    }


    //Function which manages the case when snake eats the fruit
    public void fruitEaten(SnakePlayer snake, SnakeAI snakeAI, List<BoardObjects> obstacles)
    {
        //Checking if player snake head is in the same position as the fruit is.
        if((snake.getHeadX() == this.getPosX()) && (snake.getHeadY() == this.getPosY()))
        {
            // then snake grows
            snake.setBodyLength(snake.getBodyLength() + 1);
            //Generating another one in random place
            this.putOnBoard(obstacles);
        }
        //If AI snake eats the fruit, generating another one
        else if(((snakeAI.getHeadX() == this.getPosX()) && (snakeAI.getHeadY() == this.getPosY())))
        {
            snakeAI.setBodyLength(snakeAI.getBodyLength() + 1);
            this.putOnBoard(obstacles);
        }
    }

}
