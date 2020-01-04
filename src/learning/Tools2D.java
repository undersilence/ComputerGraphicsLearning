package learning;

import java.awt.Polygon;

import sun.launcher.resources.launcher;

public class Tools2D {
	static float area2(Point2D a, Point2D b, Point2D c) {
		return (a.x - c.x) * (b.y - c.y) - (a.y - c.y) * (b.x - c.x);
	}

	static boolean ccw(Point2D[] p) {
		int n = p.length, k = 0;
		for (int i = 1; i < n; i++) {
			if (p[i].x == p[k].x ? p[i].y < p[k].y : p[i].x < p[k].x) {
				k = i;
			}
		}
		int prev = k - 1, next = k + 1;
		if (prev == -1)
			prev = n - 1;
		if (next == n)
			next = 0;
		return Tools2D.area2(p[prev], p[k], p[next]) > 0;
	}

	static float area2(Point2D[] poly) {
		int n = poly.length;
		int j = n - 1;
		float area = 0;
		for (int i = 0; i < n; i++) {
			area += poly[j].x * poly[i].y - poly[j].y * poly[i].x;
		}
		return area;
	}

	static boolean insideTriangle(Point2D a, Point2D b, Point2D c, Point2D p) {
		// ABC is assumed to be counter-clockwise
		return area2(a, b, p) >= 0 && area2(b, c, p) >= 0 && area2(c, a, p) >= 0;
	}

	static boolean insidePolygon(Point2D p, Point2D[] poly) {
		int n = poly.length, j = n - 1;
		float x = p.x, y = p.y;
		boolean inside = false;
		for (int i = 0; i < n; i++) {
			if (poly[j].y <= y && y < poly[i].y && area2(poly[j], poly[i], p) > 0 && poly[i].y <= y && y < poly[j].y
					&& area2(poly[i], poly[j], p) > 0)
				inside = !inside;
			j = i;
		}
		return inside;
	}

	static boolean onSegment(Point2D a, Point2D b, Point2D p) {
		double eps = 1e-2;
		return (a.x != b.x && (a.x <= p.x && p.x <= b.x || b.x <= p.x && p.x <= a.x)
				|| a.x == b.x && (a.y <= p.y && p.y <= b.y || b.y <= p.y && p.y <= a.y))
				&& Math.abs(area2(a, b, p)) < eps;
	}

	static boolean projOnSegment(Point2D a, Point2D b, Point2D p) {
		double eps = 1e-2;
		double ux = b.x - a.x, uy = b.y - a.y;
		double len2 = ux * ux + uy * uy;
		double inprod = ux * (p.x - a.x) + uy * (p.y - a.y);
		return inprod > -eps && inprod < eps + len2;
	}

	// Compute P' (P projected on AB)
	static Point2D projection(Point2D a, Point2D b, Point2D p) {
		float vx = b.x - a.x, vy = b.y - a.y;
		float len2 = vx * vx + vy * vy;
		float inprod = vx * (p.x - a.x) + vy * (p.y - a.y);
		return new Point2D(a.x + inprod * vx / len2, a.y + inprod * vy / len2);
	}

	// Compute P', the projection of P on line l given as
	// ax + bx = h, where a * a + b * b = 1
	static Point2D projection(float a, float b, float h, Point2D p) {
		float d = p.x * a + p.y * b - h;
		return new Point2D(p.x - d * a, p.y - d * b);
	}

	static void triangulate(Point2D[] p, Triangle[] tr) {
		int n = p.length, j = n - 1, iA = 0, iB, iC;
		int[] next = new int[n];
		for (int i = 0; i < n; i++) {
			next[j] = i;
			j = i;
		}
		for (int k = 0; k < n - 2; k++) {
			Point2D a, b, c;
			boolean triaFound = false;
			int count = 0;
			while (!triaFound && ++count < n) {
				iB = next[iA];
				iC = next[iB];
				a = p[iA];
				b = p[iB];
				c = p[iC];
				if(Tool2D.area2(a,b,c) >= 0) {
					// Edges AB and BC
					j = next[iC];
					while(j!=iA && !insideTriangle(a, b, c, p[j])) {
						j = next[j];
					}
					if(j == iA) {
						tr[k] = new Triangle(a, b, c);
						next[iA] = iC;
						triaFound = true;
					}
				}
				iA = next[iA];
			}
			if (count == n) {
				System.out.println("Not a proper polygon or vertex sequence not ccw");
				return;
			}
		}
	}
	
	static float distance2(Point2D a, Point2D b) {
		return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
	}
	
	static Circle circumCircle(Point2D a, Point2D b, Point2D c) {
		float ux = b.x - a.x, vx = c.x - a.x;
		float uy = b.y - a.y, vy = c.y - a.y;
		float det = 2 * (ux * vy - uy * vx);
		float f = (b.x * b.x + b.y * b.y) - (a.x * a.x + a.y * a.y);
		float g = (c.x * c.x + c.y * c.y) - (a.x * a.x + a.y * a.y);
		Point2D o = new Point2D((f * vy - g * uy)/det, (ux * g - vx * f)/det);
		return new Circle(o, (float)Math.sqrt(distance2(o, a)));
	}
}