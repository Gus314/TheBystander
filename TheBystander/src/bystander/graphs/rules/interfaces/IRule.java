package bystander.graphs.rules.interfaces;

import java.util.Collection;

import bystander.graphs.grids.IGrid;
import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.IPath;

public interface IRule 
{
	//Note that the number of failures must be returned rather than a boolean success/failure status to allow the IgnoreFaceRule to function.
	int ruleFailures(Collection<IArea> areas, IPath path, IGrid grid);
}
