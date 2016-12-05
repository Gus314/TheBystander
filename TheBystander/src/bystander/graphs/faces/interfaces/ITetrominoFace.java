package bystander.graphs.faces.interfaces;

import bystander.graphs.grids.Position;

public interface ITetrominoFace extends IFace
{
	boolean getRotatable();
	Position[] getShape();
}
