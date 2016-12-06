package main.graphs;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import main.graphs.faces.interfaces.IFace;
import main.graphs.interfaces.IArea;

public class Area implements IArea, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection<IFace> faces;
	
	public Area()
	{
		faces = new ArrayList<IFace>();
	}
	
	public Collection<IFace> getFaces() 
	{
		return faces;
	}
	
	public void addFace(IFace face)
	{
		faces.add(face);
	}
	
	public String toString()
	{
		String result = "<Area>\n";
		for(IFace face: this.getFaces())
		{
			result = result + face + '\n';
		}
		return result + "</Area>\n";
	}
	
	 private void writeObject(java.io.ObjectOutputStream out) throws IOException
	 {
		 out.writeInt(faces.size());
		 for(IFace face: faces)
		 {
			 out.writeObject(face);
		 }
	 }
	 
	 private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	 {
		 faces = new ArrayList<IFace>();
		 int numFaces = in.readInt();
		 for(int i = 0; i < numFaces; i++)
		 {
			 faces.add((IFace)in.readObject());
		 }
	 }
}
