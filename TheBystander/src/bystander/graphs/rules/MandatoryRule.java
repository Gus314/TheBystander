package bystander.graphs.rules;

import java.util.Collection;

import bystander.graphs.decorations.Mandatory;
import bystander.graphs.grids.IGrid;
import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.IDecoration;
import bystander.graphs.interfaces.IEdge;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.rules.interfaces.IRule;

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
