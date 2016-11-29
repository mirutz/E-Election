package draft;

import java.math.BigInteger;
import java.util.ArrayList;


public class Mix {

    private Waehlerliste waehlerliste;
    private RSA_class rsa;

    private ArrayList<BigInteger[]> empfangeneDaten;

    public Mix(Waehlerliste waehlerliste) {
        this.waehlerliste = waehlerliste;
        rsa = new RSA_class();
        empfangeneDaten = new ArrayList<>();
    }

    public void empfange(BigInteger[] empfangeneDaten) {
        /**
         * Das BigInt Array hat hier das Format [e_b, n_b, z verschlüsselt mit nB]
         */
        BigInteger[] loc = new BigInteger[3];
        loc[0] = rsa.decrypt(empfangeneDaten[0]);
        loc[1] = rsa.decrypt(empfangeneDaten[1]);
        loc[2] = rsa.decrypt(empfangeneDaten[2]);
        this.empfangeneDaten.add(loc);
    }

    public BigInteger getPublicN() {
        return rsa.getN();
    }

    public BigInteger getPublicE() {
        return rsa.getE();
    }

    public void sende() {
        for(int i=0;i<empfangeneDaten.size();i++){
            waehlerliste.eintragen(empfangeneDaten.get(i));    
            
        }
    }

}
