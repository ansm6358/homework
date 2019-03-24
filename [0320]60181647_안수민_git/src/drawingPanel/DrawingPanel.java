package drawingPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import global.Constants.EToolBar;
import shape.Shape;

public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private MouseHandler mouseHandler;
	
	private boolean start = false;
	private Shape currentTool;
	public void setCurrentTool(EToolBar currentTool) {
		this.currentTool = currentTool.getShape();
	}
	public DrawingPanel() {
		this.setBackground(Color.WHITE);
		
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);
		
		currentTool = EToolBar.rectangle.getShape(); //버튼 순서를 바꾸면 설렉트를 부르는데 버튼이 다른것이 눌려 있다
	}
	
	private void drawShape(int x, int y) {
		if(!currentTool.equals(EToolBar.Polygon.getShape())){
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		
		this.currentTool.setOrigin(x, y);
		this.currentTool.draw(graphics);
	}}
	private void moveShape(boolean last, int x, int y) {
		if(!currentTool.equals(EToolBar.Polygon.getShape())){
		Graphics graphics = this.getGraphics();
		if(!last) {
		graphics.setXORMode(getBackground());
		Graphics2D graphics2d = (Graphics2D) graphics;
		float[] dot = new float[] { 2, 2, 2, 2 };
		graphics2d.setStroke(new BasicStroke(1, 0, BasicStroke.JOIN_MITER, 1.0f, dot, 0));

		this.currentTool.draw(graphics2d);
		this.currentTool.setPoint(x, y);
		this.currentTool.draw(graphics2d);
		graphics2d.setStroke(new BasicStroke(1, 0, BasicStroke.JOIN_MITER, 1.0f, null, 0));

		} else if (last) {
			this.currentTool.draw(graphics);

		}
	}}

	private void drawPoly(int x, int y, int clickcount) {
		if(currentTool.equals(EToolBar.Polygon.getShape())){
			if(!start) {
				start = true; //점을 찍었는지 확인용
				this.currentTool.newArray();
				this.currentTool.setOriginArray(x, y);

			}
		Graphics graphics = this.getGraphics();
		if(clickcount == 1) {	
			
			Graphics2D graphics2d = (Graphics2D) graphics;
			float[] dot = new float[] { 2, 2, 2, 2 };
			graphics2d.setStroke(new BasicStroke(1, 0, BasicStroke.JOIN_MITER, 1.0f, dot, 0));

			this.currentTool.removeArray(x, y);
			this.currentTool.setOriginArray(x, y);
			this.currentTool.draw(graphics2d);
			graphics2d.setStroke(new BasicStroke(1, 0, BasicStroke.JOIN_MITER, 1.0f, null, 0));

		} else if(clickcount==2) {
			start = false; //끝났음을 알림
			this.currentTool.removeArray(x, y);
			this.currentTool.setOriginArray(x, y);
			this.currentTool.draw(graphics);
			}
					
		}
	}
	private void movePoly(int x, int y) {
		if(currentTool.equals(EToolBar.Polygon.getShape())){
		if(start) {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		Graphics2D graphics2d = (Graphics2D) graphics;
		float[] dot = new float[] { 2, 2, 2, 2 };
		graphics2d.setStroke(new BasicStroke(1, 0, BasicStroke.JOIN_MITER, 1.0f, dot, 0));

		this.currentTool.draw(graphics);
		this.currentTool.removeArray(x, y);
		this.currentTool.setPointArray(x, y);
		this.currentTool.draw(graphics);
		}}
		}
	private class MouseHandler implements MouseListener, MouseMotionListener {
		private boolean last;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			drawPoly(e.getX(),e.getY(),e.getClickCount());
		}
		@Override
		public void mousePressed(MouseEvent e) {
			
			drawShape(e.getX(),e.getY());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			last = true;
			moveShape(last, e.getX(),e.getY());
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			last = false;
			moveShape(last, e.getX(),e.getY());

		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			movePoly(e.getX(),e.getY());
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {	
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
		}
		
	}


}
