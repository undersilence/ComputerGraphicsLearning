package learning;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.sun.org.apache.bcel.internal.generic.SWAP;

public class Problem2_2 extends Frame {
	public Problem2_2() {
		// TODO Auto-generated constructor stub
		super("Define Arc.");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(0);
			}
		});
		setSize(1280, 800);
		add("Center", new CvProblem2_2());
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setVisible(true);
	}

	public static void main(String[] args) {
		new Problem2_2();
	}
}

class CvProblem2_2 extends CvDefPoly {
	public void paint(Graphics g) {
		if (v.size() == 3) {
			ready = true;
		}
		super.paint(g);
		if (v.size() >= 3 && ready) {
			Point2D a = v.elementAt(0);
			Point2D b = v.elementAt(1);
			Point2D c = v.elementAt(2);
			Circle cir = Tools2D.circumCircle(a, b, c);
			Point2D o = cir.o;
			if(Tools2D.area2(a, b, c) < 0) {
				Point2D t = new Point2D(a.x, a.y);
				a = c;
				c = t;
			}
			double startArc = Math.atan2(a.y - o.y, a.x - o.x);
			double finalArc = Math.atan2(c.y - o.y, c.x - o.x);
			if(finalArc < startArc) {
				finalArc += 2 * Math.PI;
			}
			int radius = (int) (cir.r / pixelSize);
			System.out.println("Get startArc: " + Math.toDegrees(startArc) + ",finalArc: " + Math.toDegrees(finalArc));
			g.drawOval(iX(o.x) - 2, iY(o.y) - 2, 4, 4);
			g.drawArc(iX(o.x) - radius, iY(o.y) - radius, radius + radius, radius + radius,
					(int) Math.toDegrees(startArc), (int) (Math.toDegrees(finalArc - startArc)));
		}
	}
}
