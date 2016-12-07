package test.unit.graphs;

import main.enums.StartOrExit;
import main.exceptions.InvalidPathException;
import main.graphs.Path;
import main.graphs.interfaces.IEdge;
import main.graphs.interfaces.IPath;
import main.graphs.interfaces.IVertex;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Gus on 07/12/2016.
 * Unit tests for the Path class
 */
public class PathTest
{
    @Test
    public void pathFromStartVertexToEndVertexIsComplete() throws Exception
    {
        IEdge edge = mock(IEdge.class);
        IVertex sourceVertex = mock(IVertex.class);
        when(sourceVertex.getStartOrExit()).thenReturn(StartOrExit.START);
        IVertex targetVertex = mock(IVertex.class);
        when(targetVertex.getStartOrExit()).thenReturn(StartOrExit.EXIT);
        when(edge.getSource()).thenReturn(sourceVertex);
        when(edge.getTarget()).thenReturn(targetVertex);

        Path path = new Path(true);
        path.addEdge(edge);
        assertTrue(path.isComplete());
    }

    @Test
    public void pathFromStartVertexToNonEndVertexIsNotComplete() throws Exception
    {
        IEdge edge = mock(IEdge.class);
        IVertex sourceVertex = mock(IVertex.class);
        when(sourceVertex.getStartOrExit()).thenReturn(StartOrExit.START);
        IVertex targetVertex = mock(IVertex.class);
        when(targetVertex.getStartOrExit()).thenReturn(StartOrExit.NEITHER);
        when(edge.getSource()).thenReturn(sourceVertex);
        when(edge.getTarget()).thenReturn(targetVertex);

        Path path = new Path(true);
        path.addEdge(edge);
        assertFalse(path.isComplete());
    }

    @Test
    public void pathWithNoEdgesCannotAddEdgeFromNonStartVertex() throws Exception
    {
        IEdge edge = mock(IEdge.class);
        IVertex sourceVertex = mock(IVertex.class);
        when(sourceVertex.getStartOrExit()).thenReturn(StartOrExit.NEITHER);
        IVertex targetVertex = mock(IVertex.class);
        when(targetVertex.getStartOrExit()).thenReturn(StartOrExit.NEITHER);
        when(edge.getSource()).thenReturn(sourceVertex);
        when(edge.getTarget()).thenReturn(targetVertex);

        Path path = new Path(true);
        assertFalse(path.canAddEdge(edge));
    }

    @Test
    public void pathWithNoEdgesCanAddEdgeFromStartVertex() throws Exception
    {
        IEdge edge = mock(IEdge.class);
        IVertex sourceVertex = mock(IVertex.class);
        when(sourceVertex.getStartOrExit()).thenReturn(StartOrExit.START);
        IVertex targetVertex = mock(IVertex.class);
        when(targetVertex.getStartOrExit()).thenReturn(StartOrExit.NEITHER);
        when(edge.getSource()).thenReturn(sourceVertex);
        when(edge.getTarget()).thenReturn(targetVertex);

        Path path = new Path(true);
        assertTrue(path.canAddEdge(edge));
    }

    @Test(expected = InvalidPathException.class)
    public void pathCannotAddEdgeWithDifferentSourceFromLastTargetEdge() throws Exception
    {
        IEdge edge = mock(IEdge.class);
        IVertex sourceVertex = mock(IVertex.class);
        when(sourceVertex.getStartOrExit()).thenReturn(StartOrExit.START);
        IVertex targetVertex = mock(IVertex.class);
        when(targetVertex.getStartOrExit()).thenReturn(StartOrExit.NEITHER);
        when(edge.getSource()).thenReturn(sourceVertex);
        when(edge.getTarget()).thenReturn(targetVertex);

        IEdge edge2 = mock(IEdge.class);
        IVertex target2Vertex = mock(IVertex.class);
        when(target2Vertex.getStartOrExit()).thenReturn(StartOrExit.NEITHER);
        when(edge2.getSource()).thenReturn(target2Vertex);
        when(edge2.getTarget()).thenReturn(targetVertex);

        IPath path = new Path(true);
        path.addEdge(edge);
        path.addEdge(edge2);
    }

    @Test
    public void pathCanAddNonStartVertexAsSecondEdge() throws Exception
    {
        IEdge edge = mock(IEdge.class);
        IVertex sourceVertex = mock(IVertex.class);
        when(sourceVertex.getStartOrExit()).thenReturn(StartOrExit.START);
        IVertex targetVertex = mock(IVertex.class);
        when(targetVertex.getStartOrExit()).thenReturn(StartOrExit.NEITHER);
        when(edge.getSource()).thenReturn(sourceVertex);
        when(edge.getTarget()).thenReturn(targetVertex);

        IEdge edge2 = mock(IEdge.class);
        IVertex target2Vertex = mock(IVertex.class);
        when(target2Vertex.getStartOrExit()).thenReturn(StartOrExit.NEITHER);
        when(edge2.getSource()).thenReturn(targetVertex);
        when(edge2.getTarget()).thenReturn(target2Vertex);

        IPath path = new Path(true);
        path.addEdge(edge);
        path.addEdge(edge2);
    }

    @Test
    public void pathCanAddStartVertexAsFirstEdge() throws Exception
    {
        IEdge edge = mock(IEdge.class);
        IVertex sourceVertex = mock(IVertex.class);
        when(sourceVertex.getStartOrExit()).thenReturn(StartOrExit.START);
        IVertex targetVertex = mock(IVertex.class);
        when(targetVertex.getStartOrExit()).thenReturn(StartOrExit.NEITHER);
        when(edge.getSource()).thenReturn(sourceVertex);
        when(edge.getTarget()).thenReturn(targetVertex);

        IPath path = new Path(true);
        path.addEdge(edge);
    }


    @Test(expected = InvalidPathException.class)
    public void pathCannotAddNonStartVertexAsFirstEdge() throws Exception
    {
        IEdge edge = mock(IEdge.class);
        IVertex sourceVertex = mock(IVertex.class);
        when(sourceVertex.getStartOrExit()).thenReturn(StartOrExit.NEITHER);
        IVertex targetVertex = mock(IVertex.class);
        when(targetVertex.getStartOrExit()).thenReturn(StartOrExit.NEITHER);
        when(edge.getSource()).thenReturn(sourceVertex);
        when(edge.getTarget()).thenReturn(targetVertex);

        IPath path = new Path(true);
        path.addEdge(edge);
    }

    @Test
    public void pathWithOneEdgeCanCreateSubpathWithThatEdge() throws Exception
    {
            IEdge edge = mock(IEdge.class);
            IVertex sourceVertex = mock(IVertex.class);
            when(sourceVertex.getStartOrExit()).thenReturn(StartOrExit.START);
            IVertex targetVertex = mock(IVertex.class);
            when(targetVertex.getStartOrExit()).thenReturn(StartOrExit.NEITHER);
            when(edge.getSource()).thenReturn(sourceVertex);
            when(edge.getTarget()).thenReturn(targetVertex);

            IPath path = new Path(true);
            path.addEdge(edge);
            IPath subPath = path.subPath(sourceVertex, targetVertex);
    }

    @Test(expected = InvalidPathException.class)
    public void pathWithOneEdgeCannotCreateSubpathWithADifferentVertex() throws Exception
    {
        IVertex sourceVertex = mock(IVertex.class);
        IVertex targetVertex = mock(IVertex.class);
        IEdge masterEdge = mock(IEdge.class);
        when(masterEdge.getSource()).thenReturn(sourceVertex);
        when(masterEdge.getTarget()).thenReturn(targetVertex);

        IVertex invalidVertex = mock(IVertex.class);
        IEdge fakeEdge = mock(IEdge.class);
        when(fakeEdge.getSource()).thenReturn(invalidVertex);
        when(fakeEdge.getTarget()).thenReturn(targetVertex);

        IPath path = new Path(true);
        path.addEdge(masterEdge);

        IPath subPath = path.subPath(invalidVertex, targetVertex);
    }
}