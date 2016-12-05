package bystander.graphs.decorations;

import java.io.IOException;
import java.io.Serializable;

import bystander.graphs.Decoration;

/**
 * @author Gus
 * A rule used to indicate a forbidden edge. The correct solution may not use this edge.
 */
public class Forbidden extends Decoration implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void writeObject(java.io.ObjectOutputStream out) throws IOException
	 {		
	 }
	 
	 protected void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	 {
	 }	
}
