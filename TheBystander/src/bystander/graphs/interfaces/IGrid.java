package bystander.graphs.interfaces;

import java.util.Collection;
import java.util.List;

public interface IGrid
{
	IVertex[][] getVertices();
	Collection<IEdge> getEdges();
    List<IEdge> edgesWithVertex(IVertex vertex, IPath path);
    void addVertex(IVertex vertex, int row, int column);
    void addEdge(IEdge edge);   
}
