package bystander.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bystander.graphs.interfaces.IEdge;
import bystander.graphs.interfaces.IGrid;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.interfaces.IVertex;

public class Grid implements IGrid
{
    private final int numRows = 8;
    private final int numColumns = 8;
    private IVertex[][] vertices = new Vertex[numRows][numColumns];
    private Collection<IEdge> edges;

    
    public IVertex[][] getVertices() {
		return vertices;
	}

	public Collection<IEdge> getEdges() {
		return edges;
	}

	public Grid()
    {
        edges = new ArrayList<IEdge>();
    }

    public List<IEdge> edgesWithVertex(IVertex vertex, IPath p)
    {
        List<IEdge> result = new ArrayList<IEdge>();

        for(IEdge e: edges)
        {
            boolean visited = false;
            for(IVertex usedVertex: p.getVertices())
            {
                if(e.getTarget() == usedVertex)
                {
                    // Ignore vertices already visited.
                    visited = true;
                    continue;
                }
            }

            if((!visited) && (e.getSource() == vertex))
            {
                result.add(e);
            }
        }

        return result;
    }

    public void addVertex(IVertex vertex, int row, int column)
    {
        vertices[row][column] = vertex;
    }

    public  void addEdge(IEdge edge)
    {
        edges.add(edge);
    }
}
