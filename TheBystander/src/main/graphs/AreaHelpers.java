package main.graphs;

import main.graphs.faces.interfaces.IFace;
import main.graphs.faces.interfaces.ISquareFace;
import main.graphs.faces.interfaces.IStarFace;
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

    public static Collection<ISquareFace> getSquareFaces(IArea area) {
        Collection<ISquareFace> result = new ArrayList<ISquareFace>();

        for (IFace face : area.getFaces()) {
            if (face instanceof ISquareFace) {
                result.add((ISquareFace) face);
            }
        }

        return result;
    }
}
