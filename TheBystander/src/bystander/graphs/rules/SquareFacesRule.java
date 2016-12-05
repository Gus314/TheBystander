package bystander.graphs.rules;

import java.util.ArrayList;
import java.util.Collection;

import bystander.enums.Colour;
import bystander.graphs.faces.interfaces.IColouredFace;
import bystander.graphs.faces.interfaces.IFace;
import bystander.graphs.grids.IGrid;
import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.rules.interfaces.IRule;

/**
 * @author Gus
 * Checks the rule detailed in the SquareFace class.
 */
public class SquareFacesRule implements IRule
{
	public int ruleFailures(Collection<IArea> areas, IPath path, IGrid grid)
	{
		int result = 0;
		
		for(IArea area: areas)
		{
			Collection<Colour> coloursInArea = new ArrayList<Colour>();
			for(IFace face: area.getFaces())
			{
				if(face instanceof IColouredFace)
				{
					IColouredFace colouredFace = (IColouredFace)face;
					if(coloursInArea.size() == 0)
					{
						coloursInArea.add(colouredFace.getColour());
					}
					else if(!coloursInArea.contains(colouredFace.getColour()))
					{ // Encountered a second coloured face in an area.
				      result++;
					}
				}
			}
		}
		return result;
	}
}
