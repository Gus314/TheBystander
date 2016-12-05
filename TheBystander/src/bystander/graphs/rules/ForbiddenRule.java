package bystander.graphs.rules;

import java.util.Collection;

import bystander.graphs.decorations.Forbidden;
import bystander.graphs.grids.IGrid;
import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.IDecoration;
import bystander.graphs.interfaces.IEdge;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.rules.interfaces.IRule;

/**
 * @author Gus
 * Checks the rule detailed in the ForbiddenEdge class.
 */
public class ForbiddenRule implements IRule
{
	public int ruleFailures(Collection<IArea> areas, IPath path, IGrid grid)
	{
		int result = 0;
		for(IEdge edge: grid.getEdges())
		{
			for(IDecoration decoration: edge.getDecorations())
			{
				if(decoration instanceof Forbidden)
				{
					if(path.getEdges().contains(edge))
					{
						// If path contains an edge marked as forbidden, this rule fails.
						result++;
					}
				}
			}
		}
		
		return result;
	}
}
