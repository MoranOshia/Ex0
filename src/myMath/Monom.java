
package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{


	private double _coefficient; // 
	private int _power; 

	// ***************** Constructors **********************
	/**
	 *  Default Constructor make zero Monom.
	 * @param
	 */
	public Monom(){//Default Constructor
		this.set_coefficient(0);
		this.set_power(0);
	}
	/**
	 *   Constructor get a,b and make Monom with a as coefficient and b as power.
	 * @param double a as coefficient
	 * @param double b as power
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 *   Constructor get Monom and make Monom with same coefficient and power of this Monom.
	 * @param Monom ot
	 * 
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	/**
	 * Constructor get String s and change him to Monom type.
	 * @param String s
	 */
	public Monom(String s){//Constructor String 

		int a=0 ,b=0;
		String aS="";
		String bS="";
		int iEnd=0;
		boolean flag =true;


		if(s.isEmpty()==true)
		{
			throw new IllegalArgumentException("Not possible the String is empty");
		}

		else 
		{
			s = s.replaceAll("X", "x");
			s = s.replaceAll(" ", "");

			if(!s.contains("x"))
			{
				a=Integer.parseInt(s);
				this.set_coefficient(a);
				this.set_power(0);
			}
			else
			{
				for(int i=0;i<s.length()&&flag;i++)
				{
					if(s.charAt(i)!='x'&&s.charAt(i)!='*')
					{
						if(s.charAt(i)=='^')
						{
							flag=false;
							iEnd=i;
						}
						else
							aS=aS+""+s.charAt(i);

					}

				}
				for(int i=iEnd+1;i<s.length();i++)
				{
					bS=bS+""+s.charAt(i);
				}

				a=Integer.parseInt(aS);
				b=Integer.parseInt(bS);
				this.set_coefficient(a);
				this.set_power(b);
			}
		}

	}

	// ***************** add your code below **********************
	@Override
	public double f(double x) {

		return _coefficient*Math.pow(x, _power);
	}
	/**
	 * return the derivative of the Monom
	 * @param 
	 * @return Monom who's the derivative 
	 */
	public Monom derivative() {

		Monom m= new Monom ();
		if(get_coefficient()!=0||get_power()!=0)
		{
			m=new Monom (get_coefficient()*get_power(),get_power()-1);
		}
		else
		{

			set_coefficient(0);
			set_coefficient(0);
			m=new Monom (0,0);
		}
		return m;

	}
	/**
	 * Check if it possible to add the Monom we get and if so add them to one Monom
	 * @param Monom m
	 * @return Monom who's the sum 
	 */
	public Monom add(Monom m) {

		if(m._power==get_power())
		{
			Monom ansM = new Monom(m._coefficient+get_coefficient(), m._power );
			return ansM;
		}
		else
		{
			throw new IllegalArgumentException("Not possible the power must to be same");
		}

	}
	/**
	 * Multiply the Monom we get with the monom
	 * @param Monom m
	 * @return Monom who's the multiply  
	 */
	public Monom multiply(Monom m) {


		Monom ansM = new Monom(m._coefficient*get_coefficient(), m._power+get_power());
		return ansM;


	}
	/**
	 * return the power of the Monom.
	 * @param 
	 * @return _power
	 */
	public int get_power() {

		return _power;
	}
	/**
	 * return the coefficient of the Monom.
	 * @param 
	 * @return _coefficient
	 */
	public double get_coefficient() {
		// TODO Auto-generated method stub
		return _coefficient;
	}
	/**
	 * return the String of the Monom
	 * @param 
	 * @return String
	 */
	public String toString() {
		
		return this.get_coefficient()+"*x^"+this.get_power();
	}

	//****************** Private Methods and Data *****************
	/**
	 * set the coefficient of the Monom as the value we get.
	 * @param double a
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	/**
	 * set the power of the Monom as the value we get.
	 * @param int p
	 */
	private void set_power(int p) {
		this._power = p;
	}




}
