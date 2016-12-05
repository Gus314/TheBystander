package bystander.graphs.rules;

import java.util.ArrayList;
import java.util.Collection;

import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.IGrid;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.rules.interfaces.IRule;
import bystander.graphs.rules.interfaces.IRuleChecker;

public class RuleChecker implements IRuleChecker
{	
	private Collection<IRule> rules = new ArrayList<IRule>();
	private IGrid grid = null;
	
	public RuleChecker(IGrid grid)
	{
		rules.add(new ColouredFacesRule());
		rules.add(new MandatoryEdgesRule());
		this.grid = grid;
	}
	
	public boolean isSolution(Collection<IArea> areas, IPath path)
	{
		for(IRule rule: rules)
		{
			if(!rule.isRuleMet(areas, path, grid))
			{
				return false;
			}
		}
		return true;
	}	
}
