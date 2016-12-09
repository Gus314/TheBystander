package main.graphs.grids;

import main.enums.StartOrExit;
import main.exceptions.InvalidGridException;
import main.graphs.DecorationSpecification;
import main.graphs.Edge;
import main.graphs.Vertex;
import main.graphs.faces.Face;
import main.graphs.faces.interfaces.IFace;
import main.graphs.interfaces.IEdge;
import main.graphs.interfaces.IVertex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class GridFactory implements IGridFactory
{
	private static void decorateEdge(IEdge edge, IVertex source, IVertex target, Collection<DecorationSpecification> decorationSpecifications)
	{
        for(DecorationSpecification decorationSpecification: decorationSpecifications)
        {
            if (decorationSpecification.getSource().equals(source) && decorationSpecification.getTarget().equals(target)) {
        		edge.getDecorations().add(decorationSpecification.getDecoration());
        	}
        }
	}
	
    public IGrid Construct(int rows, int columns, Collection<Position> startPositions, Collection<Position> exitPositions, Map<IFace, Position> specialFaces,
    						Collection<DecorationSpecification> decorationSpecifications) throws InvalidGridException
    {
    	if(rows < 1 || columns < 1)
    	{
    		throw new InvalidGridException("Invalid grid, number of rows and columns must both be greater than 0.");
    	}
    	
        IGrid result = new Grid(rows, columns);
        IVertex[][] vertices = new IVertex[rows][columns];
        for(int i = 0; i < rows; ++i)
        {
            for(int j = 0; j < columns; ++j)
            {
            	Position position = new Position(i,j);
                StartOrExit startOrExit = startPositions.contains(position) ? StartOrExit.START : exitPositions.contains(position) ? StartOrExit.EXIT : StartOrExit.NEITHER;
                IVertex newVertex = new Vertex(i, j, startOrExit);
                vertices[i][j] = newVertex;
                result.addVertex(newVertex, i, j);
                if (i > 0)
                {
                    IVertex upVertex = vertices[i - 1][j];
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
        
        Collection<IFace> faces = determineFaces(vertices, rows, columns, specialFaces);
    
        for(IFace face: faces)
        {
        	result.addFace(face);
        }
        
        return result;
    }
    
	private Collection<IFace> determineFaces(IVertex[][] vertices, int rows, int columns, Map<IFace, Position> specialFaces)
	{
		Collection<IFace> faces = new ArrayList<IFace>();
		
		// Rearrange the faces on the graph as vertices and edges are added. A face is a minimal cycle
		// and they cannot overlap.
		for(int i = 0; i < rows - 1; ++i)
		{
			for(int j = 0; j < columns - 1; ++j)
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
		}
		return faces;
	}
}
