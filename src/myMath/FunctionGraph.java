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
		show (pointsOfThePoly, extremum);
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

			double d0 = pDer.f(x-0.01);
			double d1 = pDer.f(x+0.01);
			if (checkDerivative(d0, d1)==true) {

				while (checkDerivative(d0, d1)==true) {
					x += 0.01;
					d0 = pDer.f(x-0.01);
					d1 = pDer.f(x+0.01);
				}
				double pointX= x;
				double pointY = poly.f(pointX);
				pointX= Double.parseDouble(df2.format(pointX));
				pointY=Double.parseDouble(df2.format(pointY));
				extremum.add(pointX, pointY);

				//System.out.println("(" + pointX + ", " + pointY + ")");
				x=pointX;
			}
		}

		return extremum;
	}

	private static DecimalFormat df2 = new DecimalFormat(".###"); //present 3 digit after points

	

	/**
	 * check if the derivative if zero in the range of the epsilon
	 * @param d0 - the answer in x of the derivative minus the epsilon
	 * @param d1 - the answer in x of the derivative plus the epsilon
	 * @return true if it possible to min or max
	 */
	private boolean checkDerivative(double d0, double d1) {
		if (d0 < 0) {//possible of minimum
			if (d1 > 0) {
				return true;
			}
		}
		else if (d0 > 0) {//possible of maximum
			if (d1 < 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param ploy points of the polynom's function
	 * @param points that are suspected to be extremum in the function
	 */
	private void show (DataTable poly, DataTable ext) {

		XYPlot plot = new XYPlot(poly, ext);
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(poly, lines);
		Color c = new Color(0,215,0);
		plot.getPointRenderers(poly).get(0).setColor(c);
		plot.getLineRenderers(poly).get(0).setColor(c);
	}








}



