package learning;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DefPoly extends Frame{
	public DefPoly() {
		// TODO Auto-generated constructor stub
		super("Define polygon vertices by clicking");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(0);
			}
		});
		setSize(500, 300);
		add("Center", new CvDefPoly());
		setVisible(true);
	}
	public static void main(String[] args) {
		new DefPoly();
	}
}
