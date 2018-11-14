package myMath;

public class TestClass {

	public static void main(String[] args) {


		//************** TESTING MONOM *********************

		Monom m1 = new Monom ();
		Monom m2 = new Monom (-3,5);
		Monom m3 = new Monom ("3*x^7");
		Monom m4 = new Monom (1,1);

		// Constructors checking \\

		//constructors
		System.out.println("Constructor: *(0,0)*: " + m1.toString() );
		System.out.println("Constructor: *(-3,5)*: " + m2.toString() );
		System.out.println("Constructor: *\"3*x^7.6\"*: " + m3.toString() );
		System.out.println("Constructor: *(1,1)*: " + m4.toString() );
		System.out.println();

		//copy constructor
		m1 = new Monom (m2);
		m4 = new Monom (m3);
		System.out.println("Copy Constructor: *(0,0)* ---->(-3,5)*: " + m1.toString() );
		System.out.println("Copy Constructor: *(1,1)---->3*x^7**: " + m4.toString() );
		System.out.println();

		// Checking f  \\
		m1 = new Monom ();
		m2 = new Monom (-3,5);
		m3 = new Monom ("3*x^7");
		System.out.println("f(2)="+m1.toString()+"=0-----> " + m1.f(2) );	
		System.out.println("f(-1)="+m2.toString()+"=3-----> " + m2.f(-1) );
		System.out.println(" f(1):="+m2.toString()+"=-3-----> " + m3.f(-1) );
		System.out.println();

		// Checking Add  \\
		//System.out.println("Throw an Exption error");
		//m1.add(m2);
		m1=new Monom(5,5);
		m1=m1.add(m2);
		System.out.println("Add: 2*x^5 --->"+m1.toString());
		System.out.println();

		// Checking multiply  \\
		m2 = new Monom (-3,5);
		m3 = new Monom ("3*x^7");
		System.out.println("Multiply: -9*x^12 --->"+m2.multiply(m3).toString());
		System.out.println();

		// Checking derivative  \\
		m2 = new Monom (-3,5);
		System.out.println("derivative: -15*x^4 --->"+m2.derivative().toString());
		System.out.println();


		//************** TESTING POLYNOM *********************

		m1 = new Monom ();
		m2 = new Monom (-3,5);
		m3 = new Monom ("3*x^7");
		Polynom_able pa = new Polynom ();
		Polynom_able pa1 = new Polynom ("3*x^3+2*x^2+1*x^1");



		// Constructors checking \\

		//Constructors
		//System.out.println(pa.toString());

		//Copy Constructor + String Constructor
		pa=new Polynom(pa1);
		System.out.println("Copy Constructor"+pa1.toString()+"--->"+pa.toString());
		System.out.println();

		// Checking f \\
		System.out.println("f(1)="+pa.toString()+"=6 ---> "+pa.f(1));
		System.out.println();

		// Checking add(Polynom_able p1)\\
		pa.add(pa1);
		System.out.println(" Add Polynom_Able: +0.0*x^9+6.0*x^3+4.0*x^2+2.0*x^1 ---> "+pa.toString());
		System.out.println();

		// Checking add(Monom m1)\\
		pa = new Polynom ("3*x^3+2*x^2+1*x^1+0*x^9");
		pa.add(m3);
		System.out.println("Add Monom: 3*x^3+2*x^2+1*x^1+0*x^9  + 3*x^7 =  "+pa);
		System.out.println();

		// Checking substract\\
		pa1 = new Polynom ("3*x^3+2*x^2+1*x^1+0*x^9");
		pa1.substract(pa);
		System.out.println("3*x^3+2*x^2+1*x^1+0*x^9 -  3*x^3+2*x^2+1*x^1+3*x^7+0*x^9   "+pa1.toString());
		System.out.println();

		// Checking multiply\\
		pa = new Polynom ("3*x^3-9*x^2");
		pa1 = new Polynom ("3*x^3");
		pa.multiply(pa1);
		System.out.println("Multiply: 3*x^3-9*x^2 * 3*x^3 = " +pa.toString());
		System.out.println();

		// Checking equal\\
		pa = new Polynom ("3*x^3");
		pa1 = new Polynom ("3*x^3");
		pa.equals(pa1);
		System.out.println("Equal:3*x^3==3*x^3 --->"+pa.equals(pa1));

		pa = new Polynom ("3*x^3-9*x^2");
		pa1 = new Polynom ("3*x^3");
		pa.equals(pa1);
		System.out.println("Equal:3*x^3==3*x^3-9*x^2 --->"+pa.equals(pa1));
		System.out.println();

		// Checking derivative\\
		pa1 = new Polynom ("3*x^3-5*x^2");
		pa1=pa1.derivative();
		System.out.println("The derivative : +9.0*x^2-10.0*x^1 --->"+pa1.toString());
		System.out.println();

		// Checking root \\
		pa1= new Polynom ("4*x^1");
		//System.out.println("Root:  " + pa1.root(1, 5, 0.001));

		// Checking area\\
		pa1= new Polynom ("4*x^1");
		System.out.println("Area: "+ pa1.area(0, 6, 3));
		System.out.println("Area(x1<xo): "+ pa1.area(6, 0, 3));
		
	}

}
