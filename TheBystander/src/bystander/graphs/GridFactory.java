package bystander.graphs;

import java.util.ArrayList;
import java.util.Collection;

import bystander.enums.Colour;
import bystander.enums.StartOrExit;
import bystander.exceptions.InvalidVertexException;
import bystander.graphs.faces.ColouredFace;
import bystander.graphs.faces.Face;
import bystander.graphs.faces.interfaces.IFace;
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
        
        Collection<IFace> faces = determineFaces(vertices);
    
        for(IFace face: faces)
        {
        	result.addFace(face);
        }
        
        return result;
    }
    
	private Collection<IFace> determineFaces(IVertex[][] vertices)
	{
		Collection<IFace> faces = new ArrayList<IFace>();
		
		// Rearrange the faces on the graph as vertices and edges are added. A face is a minimal cycle
		// and they cannot overlap.
		// TODO: Generalize to non-rectangular graphs.
		for(int i = 0; i < 8 - 1; ++i)
		{
			for(int j = 0; j < 8 - 1; ++j)
			{
				try
				{			
					IFace face = new Face();
					// TODO: Produce a far better way of putting in special faces.
					if(i == 1 && j == 1)
					{
						face = new ColouredFace(Colour.BLACK);
					}
					if(i == 2 && j == 1)
					{
						face = new ColouredFace(Colour.WHITE);
					}
					face.addVertex(vertices[i][j]);
					face.addVertex(vertices[i+1][j]);
					face.addVertex(vertices[i][j+1]);
					face.addVertex(vertices[i+1][j+1]);
					faces.add(face);
				}
				catch(InvalidVertexException invex)
				{
					System.out.println(invex.getMessage());
				}
			}
		}
		return faces;
	}
}
