package GameObjects;

import java.awt.*;

public class HorizontalObstacle extends BoardObjects{
    HorizontalObstacle(){
        //Loading obstacle as a image
        loadImage("src/Assets/HorizontalObstacle.png");
        //setting size and position of the obstacle
        createObstacle();
    }

    private void createObstacle()
    {
        //Assigning original picture width
        objWidth = image.getWidth(null);
        //Assigning original picture height
        objHeight = image.getHeight(null);
        //Setting random position to created object
        positionX = randNumber(5, 40);
        positionY = randNumber(45,60);
    }
}
