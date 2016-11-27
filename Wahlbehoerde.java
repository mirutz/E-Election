package draft;

import java.math.BigInteger;

public class Wahlbehoerde {
    private RSA_class rsa_keys;
    private BigInteger t;
    public Wahlbehoerde(){
        init();
    }
    private void init(){
        rsa_keys= new RSA_class();
        System.out.println("test");
    }
    
    public BigInteger getPublicE(){
        return rsa_keys.getE();
    }
    public BigInteger getPublicN(){
        return rsa_keys.getN();
    }
    
    //TEST ONLY
    public BigInteger getD(){
        return rsa_keys.getD();
    }
    
    public BigInteger blindSignature(BigInteger y){
        t = y.modPow(rsa_keys.getD(), rsa_keys.getN());
//        System.out.println(t);
        return t;
    }
    

}
