package bystander.graphs.interfaces;

import java.util.Collection;

public interface IRuleChecker 
{
	boolean isSolution(Collection<IArea> areas, IPath path);
}
