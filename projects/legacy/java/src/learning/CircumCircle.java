package learning;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CircumCircle extends Frame{
	public CircumCircle() {
		// TODO Auto-generated constructor stub
		super("Define circle get circumcircle.");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(0);
			}
		});
		setSize(1280, 800);
		add("Center", new CvCircumCircle());
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setVisible(true);
	}

	public static void main(String[] args) {
		new CircumCircle();
	}
}

class CvCircumCircle extends CvDefPoly {
	public void paint(Graphics g) {
		if(v.size() == 3) {
			ready = true;
		}
		super.paint(g);
		int n = v.size();
		if (n >= 3 && ready) {
			Point2D a = v.elementAt(0);
			Point2D b = v.elementAt(1);
			Point2D c = v.elementAt(2);
			Circle O = Tools2D.circumCircle(a, b, c);
			System.out.println("Draw circle at " + O.o.x + "," + O.o.y + "," + O.r);
			int radius = (int)(O.r/pixelSize);
			g.drawOval(iX(O.o.x) - radius, iY(O.o.y) - radius, radius + radius, radius + radius);
		}
	}
}