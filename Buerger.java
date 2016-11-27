package draft;

import java.math.BigInteger;
import java.util.Random;

public class Buerger {
    /**
     * Struktur von x ist x zweimal hintereinander geschrieben. Bsp: x = 1337
     * --> x = 13371337; Rückrechnung: Durch 10^(log10(x)/2) Bsp: BigInteger i2
     * = new BigInteger("10"); int count=1; while(true){ if(i2.compareTo(x)<=0){
     * i2 = i2.multiply(BigInteger.valueOf(10)); count++; } else { test =
     * x.divide(BigInteger.valueOf(10).pow(count/2)); break; } }
     */
    private BigInteger x, r, y, z;

    public Buerger() {
        init();
    }

    /*
     * init berechnet x und r
     */
    private void init() {
        r = new BigInteger(1024, 100, new Random());
        x = new BigInteger(512, new Random());
        BigInteger i = new BigInteger("10");
        while (true) {
            if (i.compareTo(x) <= 0) {
                i = i.multiply(BigInteger.valueOf(10));
            } else {
                x = x.multiply(i).add(x);
                break;
            }
        }
    }

    public BigInteger calculateY(BigInteger e_w, BigInteger n_w) {
        y = x.multiply(r.modPow(e_w, n_w)).mod(n_w);
        // System.out.println(y);
        return y;
    }

    public void calculateZ(BigInteger t, BigInteger n_w) {
        z = t.multiply(r.mod(n_w).modInverse(n_w));
    }

}
