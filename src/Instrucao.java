public class Instrucao {
    private String strCmd;
    private char tipo;

    private String op;  // comum em todos os tipos (6-bits)
    private String rs, rt;  // comum para tipos I e R (5-bits)
    private String imm;   // exclusivo para tipo I (16-bits)
    private String target;  // exclusivo para tipo J (26-bits)
    private String rd, shamt, funct;    // exclusivo do tipo R (5, 5 e 6 bits)

    //#region Getters e Setters
    public String getRd() {
        return rd;
    }
    public String getShamt() {
        return shamt;
    }
    public String getFunct() {
        return funct;
    }
    public String getTarget() {
        return target;
    }
    public String getImm() {
        return imm;
    }
    public String getRs() {
        return rs;
    }
    public String getRt() {
        return rt;
    }
    public String getOp() {
        return op;
    }
    public char getTipo() {
        return tipo;
    }
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    public String getStrCmd() {
        return strCmd;
    }
    public void setStrCmd(String strCmd) {
        this.strCmd = strCmd;
    }
    //#endregion

    public Instrucao(String strCmd) {
        this.strCmd = strCmd.split(" ")[0];
        this.tipo = checkTipo(strCmd.split(" "));
    }
    
    public char checkTipo(String[] entrada) {
        String cmd = entrada[0];
        switch (cmd) {
            //#region Tipo R
            case "add":
                this.op = "000000";
                this.funct = "100000";
                String nRD = entrada[1].substring(1, entrada[1].length()-1);
                String nRS = entrada[2].substring(1, entrada[2].length()-1);
                String nRT = entrada[3].substring(1);
                this.rd = util.intToBinaryString(Integer.parseInt(nRD),5);
                this.rs = util.intToBinaryString(Integer.parseInt(nRS),5);
                this.rt = util.intToBinaryString(Integer.parseInt(nRT),5);
                this.shamt="00000";
                return 'R';
            case "sub":
                this.op = "000000";
                this.funct = "100010";
                nRD = entrada[1].substring(1, entrada[1].length()-1);
                nRS = entrada[2].substring(1, entrada[2].length()-1);
                nRT = entrada[3].substring(1);
                this.rd = util.intToBinaryString(Integer.parseInt(nRD),5);
                this.rs = util.intToBinaryString(Integer.parseInt(nRS),5);
                this.rt = util.intToBinaryString(Integer.parseInt(nRT),5);
                this.shamt="00000";
                return 'R';
            case "mult":
                this.funct = "011000";
                nRS = entrada[1].substring(1, entrada[2].length()-1);
                nRT = entrada[2].substring(1);
                this.rs = util.intToBinaryString(Integer.parseInt(nRS),5);
                this.rt = util.intToBinaryString(Integer.parseInt(nRT),5);
                return 'R';
            case "div":
                this.funct = "011010";
                nRS = entrada[1].substring(1, entrada[2].length()-1);
                nRT = entrada[2].substring(1);
                this.rs = util.intToBinaryString(Integer.parseInt(nRS),5);
                this.rt = util.intToBinaryString(Integer.parseInt(nRT),5);
                this.shamt="00000";
                return 'R';
            case "jr":
                this.op = "000000";
                this.funct = "001000";
                nRS = entrada[1].substring(1);
                this.rs = util.intToBinaryString(Integer.parseInt(nRS),5);
                this.shamt="00000";
                return 'R';
            //#endregion      
            //#region Tipo I
            case "lw":
                this.op = "100011";
                nRT = entrada[1].substring(1, entrada[1].length()-1);
                String nIMM = entrada[2].substring(0, entrada[2].indexOf('('));
                nRS = entrada[2].substring(entrada[2].indexOf('$')+1, entrada[2].length()-1);
                this.imm = util.intToBinaryString(Integer.parseInt(nIMM), 16);
                this.rs = util.intToBinaryString(Integer.parseInt(nRS),5);
                this.rt = util.intToBinaryString(Integer.parseInt(nRT),5);
                return 'I';
            case "sw":
                this.op = "101011";
                nRT = entrada[1].substring(1, entrada[1].length()-1);
                nIMM = entrada[2].substring(0, entrada[2].indexOf('('));
                nRS = entrada[2].substring(entrada[2].indexOf('$')+1, entrada[2].length()-1);
                this.imm = util.intToBinaryString(Integer.parseInt(nIMM), 16);
                this.rs = util.intToBinaryString(Integer.parseInt(nRS),5);
                this.rt = util.intToBinaryString(Integer.parseInt(nRT),5);
                return 'I';
            case "bne":
                this.op = "000101";
                nRS = entrada[1].substring(1, entrada[1].length()-1);
                nRT = entrada[2].substring(1, entrada[2].length()-1);
                nIMM = entrada[3];
                this.rd = util.intToBinaryString(Integer.parseInt(nRS),5);
                this.rs = util.intToBinaryString(Integer.parseInt(nRT),5);
                this.rt = util.intToBinaryString(Integer.parseInt(nIMM),16);
                return 'I';
            case "beq":
                this.op = "000100";
                nRS = entrada[1].substring(1, entrada[1].length()-1);
                nRT = entrada[2].substring(1, entrada[2].length()-1);
                nIMM = entrada[3];
                this.rd = util.intToBinaryString(Integer.parseInt(nRS),5);
                this.rs = util.intToBinaryString(Integer.parseInt(nRT),5);
                this.rt = util.intToBinaryString(Integer.parseInt(nIMM),16);
                return 'I';

            // TODO: bltz e bgtz
            case "bltz":
                this.op = "000001";
            case "bgtz":
                this.op = "000111";
                return 'I';
            //#endregion
            //#region Tipo J
            case "j":
                this.op = "000010";
                int nTarget = Integer.parseInt(entrada[1]);
                this.target = util.intToBinaryString(nTarget, 26);
                return 'J';
            //#endregion
            //#region Tipo D
            case "noop":
                this.op = "000000";
                this.funct = "000000";
                return 'D';
            // "get_tc" não possui um código do MIPS, então eu coloquei com o ultimo código possível
            case "get_tc":
                this.op = "111111";
                nTarget = Integer.parseInt(entrada[1]);
                this.target = util.intToBinaryString(nTarget, 26);
                return 'D';
            //#endregion

            default:
                return ' ';
        }
    }
}