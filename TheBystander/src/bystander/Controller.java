package bystander;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import bystander.enums.Colour;
import bystander.exceptions.InvalidGridException;
import bystander.exceptions.InvalidPathException;
import bystander.graphs.DecorationSpecification;
import bystander.graphs.PathFinder;
import bystander.graphs.decorations.Mandatory;
import bystander.graphs.faces.ColouredFace;
import bystander.graphs.faces.interfaces.IFace;
import bystander.graphs.grids.GridFactory;
import bystander.graphs.grids.IGrid;
import bystander.graphs.grids.IGridFactory;
import bystander.graphs.grids.Position;
import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.interfaces.IPathFinder;
import bystander.ui.GridDrawer;

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
		IFace blackFace = new ColouredFace(Colour.BLACK);
		specialFaces.put(blackFace, new Position(2, 1));
		IFace whiteFace = new ColouredFace(Colour.WHITE);
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

