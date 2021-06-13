package GameObjects;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GameTime extends JLabel {
    /**Initializing timer and time variables*/
    static long startTime;
    static long stopTime;
    static Timer timer;

    public GameTime()
    {
        /**Setting game timer in certain format*/
        timer = new Timer(1000, event -> setText(String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - startTime),
                TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - startTime)
                        ))));
        /**Showing the time as a text*/
        showTime();
    }

    private void showTime()
    {
        setText("00:00");
        setFont(new Font("Roboto", Font.BOLD, 40));
        setForeground(Color.WHITE);
    }


    /**Starting counting the time*/
    public static void startTimer(boolean ifGameRun)
    {
        if(!ifGameRun)
        {
            startTime = System.currentTimeMillis();
        }
        else{
            startTime = startTime + (System.currentTimeMillis()- stopTime);
        }
        timer.start();
    }

    /**Stopping timer*/
    public static void stopTimer()
    {
        stopTime = System.currentTimeMillis();
        timer.stop();
    }
}
