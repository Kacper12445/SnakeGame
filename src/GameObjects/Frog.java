package GameObjects;

import Values.GameValues;

import java.awt.*;
import java.util.List;

public class Frog extends FruitGenerator{

    //Loading image in constructor
    public Frog()
    {
        loadImage("src/Assets/BoardFrog.png");
    }

    //Functions which are used to move frog
    //Move is considered as a position change
    private void goLeft(){
        positionX -= 10;
    }
    private void goRight(){
        positionX += 10;
    }
    private void goTop(){
        positionY += 10;
    }
    private void goBot(){
        positionY -= 10;
    }

    //Choosing random location for frog when game starts
    //Position doesn't locate the object in the place where obstacle is located
    @Override
    public void putOnBoard(List<BoardObjects> obstacles)
    {
        positionX = randNumber(16, 55);
        positionY = randNumber(16, 55);
        //checking if there is collision with another obstacle
        while(checkObstacleCollision(obstacles))
        {
            positionX = randNumber(25, 64);
            positionY = randNumber(25, 54);
        }
    }

    //Starting movement of the snake using Runnable interface
    public Runnable createMovement(Rectangle snakeRect)
    {
        Runnable tmpRunnable = new Runnable() {
            @Override
            public void run() {
                move(snakeRect);
            }
        };
        return tmpRunnable;
    }

    //Algorithm which chose the way how frog should move
    public void move(Rectangle snakeRectangle)
    {
        int randomNumber = randNumber(0, 3)/10;
        int checkRand = randNumber(0, 2)/10;
        if(checkRand % 2 == 1)
        {
            return;
        }
        chaseFromDown(snakeRectangle, randomNumber);
        chaseFromUp(snakeRectangle, randomNumber);
    }

//Checking if the snake is coming from bot then its running in all ways but except bot
// As a warn area there is set rectangle 300 width and 150 height
//The frog move is random but there is checked a place where it is in case frog would like to run from the board
    private void chaseFromDown(Rectangle snakeRectangle, int randomNumber)
    {
        if(snakeRectangle.intersects(positionX, positionY + 100, 300, 150))
        {
            while(getPosX() < GameValues.BoardWidth-50 && getPosX() > 20 && getPosY() > 20 && getPosY() < GameValues.BoardHeight - 20)
            {
                if(randomNumber <= 1 && getPosY() -20 > 0)
                {
                        goTop();
                }

                else if(randomNumber == 2 && getPosX()+20 < GameValues.BoardWidth)
                {
                    goRight();
                }

                else if(randomNumber == 3 && getPosX()-20  > 0)
                    goLeft();
            }

        }
    }

//While snake comes from left ,Top -> frog jumps in random place except top
    // As a warn area there is set rectangle 300 width and 150 height
    //The frog move is random but there is checked a place where it is in case frog would like to run from the board
    private void chaseFromUp(Rectangle snakeRectangle, int randomNumber)
    {
        if(snakeRectangle.intersects(positionX, positionY-150, 300, 150)) {

            if(randomNumber <= 1  && getPosY() +20 < GameValues.BoardHeight)
            {
                goBot();
            }
            else if (randomNumber == 2 && getPosX()+20 < GameValues.BoardWidth)
                goRight();
            else if (randomNumber == 3 && getPosX()-20 > 0)
                goLeft();
        }
    }
}
