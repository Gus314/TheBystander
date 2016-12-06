package main.graphs;

import java.util.ArrayList;
import java.util.Collection;

import main.enums.Colour;
import main.graphs.faces.interfaces.IColouredFace;
import main.graphs.faces.interfaces.IFace;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IPath;
import main.graphs.interfaces.IRuleChecker;

public class RuleChecker implements IRuleChecker
{
	// TODO: For now this could be static.
	public boolean isSolution(Collection<IArea> areas, IPath path)
	{
		// TODO: Tidy this before more rules are added.
		for(IArea area: areas)
		{
			Collection<Colour> coloursInArea = new ArrayList<Colour>();
			for(IFace face: area.getFaces())
			{
				if(face instanceof IColouredFace)
				{
					// TODO: Might eventually want a way to mark faces as failed.
					IColouredFace colouredFace = (IColouredFace)face;
					if(coloursInArea.size() == 0)
					{
						coloursInArea.add(colouredFace.getColour());
					}
					else if(!coloursInArea.contains(colouredFace.getColour()))
					{ // Encountered a second coloured face in an area.
				      return false;
					}
				}
			}
		}
		
		return true;
	}	
}
