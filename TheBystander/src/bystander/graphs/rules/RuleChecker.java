package bystander.graphs.rules;

import java.util.ArrayList;
import java.util.Collection;

import bystander.graphs.grids.IGrid;
import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.rules.interfaces.IRule;
import bystander.graphs.rules.interfaces.IRuleChecker;

public class RuleChecker implements IRuleChecker
{	
	private Collection<IRule> rules = new ArrayList<IRule>();
	private IGrid grid = null;
	
	public RuleChecker(IGrid grid)
	{
		rules.add(new SquareFacesRule());
		rules.add(new MandatoryRule());
		rules.add(new BlueBlocksRule());
		rules.add(new ForbiddenRule());
		rules.add(new IgnoreFaceRule());
		rules.add(new StarsRule());
		rules.add(new TetrominosRule());
		this.grid = grid;
	}
	
	public boolean isSolution(Collection<IArea> areas, IPath path)
	{
		int failures = 0;
		
		// Note that the number of failures must be returned rather than a boolean success/failure status to allow the IgnoreFaceRule to function.
		for(IRule rule: rules)
		{
			failures += rule.ruleFailures(areas, path, grid);

		}
		return (0 == failures);
	}	
}
