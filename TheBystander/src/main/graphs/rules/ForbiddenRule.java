package main.graphs.rules;

import java.util.Collection;

import main.graphs.decorations.Forbidden;
import main.graphs.grids.IGrid;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IDecoration;
import main.graphs.interfaces.IEdge;
import main.graphs.interfaces.IPath;
import main.graphs.rules.interfaces.IRule;

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
