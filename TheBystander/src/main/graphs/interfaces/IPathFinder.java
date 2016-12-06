package main.graphs.interfaces;

import java.util.Collection;

import main.graphs.grids.IGrid;

public interface IPathFinder
{
    Collection<IPath> findPaths(IGrid grid);
}
