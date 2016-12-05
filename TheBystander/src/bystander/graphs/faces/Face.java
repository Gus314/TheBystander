package bystander.graphs.faces;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import bystander.exceptions.InvalidVertexException;
import bystander.graphs.faces.interfaces.IFace;
import bystander.graphs.interfaces.IVertex;

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
	
	public void addVertex(IVertex vertex) throws InvalidVertexException
	{
		// A face in a graph consists of a minimal cycle. Faces cannot overlap.
		// TODO: Check for overlapping of faces. This will only occur once non-rectangular puzzles are considered.
		vertices.add(vertex);
	}
	
	public IVertex getBottomLeftVertex()
	{
		// TODO: Remove this?
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
		// TODO: Tidy this function.
		String result = "<Face>";
		result += "(" + getBottomLeftVertex().getRow() + ", " + getBottomLeftVertex().getColumn() + ")";
		result += "(" + getBottomLeftVertex().getRow() + ", " + (getBottomLeftVertex().getColumn()+1) + ")";
		result += "(" + (getBottomLeftVertex().getRow()+1) + ", " + getBottomLeftVertex().getColumn() + ")";
		result += "(" + (getBottomLeftVertex().getRow()+1) + ", " + (getBottomLeftVertex().getColumn()+1) + ")";
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
	 
	 private void readObjectNoData() throws ObjectStreamException
	 {
		 ;
	 }
}
