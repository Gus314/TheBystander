package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
import main.ui.GridDrawer;

public class Controller
{
	private static Map<IPath, Collection<IArea>> retrieveData(IGrid grid)
	{        
        Map<IPath, Collection<IArea>> data = Repository.readData();
        Collection<IPath> completePaths = (data == null) ? null : data.keySet();
        if(completePaths == null)
        {
        	System.out.println("Could not load paths, this may take some time (approximately 20 minutes).");
        	IPathFinder pathFinder = new PathFinder();
        	completePaths = pathFinder.findPaths(grid);
        	data = new HashMap<IPath, Collection<IArea>>();
        	for(IPath p: completePaths)
            {      	
            	/*System.out.println(p);*/
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
        else
        {
        	System.out.println("Successfully loaded paths.");
        }
        return data;
	}
	
	public static void main(String[] args)
	{
		IGridFactory gridFactory = new GridFactory();
		
		Map<IFace, Position> specialFaces = new HashMap<IFace, Position>();
		IFace blackFace = new SquareFace(Colour.BLACK);
		specialFaces.put(blackFace, new Position(2, 1));
		IFace whiteFace = new SquareFace(Colour.WHITE);
		specialFaces.put(whiteFace, new Position(3,4));
		Collection<DecorationSpecification> decorationSpecifications = new ArrayList<DecorationSpecification>();
		DecorationSpecification blackSpot = new DecorationSpecification(new Position(2, 3), new Position(2, 4), new Mandatory());
		decorationSpecifications.add(blackSpot);	
		
		Collection<Position> startPositions = new ArrayList<Position>();
		startPositions.add(new Position(0,0));
		//startPositions.add(new Position(7,0));

		
		Collection<Position> exitPositions = new ArrayList<Position>();
		exitPositions.add(new Position(7,7));
		//exitPositions.add(new Position(0,7));	
		
        IGrid grid = null;
		try 
		{
			grid = gridFactory.Construct(8, 8, startPositions, exitPositions, specialFaces, decorationSpecifications);
		}
		catch (InvalidGridException e) 
		{
			System.out.println(e.getMessage());
			System.exit(-1);
		}

        GridDrawer.drawGrid(grid);

        /*
		Map<IPath, Collection<IArea>> data = retrieveData(grid);
     	System.out.println("Total number of paths being considered:" + data.keySet().size());
 		IRuleChecker ruleChecker = new RuleChecker(grid);
 		
 		boolean succeeded = false;
     	for(IPath path: data.keySet())
     	{
     		if(ruleChecker.isSolution(data.get(path), path))
     		{
     			succeeded = true;
     			System.out.println("Solved.");
     			System.out.println(path);
     			break;
     		}
     	}
     	
     	if(!succeeded)
     	{
     		System.out.println("Failed to find a solution.");
     	}
        System.out.println("Finished.");
        */
	}
}

