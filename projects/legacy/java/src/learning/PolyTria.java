package learning;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PolyTria extends Frame {
	public PolyTria() {
		// TODO Auto-generated constructor stub
		super("Define polygon vertices by clicking and triangulate it.");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(0);
			}
		});
		setSize(500, 300);
		add("Center", new CvPolyTria());
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setVisible(true);
	}

	public static void main(String[] args) {
		new PolyTria();
	}
}

class CvPolyTria extends CvDefPoly {

	public void paint(Graphics g) {
		int n = v.size();
		g.setColor(Color.black);
		super.paint(g);
		if (n > 3 && ready) {
			System.out.println("Begin triangulated");
			Point2D[] p = new Point2D[n];
			for (int i = 0; i < n; i++)
				p[i] = v.elementAt(i);
			if (!Tools2D.ccw(p)) {
				for (int i = 0; i < n; i++) {
					p[i] = v.elementAt(v.size() - i - 1);
				}
			}
			int ntr = n - 2;
			Triangle[] tr = new Triangle[ntr];
			Tools2D.triangulate(p, tr);
			initgr();
	
			for (int i = 0; i < ntr; i++) {
				g.setColor(new Color(rand256(), rand256(), rand256()));
				int[] x = new int[3];
				int[] y = new int[3];
				x[0] = iX(tr[i].a.x);
				y[0] = iY(tr[i].a.y);
				x[1] = iX(tr[i].b.x);
				y[1] = iY(tr[i].b.y);
				x[2] = iX(tr[i].c.x);
				y[2] = iY(tr[i].c.y);
				System.out.println("a:" + x[0] + "," + y[0]);
				System.out.println("b:" + x[1] + "," + y[1]);
				System.out.println("c:" + x[2] + "," + y[2]);
				// 已经转换为屏幕坐标
				g.fillPolygon(x, y, 3);
			}
		}
	}

	int rand256() {
		return (int) (Math.random() * 256);
	}
}
