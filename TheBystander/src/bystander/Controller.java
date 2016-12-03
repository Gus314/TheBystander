package bystander;

import java.util.ArrayList;
import java.util.Collection;

import bystander.exceptions.InvalidPathException;
import bystander.graphs.GridFactory;
import bystander.graphs.PathFinder;
import bystander.graphs.RuleChecker;
import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.IGrid;
import bystander.graphs.interfaces.IGridFactory;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.interfaces.IPathFinder;
import bystander.graphs.interfaces.IRuleChecker;

public class Controller
{
	public static void main(String[] args)
	{
        IGridFactory gridFactory = new GridFactory();
        IGrid grid = gridFactory.Construct();
        IPathFinder pathFinder = new PathFinder();
        Collection<IPath> completePaths = pathFinder.findPaths(grid);
        System.out.println("completePaths.size() == " + completePaths.size());
        int i = 0;
        for(IPath p: completePaths)
        {
        	i++;
        	/*System.out.println(p);*/
            Collection<IArea> areas = new ArrayList<IArea>();
        	try 
        	{
				areas.addAll(grid.determineAreas(p));
				IRuleChecker ruleChecker = new RuleChecker();	
				boolean isSolution = ruleChecker.isSolution(areas, p);
				System.out.println(isSolution ? "Solved." : "Failed Attempt " + i);
				if(isSolution)
				{
					break;
				}
				/*for(IArea area: areas)
				{
					System.out.println(area);;	
				}*/
			} 
        	catch (InvalidPathException e) 
        	{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }      
	}
}
