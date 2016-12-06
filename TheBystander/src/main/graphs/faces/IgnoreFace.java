package main.graphs.faces;

import java.io.IOException;
import java.io.Serializable;

import main.graphs.faces.interfaces.IIgnoreFace;

/**
 * @author Gus
 * Represents a face that causes one face within the area to be ignored when checking for rules.
 */
public class IgnoreFace extends Face implements IIgnoreFace, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void writeObject(java.io.ObjectOutputStream out) throws IOException
	 {		
		 super.writeObject(out);
	 }
	 
	 protected void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	 {
		 super.readObject(in);
	 }
}
