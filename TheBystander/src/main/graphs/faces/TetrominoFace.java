package main.graphs.faces;

import java.io.IOException;
import java.io.Serializable;

import main.enums.Colour;
import main.graphs.faces.interfaces.ITetrominoFace;
import main.graphs.grids.Position;

/**
 * @author Gus
 * Represents a tetromino face. The shape given by the tetromino must appear within the given
 * area and not overlap shapes created for other tetromino faces in the same area. A flag
 * indicates if a rotation of this shape is acceptable. 
 */
public class TetrominoFace extends ColouredFace implements ITetrominoFace, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean rotatable;
	private Position[] shape;
	
	public boolean getRotatable()
	{
		return rotatable;
	}
	
	public Position[] getShape()
	{
		return shape;
	}

	
	public TetrominoFace(Colour colour, boolean rotatable, Position[] shape)
	{
		super(colour);
		this.rotatable = rotatable;
		this.shape = shape;
	}
	
	
	protected void writeObject(java.io.ObjectOutputStream out) throws IOException
	 {
		 super.writeObject(out);
		 out.writeBoolean(rotatable);
		 out.writeInt(shape.length);
		 for(int i = 0; i < shape.length; i++)
		 {
			 out.writeObject(shape[i]);
		 }
	 }
	 
	 protected void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	 {
		 super.readObject(in);
		 rotatable = in.readBoolean();
		 int count = in.readInt();
		 shape = new Position[count];
		 for(int i = 0; i < count; i++)
		 {
			 shape[i] = (Position) in.readObject();
		 }
	 }
}
