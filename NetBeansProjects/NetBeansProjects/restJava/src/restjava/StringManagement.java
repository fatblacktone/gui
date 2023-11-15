/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restjava;

/**
 *
 * @author Fatblack
 */
public class StringManagement {
    public static String removeCharacter(String input,char toRemove){
        char[] newString = new char[input.length()];
        int modifier = 0;
        for(int i=0;i<input.length();i++){
            if(toRemove!=input.charAt(i)){
                newString[i-modifier] = input.charAt(i);
            }else{
                modifier++;
            }            
        }        
        return new String(newString);
    }
}
