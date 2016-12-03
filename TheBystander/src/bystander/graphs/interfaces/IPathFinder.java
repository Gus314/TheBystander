package bystander.graphs.interfaces;

import java.util.Collection;

public interface IPathFinder
{
    Collection<IPath> findPaths(IGrid grid);
}
