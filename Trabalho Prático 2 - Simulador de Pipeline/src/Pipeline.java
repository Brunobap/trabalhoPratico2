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
        this.WB = 0;
        this.MEM = 0;

        this.arrayRegGerais = new ArrayList<Registrador>();
        for (int i=0; i<32; i++)
            this.arrayRegGerais.add(new Registrador());

        this.regHi = new Registrador();
        this.regLo = new Registrador();

        this.memoria = new Memoria();
    }

    public void executarEX() {
        this.IF++;
        this.WB = 0;
        this.MEM = 0;
        switch (this.EX.getStrCmd()) {
            case "add":
                int rs = EX.getRs();
                int rt = EX.getRt();
                int rd = EX.getRd();
                this.WB = rd;
                operacoesMIPS.add(this.arrayRegGerais.get(rs), this.arrayRegGerais.get(rt), this.arrayRegGerais.get(rd));
                break;
            
            case "sub":
                rs = EX.getRs();
                rt = EX.getRt();
                rd = EX.getRd();
                this.WB = rd;
                operacoesMIPS.sub(this.arrayRegGerais.get(rs), this.arrayRegGerais.get(rt), this.arrayRegGerais.get(rd));
                break;

            case "mult":
                rs = EX.getRs();
                rt = EX.getRt();   
                this.WB = 32;
                operacoesMIPS.mult(this.arrayRegGerais.get(rs), this.arrayRegGerais.get(rt), regHi, regLo);
                break;
            
            case "div":
                rs = EX.getRs();
                rt = EX.getRt();
                this.WB = 33;   
                operacoesMIPS.div(this.arrayRegGerais.get(rs), this.arrayRegGerais.get(rt), regHi, regLo);
                break;
            
            case "beq":
                rs = EX.getRs();
                rd = EX.getRd();
                int imm = EX.getImm();
                this.IF = operacoesMIPS.beq(this.arrayRegGerais.get(rs), this.arrayRegGerais.get(rd), this.IF, imm);
                break;

            case "bne":
                rs = EX.getRs();
                rd = EX.getRd();
                imm = EX.getImm();
                this.IF = operacoesMIPS.bne(this.arrayRegGerais.get(rs), this.arrayRegGerais.get(rd), this.IF, imm);
                break;
                
            case "bgtz":
                rs = EX.getRs();
                imm = EX.getImm();
                this.IF = operacoesMIPS.bgtz(this.arrayRegGerais.get(rs), this.IF, imm);  
                break;

            case "bltz":
                rs = EX.getRs();
                imm = EX.getImm();
                this.IF = operacoesMIPS.bltz(this.arrayRegGerais.get(rs), this.IF, imm);  
                break;

            case "j":
                int target = EX.getTarget();
                operacoesMIPS.j(target);
                break;
                
            case "jr":
                rs = EX.getRs();
                operacoesMIPS.jr(this.arrayRegGerais.get(rs));
                break;
            
            case "lw":
                rt = EX.getRt();
                rs = EX.getRs();
                imm = EX.getImm();
                this.WB = rt;
                this.MEM = imm + rs;
                operacoesMIPS.lw(this.arrayRegGerais.get(rt), memoria, this.arrayRegGerais.get(rs), imm);
                break;

            case "sw":
                rt = EX.getRt();
                rs = EX.getRs();
                imm = EX.getImm();
                this.WB = rt;
                this.MEM = imm + rs;
                operacoesMIPS.st(this.arrayRegGerais.get(rt), memoria, this.arrayRegGerais.get(rs), imm);
                break;
                
            case "get_tc":
                target = EX.getTarget();
                this.MEM = target;
                operacoesBinarias.get_tc(memoria, target);
                break;
        

            default:
                this.IF = operacoesBinarias.noop();
                break;
        }
        
        if (IF != -1) {
            this.EX = new Instrucao(this.getBruto().get(IF));
            if (! EX.getStrCmd().equals("noop")) this.ID = new Instrucao(this.getBruto().get(IF+1));
        }
    }
}
