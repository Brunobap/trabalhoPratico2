

public class operacoesMIPS {
    // Operações implementadas:
    //  - add (Adição)
    public static void add(Registrador rs, Registrador rt, Registrador rd) {
        int soma = rs.getDado() + rt.getDado();
        rd.setDado(soma);
    }
    //  - sub (Subtração)
    public static void sub(Registrador rs, Registrador rt, Registrador rd) {
        int subtracao = rs.getDado() - rt.getDado();
        rd.setDado(subtracao);
    }
    //  - lw (Load no registrador)
    public static void lw () {

    }
    //  - sw (Store no registrador)

    //  - mult (Multiplicação)
    public static void mult(Registrador rs, Registrador rt, Registrador hi, Registrador lo) {
        int multiplicacao = rs.getDado() * rt.getDado();
        hi.setDado(multiplicacao);
        lo.setDado(multiplicacao);
    }
    //  - div (Divisão)
    public static void div(Registrador rs, Registrador rt, Registrador hi, Registrador lo) {
        int multiplicacao = rs.getDado() * rt.getDado();
        hi.setDado(multiplicacao);
        lo.setDado(multiplicacao);
    }
    //  - bltz (??)

    //  - bgtz (??)

    //  - bne (Jump se o valor não é igual)

    //  - beq (Jump se o valor é igual)

    //  - j (Jump direto no endereço)

    //  - jr (Jump direto no valor do registrador)
     
}
