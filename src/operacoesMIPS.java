

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
    //  - lw (Rt recebe da memória)
    public static void lw (Registrador rt, Memoria mem, Registrador rs, int imm) {
        int posicao = rs.getDado()+imm;
        rt.setDado(mem.getDados().get(posicao));
    }
    //  - sw (Memória recebe do registrador)
    public static void st (Registrador rt, Memoria mem, Registrador rs, int imm) {
        int posicao = rs.getDado()+imm;
        rt.setDado(mem.getDados().get(posicao));
    }
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
    //  - bltz (Jump se o valor é negativo)
    public static int bltz (Registrador rs, int PC, int imm) {
        if (rs.getDado() < 0) return PC + imm;
        else return 0;
    }
    //  - bgtz (Jump se o valor é positivo)
    public static int bgtz (Registrador rs, int PC, int imm) {
        if (rs.getDado() > 0) return PC + imm;
        else return 0;
    }
    //  - bne (Jump se o valor não é igual)
    public static int bne (Registrador rs, Registrador rt, int PC, int imm) {
        if (rs.getDado() != rt.getDado()) return PC + imm;
        else return 0;
    }
    //  - beq (Jump se o valor é igual)
    public static int beq (Registrador rs, Registrador rt, int PC, int imm) {
        if (rs.getDado() == rt.getDado()) return PC + imm;
        else return 0;
    }
    //  - j (Jump direto no endereço)
    public static int j (int target) {
        return target/4; 
    }
    //  - jr (Jump direto no valor do registrador)
     public static int j (Registrador rs) {
        return rs.getDado(); 
    }
}
