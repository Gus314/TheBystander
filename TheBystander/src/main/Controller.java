package main;

import main.control.GameManager;
import main.control.interfaces.IGameManager;
import main.enums.Colour;
import main.enums.StartOrExit;
import main.exceptions.InvalidEdgeException;
import main.exceptions.InvalidGridException;
import main.exceptions.InvalidPathException;
import main.graphs.DecorationSpecification;
import main.graphs.decorations.Mandatory;
import main.graphs.faces.SquareFace;
import main.graphs.faces.StarFace;
import main.graphs.faces.interfaces.IFace;
import main.graphs.grids.GridFactory;
import main.graphs.grids.IGrid;
import main.graphs.grids.IGridFactory;
import main.graphs.grids.Position;
import main.graphs.interfaces.IEdge;
import main.graphs.interfaces.IVertex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Controller
{
    private static IGrid getExample() {
        IGridFactory gridFactory = new GridFactory();

        Map<IFace, Position> specialFaces = new HashMap<IFace, Position>();
        IFace blackFace = new SquareFace(Colour.BLACK);
        specialFaces.put(blackFace, new Position(2, 1));
        IFace whiteFace = new SquareFace(Colour.WHITE);
        specialFaces.put(whiteFace, new Position(3, 4));
        Collection<DecorationSpecification> decorationSpecifications = new ArrayList<DecorationSpecification>();
        DecorationSpecification blackSpot = new DecorationSpecification(new Position(2, 3), new Position(2, 4), new Mandatory());
        decorationSpecifications.add(blackSpot);
        IFace greenStar1 = new StarFace(Colour.GREEN);
        IFace greenStar2 = new StarFace(Colour.GREEN);
        IFace greenStar3 = new StarFace(Colour.GREEN);
        IFace greenStar4 = new StarFace(Colour.GREEN);
        specialFaces.put(greenStar1, new Position(0, 1));
        specialFaces.put(greenStar2, new Position(0, 2));
        specialFaces.put(greenStar3, new Position(3, 1));
        specialFaces.put(greenStar4, new Position(3, 2));

        Collection<Position> startPositions = new ArrayList<Position>();
        startPositions.add(new Position(0, 0));
        //startPositions.add(new Position(7,0));


        Collection<Position> exitPositions = new ArrayList<Position>();
        exitPositions.add(new Position(5, 5));
        //exitPositions.add(new Position(0,7));

        IGrid grid = null;
        try {
            grid = gridFactory.Construct(6, 6, startPositions, exitPositions, specialFaces, decorationSpecifications);
        } catch (InvalidGridException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        return grid;
    }

    private static IEdge startExample(IGrid grid) {
        IVertex vertex = null;
        IVertex[][] vertices = grid.getVertices();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (StartOrExit.START == vertices[i][j].getStartOrExit()) {
                    vertex = vertices[i][j];
                    break;
                }
            }
        }

        for (IEdge edge : grid.getEdges()) {
            if (vertex == edge.getSource()) {
                return edge;
            }
        }

        return null;
    }

    private static boolean moveRight(IGameManager gameManager) {
        boolean success = true;
        try {
            IEdge rightEdge = gameManager.rightEdge();
            gameManager.traverseEdge(rightEdge);
        } catch (InvalidEdgeException e) {
            success = false;
        }

        return success;
    }

    private static boolean moveLeft(IGameManager gameManager) {
        boolean success = true;
        try {
            IEdge leftEdge = gameManager.leftEdge();
            gameManager.traverseEdge(leftEdge);
        } catch (InvalidEdgeException e) {
            success = false;
        }

        return success;
    }

    private static boolean moveUp(IGameManager gameManager) {
        boolean success = true;
        try {
            IEdge upEdge = gameManager.upEdge();
            gameManager.traverseEdge(upEdge);
        } catch (InvalidEdgeException e) {
            success = false;
        }

        return success;
    }

    private static boolean moveDown(IGameManager gameManager) {
        boolean success = true;
        try {
            IEdge downEdge = gameManager.downEdge();
            gameManager.traverseEdge(downEdge);
        } catch (InvalidEdgeException e) {
            success = false;
        }

        return success;
    }

	public static void main(String[] args)
	{
        // This class now requires considerable refactoring but is currently experimental.
        IGrid grid = getExample();
        IGameManager gameManager = new GameManager(grid);
        gameManager.start(startExample(grid));
        while (moveRight(gameManager)) {
        }
        while (moveDown(gameManager)) {
        }
        try {
            gameManager.checkSolution();
        } catch (InvalidPathException ipex) {
            System.out.println(ipex.getMessage());
        }

        //IGridDrawer gridDrawer = new GridDrawer(grid);
        //gridDrawer.drawGrid();
    }
}

