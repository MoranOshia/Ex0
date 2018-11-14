package myMath;

import java.util.ArrayList;
import java.util.Iterator;


import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

	 ArrayList<Monom> poly;

	/**
	 *  Default Constructor open new Array list of monoms.
	 * @param
	 */
	public Polynom() //Constructor
	{ 
		poly = new ArrayList<Monom>();

	}
	/**
	 * Constructor get Polynom_able p1 and make a deep copy to Polynom.
	 * @param Polynom_able p1
	 */
	public Polynom(Polynom_able p1) //Constructor
	{ 

		this.poly = new ArrayList<Monom>();
		Polynom p =new Polynom();
		Iterator<Monom> itP1 = p1.iteretor();
		Monom m=new Monom();
		while(itP1.hasNext())
		{
			m = new Monom(itP1.next());
			p.poly.add(m);
		}
		for (int i = 0; i <p.poly.size(); i++) {
			this.poly.add(p.poly.get(i));

		}

	}
	/**
	 * Constructor get String s and change him to Polynom type.
	 * @param String s
	 */
	public Polynom(String s)
	{
		poly=new ArrayList<Monom>();
		if(s.isEmpty())
		{
			throw new IllegalArgumentException("The string is empty");
		}

		else
		{
			String sMonom=""+s.charAt(0);

			for(int i=1;i<s.length();i++)
			{

				if(s.charAt(i)!='+'&&s.charAt(i)!='-')
				{
					if(s.charAt(i-1)=='-')
						sMonom="-"+sMonom+""+s.charAt(i);
					else
						sMonom=sMonom+""+s.charAt(i);
				}
				else
				{

					Monom m=new Monom(sMonom);
					poly.add(m);
					sMonom="";

				}

				if(i==s.length()-1)
				{
					Monom m=new Monom(sMonom);
					poly.add(m);
					sMonom="";
				}
			}

		}
	}
	// ********** add your code below ***********
	@Override
	public double f(double x) {

		double sum=0;
		Iterator<Monom> itP = poly.iterator();
		Monom m = new Monom();

		while(itP.hasNext())
		{
			m=itP.next();
			sum+=m.f(x);
		}

		return sum;

	}

	@Override
	public void add(Polynom_able p1) {
		// TODO Auto-generated method stub
		Iterator<Monom> itP = p1.iteretor();
		Monom m =new Monom();
		while(itP.hasNext())
		{
			m = new Monom(itP.next());
			//itP.remove();
			add(m);
		}
	}

	@Override
	public void add(Monom m1) {
		// TODO Auto-generated method stub


		if(poly.size()==0|| poly == null )
		{
			poly.add(m1);
		}
		else
		{
			Iterator<Monom> itP = poly.iterator();
			Monom m = new Monom();
			boolean flag =false;//check if found a monom with the same power if not add the monom to the polynom

			while(itP.hasNext()&&flag==false)
			{
				m=itP.next();
				if(m.get_power()==m1.get_power())
				{
					if(m.get_coefficient()+m1.get_coefficient()==0) 
						m=new Monom(0,m1.get_power());
					else {

						m=m.add(m1);
					}
					itP.remove();
					this.poly.add(m);
					flag=true;
				}
			}
			if(flag==false)
				poly.add(m1);
		}
	}

	@Override
	public void substract(Polynom_able p1) {
		// TODO Auto-generated method stub

		Iterator<Monom> itP = p1.iteretor();
		Monom m = new Monom();
		Monom negM = new Monom();
		while(itP.hasNext())
		{
			m=itP.next();
			negM=new Monom ((-m.get_coefficient()),m.get_power());
			add(negM);
		}

	}

	@Override
	public void multiply(Polynom_able p1) {
		// TODO Auto-generated method stub

		Iterator<Monom> itPoly = poly.iterator();
		Monom mPoly = new Monom();
		Iterator<Monom> itP1 = p1.iteretor();
		Monom mP1 = new Monom();
		Polynom_able p =new Polynom();
		while(itP1.hasNext())
		{
			mP1=itP1.next();
			while(itPoly.hasNext())
			{
				mPoly=itPoly.next();

				p.add(new Monom(mP1.multiply(mPoly)));
			}
		}

		this.poly=new ArrayList<Monom>();
		this.add(p);

	}

	@Override
	public boolean equals(Polynom_able p1) {
		sort();
		Polynom p =new Polynom(p1);
		p.sort();

		if(p.poly.size()!=this.poly.size()) return false;

		else
		{
			double pC=0,polyC=0;
			int pP=0,polyP=0;
			for (int i = 0; i <this.poly.size(); i++) {
				pC=p.poly.get(i).get_coefficient();
				polyC=this.poly.get(i).get_coefficient();
				pP=p.poly.get(i).get_power();
				polyP=this.poly.get(i).get_power();

				if(polyC!=pC &&polyP!=pP) return false;

			}
			return true;
		}
	}

	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		if(poly.size()==0 || poly==null)
			return true;
		else
			return false;
	}

	@Override
	/**
	 * /https://he.wikipedia.org/wiki/%D7%A9%D7%99%D7%98%D7%AA_%D7%94%D7%97%D7%A6%D7%99%D7%99%D7%94 For more information
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
	 * *	(i) x0<=x<=x2 && (ii) f(x2)<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return 
	 */
	//https://he.wikipedia.org/wiki/%D7%A9%D7%99%D7%98%D7%AA_%D7%94%D7%97%D7%A6%D7%99%D7%99%D7%94 For more information
	public double root(double x0, double x1, double eps) {

		double mid=0;
		if(f(x0)*f(x1)<=0)
		{
			while(f(x0)*f(x1)<eps)
			{
				mid=(x0+x1)/2;
				if(f(mid)==0)
					return mid;
				else
				{
					if(f(x0)*f(mid)>0)
						x0=mid;
		
					else
						x1=mid;
				}
			}
			return mid;
		}
		else
			throw new IllegalArgumentException("Not possible f(x0)*f(x1) must be small or equal to 0 ");
	}

	@Override
	public Polynom_able copy() {

		// TODO Auto-generated method stub
		Polynom p =new Polynom();
		Iterator<Monom> itP = p.iteretor();
		Monom m=new Monom();
		while(itP.hasNext())
		{
			m = new Monom(itP.next());
			p.add(m);
		}

		return p;
	}

	@Override
	public Polynom_able derivative() {
		Polynom p =new Polynom();
		Iterator<Monom> itP = this.poly.iterator();
		Monom m=new Monom();
		while(itP.hasNext())
		{
			m =new Monom (itP.next());
			m=m.derivative();
			p.add(m);
		}
		return p;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub

		if(x1<x0)
		{
			double temp=x0;
			x0=x1;
			x1=temp;
		}
		double width = (x1 - x0) / eps;
		double sum = 0;

		for (int i = 0; i <width; i++) {
			if(f(x0+ i*eps)>0)
			{
				sum+=f(x0+ i*eps)*eps;
			}        	
		}
		return sum;
	}
	/**
	 * Calculate the area the the X line in close area [xo,x1]
	 * @param poly the polynom function
	 * @param x0 starting point
	 * @param x1 end point 
	 * @return  sum - area of the function
	 */
	public double areaUnderX(Polynom poly, double x0, double x1) {

		double sum = 0;
		double temp=0;
		double eps=0.01;
		double width = (x1 - x0) / eps;
		
		if (x0 > x1) 
		{
			temp=x0;
			x0 = x1;
			x1 = x0;
		} 
		if(x0==x1) return 0;

		for (int i = 0; i <width; i++) {
			if(poly.f(x0+ i*eps)<0)
			{
				sum+=poly.f(x0+ i*eps)*eps;
			}        	
		}
		return sum;
		
	}
	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub
		Iterator<Monom> itP = this.poly.iterator();
		return itP;
	}
	/**
	 *	sort the Polynom in order to the biggest Monom to smallest.
	 * @param
	 * 
	 */
	private void sort()
	{
		final Monom_Comperator  mc =new Monom_Comperator();
		poly.sort(mc);	
	}
	/**
	 *	
	 * @param presenter the Polynom as String and return it.
	 * @return String
	 * 
	 */
	public String toString()
	{
		String ans="";
		if(poly.size()==0)
			throw new IllegalArgumentException("The Polynom is empty");

		else
		{
			sort();
			for (int i = 0; i <this.poly.size(); i++)
			{
				if(this.poly.get(i).get_coefficient()!=0)
				{
					if(this.poly.get(i).get_coefficient()<0)
						ans+=this.poly.get(i).get_coefficient()+"*x^"+this.poly.get(i).get_power();
					else
						ans+="+"+this.poly.get(i).get_coefficient()+"*x^"+this.poly.get(i).get_power();
				}
			}
			return ans;
		}
	}



}
