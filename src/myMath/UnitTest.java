package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnitTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPolynomString() {
		String s = "3*x^2+2*x^1";
		Polynom p1 = new Polynom(s);
		if(p1.poly.size()!=2) 
		{
			fail("Error the polynoms are hot the same");
		}

	}

	@Test
	void testF() {


	}

	@Test
	void testAddPolynom_able() {
		String s = "3*x^2+2*x^1";
		Polynom p1 = new Polynom(s);
		Polynom p = new Polynom();
		p.add(p1);
		if(!p.equals(p1)) {
			fail("Error the polynoms are hot the same");
		}
	}

	@Test
	void testAddMonom() {

		String s = "5*x^3+3*x^2+2*x^1";
		Polynom p1 = new Polynom(s);

		String s1 = "5*x^3+3*x^2";
		Polynom p = new Polynom(s1);
		Monom m1=new Monom("+2*x^1");
		p.add(m1);
		if(!p.equals(p1))
			fail("Error the polynoms are not the same");
	}

	@Test
	void testSubstract() {
		Polynom p = new Polynom("5*x^3");
		Polynom p1 = new Polynom("5*x^3+3*x^2");
		Polynom p2 = new Polynom("3*x^2");
		p1.substract(p2);
		if(!p.equals(p1))
			fail("Error the polynoms are not the same");
	}

	@Test
	void testMultiply() {
		Polynom p1 = new Polynom("x^2");
		Polynom p2 = new Polynom("x^2");
		Polynom p3 = new Polynom("x^4");
		p1.multiply(p2);
		if(!p1.equals(p3)) 
			fail("Error the polynoms are not the same");

	}

	@Test
	void testEqualsPolynom_able() {
		Polynom p1 = new Polynom("x^2");
		Polynom p2 = new Polynom("x^2");
		if(!p1.equals(p2))
			fail("Error the polynoms are not the same");
	}

	@Test
	void testCopy() {
		Polynom p = new Polynom("5*x^3+3*x^2");
		Polynom_able p1 = new Polynom();
		p1=(Polynom) p.copy();
		if(!p.equals(p1))
			fail("Error the polynoms are not the same");

	}

	@Test
	void testDerivative() {
		Polynom p1 = new Polynom("5*x^3+3*x^2");
		Polynom p2 = (Polynom)p1.derivative();
		Polynom p3 = new Polynom("15.0*x^2+6.0*x^1");
		if(!p3.equals(p2))
			fail("Error the polynoms are not the same");

	}

	@Test
	void testArea() {
		Polynom pa1= new Polynom ("4*x^1");
		double area= pa1.area(0, 6, 3);
		if(area!=36)
			fail("Error the areas are not the same");
	}

	@Test
	void testToString() {
		Polynom p1 = new Polynom("5*x^3+3*x^2");
		String s="+5*x^3+3*x^2";
		if(!p1.toString().equals(s))
			fail("Error the polynom and the string are not the same");
			}

}
