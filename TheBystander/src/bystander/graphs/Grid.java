package bystander.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bystander.exceptions.InvalidPathException;
import bystander.graphs.faces.interfaces.IFace;
import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.ICycle;
import bystander.graphs.interfaces.IEdge;
import bystander.graphs.interfaces.IGrid;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.interfaces.IVertex;

public class Grid implements IGrid
{
    private final int numRows = 8;
    private final int numColumns = 8;
    private IVertex[][] vertices = new Vertex[numRows][numColumns];
    private Collection<IEdge> edges;
    private Collection<IFace> faces;
    
    public IVertex[][] getVertices() {
		return vertices;
	}

	public Collection<IEdge> getEdges() {
		return edges;
	}

	public Grid()
    {
        edges = new ArrayList<IEdge>();
        faces = new ArrayList<IFace>();
    }

	@SuppressWarnings("unused")
	private boolean isConsistent()
	{
		// TODO: This isn't currently used but will be important when creating different puzzles is possible.
		// A planar graph should always have Euler characteristic 2 (vertices - edges + faces). This could
		// be extended if hyperbolic and other surfaces are eventually allowed.
		return (numRows*numColumns - edges.size()/2 + faces.size()) == 2; // (use edges / 2 as the graph is directed.);
	}
	
	public void addFace(IFace face)
	{
		faces.add(face);
	}
	
    public List<IEdge> edgesWithVertex(IVertex vertex, IPath p)
    {
        List<IEdge> result = new ArrayList<IEdge>();

        for(IEdge e: edges)
        {
            boolean visited = false;
            for(IVertex usedVertex: p.getVertices())
            {
                if(e.getTarget() == usedVertex)
                {
                    // Ignore vertices already visited.
                    visited = true;
                    continue;
                }
            }

            if((!visited) && (e.getSource() == vertex))
            {
                result.add(e);
            }
        }

        return result;
    }

    private Collection<IEdge> edgesWithVertex(IVertex vertex)
    {
        List<IEdge> result = new ArrayList<IEdge>();

        for(IEdge e: edges)
        {
                if(e.getTarget() == vertex || e.getSource() == vertex)
                {
                    result.add(e);
                }
        }

        return result;
    }
    
    public void addVertex(IVertex vertex, int row, int column)
    {
        vertices[row][column] = vertex;
    }

    public  void addEdge(IEdge edge)
    {
        edges.add(edge);
    }
    
    public boolean isOnBoundary(IVertex vertex)
    {
    	return edgesWithVertex(vertex).size() < 8;
    }
         
    private ICycle completeAreaPath(IPath subPath, IPath masterPath) throws InvalidPathException
    {
    	ICycle result = new Cycle(subPath);
    	// Take in a path and sub-path, then move along the boundary in the direction not taken by the larger path to
    	// determine the area spanned by the sub-path.
    	// TODO: Add conditions to check as it sounds like a lot could go wrong here.
    	IVertex finalSharedVertex = result.getVertices().get(result.getVertices().size()-1);
    	IEdge latestEdge = null;
    	for(IEdge e: edgesWithVertex(finalSharedVertex))
    	{
    		if(isOnBoundary(e.getSource()) && isOnBoundary(e.getTarget()) && (!masterPath.getEdges().contains(e)))
    		{   // Find other edge moving along the boundary but not in masterPath.
    			if(result.getVertices().contains(e.getSource())) // Check direction of path
    			{
    				result.addEdge(e);
    				latestEdge = e;
    				break;    				
    			}
    		}
    	}
    	
    	IVertex currentVertex = latestEdge.getTarget();
    	
    	// Keep adding edges on the boundary until a vertex in the master path is reached.
    	while(!masterPath.getVertices().contains(currentVertex))
    	{
    		// Find next edge to use.
    		for(IEdge edge: edgesWithVertex(currentVertex))
    		{	// TODO: Create functions edgesWithSourceVertex and edgesWithTargetVertex.
    			if(edge.getSource() == currentVertex)
    			{
    				if(isOnBoundary(edge.getTarget()) && !result.getVertices().contains(edge.getTarget()))
    				{
    					latestEdge = edge;
    					result.addEdge(latestEdge);
    					break;
    				}
    			}
    		}
    		if(currentVertex == latestEdge.getTarget())
    		{ // TODO: Massive hack!
    			return result;
    		}
    		
    		currentVertex = latestEdge.getTarget();
    	}    	

    	return result;
    }
    
    private Collection<IVertex> getBoundaryVertices(IFace face)
    {
    	Collection<IVertex> result = new ArrayList<IVertex>();
    	for(IVertex vertex: face.getVertices())
    	{
    		if(isOnBoundary(vertex))
    		{
    			result.add(vertex);
    		}
    	}
    	return result;    	
    }
    
    public IArea areaSpannedByCompletePath(IPath path)
    {
    	// TODO: Check path is complete.
    	IArea result = new Area();
    	Collection<IVertex> areaVertices = new ArrayList<IVertex>();
    	areaVertices.addAll(path.getVertices());
    	// A face is contained in an area if it contains boundary vertices all of which are 
    	// in the path or if of it at least 3 of its vertices are in the area (Logic needs checked. Also, what about non-rectangular puzzles?)
    	for(IFace face: faces)
    	{  // Add boundary faces.
    		Collection<IVertex> boundaryVertices = getBoundaryVertices(face);
    		boolean boundariesInPath = (boundaryVertices.size() > 0) && (path.getVertices().containsAll(boundaryVertices));
    		
    		if(boundariesInPath)
    		{
    			result.addFace(face);
    			areaVertices.addAll(face.getVertices());
    			continue;
    		} 		
    	}
    	
    	boolean change = true;
    	while(change)
    	{
    		change = addEnclosedFaces(areaVertices, result);
    	}
    	   	    	    	
    	return result;
    }
    
    private boolean addEnclosedFaces(Collection<IVertex> areaVertices, IArea area)
    {
    	// If an area contains all but one of the vertices of a face, it must contain that face. (TODO: Does this work for non-rectangular grids?)
    	boolean change = false;
    	
    	for(IFace face: faces)
    	{
    		if(area.getFaces().contains(face))
    		{
    			continue; // Ignore faces already considered.
    		}
    		
    		int verticesInArea = 0;
    		for(IVertex v: face.getVertices())
    		{
    			if(areaVertices.contains(v))
    			{
    				verticesInArea++;
    			}
    		}
    		if(verticesInArea >= face.getVertices().size()-1) 
    		{
    			area.addFace(face);
    			change = true;
    			for(IVertex v: face.getVertices())
    			{
    				if(!areaVertices.contains(v))
    				{
    					areaVertices.add(v);
    				}
    			}
    		}
    	}
    	return change;
    }
    
    public Collection<IArea> determineAreas(IPath path) throws InvalidPathException
    {
    	Collection<IArea> areas = new ArrayList<IArea>();
    	if(path.getVertices().size() == 0)
    	{
    		return areas;
    	}
    	if(!path.isComplete())
    	{
    		// TODO: Could it save time to do this with incomplete paths?
    		return areas;
    	}
    	
    	IVertex last = null;
    	boolean previousTouchedBoundary = true;
    	IVertex areaStart = path.getVertices().get(0);
    	
    	for(IVertex v: path.getVertices())
    	{    		
    		if(last != null)
    		{
    			if(isOnBoundary(v))
    			{
    				if(previousTouchedBoundary)
    				{
    					continue; // Path is moving along boundary.
    				}
    				else
    				{
    					// Path has just touched boundary, creating a new area.
    					IPath pathToNewArea = path.subPath(areaStart, v);
    					ICycle completeAreaPath = completeAreaPath(pathToNewArea, path);
    					areas.add(areaSpannedByCompletePath(completeAreaPath));
    					areaStart = v;
    				}
    				previousTouchedBoundary = true;
    			}
    			else
    			{
    				previousTouchedBoundary = false;
    			}
    		}
    		last = v;
    	}
    	
    	// Once the exit is reached, if it is on a boundary then a final area is created consisting of any faces
    	// not yet in an area. This may in fact be the sole area.
    	IVertex finalVertex = path.getVertices().get(path.getVertices().size()-1);
    	if(isOnBoundary(finalVertex))
    	{
    		IArea finalArea = new Area();
    		// Create are consisting of remaining faces.
    		Collection<IFace> usedFaces = new ArrayList<IFace>();
    		for(IArea area: areas)
    		{
    			usedFaces.addAll(area.getFaces());
    		}
    		for(IFace face: faces)
    		{
    			if(!(usedFaces.contains(face)))
    			{
    				finalArea.addFace(face);
    			}
    		}
    		if(finalArea.getFaces().size() > 0)
    		{
    			areas.add(finalArea);
    		}
    	}
    	
    	return areas;    	
    }
}
