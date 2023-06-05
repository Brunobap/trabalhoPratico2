import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
       ArrayList<String> exemplo = FileManager.stringReader("./dados/exemplo.txt");
       Pipeline pipe = new Pipeline(exemplo);

       for (Instrucao linha : pipe.getID()) {
           System.out.println(linha);
       }
    }

}
