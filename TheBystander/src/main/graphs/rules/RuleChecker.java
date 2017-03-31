package main.graphs.rules;

import main.graphs.Area;
import main.graphs.grids.IGrid;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IPath;
import main.graphs.rules.interfaces.IRule;
import main.graphs.rules.interfaces.IRuleChecker;

import java.util.ArrayList;
import java.util.Collection;

public class RuleChecker implements IRuleChecker
{	
	private Collection<IRule> rules = new ArrayList<IRule>();
	private IGrid grid = null;
	
	public RuleChecker(IGrid grid)
	{
		rules.add(new SquareFacesRule());
		rules.add(new SidesCoveredRule());
		rules.add(new StarsRule());
		rules.add(new TetrominosRule());
		this.grid = grid;
	}
	
	public boolean isSolution(Collection<IArea> areas, IPath path)
	{
		// TODO: IgnoreFaces can ignore black dots?
		int failures = 0;
		IArea blankArea = new Area();

		// Note that the number of failures must be returned rather than a boolean success/failure status to allow the IgnoreFaceRule to function.
		for (IArea area : areas) {
			for (IRule rule : rules) {
				int thisAreaFailures = rule.ruleFailures(area, path, grid);
				failures += thisAreaFailures;
				// TODO: Failures needs split into areas.
				IgnoreFaceRule ignoreFaceRule = new IgnoreFaceRule(thisAreaFailures); // This must be done last as it requires the results of the other rules.
				failures += ignoreFaceRule.ruleFailures(blankArea, path, grid);
			}
		}

		// MandatoryRule and ForbiddenRule should only be used once as they ignore areas.
		// TODO: Refactor to separate area-based rules and non-area-based rules
		MandatoryRule mandatoryRule = new MandatoryRule();
		failures += mandatoryRule.ruleFailures(blankArea, path, grid);

		ForbiddenRule forbiddenRule = new ForbiddenRule();
		failures += forbiddenRule.ruleFailures(blankArea, path, grid);

		return (0 == failures);
	}	
}
