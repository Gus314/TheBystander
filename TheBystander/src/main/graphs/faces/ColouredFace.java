package main.graphs.faces;

import java.io.IOException;
import java.io.Serializable;

import main.enums.Colour;
import main.graphs.faces.interfaces.IColouredFace;

/**
 * 
 * @author Gus
 * Represents a face with a colour, with no rules attached.
 */
public abstract class ColouredFace extends Face implements IColouredFace, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Colour colour;
	public Colour getColour()
	{
		return colour;
	}
	
	public ColouredFace(Colour colour)
	{
		this.colour = colour;
	}
	
	protected void writeObject(java.io.ObjectOutputStream out) throws IOException
	 {
		 out.writeChars(colour.toString());
		 super.writeObject(out);
	 }
	 
	 protected void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	 {
		 colour = Colour.valueOf(in.readUTF());
		 super.readObject(in);
	 }
}
