package Cryptography;

import edu.duke.*;
/**
 * Décrivez votre classe Tester ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Tester {

    public void testCaesar(){
        FileResource fr = new FileResource();
        String msg = fr.asString();
        CaesarCipher cc = new CaesarCipher(8);
        System.out.println(cc.encryptLetter('c'));
        System.out.println(cc.decryptLetter('c'));
        System.out.println(cc.encrypt(msg));
        CaesarCracker cracker = new CaesarCracker('e');
        System.out.println(cracker.decrypt(cc.encrypt(msg)));
    }
    public void testVigenere(){
        FileResource fr = new FileResource();
        String msg = fr.asString();
        int[] arr = {17,14,12,4};
        VigenereCipher vg = new VigenereCipher(arr);
        System.out.println(vg.encrypt(msg));
        System.out.println(vg.decrypt(vg.encrypt(msg)));
        
    }
    
}
