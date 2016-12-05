package bystander.graphs.interfaces;

import bystander.enums.StartOrExit;
import bystander.graphs.grids.Position;;

public interface IVertex
{
    StartOrExit getStartOrExit();
    int getRow();
    int getColumn();
    Position getPosition();
}
