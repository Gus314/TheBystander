package bystander.graphs;

import bystander.graphs.grids.Position;
import bystander.graphs.interfaces.IDecoration;

public class DecorationSpecification 
{
	private Position source;
	private Position target;
	private IDecoration decoration;
	
	public Position getSource() {
		return source;
	}
	public Position getTarget() {
		return target;
	}
	public IDecoration getDecoration() {
		return decoration;
	}
	
	public DecorationSpecification(Position source, Position target, IDecoration decoration) {
		super();
		this.source = source;
		this.target = target;
		this.decoration = decoration;
	}
}
