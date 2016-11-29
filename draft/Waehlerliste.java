package draft;

import java.math.BigInteger;
import java.util.ArrayList;

public class Waehlerliste {

    private ArrayList<BigInteger[]> waehlerliste;
    
    public Waehlerliste(){
        waehlerliste = new ArrayList<>();
    }
    
    public void eintragen(BigInteger[] waehlerinformationen){
        waehlerliste.add(waehlerinformationen);
    }
     
    
}
