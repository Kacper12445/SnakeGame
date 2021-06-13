package GameObjects;

import GamePanels.BoardPanel;

import javax.swing.*;
import java.awt.*;

public class BoardObjects extends JPanel{
    /**Variable of board object position*/
    protected int positionX;
    protected int positionY;

    /**Initial size of board object*/
    public int objWidth = 10;
    public int objHeight = 10;

    /**Image variable*/
    protected Image image;

    /**Constructor*/
    public BoardObjects(){}

    /**Function which returns (gives access) to value of positionX*/
    public int getPosX(){
        return positionX;
    }
    /**Function which returns (gives access) to value of positionY*/
    public int getPosY(){
        return positionY;
    }

    /**Function which loads image and assign to class variable*/
    public void loadImage(String path)
    {
        ImageIcon loadedImage = new ImageIcon(path);
        image = loadedImage.getImage();
    }


    /**Drawing object on board*/
    public void DrawObject(Graphics g, BoardPanel board)
    {
        /**
        image - image which we want to draw
        getX - X position where we want to draw it
        getY - Y position where we want to draw it
        board - object to be notified as more of the image is converted
         */

        g.drawImage(image, getPosX(), getPosY(), board);
    }

    /**Method returns random number from given range ->used to set location of board objects
    Random number from min + value from min to max*/
    public int randNumber(int min, int max)
    {
        return (min + (int) (Math.random() * ((max - min) + 1))) * 10;
    }


    /**Function which return object position and size as a rectangle*/
    public Rectangle getRectProps()
    {
        return new Rectangle(positionX, positionY, objWidth, objHeight);
    }


}
