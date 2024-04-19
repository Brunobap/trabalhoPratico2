import java.util.Scanner;

public class operacoesBinarias {
    // Operações implemementadas:
    //  - get_tc M(y) (salva em M(y) o valor inteiro digitado no teclado)
    public static void get_tc (Memoria mem, int target) {
        Scanner teclado = new Scanner(System.in);

        int entrada = teclado.nextInt();
        mem.getDados().remove(target);
        mem.getDados().add(target, entrada);

        System.out.println("get_tc: M["+target+"] <- "+entrada);
    }
    //  - noop (finaliza a execução)
    public static int noop () {
        return -1;
    }
    
}
