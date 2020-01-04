package learning;

import java.awt.print.Printable;
import java.math.BigInteger;

public class Main {
	static boolean testException() {
		try {
			BigInteger[] x = new BigInteger[5];
			x[2].abs();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			return true;
		}
	}

	public static void main(String[] args) {
		try {
			testException();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.println("Exception loop over");
		}
	}
}
