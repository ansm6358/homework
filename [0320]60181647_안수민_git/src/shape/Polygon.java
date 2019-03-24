package shape;

import java.awt.Graphics;

public class Polygon extends Shape {
	public void draw(Graphics graphics) {
		int[] arrayX = new int[px.size()];
		int[] arrayY = new int[py.size()];
		
		for(int i= 0; i < arrayX.length; i++) {
		arrayX[i] = px.get(i).intValue();
		}
		
		for(int i= 0; i < arrayY.length; i++) {
			arrayY[i] = py.get(i).intValue();	
			}

		graphics.drawPolygon(arrayX, arrayY, arrayX.length);
	}
}