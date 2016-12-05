package bystander.graphs.faces;

import java.io.IOException;
import java.io.Serializable;

import bystander.enums.Colour;
import bystander.graphs.faces.interfaces.ISquareFace;

/**
 * @author Gus
 * Represents a coloured face such that any other coloured faces in the
 * same area must have the same colour.
 */
public class SquareFace extends ColouredFace implements ISquareFace, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SquareFace(Colour colour) 
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
