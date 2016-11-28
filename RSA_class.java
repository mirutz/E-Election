package draft;

import java.math.BigInteger;
import java.util.Random;

public class RSA_class {
    private BigInteger p = null;

    private BigInteger q = null;

    private BigInteger e = null;

    private BigInteger d = null;

    private BigInteger N = null;

    private BigInteger phi_n = null;

    private int bit = 1024;

    public RSA_class() {
        generateAll();
    }

    public RSA_class(BigInteger e_B) {
        e = e_B;

        while (true) {
            p = BigInteger.probablePrime(bit, new Random());
            q = BigInteger.probablePrime(bit, new Random());
            if (p.add(q).add(BigInteger.ONE).compareTo(e) == 0) {
                phi_n = q.subtract(BigInteger.ONE).multiply(p.subtract(BigInteger.ONE));
                if (e.gcd(phi_n).compareTo(BigInteger.ONE) == 0) {
                    if (e.compareTo(phi_n) < 0) {
                        break;
                    }
                }
            }
        }

        N = p.multiply(q);
    }

    public BigInteger getD() {
        return d;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getN() {
        return N;
    }

    public void generatePrivate() {
        if (e == null) {
            System.out.println("Generate Public first");
            return;
        }
        d = e.modInverse(phi_n);
        // System.out.println("Private generated");
    }

    public void generatePublic() {
        e = BigInteger.probablePrime(bit * 2 - 2, new Random());
        while (e.gcd(phi_n).compareTo(BigInteger.ONE) != 0)
            while (!e.isProbablePrime(100) && e.compareTo(phi_n) < 0)
                e = BigInteger.probablePrime(bit * 2 - 2, new Random());
        // System.out.println("Public generated");
    }

    public void generatepq() {
        p = BigInteger.probablePrime(bit, new Random());
        q = BigInteger.probablePrime(bit, new Random());
        phi_n = q.subtract(BigInteger.ONE).multiply(p.subtract(BigInteger.ONE));
        N = p.multiply(q);
    }

    public void generateAll() {
        generatepq();
        generatePublic();
        generatePrivate();
    }

    public BigInteger encrypt(BigInteger data) {
        return data.modPow(e, N);
    }

    public BigInteger encrypt(byte[] data) {
        BigInteger b = new BigInteger(data);
        return b.modPow(e, N);
    }

    public BigInteger decrypt(BigInteger data) {
        return data.modPow(d, N);
    }

    public BigInteger decrypt(byte[] data) {
        BigInteger b = new BigInteger(data);
        return b.modPow(d, N);
    }

    @Override
    public String toString() {
        return (p + "\n" + q);
    }
}
