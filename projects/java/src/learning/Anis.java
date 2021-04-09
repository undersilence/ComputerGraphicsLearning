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

public class Anis extends Frame {
	public Anis() {
		// TODO Auto-generated constructor stub
		super("Anisotropic mapping mode");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(0);
			}
		});
		setSize(400, 300);
		add("Center", new CvAnis());
		setVisible(true);
	}

	public static void main(String[] args) {
		new Anis();
	}
}

class CvAnis extends Canvas {
	int maxX, maxY;
	float pixelWidth, pixelHeight;
	float rWidth = 10.0f, rHeight = 7.5f;
	float xP = -1, yP;

	public CvAnis() {
		// TODO Auto-generated constructor stub
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				xP = fx(e.getX());
				yP = fy(e.getY());
				repaint();
			}
		});
	}

	void initgr() {
		Dimension d = getSize();
		maxX = d.width - 1;
		maxY = d.height - 1;
		pixelWidth = rWidth / maxX;
		pixelHeight = rHeight / maxY;
	}

	int iX(float x) {
		return Math.round(x / pixelWidth);
	}

	int iY(float y) {
		return maxY - Math.round(y / pixelHeight);
	}

	float fx(float x) {
		return x * pixelWidth;
	}

	float fy(float y) {
		return (maxY - y) * pixelHeight;
	}

	public void paint(Graphics g) {
		initgr();
		int left = iX(0), right = iX(rWidth);
		int bottom = iY(0), top = iY(rHeight);
		if (xP >= 0)
			g.drawString("Logical coordinates of selected point: " + xP + " " + yP, 20, 100);
		g.setColor(Color.red);
		g.drawLine(left, bottom, right, bottom);
		g.drawLine(right, bottom, right, top);
		g.drawLine(right, top, left, top);
		g.drawLine(left, top, left, bottom);
	}
}