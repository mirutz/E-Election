package draft;

import java.math.BigInteger;
import java.util.Random;

public class RSA_class {
	private static BigInteger p = null;
	private static BigInteger q = null;
	private static BigInteger e = null;
	private static BigInteger d = null;
	private static BigInteger N = null;
	private static BigInteger phi_n = null;
	private static int bit = 1024;
	
	public RSA_class(){
	    generateAll();
	}
	
	public static BigInteger getD() {
        return d;
    }
	public static BigInteger getE() {
        return e;
    }

    public static BigInteger getN() {
        return N;
    }

    public static void generatePrivate() {
		if (e == null) {
			System.out.println("Generate Public first");
			return;
		}
		d = e.modInverse(phi_n);
//		System.out.println("Private generated");
	}

    public static void generatePublic() {
		e = BigInteger.probablePrime(bit * 2 - 2, new Random());
		while (e.gcd(phi_n).compareTo(BigInteger.ONE) != 0)
			while (!e.isProbablePrime(100) && e.compareTo(phi_n) < 0)
				e = BigInteger.probablePrime(bit * 2 - 2, new Random());
//		System.out.println("Public generated");
	}

	public static void generatepq() {
		p = BigInteger.probablePrime(bit, new Random());
		q = BigInteger.probablePrime(bit, new Random());
		phi_n = q.subtract(BigInteger.ONE).multiply(p.subtract(BigInteger.ONE));
		N = p.multiply(q);
	}

	public static void generateAll() {
		generatepq();
		generatePublic();
		generatePrivate();
	}

	public static BigInteger encrypt(BigInteger data) {
		return data.modPow(e, N);
	}
	
	public static BigInteger encrypt(byte[] data) {
		BigInteger b = new BigInteger(data);
		return b.modPow(e, N);
	}
	
	public static BigInteger decrypt(BigInteger data) {
		return data.modPow(d, N);
	}
	
	public static BigInteger decrypt(byte[] data) {
		BigInteger b = new BigInteger(data);
		return b.modPow(d, N);
	}
}
