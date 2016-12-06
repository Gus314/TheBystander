package main.graphs.rules.interfaces;

import java.util.Collection;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IPath;

public interface IRuleChecker 
{
	boolean isSolution(Collection<IArea> areas, IPath path);
}
