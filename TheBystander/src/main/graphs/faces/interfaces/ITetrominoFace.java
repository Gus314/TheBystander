package main.graphs.faces.interfaces;

import main.graphs.grids.Position;

public interface ITetrominoFace extends IFace
{
	boolean getRotatable();
	Position[] getShape();
}
