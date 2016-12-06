package main.graphs.faces;

import java.io.IOException;
import java.io.Serializable;

import main.enums.Colour;
import main.graphs.faces.interfaces.IStarFace;

/**
 * @author Gus
 * Represents a coloured star face. It must be paired with exactly one other star of the same colour
 * in the same area. If there is only one of a given colour it may be paired with a square face of the
 * same colour in that area.
 */
public class StarFace extends ColouredFace implements IStarFace, Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StarFace(Colour colour) 
	{
		super(colour);
	}
	
	protected void writeObject(java.io.ObjectOutputStream out) throws IOException
	 {
		 super.writeObject(out);
	 }
	 
	 protected void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	 {
		 super.readObject(in);
	 }
}
