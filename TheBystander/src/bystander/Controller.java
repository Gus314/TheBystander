package bystander;

import bystander.graphs.GridFactory;
import bystander.graphs.PathFinder;
import bystander.graphs.interfaces.IGrid;
import bystander.graphs.interfaces.IGridFactory;
import bystander.graphs.interfaces.IPathFinder;

public class Controller
{
	public static void main(String[] args)
	{
        IGridFactory gridFactory = new GridFactory();
        IGrid grid = gridFactory.Construct();
        IPathFinder pathFinder = new PathFinder();
        pathFinder.findPath(grid);
	}
}
