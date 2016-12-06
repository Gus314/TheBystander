package main.graphs.interfaces;

import main.enums.StartOrExit;
import main.graphs.grids.Position;;

public interface IVertex
{
    StartOrExit getStartOrExit();
    int getRow();
    int getColumn();
    Position getPosition();
}
