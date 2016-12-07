package main.graphs.rules;

import main.enums.Colour;
import main.graphs.AreaHelpers;
import main.graphs.faces.interfaces.ISquareFace;
import main.graphs.faces.interfaces.IStarFace;
import main.graphs.grids.IGrid;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IPath;
import main.graphs.rules.interfaces.IRule;

import java.util.Collection;

/**
 * @author Gus
 * Checks the rule detailed in the StarFace class.
 */
public class StarsRule  implements IRule
{
    private static int starsOfColour(Collection<IStarFace> starFaces, Colour colour) {
        int result = 0;
        for (IStarFace starFace : starFaces) {
            if (starFace.getColour() == colour) {
                result++;
            }
        }
        return result;
    }

    private static int squaresOfColour(Collection<ISquareFace> squareFaces, Colour colour) {
        int result = 0;
        for (ISquareFace squareFace : squareFaces) {
            if (squareFace.getColour() == colour) {
                result++;
            }
        }
        return result;
    }

    /**
     * @param area The area to check the rule against.
     * @return The number of rule-failing faces in the area. The face passes if and only if for each colour
     * one of the following is true:
     * 1. There are 0 stars of that colour in the area.
     * 2. There is exactly 1 star of that colour in the area and exactly one square of that colour.
     * 3. There are exactly 2 stars of that colour in the area and zero squares of that colour.
     */
    private static int areaRuleFailures(IArea area) {
        int result = 0;

        Collection<IStarFace> starFaces = AreaHelpers.getStarFaces(area);
        Collection<ISquareFace> squareFaces = AreaHelpers.getSquareFaces(area);
        for (Colour colour : Colour.values()) {
            int starCount = starsOfColour(starFaces, colour);
            int squaresOfColour = squaresOfColour(squareFaces, colour);

            boolean pass = ((starCount == 1 && squaresOfColour == 1) ||
                    (starCount == 2 && squaresOfColour == 0) ||
                    (starCount == 0));
            if (!pass) {
                result += starCount;
            }
        }

        return result;
    }

    public int ruleFailures(Collection<IArea> areas, IPath path, IGrid grid) {
        int result = 0;

        for (IArea area : areas) {
            result += areaRuleFailures(area);
        }

        return result;
    }
}
