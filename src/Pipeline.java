import java.util.ArrayList;

public class Pipeline {
    // IF - Contador de programas (PC);
    private int IF;
    // ID - Instrução em binário e endereços de operandos a serem lidos;
    private ArrayList<Instrucao> ID;
    // EX - a operação sendo realizada juntamente com seus operandos;
    private Instrucao EX;
    // MEM  -  o  endereço  a  ser  lido  ou  escrito  na  memória,  assim  como  seu  valor para escrita;
    private int MEM;
    // WB - o endereço a ser lido ou escrito no banco de registradores, assim como seu valor para escrita;
    private int WB;

    // Registradores gerais ($0 à $31)
    private ArrayList<Registrador> arrayRegGerais;
    // Registrador $lo e $hi (mult e divisão)
    private Registrador regLo, regHi;

    //#region Getters e Setters
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
    public ArrayList<Instrucao> getID() {
        return ID;
    }
    public void setID(ArrayList<Instrucao> iD) {
        ID = iD;
    }
    //#endregion
    
    public Pipeline(ArrayList<String> entrada) {
        this.IF = 0;
        this.EX = new Instrucao(entrada.remove(0));
        for (String s : entrada)
            this.ID.add(new Instrucao(s));

        
    }
}
