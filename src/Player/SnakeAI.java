package Player;

import GameObjects.BoardObjects;
import Values.GameValues;

import java.awt.*;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class SnakeAI extends SnakePlayer implements Runnable{

    double distanceFromFruit;
    private List<BoardObjects> mapObstacles;
    List<Double> distanceArr = new ArrayList<Double>();
    List<String> moveArr = new ArrayList<String>(){{
        add("X");
        add("X");
        add("X");
        add("X");
    }};

    public SnakeAI(List<BoardObjects> obstacles){
        mapObstacles = obstacles;
        System.out.println(mapObstacles);
        createSnake(GameValues.InitSnakeLength, 400, 400);
        loadImages("src/Assets/SnakeHeadAI.png", "src/Assets/SnakeBodyAI.png");
    }

    //The code executed by thread
    public Runnable createRunnable(int X, int Y)
    {
        Runnable tempRunnable = () -> move(X, Y);
        return tempRunnable;
    }

    //Directions selected
    //Used to pass information to snake in which direction it should move
    //Used also to check what was previous move of the snake
    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private boolean upDirection = false;
    private boolean downDirection = false;


    //Changing head position (snake move)
    public void goRight(){x[0] += GameValues.BoardUnitSize;}
    public void goLeft(){x[0] -= GameValues.BoardUnitSize;}
    public void goTop(){y[0] -= GameValues.BoardUnitSize;}
    public void goBot(){y[0] += GameValues.BoardUnitSize;}


    //Simulating snake move by using function which shows the path to fruit
    public void move(int fruitPosX, int fruitPosY){
        for(int i = getBodyLength(); i > 0; i--)
        {
            x[i] = x[(i-1)];
            y[i] = y[(i-1)];
        }
        createPath(fruitPosX,fruitPosY);

        if (leftDirection) {
            goLeft();
        }
        if (rightDirection) {
            goRight();
        }
        if (upDirection) {
            goTop();
        }
        if (downDirection) {
            goBot();
        }

    }

    //Checking snakeAI head collision with board obstacles
    public boolean ifCollision(List<BoardObjects> mapObstacles, int positionX, int positionY){
        Rectangle snake = new Rectangle(positionX,positionY,10,10);
        for(BoardObjects obstacle : mapObstacles)
        {
            Rectangle obstacleRect = obstacle.getRectProps();
            if(snake.intersects(obstacleRect))
            {
                return false;

            }
        }
        return true;
    }


    //Function controlling the snake
    //It's decides where snake should go and where it shouldn't
    public void createPath(int fruitPosX, int fruitPosY){
        double left = CountDistanceFromFood(fruitPosX,fruitPosY,getHeadX()-GameValues.BoardUnitSize,getHeadY());
        double right = CountDistanceFromFood(fruitPosX,fruitPosY,getHeadX()+GameValues.BoardUnitSize,getHeadY());
        double up = CountDistanceFromFood(fruitPosX,fruitPosY,getHeadX(),getHeadY()-GameValues.BoardUnitSize);
        double down = CountDistanceFromFood(fruitPosX,fruitPosY,getHeadX(),getHeadY()+GameValues.BoardUnitSize);


        //Checking if there is an collision in different possible destinations
        if(ifCollision(mapObstacles, getHeadX() - GameValues.BoardUnitSize, getHeadY()))
        {
            distanceArr.add(left);
        }
        if(ifCollision(mapObstacles, getHeadX() + GameValues.BoardUnitSize, getHeadY()))
        {
            distanceArr.add(right);
        }
        if(ifCollision(mapObstacles, getHeadX(), getHeadY() - GameValues.BoardUnitSize))
        {
            distanceArr.add(up);
        }
        if(ifCollision(mapObstacles, getHeadX(), getHeadY() + GameValues.BoardUnitSize))
        {
            distanceArr.add(down);
        }

        //Sorting array to know which destination creates the path which is the shortest one
        Collections.sort(distanceArr);

        //Checking if there is left destination in the array
        if(distanceArr.stream().anyMatch(x -> x == left))
        {
            //Checking if previous move was in the right direction
            if(rightDirection)
            {
                //If it was, we don't want to turn left
                distanceArr.remove(left);
            }
            //Else we check if left direction is the shortest one

            else if((distanceArr.indexOf(left) == 0) && !moveArr.get(moveArr.size() - 1).equals("Left"))
            {
                //If it is, we turn left
                leftDirection = true;
                rightDirection = false;
                upDirection = false;
                downDirection = false;
                moveArr.add("Left");
            }
        }
        if(distanceArr.stream().anyMatch(x -> x== right))
        {
            if(leftDirection)
            {
                distanceArr.remove(right);
            }

            else if((distanceArr.indexOf(right) == 0) && !moveArr.get(moveArr.size() - 1).equals("Right"))
            {
                leftDirection = false;
                rightDirection = true;
                upDirection = false;
                downDirection = false;
                moveArr.add("Right");
            }
        }
        if(distanceArr.stream().anyMatch(x -> x== up))
        {
            if(downDirection)
            {
                distanceArr.remove(up);
            }

            else if((distanceArr.indexOf(up) == 0) && !moveArr.get(moveArr.size() - 1).equals("Up"))
            {
                leftDirection = false;
                rightDirection = false;
                upDirection = true;
                downDirection = false;
                moveArr.add("Up");
            }
        }
        if(distanceArr.stream().anyMatch(x -> x== down))
        {
            System.out.println(moveArr);
            if(upDirection)
            {
                distanceArr.remove(down);
            }

            else if((distanceArr.indexOf(down) == 0) && !moveArr.get(moveArr.size() - 1).equals("Down"))
            {
                leftDirection = false;
                rightDirection = false;
                upDirection = false;
                downDirection = true;
                moveArr.add("Down");
            }
        }

        if(moveArr.size() >= 6)
        {
            moveArr.remove(0);
        }
        //Clearing array
        distanceArr.clear();
    }

    //Counting vector length from ai snake head to fruit
    public double CountDistanceFromFood(int foodX, int foodY, int headX, int headY){
        return distanceFromFruit = Math.sqrt(Math.pow((foodX-headX),2) + Math.pow((foodY-headY), 2));
    }
}
