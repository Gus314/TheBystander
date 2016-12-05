package bystander.graphs.rules.interfaces;

import java.util.Collection;

import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.IGrid;
import bystander.graphs.interfaces.IPath;

public interface IRule 
{
	boolean isRuleMet(Collection<IArea> areas, IPath path, IGrid grid);
}
