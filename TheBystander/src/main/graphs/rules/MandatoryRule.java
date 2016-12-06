package main.graphs.rules;

import java.util.Collection;

import main.graphs.decorations.Mandatory;
import main.graphs.grids.IGrid;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IDecoration;
import main.graphs.interfaces.IEdge;
import main.graphs.interfaces.IPath;
import main.graphs.rules.interfaces.IRule;

/**
 * @author Gus
 * Checks the rule detailed in the MandatoryEdge class.
 */
public class MandatoryRule implements IRule
{
	public int ruleFailures(Collection<IArea> areas, IPath path, IGrid grid)
	{
		int result = 0;
		
		for(IEdge edge: grid.getEdges())
		{
			for(IDecoration decoration: edge.getDecorations())
			{
				if(decoration instanceof Mandatory)
				{
					if(!path.getEdges().contains(edge))
					{
						// If path does not contain an edge marked as mandatory, this rule fails.
						result++;
					}
				}
			}
		}
		
		return result;
	}
}
