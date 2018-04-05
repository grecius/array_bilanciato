/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grecius.arrayBilanciato;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/**
 *
 * @author grecius
 */

public class ArrayBilanciato {
    private Map<Integer,String> err = new HashMap<Integer,String>();
    private int[] numArr = null;
    private int sommaDx;
    private int sommaSx;
    private final static String BILANCIATO = "Bilanciato";
    private final static String SBILANCIATO = "Sbilanciato";
    private String valoreBilanciato = "";

    public static void main(String[] args){
       if(args.length == 0){
           System.out.println("richiesto parametro input"); 
           System.exit(1);
       }
       ArrayBilanciato arrayBilanciato = new ArrayBilanciato();
        arrayBilanciato.controllaArrayBilanciato(args[0]);
        if(!arrayBilanciato.getErr().isEmpty()){
          System.out.println("errore Input " +arrayBilanciato.getErr().values());
          System.exit(1);
        }
        System.out.println("array "+arrayBilanciato.getValoreBilanciato());
    }

    public Map<Integer, String> getErr() {
        return err;
    }

    public String getValoreBilanciato() {
        return valoreBilanciato;
    }
    
    public void controllaArrayBilanciato(String arr){
        controllaArgomenti(arr);
        if(!err.isEmpty()){
            return;
        }
        valoreBilanciato = SBILANCIATO;
        for(int valore:numArr){
            sommaDx += valore;
            sommaSx -= valore;
            if(sommaDx == sommaSx){
               valoreBilanciato = BILANCIATO;
               break;
            }
        }
        
        
    }
    
    private void controllaArgomenti(String array){
        int numeroSequenza = 0;
        int contatore = 0;
        if(array.charAt(0) != '[' || array.charAt(array.length()-1) != ']'){
           err.put(new Integer(1),"formato input consentito [1,2,3,4,5]");
          return;
        }
        String sequenza = array.substring(1, array.length()-1);
        StringTokenizer st = new StringTokenizer(sequenza, ",");
        numArr = new int[st.countTokens()];

        while (st.hasMoreTokens()) {
            try{
                numeroSequenza = Integer.parseInt(st.nextToken());
                sommaSx += numeroSequenza;
                numArr[contatore] = numeroSequenza;
                contatore++;
            }catch(NumberFormatException nEx){
                err.put(new Integer(3),"solo valori numerici consentiti");
            }
        }
    }
    
}
