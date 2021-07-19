package learning;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Rectangles extends Frame {
	public static void main(String[] args) {
		new Rectangles();
	}

	public Rectangles() {
		// TODO Auto-generated constructor stub
		super("Rectangles: 80  rectangles inside each other.");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(0);
			}
		});
		setSize(600, 400);
		add("Center", new CvRectangles());
		setVisible(true);
	}
}

class CvRectangles extends Canvas {
	int maxX, maxY, minMaxXY, xCenter, yCenter;

	void initgr() {
		Dimension dimension = getSize();
		maxX = dimension.width - 1;
		maxY = dimension.height - 1;
		minMaxXY = Math.min(maxX, maxY);
		xCenter = maxX / 2;
		yCenter = maxY / 2;
	}

	int iX(float x) {
		return Math.round(x);
	}

	int iY(float y) {
		return maxY - Math.round(y);
	}

	public void paint(Graphics g) {
		initgr();
		float side = 0.95f * minMaxXY; // side length
		float sideHalf = 0.5f * side;
		float xA, xB, xC, xD, yA, yB, yC, yD;
		float xA1, xB1, xC1, xD1, yA1, yB1, yC1, yD1;
		float q = 0.02f;
		float p = 1 - q;
		xA = xCenter - sideHalf;
		yA = yCenter - sideHalf;
		xB = xCenter + sideHalf;
		yB = yA;
		xC = xB;
		yC = yA + side;
		xD = xA;
		yD = yA + side;
		for (int i = 0; i < 80; i++) {
			g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
			g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
			g.drawLine(iX(xC), iY(yC), iX(xD), iY(yD));
			g.drawLine(iX(xD), iY(yD), iX(xA), iY(yA));
			xA1 = xA * p + xB * q;
			xB1 = xB * p + xC * q;
			xC1 = xC * p + xD * q;
			xD1 = xD * p + xA * q;
			yA1 = yA * p + yB * q;
			yB1 = yB * p + yC * q;
			yC1 = yC * p + yD * q;
			yD1 = yD * p + yA * q;
			xA = xA1;
			xB = xB1;
			xC = xC1;
			xD = xD1;
			yA = yA1;
			yB = yB1;
			yC = yC1;
			yD = yD1;
		}
	}
}