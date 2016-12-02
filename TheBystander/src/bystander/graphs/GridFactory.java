package bystander.graphs;

import bystander.enums.StartOrExit;
import bystander.graphs.interfaces.IEdge;
import bystander.graphs.interfaces.IGrid;
import bystander.graphs.interfaces.IGridFactory;
import bystander.graphs.interfaces.IVertex;

public class GridFactory implements IGridFactory
{
    public IGrid Construct()
    {
        IGrid result = new Grid();
        IVertex[][] vertices = new IVertex[8][8];
        for(int i = 0; i < 8; ++i)
        {
            for(int j = 0; j < 8; ++j)
            {
                StartOrExit startOrExit = (i == 0 && j == 0) ? StartOrExit.START : (i == 7 && j == 7) ? StartOrExit.EXIT : StartOrExit.NEITHER;
                IVertex newVertex = new Vertex(i, j, startOrExit);
                vertices[i][j] = newVertex;
                result.addVertex(newVertex, i, j);
                if (i > 0)
                {
                    IVertex upVertex = vertices[i - 1][j];
                    IEdge edge = new Edge(newVertex, upVertex);
                    result.addEdge(edge);
                    IEdge reverseEdge = new Edge(upVertex, newVertex);
                    result.addEdge(reverseEdge);
                }
                if(j > 0)
                {
                    IVertex leftVertex = vertices[i][j - 1];
                    IEdge edge = new Edge(newVertex, leftVertex);
                    result.addEdge(edge);
                    IEdge reverseEdge = new Edge(leftVertex, newVertex);
                    result.addEdge(reverseEdge);
                }
            }
        }

        return result;
    }
}
