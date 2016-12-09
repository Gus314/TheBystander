package main.graphs.rules;

import main.enums.Colour;
import main.graphs.AreaHelpers;
import main.graphs.faces.interfaces.IColouredFace;
import main.graphs.faces.interfaces.IFace;
import main.graphs.grids.IGrid;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IPath;
import main.graphs.rules.interfaces.IRule;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Gus
 * Checks the rule detailed in the SquareFace class.
 */
public class SquareFacesRule implements IRule
{
	public int ruleFailures(Collection<IArea> areas, IPath path, IGrid grid)
	{
        boolean pass = true;
        int totalSquares = 0;

		for(IArea area: areas)
		{
            totalSquares += AreaHelpers.getSquareFaces(area).size();
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
                        pass = false;
                        break;
                    }
				}
			}
		}
        return pass ? 0 : totalSquares;
    }
}
