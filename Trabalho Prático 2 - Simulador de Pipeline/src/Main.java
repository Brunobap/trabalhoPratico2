import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> exemplo = FileManager.stringReader("./dados/exemplo.txt");
        Pipeline pipe = new Pipeline(exemplo);
        while (pipe.getIF() != -1) {

            pipe.executarEX();
        }
    }
}
