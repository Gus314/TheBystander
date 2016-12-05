package bystander.graphs.faces;

import java.io.IOException;
import java.io.Serializable;

import bystander.graphs.faces.interfaces.IBlueBlocksFace;

/**
 * 
 * @author Gus
 * This class represents a face with a number of blue blocks, meaning that that number
 * of blocks from tetrominos in the area may be omitted.
 */
public class BlueBlocksFace extends Face implements IBlueBlocksFace, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int count;
	
	public int getCount()
	{
		return count;
	}
	
	public BlueBlocksFace(int count)
	{
		this.count = count;
	}
	
	protected void writeObject(java.io.ObjectOutputStream out) throws IOException
	 {		
		 super.writeObject(out);
		 out.writeInt(count);
	 }
	 
	 protected void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	 {
		 super.readObject(in);
		 count = in.readInt();
	 }
}
