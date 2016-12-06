package main.graphs.faces;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import main.graphs.faces.interfaces.IFace;
import main.graphs.interfaces.IVertex;

/**
 * @author Gus
 * Represents a face consisting of only vertices.
 */
public class Face implements IFace, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection<IVertex> vertices = new ArrayList<IVertex>();;

	public Collection<IVertex> getVertices()
	{
		return vertices;
	}
	
	public Face()
	{
	}
	
	public void addVertex(IVertex vertex)
	{
		// A face in a graph consists of a minimal cycle. Faces cannot overlap.
		vertices.add(vertex);
	}
	
	public IVertex getBottomLeftVertex()
	{
		IVertex result = null;
		
		for(IVertex v: vertices)
		{
			if(result == null)
			{
				result = v;
			}
			else
			{
				// The bottom-left corner is the vertex where the row + column sum has its minimum.
				int vCoordSum = v.getRow() + v.getColumn();
				int resultCoordSum = result.getRow() + result.getColumn();
				if(vCoordSum < resultCoordSum)
				{
					result = v;
				}				
			}
		}		
		
		return result;
	}
	
	public String toString()
	{
		int row = getBottomLeftVertex().getRow();
		int column = getBottomLeftVertex().getColumn();
		String result = "<Face>";
		result += "(" + row + ", " + column + ")";
		result += "(" + row + ", " + (column+1) + ")";
		result += "(" + (row+1) + ", " + column + ")";
		result += "(" + (row+1) + ", " + (column+1) + ")";
		return result += "</Face>";
	}
	
	 protected void writeObject(java.io.ObjectOutputStream out) throws IOException
	 {
		 out.writeInt(vertices.size());
		 IVertex[] vertexArray = (IVertex[]) vertices.toArray();
		 for(int i = 0; i < vertices.size(); i++)
		 {
			 out.writeObject(vertexArray[i]);
		 }
	 }
	 
	 protected void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	 {
		 int count = in.readInt();
		 for(int i = 0; i < count; i++)
		 {
			 vertices.add((IVertex)in.readObject());
		 }
	 }
	 
	 @SuppressWarnings("unused")
	 // Might be called during serialization.
	 private void readObjectNoData() throws ObjectStreamException
	 {
		 vertices = new ArrayList<IVertex>();
	 }
}
