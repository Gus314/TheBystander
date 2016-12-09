package main;

import main.enums.Colour;
import main.exceptions.InvalidGridException;
import main.exceptions.InvalidPathException;
import main.graphs.DecorationSpecification;
import main.graphs.PathFinder;
import main.graphs.decorations.Mandatory;
import main.graphs.faces.SquareFace;
import main.graphs.faces.interfaces.IFace;
import main.graphs.grids.GridFactory;
import main.graphs.grids.IGrid;
import main.graphs.grids.IGridFactory;
import main.graphs.grids.Position;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IPath;
import main.graphs.interfaces.IPathFinder;

import java.util.*;

public class Controller
{
    private static List<SolverRunnable> retrieveData() {
        // Currently assume 2-4 areas, which should cover most puzzles.
        List<SolverRunnable> result = new ArrayList<SolverRunnable>();
        for (int i = 2; i <= 4; i++) {
            Map<IPath, Collection<IArea>> partial = Repository.readData(i);
            result.add(new SolverRunnable(partial));
        }

        return result;
    }

    private static void computeData(IGrid grid) {
        System.out.println("Could not load paths, this may take some time (approximately 20 minutes).");
        	IPathFinder pathFinder = new PathFinder();
        Collection<IPath> completePaths = pathFinder.findPaths(grid);
        Map<IPath, Collection<IArea>> data = new HashMap<IPath, Collection<IArea>>();

        	for(IPath p: completePaths) {
                Collection<IArea> areas = new ArrayList<IArea>();
            	try 
            	{
    				areas.addAll(grid.determineAreas(p));
    				data.put(p, areas);
    			} 
            	catch (InvalidPathException e) 
            	{
    				System.out.println(e.getMessage());
    			}          	
            }  
        	
        	Repository.writeData(data);
    }

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

    private static void solvePuzzle(IGrid grid) {
        List<SolverRunnable> solvers = retrieveData();
        List<Thread> solverThreads = new ArrayList<Thread>();
        for (SolverRunnable solver : solvers) {
            solver.setGrid(grid);
            Thread solverThread = new Thread(solver);
            solverThreads.add(solverThread);
            solverThread.run();
        }
        int threadCount = solverThreads.size();
        int finishedCount = 0;
        IPath solution = null;

        while ((finishedCount < threadCount) && (solution == null)) {
            finishedCount = 0;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < solverThreads.size(); i++) {
                if (!solverThreads.get(i).isAlive()) {
                    if (solvers.get(i).getResult() != null) {
                        solution = solvers.get(i).getResult();
                        break;
                    }
                    finishedCount++;
                }
            }
        }

        if (solution != null) {
            System.out.println("Solution found.");
            System.out.println(solution);
        } else {
            System.out.println("Failed to find solution.");
        }
    }

	public static void main(String[] args)
	{
        // This class now requires considerable refactoring but is currently experimental.
        IGrid grid = getExample();

        //IGridDrawer gridDrawer = new GridDrawer(grid);
        //gridDrawer.drawGrid();

        //computeData(grid); // Comment out either this or solvePuzzle.
        solvePuzzle(grid);
    }
}

