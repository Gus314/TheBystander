package main.graphs.rules;

import java.util.ArrayList;
import java.util.Collection;

import main.enums.Colour;
import main.graphs.faces.interfaces.IColouredFace;
import main.graphs.faces.interfaces.IFace;
import main.graphs.grids.IGrid;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IPath;
import main.graphs.rules.interfaces.IRule;

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
