package GameObjects;

public class VerticalObstacle extends BoardObjects{
    //Vertical obstacle counter
    private static int obstacleCounter;

    public VerticalObstacle(){
        obstacleCounter++;
        /**loading object as a image*/
        loadImage("src/Assets/VerticalObstacle.png");
        /**Creating obstacle with generated position and size*/
        createObstacle();
    }
    private void createObstacle()
    {
        /**Setting original size to created obstacle*/
        objWidth = image.getWidth(null);
        objHeight = image.getHeight(null);

        /**Setting random position to obstacle*/
        positionY = randNumber(5, 30);
        positionX = (obstacleCounter % 2 == 0) ? randNumber(5, 25) : randNumber(45,60);
    }
}
