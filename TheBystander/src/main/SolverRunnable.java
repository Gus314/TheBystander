package main;

import main.graphs.grids.IGrid;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IPath;
import main.graphs.rules.RuleChecker;
import main.graphs.rules.interfaces.IRuleChecker;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Gus on 09/12/2016.
 */
public class SolverRunnable implements Runnable {
    private Map<IPath, Collection<IArea>> data;
    private IGrid grid;
    private IPath result;

    public SolverRunnable(Map<IPath, Collection<IArea>> data) {
        result = null;
        this.data = data;
    }

    public IPath getResult() {
        return result;
    }

    public void setGrid(IGrid grid) {
        this.grid = grid;
    }

    @Override
    public void run() {
        IRuleChecker ruleChecker = new RuleChecker(grid);

        for (IPath path : data.keySet()) {
            for (IArea area : data.get(path)) {
                area.returnSpecialFaces(grid);
            }

            if (ruleChecker.isSolution(data.get(path), path)) {
                result = path;
                break;
            }
        }
    }
}
