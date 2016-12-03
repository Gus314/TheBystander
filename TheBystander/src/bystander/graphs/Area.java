package bystander.graphs;

import java.util.ArrayList;
import java.util.Collection;

import bystander.graphs.faces.interfaces.IFace;
import bystander.graphs.interfaces.IArea;

public class Area implements IArea
{
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
}
