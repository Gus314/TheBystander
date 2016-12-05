package bystander.graphs.grids;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import bystander.enums.StartOrExit;
import bystander.exceptions.InvalidVertexException;
import bystander.graphs.DecorationSpecification;
import bystander.graphs.Edge;
import bystander.graphs.Vertex;
import bystander.graphs.faces.Face;
import bystander.graphs.faces.interfaces.IFace;
import bystander.graphs.interfaces.IEdge;
import bystander.graphs.interfaces.IVertex;

public class GridFactory implements IGridFactory
{
	private static void decorateEdge(IEdge edge, IVertex source, IVertex target, Collection<DecorationSpecification> decorationSpecifications)
	{
        for(DecorationSpecification decorationSpecification: decorationSpecifications)
        {
        	if(decorationSpecification.getSource() == source && decorationSpecification.getTarget() == target)
        	{
        		edge.getDecorations().add(decorationSpecification.getDecoration());
        	}
        }
	}
	
    public IGrid Construct(int rows, int columns, Position start, Position exit, Map<IFace, Position> specialFaces, Collection<DecorationSpecification> decorationSpecifications)
    {
    	// TODO: Add validation of positions and size.
        IGrid result = new Grid();
        IVertex[][] vertices = new IVertex[rows][columns];
        for(int i = 0; i < rows; ++i)
        {
            for(int j = 0; j < columns; ++j)
            {
                StartOrExit startOrExit = (i == start.getRow() && j == start.getColumn()) ? StartOrExit.START : (i == exit.getRow() && j == exit.getColumn()) ? StartOrExit.EXIT : StartOrExit.NEITHER;
                IVertex newVertex = new Vertex(i, j, startOrExit);
                vertices[i][j] = newVertex;
                result.addVertex(newVertex, i, j);
                if (i > 0)
                {
                    IVertex upVertex = vertices[i - 1][j];
                    // TODO: Produce a far better way of producing mandatory edges.
                    IEdge edge = new Edge(newVertex, upVertex);
                    decorateEdge(edge, newVertex, upVertex, decorationSpecifications);                   
                    result.addEdge(edge);
                    
                    IEdge reverseEdge = new Edge(upVertex, newVertex);
                    decorateEdge(reverseEdge, upVertex, newVertex, decorationSpecifications);                
                    result.addEdge(reverseEdge);
                }
                if(j > 0)
                {
                    IVertex leftVertex = vertices[i][j - 1];
                    IEdge edge = new Edge(newVertex, leftVertex);
                    decorateEdge(edge, newVertex, leftVertex, decorationSpecifications);  
                    result.addEdge(edge);
                    
                    IEdge reverseEdge = new Edge(leftVertex, newVertex);
                    decorateEdge(reverseEdge, leftVertex, newVertex, decorationSpecifications);  
                    result.addEdge(reverseEdge);
                }
            }
        }
        
        Collection<IFace> faces = determineFaces(vertices, specialFaces);
    
        for(IFace face: faces)
        {
        	result.addFace(face);
        }
        
        return result;
    }
    
	private Collection<IFace> determineFaces(IVertex[][] vertices, Map<IFace, Position> specialFaces)
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
					for(IFace specialFace: specialFaces.keySet())
					{
						if(specialFaces.get(specialFace).getRow() == i && specialFaces.get(specialFace).getColumn() == j)
						{
							face = specialFace;
							break;
						}
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
