package main.graphs.rules.interfaces;

import java.util.Collection;

import main.graphs.grids.IGrid;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IPath;

public interface IRule 
{
	//Note that the number of failures must be returned rather than a boolean success/failure status to allow the IgnoreFaceRule to function.
	int ruleFailures(Collection<IArea> areas, IPath path, IGrid grid);
}
