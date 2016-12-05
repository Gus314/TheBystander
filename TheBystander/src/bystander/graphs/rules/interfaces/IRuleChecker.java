package bystander.graphs.rules.interfaces;

import java.util.Collection;
import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.IPath;

public interface IRuleChecker 
{
	boolean isSolution(Collection<IArea> areas, IPath path);
}
