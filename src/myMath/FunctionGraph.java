package myMath;


import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.colors.ColorMapper;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class FunctionGraph extends JFrame {


	private static final long serialVersionUID = 1L;

	public FunctionGraph(Polynom p, double x0, double x1) {

		DataTable pointsOfThePoly = creatDTPolyXY(p,x0, x1);
		DataTable extremum = extremumPoints(p, x0, x1);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		
		XYPlot plot = new XYPlot(pointsOfThePoly, extremum);
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(pointsOfThePoly, lines);
		Color c = new Color(0,215,0);
		plot.getPointRenderers(pointsOfThePoly).get(0).setColor(c);
		plot.getLineRenderers(pointsOfThePoly).get(0).setColor(c);
	}

	/**
	 * create DataTable of the points of the polynom jump by 0.5
	 * @param poly - polynom
	 * @param x0 - start point
	 * @param x1 - end point
	 * @return - data table points 
	 */

	private DataTable creatDTPolyXY(Polynom poly, double x0, double x1) {
		DataTable pXY = new DataTable(Double.class,Double.class);

		for (double i = x0; i <= x1; i+=0.5) {
			pXY.add(i, poly.f(i));
		}
		return pXY;
	}

	/**
	 * Finds the extremum points of the function
	 * @param poly - polynom function
	 * @param x0 - start point
	 * @param x1 - end point
	 * @return -DataTable of the points that suspected to be extremum in the function
	 */
	private DataTable extremumPoints(Polynom poly, double x0, double x1) {

		DataTable extremum = new DataTable(Double.class, Double.class);   
		Polynom_able pDer = poly.derivative();

		for (double x = x0; x <= x1; x+=0.01) {
			double xNext=x+0.01;
			double d0 = pDer.f(x);
			double d1 = pDer.f(xNext);
			if (d0*d1<=0) 
			{
				
				double pointX;
				pointX=rootExm(x, xNext, 0.001,pDer);
				double pointY = poly.f(pointX);
				pointX= Double.parseDouble(df2.format(pointX));
				pointY=Double.parseDouble(df2.format(pointY));
				extremum.add(pointX, pointY);

				System.out.println("(" + pointX + ", " + pointY + ")");
				x=pointX;
			}
		}

		return extremum;
	}
	public  double rootExm(double x0, double x1, double eps,Polynom_able p) {

		double mid=0;
		if(p.f(x0)*p.f(x1)<=0)
		{
			while(p.f(x0)*p.f(x1)<eps)
			{
				mid=(x0+x1)/2;
				if(p.f(mid)==0)
					return mid;
				else
				{
					if(p.f(x0)*p.f(mid)>0) {
						x0=mid;
						mid = (x1+x0)/2;
					}

					else {
						x1=mid;
						mid = (x1+x0)/2;
					}
				}
			}
			return mid;
		}
		else
			throw new IllegalArgumentException("Not possible f(x0)*f(x1) must be small or equal to 0 ");
	}

	private static DecimalFormat df2 = new DecimalFormat(".###"); //present 3 digit after points


}



