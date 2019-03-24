package shape;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class Shape {
	protected int x1, y1, x2, y2;
	protected List<Integer> px;
	protected List<Integer> py;
	public void setOrigin(int x, int y) {
		this.x1 = x;
		this.y1 = y;
		this.x2 = x;
		this.y2 = y;
	}
	public void setPoint(int x, int y) {
		this.x2 = x;
		this.y2 = y;
	}
	public void newArray() {
		this.px = new ArrayList<Integer>();
		this.py = new ArrayList<Integer>();
	}
	public void setOriginArray(int x, int y) {
		
		this.px.add(x);
		this.px.add(x);
		this.py.add(y);
		this.py.add(y);
	}
	public void setPointArray(int x, int y) {
		this.px.add(x);
		this.py.add(y);
	}
	public void removeArray(int x, int y) {
		this.px.remove(px.size()-1);
		this.py.remove(py.size()-1);
	}
	abstract public void draw(Graphics graphics);
	
	
}
