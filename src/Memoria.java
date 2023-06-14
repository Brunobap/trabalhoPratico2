import java.util.ArrayList;

public class Memoria {
    private ArrayList<Integer> dados;
    
    public ArrayList<Integer> getDados() {
        return dados;
    }
    public void setDados(ArrayList<Integer> dados) {
        this.dados = dados;
    }

    public Memoria() {
        this.dados = new ArrayList<Integer>();
        for (int i=0; i<1000; i++)
            this.dados.add(0);
    }
}
