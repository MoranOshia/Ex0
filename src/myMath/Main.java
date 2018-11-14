package myMath;
import java.awt.Color;
import java.awt.Font;


public class Main {

	
	public static void main(String []arg)
	{
		Polynom p=new Polynom("1*x^3-4x^1");
		FunctionGraph fp=new FunctionGraph(p,0,6);

		// number of line segments to plot

		//		// plot the approximation to the function
		//		for (int i = 0; i < n; i++) {
		//		StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
		//		}
		//		StdDraw.setPenColor(Color.RED);
		//		StdDraw.setPenRadius(0.01);
		//		StdDraw.point(x[n/2], 1);

	}


}
