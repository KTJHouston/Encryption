import java.util.ArrayList;

public class Enc {

	double p, q, N, phi, e, d;
	
	/**
	 * Constructor for Encryption
	 * @param p prime number
	 * @param q another prime number
	 */
	public Enc( double p, double q ) {
		this.p = p;
		this.q = q;
		N = p * q;
		phi = ( p - 1 ) * ( q - 1 );
		e = gete( p - 1, q - 1 );
		gcd( 3, 4 );
	}
	
	/**
	 * Finds an e such that gcd( phi, e ) = 1, where phi is pmin * qmin
	 * @param pmin p - 1
	 * @param qmin q - 1
	 * @return e such that gcd( phi, e ) = 1
	 */
	public double gete( double pmin, double qmin ) {
		ArrayList<Integer> pFactors = new ArrayList<Integer>(), qFactors = new ArrayList<Integer>();
		while( pmin % 2 == 0 ) {
			pmin /= 2;
			if( pFactors.size() == 0 ) {
				pFactors.add(2);
			}
		}
		while( qmin % 2 == 0 ) {
			qmin /= 2;
			if( qFactors.size() == 0 ) {
				qFactors.add(2);
			}
		}
		for( double i = 3; i <= Math.sqrt(pmin); i += 2 ) {
			while( pmin % i == 0 ) {
				pmin /= i;
				if( pFactors.get( pFactors.size() - 1 ) != i ) {
					pFactors.add((int)i);
				}
			}
		}
		for( double i = 3; i <= Math.sqrt(qmin); i += 2 ) {
			if( qmin % i == 0 ) {
				qmin /= i;
				if( qFactors.get( qFactors.size() - 1 ) != i ) {
					qFactors.add((int)i);
				}
			}
		}
		if( pmin > 1 ) {
			pFactors.add((int)pmin);
		}
		if( qmin > 1 ) {
			qFactors.add((int)qmin);
		}
		//put both facts into one array list:
		ArrayList<Integer> factors = pFactors;
		for( int q : qFactors ) {
			factors.add(q);
		}
		//find e such that gcd( phi, e ) = 1:
		int e = 1, max = (int)Math.sqrt(phi);
		boolean timesTwo = true;
		for( int f : factors) {
			if( f == 2 ) {
				timesTwo = false;
			}
		}
		if(timesTwo) {
			e *= 2;
		}
		for( double i = 3; e < max; i+=2 ) {
			boolean mult = true;
			for( int f : factors) {
				if( i % f == 0 ) {
					mult = false;
				}
			}
			if(mult) {
				e *= i;
			}
		}
		return e;
	}
	
	/**
	 * Finds the greatest common denominator of two integers. 
	 * @param a integer
	 * @param b integer
	 * @return gcd( a, b)
	 */
	public int gcd( int first, int second ) {
		int a = first, b = second;
		if( b > a ) {
			int c = a;
			a = b;
			b = c;
			System.out.println( a + ", " + b );
		}
		ArrayList<Integer> as = new ArrayList<Integer>(), bs = new ArrayList<Integer>(), qs = new ArrayList<Integer>(), rs = new ArrayList<Integer>();
		as.add(a);
		bs.add(b);
		while( rs.get(rs.size()-1) != 1 ) {
			
		}
		return first;
	}
	
}