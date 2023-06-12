import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> exemplo = FileManager.stringReader("./dados/exemplo.txt");
        Pipeline pipe = new Pipeline(exemplo);
        int i=0,j=0,r=0, bin=0;

        while (pipe.getIF() != -1) {
            if (pipe.getEX().getTipo() == 'R') r++;
            else if (pipe.getEX().getTipo() == 'J') j++;
            else if (pipe.getEX().getTipo() == 'I') i++;
            else bin++;

            System.out.println(i+" "+j+" "+r+" "+bin+"\n");

            pipe.executarEX();
        }
    }
}
