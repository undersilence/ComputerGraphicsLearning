package learning;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.sun.java.swing.Painter;

public class Isot extends Frame {
	public Isot() {
		// TODO Auto-generated constructor stub
		super("Isotropic mapping mode");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(0);
			}
		});
		setSize(400, 300);
		add("Center", new CvIsot());
		setVisible(true);
	}

	public static void main(String[] args) {
		new Isot();
	}
}

class CvIsot extends Canvas {
	int centerX, centerY;
	float pixelSize, rWidth = 10f, rHeight = 7.5f;
	float xP = 1000000, yP;

	CvIsot() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				xP = fx(e.getX());
				yP = fy(e.getY());
				repaint();
			}
		});
	}

	void initgr() {
		Dimension d = getSize();
		int maxX = d.width - 1;
		int maxY = d.height - 1;
		pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
		centerX = maxX / 2;
		centerY = maxY / 2;
	}

	int iX(float x) {
		return Math.round(centerX + x / pixelSize);
	}

	int iY(float y) {
		return Math.round(centerY - y / pixelSize);
	}

	float fx(int x) {
		return (x - centerX) * pixelSize;
	}

	float fy(int y) {
		return (centerY - y) * pixelSize;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		initgr();
		int left = iX(-rWidth / 2), right = iX(rWidth / 2);
		int bottom = iY(-rHeight / 2), top = iY(rHeight / 2);
		int xMiddle = iX(0), yMiddle = iY(0);

		System.out.printf("left:%d right:%d bottom:%d top:%d xMid:%d yMid:%d\n", left, right, bottom, top, xMiddle,
				yMiddle);
		g.drawLine(xMiddle, bottom, right, yMiddle);
		g.drawLine(right, yMiddle, xMiddle, top);
		g.drawLine(xMiddle, top, left, yMiddle);
		g.drawLine(left, yMiddle, xMiddle, bottom);

		if (xP != 1000000) {
			g.drawString("Logical coordinates of clicked point: " + xP + " " + yP, 20, 100);
		}
	}
}