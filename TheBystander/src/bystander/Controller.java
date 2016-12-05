package bystander;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import bystander.exceptions.InvalidPathException;
import bystander.graphs.GridFactory;
import bystander.graphs.PathFinder;
import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.IGrid;
import bystander.graphs.interfaces.IGridFactory;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.interfaces.IPathFinder;
import bystander.graphs.rules.RuleChecker;
import bystander.graphs.rules.interfaces.IRuleChecker;

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
    				// TODO Auto-generated catch block
    				e.printStackTrace();
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
        IGrid grid = gridFactory.Construct();
        
		Map<IPath, Collection<IArea>> data = retrieveData(grid);
     	System.out.println("Total number of paths being considered:" + data.keySet().size());
 		IRuleChecker ruleChecker = new RuleChecker(grid);
 		
     	for(IPath path: data.keySet())
     	{
     		if(ruleChecker.isSolution(data.get(path), path))
     		{
     			System.out.println("Solved.");
     			System.out.println(path);
     			break;
     		}
     	}
     	
        System.out.println("Finished.");
	}
}

