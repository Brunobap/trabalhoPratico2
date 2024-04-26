/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopratico1;

import java.util.ArrayList;
import trabalhopratico1.util;

/**
 *
 * @author Douglas
 */
public class TrabalhoPratico1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<String> dadosConfig = FileManager.stringReader("./dados/config.txt");
        String dadosMemoriaCompleto = dadosConfig.get(0);
        String partesMem[] = dadosMemoriaCompleto.split("=");
        System.out.println(partesMem[0]);
        System.out.println(partesMem[1]);
        String partesDado[] = partesMem[1].split(" ");
        
       ArrayList<String> teste = FileManager.stringReader("./dados/teste_1.txt");
       for (String linha : teste) {
           System.out.println(linha);
           int acesso = Integer.parseInt(linha);
           int bin[] = util.intToBinary(acesso, 24);
           String stringBin = util.intToBinaryString(acesso, 24);
           System.out.println(stringBin);
       }
       int bin[] = util.intToBinary(3, 4);
       if (bin != null){
           for (int i = 0; i < bin.length; i++) {
               System.out.println(i + " - " + bin[i]);
           }
       }
       else {
           System.out.println("Numero de bits insuficiente..");
       }
    }

}
