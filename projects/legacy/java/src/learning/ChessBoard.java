package learning;
import java.awt.*;
import java.awt.event.*;

public class ChessBoard extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChessBoard() {
		super("ChessBoard");
		addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(0);
			}
		});
		setSize(408, 434);
		add("Center", new CvChessBoard());
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChessBoard();
	}
	
}

class CvChessBoard extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		Dimension dimension = getSize();
		int w = 50;
		for (int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				g.setColor((i-j+8)%2==0 ? 
						Color.LIGHT_GRAY : Color.DARK_GRAY);
				g.fillRect(i*w, j*w, w, w);
			}
		}
	}
}