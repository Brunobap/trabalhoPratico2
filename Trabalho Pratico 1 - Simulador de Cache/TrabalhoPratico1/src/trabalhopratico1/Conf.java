package trabalhopratico1;

import java.io.FileNotFoundException;
import java.util.*;
/* Exemplo:
 * mem = 128 KB;
 * palavra = 4 B;
 * cache = 4 KB;
 * linha = 4 pal;
 */

public class Conf{
    private long mem, cache;
    private int palavra, linha, tamEnd, nBlocos, nLinhas, nConj, nLinConj;

    public Conf(String path){
        ArrayList<String> dadosConfig = FileManager.stringReader(path);
        String dadosMem[] = dadosConfig.get(0).split("=")[1].split(" ");
        
        this.mem = Integer.parseInt(dadosMem[1]) * (int)auxSize(dadosMem[2]);
        String dadosWrd[] = dadosConfig.get(1).split("=")[1].split(" ");
        
        this.palavra = Integer.parseInt(dadosWrd[1]) * (int)auxSize(dadosWrd[2]);
        String dadosCac[] = dadosConfig.get(2).split("=")[1].split(" ");
        
        this.cache = Integer.parseInt(dadosCac[1]) * (int)auxSize(dadosCac[2]);
        String dadosLin[] = dadosConfig.get(3).split("=")[1].split(" ");
        
        this.linha = Integer.parseInt(dadosLin[1]);
        
        this.nBlocos = (int)this.mem / (this.palavra * this.linha);
        this.nLinhas = (int)this.cache / ((this.palavra * this.linha));
        
        this.nConj = 1;
        this.nLinConj = this.nLinhas;
        
        this.tamEnd =  (int)(Math.log10(this.mem)/Math.log10(2));
    }
    public Conf(String path, int nConj){
        ArrayList<String> dadosConfig = FileManager.stringReader(path);

        String dadosMem[] = dadosConfig.get(0).split("=")[1].split(" ");
        this.mem = Integer.parseInt(dadosMem[1]) * (int)auxSize(dadosMem[2]);
        
        String dadosWrd[] = dadosConfig.get(1).split("=")[1].split(" ");
        this.palavra = Integer.parseInt(dadosWrd[1]) * (int)auxSize(dadosWrd[2]);
        
        String dadosCac[] = dadosConfig.get(2).split("=")[1].split(" ");
        this.cache = Integer.parseInt(dadosCac[1]) * (int)auxSize(dadosCac[2]);

        String dadosLin[] = dadosConfig.get(3).split("=")[1].split(" ");
        this.linha = Integer.parseInt(dadosLin[1]);

        this.nBlocos = (int)this.mem / (this.palavra * this.linha);
        this.nLinhas = (int)this.cache / ((this.palavra * this.linha));

        this.nConj = nConj;
        this.nLinConj = this.nLinhas/this.nConj;

        this.tamEnd =  (int)(Math.log10(this.mem)/Math.log10(2));
    }

    private double auxSize(String size){
        if (size.equals("KB;"))
            return Math.pow(2,10);
        else if (size.equals("MB;"))
            return Math.pow(2,20);
        else if (size.equals("GB;"))
            return Math.pow(2,30);
        else
            return 1;
    }

    public long getMem() {
        return mem;
    }
    public int getPalavra() {
        return palavra;
    }
    public long getCache() {
        return cache;
    }
    public int getLinha() {
        return linha;
    }

    //  Informação de memória principal e cache (número de blocos, número de linhas, espaço de memória para tag)
    public int getnBlocos() {
        return nBlocos;
    }
    public int getnLinhas() {
        return nLinhas;
    }

    // Tamanho do endereço é definido pelos atributos da memória
    public int getTamEnd() {
        return tamEnd;
    }

    // Esses atributos são definidos pelo usúario na interface    
    public int getnConj() {
        return nConj;
    }
    public void setnConj(int nConj) {
        this.nConj = nConj;
    }

    public int getnLinConj() {
        return nLinConj;
    }
    public void setnLinConj(int nLinConj) {
        this.nLinConj = nLinConj;
    }
}