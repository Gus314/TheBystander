package main.graphs.interfaces;

import java.util.Collection;

public interface IEdge
{
	IVertex getSource();
	IVertex getTarget();
	Collection<IDecoration> getDecorations();

    boolean isLeft(IVertex vertex);

    boolean isRight(IVertex vertex);

    boolean isUp(IVertex vertex);

    boolean isDown(IVertex vertex);

    boolean isHorizontallyAligned(IVertex vertex);

    boolean isVerticallyAligned(IVertex vertex);
}
