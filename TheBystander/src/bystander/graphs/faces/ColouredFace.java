package bystander.graphs.faces;

import bystander.enums.Colour;
import bystander.graphs.faces.interfaces.IColouredFace;

public class ColouredFace extends Face implements IColouredFace
{
	private Colour colour;
	public Colour getColour()
	{
		return colour;
	}
	
	public ColouredFace(Colour colour)
	{
		this.colour = colour;
	}
}
