package bystander.graphs;

import java.util.ArrayList;
import java.util.Collection;

import bystander.enums.Colour;
import bystander.graphs.faces.interfaces.IColouredFace;
import bystander.graphs.faces.interfaces.IFace;
import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.interfaces.IRuleChecker;

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
