package draft;

import java.math.BigInteger;
import java.util.Random;

public class RSA_class {
	private static BigInteger p = null;
	private static BigInteger q = null;
	private static BigInteger e = null;
	private static BigInteger d = null;
	private static BigInteger n = null;
	private static int bit = 1024;
	
	public RSA_class(){
	    generateAll();
	}
	
	public static BigInteger getE() {
        return e;
    }

    public static BigInteger getN() {
        return n;
    }

    public static void generatePrivate() {
		if (e == null) {
			System.out.println("Generate Public first");
			return;
		}
		d = e.modInverse(n);
//		System.out.println("Private generated");
	}

	public static BigInteger getD() {
        return d;
    }

    public static void generatePublic() {
		e = BigInteger.probablePrime(bit * 2 - 2, new Random());
		while (e.gcd(n).compareTo(BigInteger.ONE) != 0)
			while (!e.isProbablePrime(100) && e.compareTo(n) < 0)
				e = BigInteger.probablePrime(bit * 2 - 2, new Random());
//		System.out.println("Public generated");
	}

	public static void generatepq() {
		p = BigInteger.probablePrime(bit, new Random());
		q = BigInteger.probablePrime(bit, new Random());
		n = q.subtract(BigInteger.ONE).multiply(p.subtract(BigInteger.ONE));
	}

	public static void generateAll() {
		generatepq();
		generatePublic();
		generatePrivate();
	}

	public static BigInteger encrypt(BigInteger data) {
		return data.modPow(e, p.multiply(q));
	}
	
	public static BigInteger encrypt(byte[] data) {
		BigInteger b = new BigInteger(data);
		return b.modPow(e, p.multiply(q));
	}
	
	public static BigInteger decrypt(BigInteger data) {
		return data.modPow(d, p.multiply(q));
	}
	
	public static BigInteger decrypt(byte[] data) {
		BigInteger b = new BigInteger(data);
		return b.modPow(d, p.multiply(q));
	}
}
