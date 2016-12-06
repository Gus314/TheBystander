package main.graphs.decorations;

import java.io.IOException;
import java.io.Serializable;

import main.graphs.Decoration;

/**
 * @author Gus
 * A rule used to decorate a mandatory edge. The correct solution must use this edge.
 */
public class Mandatory extends Decoration implements Serializable{

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
