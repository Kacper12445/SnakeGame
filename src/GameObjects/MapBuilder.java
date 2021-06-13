package GameObjects;

import GamePanels.BoardPanel;
import Values.GameValues;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapBuilder {

    /**List of obstacles*/
    private ArrayList<BoardObjects> obstacleList;

    public MapBuilder(){
        obstacleList = new ArrayList<BoardObjects>();
        buildMap();
    }

    public List<BoardObjects> getObstacles()
    {
        return obstacleList;
    }

    private void buildMap()
    {
        /**Creating one horizontal obstacle on the board*/
        BoardObjects mapObstacle = new HorizontalObstacle();
        obstacleList.add(mapObstacle);

        /**Creating two vertical obstacles on the board*/
        for(int i = 0 ; i < 2; i++)
        {
            mapObstacle = new VerticalObstacle();
            obstacleList.add(mapObstacle);
        }
    }

    /**Drawing the game board with board objects*/
    public void drawBoard(Graphics g, BoardPanel board)
    {
        /**Setting the grid on the board to simplify the game for user and to showing the grids which are used to move snake*/
        for (int i = 0; i < GameValues.BoardHeight /  GameValues.BoardUnitSize; i++) {
            g.drawLine(i *  GameValues.BoardUnitSize, 0, i *  GameValues.BoardUnitSize, GameValues.BoardHeight);
            g.drawLine(0, i *  GameValues.BoardUnitSize, GameValues.BoardWidth, i *  GameValues.BoardUnitSize);
        }
        /**Drawing obstacles from the array*/
        for(BoardObjects obstacle: obstacleList)
        {
            obstacle.DrawObject(g, board);
        }
    }
}
