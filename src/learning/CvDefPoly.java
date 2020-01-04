package learning;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import learning.Point2D;

public class CvDefPoly extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<Point2D> v = new Vector<Point2D>();
	float x0, y0, rWidth = 10f, rHeight = 7.5f, pixelSize;
	boolean ready = true;
	int centerX, centerY;

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

	public CvDefPoly() {
		// TODO Auto-generated constructor stub
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				// TODO Auto-generated method stub
				super.mousePressed(evt);
				float xA = fx(evt.getX()), yA = fy(evt.getY());
				if (ready) {
					v.removeAllElements();
					x0 = xA;
					y0 = yA;
					ready = false;
				}
				float dx = xA - x0;
				float dy = yA - y0;
				if (v.size() > 0 && dx * dx + dy * dy < 4 * pixelSize * pixelSize) {
					// polygon closed
					System.out.println("Polygon ended");
					ready = true;
				} else {
					System.out.printf("add point (%f, %f)\n", xA, yA);
					v.addElement(new Point2D(xA, yA));
				}
				repaint();
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		initgr();
		int left = iX(-rWidth / 2), right = iX(rWidth / 2);
		int bottom = iY(-rHeight / 2), top = iY(rHeight / 2);
		// bottom > top in device coordinate
		g.drawRect(left, top, right - left, bottom - top);
		int n = v.size();
		if(n == 0) return;
		Point2D a = v.elementAt(0);
		g.drawRect(iX(a.x) - 2, iY(a.y) - 2, 4, 4);
		for(int i=1;i<=n;i++) {
			if(i == n && !ready) break;
			Point2D b = v.elementAt(i%n);
			g.drawLine(iX(a.x), iY(a.y), iX(b.x), iY(b.y));
			a = b;
		}
	}
}
