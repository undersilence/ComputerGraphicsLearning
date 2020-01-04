package learning;

public class Point2D {
	float x, y;

	public Point2D(float x, float y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}
}

class Tool2D {
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
		return Tool2D.area2(p[prev], p[k], p[next]) > 0;
	}
	
	static float area2(Point2D[] poly) {
		int n = poly.length;
		int j = n - 1;
		float area = 0;
		for(int i=0;i<n;i++) {
			area += poly[j].x * poly[i].y - poly[j].y * poly[i].x;
		}
		return area;
	}
	
	static boolean insideTriangle(Point2D a, Point2D b, Point2D c, Point2D p) {
		//ABC is assumed to be counter-clockwise
		return area2(a, b, p) >= 0 && area2(b, c, p) >= 0 && area2(c, a, p) >=0;
	}
	
	
}