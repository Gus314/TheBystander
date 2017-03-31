package main.graphs.rules;

import main.graphs.AreaHelpers;
import main.graphs.faces.interfaces.IBlueBlocksFace;
import main.graphs.faces.interfaces.ITetrominoFace;
import main.graphs.grids.IGrid;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IPath;
import main.graphs.rules.interfaces.IRule;

import java.util.Collection;

/**
 * @author Gus
 * Checks the rule detailed in the TetrominoFace class.
 */
public class TetrominosRule implements IRule 
{
    /**
     * @param area
     * @param path
     * @param grid
     * @return Note that the blue blocks rule is considered part of the tetrominoes rule.
     */
    public int ruleFailures(IArea area, IPath path, IGrid grid) {
        Collection<IBlueBlocksFace> blueBlocksFaces = AreaHelpers.getBlueBlocksFaces(area);
        Collection<ITetrominoFace> tetrominoFaces = AreaHelpers.getTetrominoFaces(area);

        // TODO: This only checks the number of faces in the area, not the shape.
        int numberOfFaces = area.getFaces().size();

        int numberOfTetrominoFaces = 0;
        for (ITetrominoFace tetrominoFace : tetrominoFaces) {
            numberOfTetrominoFaces += tetrominoFace.getShape().length;
        }

        int numberOfBlueBlocksFaces = 0;
        for (IBlueBlocksFace blueBlocksFace : blueBlocksFaces) {
            numberOfBlueBlocksFaces += blueBlocksFace.getCount();
        }

        int expectedFaces = numberOfTetrominoFaces - numberOfBlueBlocksFaces;
        if (expectedFaces < 0) {
            return (-1 * expectedFaces);
        } else if (numberOfFaces > expectedFaces) {
            return numberOfFaces - expectedFaces;
        } else {
            return expectedFaces - numberOfFaces;
        }
	}
}

