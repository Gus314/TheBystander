package main.graphs.rules;

import main.graphs.faces.interfaces.IFace;
import main.graphs.faces.interfaces.IIgnoreFace;
import main.graphs.grids.IGrid;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IPath;
import main.graphs.rules.interfaces.IRule;

/**
 * @author Gus
 * Checks the rule detailed in the IgnoreFace class.
 */
public class IgnoreFaceRule implements IRule
{
    private int existingFailures = 0;

    public IgnoreFaceRule(int existingFailures) {
        this.existingFailures = existingFailures;
    }

    /**
     * @param area
     * @param path
     * @param grid
     * @return For every IgnoreFace, there must be one and exactly one rule-failing face.
     */
    public int ruleFailures(IArea area, IPath path, IGrid grid) {
        int ignoreFaces = 0;

        for (IFace face : area.getFaces()) {
            if (face instanceof IIgnoreFace) {
                ignoreFaces++;
            }
        }

        if (ignoreFaces == 0) {
            return 0;
        } else {
            ignoreFaces %= 2; // Pairs of IgnoreFaces ignore one another.
            return (existingFailures == ignoreFaces) ? 0 : 1;
        }
    }
}
