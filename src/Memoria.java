import java.util.ArrayList;

public class Memoria {
    private ArrayList<Integer> dados;
    private ArrayList<String> palavras;

    public ArrayList<String> getPalavras() {
        return palavras;
    }
    public void setPalavras(ArrayList<String> palavras) {
        this.palavras = palavras;
    }
    public ArrayList<Integer> getDados() {
        return dados;
    }
    public void setDados(ArrayList<Integer> dados) {
        this.dados = dados;
    }

    public Memoria() {
        this.dados = new ArrayList<Integer>();
        this.palavras = new ArrayList<String>();
        for (int i=0; i<1000; i++) {
            this.dados.add(null);
            this.palavras.add(null);
        }
    }
}
