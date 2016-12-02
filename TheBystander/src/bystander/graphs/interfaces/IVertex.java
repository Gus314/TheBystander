package bystander.graphs.interfaces;

import bystander.enums.StartOrExit;;

public interface IVertex
{
    StartOrExit getStartOrExit();
    int getRow();
    int getColumn();
}
