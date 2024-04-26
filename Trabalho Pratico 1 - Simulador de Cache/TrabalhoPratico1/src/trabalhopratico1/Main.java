package trabalhopratico1;
import java.util.*;

public class Main {
    public static void teste(String[] args) {
        int tipoAcesso = 0, tipoSubst = 0;
        
        Conf conf = new Conf("./dados/config.txt");
        Cache cache = new Cache(tipoAcesso,conf);
        /* Visualizar as informações da config
        Config config = cache.getConf();
        System.out.println("mem = "+config.getMem());
        System.out.println("cache = "+config.getCache());
        System.out.println("linha = "+config.getLinha());
        System.out.println("palavra = "+config.getPalavra());
        System.out.println("num de blocos = "+config.getnBlocos());
        System.out.println("num de linhas = "+config.getnLinhas());
        System.out.println("tam endereço = "+config.getTamEnd()+"\n"); */

        /* Teste para um acesso individual
        cache.acessar(515, 1, 0);
        cache.acessar(515, 1, 0); */

        // Teste para todos os acesso do arquivo
        int hits = 0, misses = 0;

        ArrayList<String> teste = FileManager.stringReader("./dados/teste_1.txt");
        for (String linha : teste) {
            int acesso = Integer.parseInt(linha);

            // Acessos: 0=Direto, 1=Associativo, 2=Associativo em Conjunto
            // Substit: 0=Aleatória, 1=Fila, 2=LRU, 3=LFU
            if (cache.acessar(acesso, tipoAcesso, tipoSubst)) {
                hits++;
                System.out.println(linha+" - hit");

            } else {                
                misses++;
                System.out.println(linha+" - miss");
            }
        }

        System.out.println("Hits = "+hits+" - Misses = "+misses);
        float taxa = 100f*hits/(hits+misses);
        System.out.println("Taxa de acerto = "+taxa+"%");
    }
}
