import java.util.ArrayList;

public class Enc {

	long p, q, n, phi, e, d;
	
	/**
	 * Constructor for Encryption
	 * @param p prime number
	 * @param q another prime number
	 */
	public Enc( long p, long q ) {
		this.p = p;
		this.q = q;
		n = p * q;
		phi = ( p - 1 ) * ( q - 1 );
		e = getE( p - 1, q - 1 );
		System.out.println( "e: " + e );
		System.out.println( "d: " + getD( e, phi ) );
		System.out.println( "phi: " + phi );
	}
	
	/**
	 * Finds an e such that gcd( phi, e ) = 1, where phi is pmin * qmin
	 * @param pmin p - 1
	 * @param qmin q - 1
	 * @return e such that gcd( phi, e ) = 1
	 */
	public long getE( long pmin, long qmin ) {
		ArrayList<Long> pFactors = new ArrayList<Long>(), qFactors = new ArrayList<Long>();
		while( pmin % 2 == 0 ) {
			pmin /= 2;
			if( pFactors.size() == 0 ) {
				pFactors.add((long)2);
			}
		}
		while( qmin % 2 == 0 ) {
			qmin /= 2;
			if( qFactors.size() == 0 ) {
				qFactors.add((long)2);
			}
		}
		for( long i = 3; i <= Math.sqrt(pmin); i += 2 ) {
			while( pmin % i == 0 ) {
				pmin /= i;
				if( pFactors.get( pFactors.size() - 1 ) != i ) {
					pFactors.add((long)i);
				}
			}
		}
		for( long i = 3; i <= Math.sqrt(qmin); i += 2 ) {
			if( qmin % i == 0 ) {
				qmin /= i;
				if( qFactors.get( qFactors.size() - 1 ) != i ) {
					qFactors.add((long)i);
				}
			}
		}
		if( pmin > 1 ) {
			pFactors.add((long)pmin);
		}
		if( qmin > 1 ) {
			qFactors.add((long)qmin);
		}
		//put both factors into one array list:
		ArrayList<Long> factors = pFactors;
		for( long q : qFactors ) {
			factors.add((long)q);
		}
		//find e such that gcd( phi, e ) = 1:
		long e = 1;
		boolean timesTwo = true;
		for( long f : factors) {
			if( f == 2 ) {
				timesTwo = false;
				break;
			}
		}
		e = (timesTwo) ? (e*2) : (e);
		for( long i = 3; e < phi; i+=2 ) {
			boolean mult = true;
			for( long f : factors) {
				if( i % f == 0 ) {
					mult = false;
				}
			}
			if(mult) {
				if( e * i > phi ) {
					break;
				}
				e *= i;
			}
		}
		
		return e;
	}
	
	/**
	 * Finds a d such that d*e is congruent to 1 (mod phi)
	 * @param e
	 * @param phi
	 * @return
	 */
	public long getD( long e, long phi ) {
		long eMP = e,//e mod phi
			 d = 1;
		
		while( eMP != 1 ) {
			d++;
			eMP = (eMP + e) % phi;
		}
		
		return d;
	}
	
}