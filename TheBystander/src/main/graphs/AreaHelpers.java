package main.graphs;

import main.graphs.faces.interfaces.*;
import main.graphs.interfaces.IArea;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Gus on 07/12/2016.
 * A collection of static helper methods to perform useful operations on areas.
 */
public class AreaHelpers {
    public static Collection<IStarFace> getStarFaces(IArea area) {
        Collection<IStarFace> result = new ArrayList<IStarFace>();

        for (IFace face : area.getFaces()) {
            if (face instanceof IStarFace) {
                result.add((IStarFace) face);
            }
        }

        return result;
    }

    public static Collection<IColouredFace> getColouredFaces(IArea area) {
        Collection<IColouredFace> result = new ArrayList<IColouredFace>();

        for (IFace face : area.getFaces()) {
            if (face instanceof IColouredFace) {
                result.add((IColouredFace) face);
            }
        }

        return result;
    }

    public static Collection<ITetrominoFace> getTetrominoFaces(IArea area) {
        Collection<ITetrominoFace> result = new ArrayList<ITetrominoFace>();

        for (IFace face : area.getFaces()) {
            if (face instanceof ITetrominoFace) {
                result.add((ITetrominoFace) face);
            }
        }

        return result;
    }

    public static Collection<IBlueBlocksFace> getBlueBlocksFaces(IArea area) {
        Collection<IBlueBlocksFace> result = new ArrayList<IBlueBlocksFace>();

        for (IFace face : area.getFaces()) {
            if (face instanceof IBlueBlocksFace) {
                result.add((IBlueBlocksFace) face);
            }
        }

        return result;
    }
}
