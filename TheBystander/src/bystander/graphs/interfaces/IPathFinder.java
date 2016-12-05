package bystander.graphs.interfaces;

import java.util.Collection;

import bystander.graphs.grids.IGrid;

public interface IPathFinder
{
    Collection<IPath> findPaths(IGrid grid);
}
