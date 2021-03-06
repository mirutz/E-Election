package draft;

import java.math.BigInteger;
import java.util.ArrayList;

public class EinschreibenWaehlerliste {
    private ArrayList<Buerger> buergerListe;
    private Waehlerliste waehlerliste ;
    private Mix mix;
    
    public EinschreibenWaehlerliste(ArrayList<Buerger> buergerListe){
        this.buergerListe = buergerListe;
        waehlerliste = new Waehlerliste();
        mix = new Mix(waehlerliste);
    }
    
    public void einschreibungen(){
        for(int i=0; i<buergerListe.size();i++){
            BigInteger[] loc = new BigInteger[3];
            loc[0] = buergerListe.get(i).getE().modPow(mix.getPublicE(), mix.getPublicN());
            loc[1] = buergerListe.get(i).getN().modPow(mix.getPublicE(), mix.getPublicN());
            loc[2] = buergerListe.get(i).getZ().modPow(mix.getPublicE(), mix.getPublicN());
            mix.empfange(loc);            
        }
        System.out.println("mix beschrieben");
        mix.sende();
    }

}
