package Values;

//There are values which are used in the game to style some panels etc

public class GameValues {
    private GameValues(){}
    public static final int BoardWidth = 800;
    public static final int BoardHeight = 800;
    public static final int SnakeElementSize = 10;
    public static final int InitSnakeLength = 3;
    public static final int InitSnakeAILength = 3;
    public static final int BodyPartSize = 10;

    public static final int ScorePanelHeight = ((BoardHeight/12 + 5)/10)*10; // Calculating score panel height
    public static final int BoardUnitSize = 10;
    public static final int TimerDelay = 100;

    public static final int LogoX = 350;
    public static final int LogoY = 350;

    public static final int CupX = 200;
    public static final int CupY = 200;

    public static final int RipSnakeX = 375;
    public static final int RipSnakeY = 275;

    public static final int ScoreImgX = 50;
    public static final int ScoreImgY = 50;

    public static final int ControllerImgX = 150;
    public static final int ControllerImgY = 100;

    public static final int CupImgX  = 200;
    public static final int CupImgY  = 200;

}
