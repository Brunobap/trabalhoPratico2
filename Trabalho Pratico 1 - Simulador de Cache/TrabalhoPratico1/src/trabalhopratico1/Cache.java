package trabalhopratico1;
import java.util.*;
import java.util.Map.Entry;

import trabalhopratico1.Mapeamentos.*;

public class Cache {
    //#region Variáveis e ctor
    // Uso geral
    private baseMap map;    
    private Conf conf;

    // Usado no Direto
    private String[] arrayStrings;

    // Usado no Associativo
    private Map<Integer,Integer> mapaHash;
    private ArrayList<Integer> mapaArray;
    private Queue<Integer> mapaFila;

    // Usado no Associativo em Conjunto
    private ArrayList<Map <Integer, Integer>> arrayHash;
    private ArrayList<ArrayList<Integer>> arrayArray;
    private ArrayList<Queue<Integer>> arrayFila;

    public Cache(int tipo, Conf conf) {
        this.conf = conf;

        switch (tipo){
            case 0:
                this.arrayStrings = new String[this.conf.getnLinhas()];
                Arrays.fill(arrayStrings, "");
                break;
             
            case 1:
                this.mapaHash = new HashMap<Integer,Integer>();
                this.mapaArray = new ArrayList<Integer>();
                this.mapaFila = new LinkedList<Integer>();
                break;
                
            default:
                this.arrayHash = new ArrayList<Map <Integer, Integer>>();
                this.arrayArray = new ArrayList<ArrayList<Integer>>();
                this.arrayFila = new ArrayList<Queue<Integer>>();
                for (int i=0; i<conf.getnConj(); i++) {
                    this.arrayHash.add(i, new HashMap<Integer, Integer>());
                    this.arrayArray.add(i, new ArrayList<Integer>());
                    this.arrayFila.add(i, new LinkedList<Integer>());
                }
                break;
        }     
    }
    //#endregion

    //#region Função de acesso da cache
    public boolean acessar(int acesso, int tipoAcesso, int tipoSubst){
        switch (tipoAcesso) {
            //#region Mapeamento Direto 
            case 0:
                map = new Direto(acesso, this.conf);
                int endInt = Integer.parseUnsignedInt(map.getEndLinha(), 2);
                if (this.arrayStrings[endInt].equals(map.getEndTag()))
                    return true;
                else{
                    this.arrayStrings[endInt] = map.getEndTag();
                    return false;
                }   
            //#endregion
            //#region Mapeamento Associativo 
            case 1:
                map = new Associativo(acesso, this.conf);
                int tagInt = Integer.parseUnsignedInt(map.getEndTag(),2);
                if (this.mapaHash.containsKey(tagInt)){
                    if (tipoSubst == 2) {
                        int aux = this.mapaArray.indexOf(tagInt);
                        this.mapaArray.remove(aux);
                        this.mapaArray.add(tagInt);
                    } else if (tipoSubst == 3) {
                        int aux = this.mapaHash.get(tagInt);
                        this.mapaHash.replace(tagInt, aux+1);
                    }
                    return true;
                } else {
                    if (this.mapaHash.size() < this.conf.getnLinhas())
                        switch (tipoSubst){
                            case 0: // Caso 0: subst random, só a chave é usada, o valor não
                                this.mapaHash.put(tagInt, null);
                                break;

                            case 1: // Caso 1: subst fifo, usa o mapaFila em conjunto
                                this.mapaHash.put(tagInt, null);
                                this.mapaFila.add(tagInt);
                                break;

                            case 2: // Caso 2: subst lru, usa o mapaArray em conjunto
                                this.mapaHash.put(tagInt, null);
                                this.mapaArray.add(tagInt);
                                break;

                            case 3: // Caso 3: subst lfu, valor no mapa é a quantidade de usos
                                this.mapaHash.put(tagInt, 1);
                                break;
                        }
                    else substAssoc(tipoSubst, tagInt);
                    return false;
                }
            //#endregion
            //#region Mapeamento Associativo em Conjunto 
            default:
                map = new AssocConj(acesso, this.conf);
                if (this.conf.getnConj() != 1) 
                    endInt = Integer.parseUnsignedInt(map.getEndLinha(), 2);
                else endInt = 0;
                tagInt = Integer.parseUnsignedInt(map.getEndTag(),2);
                Map<Integer, Integer> conj = this.arrayHash.get(endInt);
                if (this.arrayHash.get(endInt).containsKey(tagInt)) {
                    if (tipoSubst == 2) {
                        int aux = this.arrayArray.get(endInt).indexOf(tagInt);
                        this.arrayArray.get(endInt).remove(aux);
                        this.arrayArray.get(endInt).add(tagInt);
                    } else if (tipoSubst == 3) {
                        int aux = conj.get(tagInt);
                        conj.replace(tagInt, aux+1);
                    }
                    return true;
                } else {
                    if (conj.size() < this.conf.getnLinConj())
                        switch (tipoSubst){
                            case 0: // Caso 0: subst random, só a chave é usada, o valor não
                                conj.put(tagInt, null);
                                break;

                            case 1: // Caso 1: subst fifo, usa o mapaFila em conjunto
                                conj.put(tagInt, null);
                                this.arrayFila.get(endInt).add(tagInt);
                                break;

                            case 2: // Caso 2: subst lru, usa o mapaArray em conjunto
                                conj.put(tagInt, null);
                                this.arrayArray.get(endInt).add(tagInt);
                                break;

                            case 3: // Caso 3: subst lfu, valor no mapa é a quantidade de usos
                                conj.put(tagInt, 1);
                                break;
                        }
                    else substAssocConj(tipoSubst, endInt, tagInt);
                    return false;
                }
            //#endregion
        }
    }
    //#endregion
    //#region Substituições SEM conjunto
    public void substAssoc(int tipo, int tag){
        int keyOff;
        switch (tipo) {
            // Caso 0: substituição aleatória
            case 0:
                int min = Collections.min(mapaHash.keySet());
                int max = Collections.max(mapaHash.keySet());
                Random random = new Random();
                int rand = random.nextInt(max - min) + min;
                if (this.mapaHash.containsKey(rand)) {
                    this.mapaHash.remove(rand);
                    break;
                }
                this.mapaHash.put(tag, null);
                break;
        
            // Caso 1: Fila (1o a entrar, 1o a sair)
            case 1:
                keyOff = this.mapaFila.remove();
                this.mapaHash.remove(keyOff);
                this.mapaFila.add(tag);
                this.mapaHash.put(tag, null);
                break;

            // Caso 2: LRU (o que não foi usado recentemente sai)
            case 2:
                keyOff = this.mapaArray.remove(0);
                this.mapaHash.remove(keyOff);
                this.mapaArray.add(tag);
                this.mapaHash.put(tag, null);
                break;

            // Caso 3: LFU (o que menos foi usado sai)
            default:
                min = Collections.min(this.mapaHash.values());
                Set<Entry<Integer,Integer>> set = this.mapaHash.entrySet();
                for(Entry<Integer,Integer> e : set) {
                    if (e.getValue() == min) {
                        keyOff = e.getKey();
                        this.mapaHash.remove(keyOff);
                        this.mapaHash.put(tag, 1);
                        break;
                    }
                }
                break;
        }
    }
    //#endregion
    //#region Substituições COM conjunto
    public void substAssocConj(int tipo, int end, int tag){
        int keyOff;
        Map<Integer, Integer> conj = this.arrayHash.get(end);
        switch (tipo) {
            // Caso 0: substituição aleatória
            case 0:
                int min = Collections.min(conj.keySet());
                int max = Collections.max(conj.keySet());
                Random random = new Random();
                int rand = random.nextInt(max - min) + min;
                if (conj.containsKey(rand)) {
                    conj.remove(rand);
                    break;
                }
                conj.put(tag, null);
                break;
        
            // Caso 1: Fila (1o a entrar, 1o a sair)
            case 1:
                Queue<Integer> fila = this.arrayFila.get(end);
                keyOff = fila.remove();
                conj.remove(keyOff);
                fila.add(tag);
                conj.put(tag, null);
                break;

            // Caso 2: LRU (o que não foi usado recentemente sai)
            case 2:
                keyOff = arrayArray.get(end).remove(0);
                this.arrayHash.get(end).remove(keyOff);
                this.arrayArray.get(end).add(tag);
                this.arrayHash.get(end).put(tag, null);
                break;

            // Caso 3: LFU (o que menos foi usado sai)
            default:
                min = Collections.min(conj.values());
                Set<Entry<Integer,Integer>> set = conj.entrySet();
                for(Entry<Integer,Integer> e : set) {
                    if (e.getValue() == min) {
                        keyOff = e.getKey();
                        conj.remove(keyOff);
                        conj.put(tag, 1);
                        break;
                    }
                }
                break;
        }
    }
    //#endregion
}
