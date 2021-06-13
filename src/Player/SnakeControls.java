package Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeControls extends KeyAdapter {
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;

    /**Getting access to variables*/
    public boolean getLeft()
    {
        return left;
    }
    public boolean getRight()
    {
        return right;
    }
    public boolean getTop()
    {
        return up;
    }
    public boolean getBot()
    {
        return down;
    }

    /**Controlling the snake depending on clicked array key*/
    @Override
    public void keyPressed(KeyEvent event)
    {
        int key = event.getKeyCode();
        if((key == KeyEvent.VK_LEFT) && (!right))
        {
            left = true;
            up = false;
            down = false;
        }
        if((key == KeyEvent.VK_RIGHT) && (!left))
        {
            right = true;
            up = false;
            down = false;
        }
        if((key == KeyEvent.VK_UP) && (!down))
        {
            up = true;
            right = false;
            left = false;
        }
        if((key == KeyEvent.VK_DOWN) && (!up))
        {
            down = true;
            right = false;
            left = false;
        }
    }

}
