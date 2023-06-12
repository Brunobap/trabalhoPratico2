import java.util.ArrayList;

public class Pipeline {
    // bruto - lista de entradas bruta, salva por precausão
    private ArrayList<String> bruto;

    // IF - Contador de programas (PC);
    private int IF;
    // ID - Instrução em binário e endereços de operandos a serem lidos;
    private Instrucao ID;
    // EX - a operação sendo realizada juntamente com seus operandos;
    private Instrucao EX;
    // MEM  -  o  endereço  a  ser  lido  ou  escrito  na  memória,  assim  como  seu  valor para escrita;
    private int MEM;
    // WB - o endereço a ser lido ou escrito no banco de registradores, assim como seu valor para escrita;
    private int WB;

    // Memórias usadas pelo pipeline (1000 endereços)
    private Memoria memoria;

    // Registradores gerais ($0 à $31)
    private ArrayList<Registrador> arrayRegGerais;
    // Registrador $lo e $hi (mult e divisão)
    private Registrador regLo, regHi;

    //#region Getters e Setters  
    public Memoria getMemoria() {
        return memoria;
    }
    public void setMemoria(Memoria memoria) {
        this.memoria = memoria;
    }  
    public ArrayList<String> getBruto() {
        return bruto;
    }
    public ArrayList<Registrador> getArrayRegGerais() {
        return arrayRegGerais;
    }
    public void setArrayRegGerais(ArrayList<Registrador> arrayRegGerais) {
        this.arrayRegGerais = arrayRegGerais;
    }
    public Registrador getRegLo() {
        return regLo;
    }
    public void setRegLo(Registrador regLo) {
        this.regLo = regLo;
    }
    public Registrador getRegHi() {
        return regHi;
    }
    public void setRegHi(Registrador regHi) {
        this.regHi = regHi;
    }
    public int getWB() {
        return WB;
    }
    public void setWB(int wB) {
        WB = wB;
    }
    public int getMEM() {
        return MEM;
    }
    public void setMEM(int mEM) {
        MEM = mEM;
    }
    public Instrucao getEX() {
        return EX;
    }
    public void setEX(Instrucao eX) {
        EX = eX;
    }
    public int getIF() {
        return IF;
    }
    public void setIF(int iF) {
        IF = iF;
    }
    public Instrucao getID() {
        return ID;
    }
    public void setID(Instrucao iD) {
        ID = iD;
    }
    //#endregion
    
    public Pipeline(ArrayList<String> entrada) {
        this.bruto = entrada;

        this.IF = 0;
        this.EX = new Instrucao(entrada.get(IF));
        this.ID = new Instrucao(entrada.get(IF+1));

        this.arrayRegGerais = new ArrayList<Registrador>();
        for (int i=0; i<32; i++)
            this.arrayRegGerais.add(new Registrador());

        this.regHi = new Registrador();
        this.regLo = new Registrador();

        this.memoria = new Memoria();
    }

    public void executarEX() {
        this.IF++;
        this.EX = this.ID;
        if (EX.getStrCmd().equals("noop")) IF = -1;
        else this.ID = new Instrucao(this.getBruto().get(IF+1));
    }
}
