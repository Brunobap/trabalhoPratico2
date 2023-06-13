public class Instrucao {
    private String bin;
    private String strCmd;
    private char tipo;

    private int op;  // comum em todos os tipos (6-bits)
    private int rs, rt;  // comum para tipos I e R (5-bits)
    private int imm;   // exclusivo para tipo I (16-bits)
    private int target;  // exclusivo para tipo J (26-bits)
    private int rd, shamt, funct;    // exclusivo do tipo R (5, 5 e 6 bits)

    //#region Getters e Setters
    public int getRd() {
        return rd;
    }
    public int getShamt() {
        return shamt;
    }
    public int getFunct() {
        return funct;
    }
    public int getTarget() {
        return target;
    }
    public int getImm() {
        return imm;
    }
    public int getRs() {
        return rs;
    }
    public int getRt() {
        return rt;
    }
    public int getOp() {
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
    public String getBin() {
        return bin;
    }
    //#endregion

    public Instrucao(String strCmd) {
        this.strCmd = strCmd.split(" ")[0];
        this.op = 0;
        this.rs = 0; this.rt = 0;
        this.imm = 0;
        this.target = 0;
        this.rd = 0; this.shamt = 0; this.funct = 0;
        this.tipo = checkTipo(strCmd.split(" "));

        if (tipo == 'R') {
            this.bin = util.intToBinaryString(op, 6) + util.intToBinaryString(rs, 5) + util.intToBinaryString(rt, 5) + util.intToBinaryString(rd, 5) + util.intToBinaryString(shamt, 5) + util.intToBinaryString(funct, 6);
        } else if (tipo == 'I') {
            this.bin = util.intToBinaryString(op, 6) + util.intToBinaryString(rs, 5) + util.intToBinaryString(rt, 5) + util.intToBinaryString(imm, 16);
        } else if (tipo == 'J') {
            this.bin = util.intToBinaryString(op, 6) + util.intToBinaryString(target, 26);
        }
    }
    
    public char checkTipo(String[] entrada) {
        String cmd = entrada[0];
        switch (cmd) {
            //#region Tipo R
            case "add":                
                String nRD = entrada[1].substring(1, entrada[1].length()-1);
                String nRS = entrada[2].substring(1, entrada[2].length()-1);
                String nRT = entrada[3].substring(1);
                
                this.op = 0;
                this.rd = Integer.parseInt(nRD);
                this.rs = Integer.parseInt(nRS);
                this.rt = Integer.parseInt(nRT);
                this.shamt = 0;
                this.funct = 32;
                return 'R';

            case "sub":
                nRD = entrada[1].substring(1, entrada[1].length()-1);
                nRS = entrada[2].substring(1, entrada[2].length()-1);
                nRT = entrada[3].substring(1);
                
                this.op = 0;
                this.funct = 34;
                this.rd = Integer.parseInt(nRD);
                this.rs = Integer.parseInt(nRS);
                this.rt = Integer.parseInt(nRT);
                this.shamt = 0;
                return 'R';

            case "mult":
                nRS = entrada[1].substring(1, entrada[2].length()-1);
                nRT = entrada[2].substring(1);

                this.op = 0;
                this.funct = 24;
                this.rs = Integer.parseInt(nRS);
                this.rt = Integer.parseInt(nRT);
                this.rd = 0;
                this.shamt = 0;
                return 'R';

            case "div":
                nRS = entrada[1].substring(1, entrada[2].length()-1);
                nRT = entrada[2].substring(1);
                
                this.op = 0;
                this.funct = 26;
                this.rs = Integer.parseInt(nRS);
                this.rt = Integer.parseInt(nRT);
                this.shamt = 0;
                return 'R';

            case "jr":
                nRS = entrada[1].substring(1);
                
                this.op = 0;
                this.funct = 8;
                this.rs = Integer.parseInt(nRS);
                this.rt = 0;
                this.rd = 0;
                this.shamt = 0;
                return 'R';
            //#endregion      
            //#region Tipo I
            case "lw":
                nRT = entrada[1].substring(1, entrada[1].length()-1);
                String nIMM = entrada[2].substring(0, entrada[2].indexOf('('));
                nRS = entrada[2].substring(entrada[2].indexOf('$')+1, entrada[2].length()-1);
                
                this.op = 35;
                this.imm = Integer.parseInt(nIMM);
                this.rs = Integer.parseInt(nRS);
                this.rt = Integer.parseInt(nRT);
                return 'I';

            case "sw":
                nRT = entrada[1].substring(1, entrada[1].length()-1);
                nIMM = entrada[2].substring(0, entrada[2].indexOf('('));
                nRS = entrada[2].substring(entrada[2].indexOf('$')+1, entrada[2].length()-1);
                
                this.op = 43;
                this.imm = Integer.parseInt(nIMM);
                this.rs = Integer.parseInt(nRS);
                this.rt = Integer.parseInt(nRT);
                return 'I';

            case "bne":
                this.op = 5;
                nRS = entrada[1].substring(1, entrada[1].length()-1);
                nRT = entrada[2].substring(1, entrada[2].length()-1);
                nIMM = entrada[3];

                this.rd = Integer.parseInt(nRS);
                this.rs = Integer.parseInt(nRT);
                this.imm = Integer.parseInt(nIMM);
                return 'I';

            case "beq":
                nRS = entrada[1].substring(1, entrada[1].length()-1);
                nRT = entrada[2].substring(1, entrada[2].length()-1);
                nIMM = entrada[3];
                
                this.op = 4;
                this.rd = Integer.parseInt(nRS);
                this.rs = Integer.parseInt(nRT);
                this.imm = Integer.parseInt(nIMM);
                return 'I';

            case "bltz":
                nRS = entrada[1].substring(1, entrada[1].length()-1);
                nIMM = entrada[2];
                
                this.op = 1;
                this.rs = Integer.parseInt(nRS);
                this.imm = Integer.parseInt(nIMM);
                return 'I';

            case "bgtz":
                nRS = entrada[1].substring(1, entrada[1].length()-1);
                nIMM = entrada[2];
                
                this.op = 7;
                this.rs = Integer.parseInt(nRS);
                this.imm = Integer.parseInt(nIMM);
                return 'I';
            //#endregion
            //#region Tipo J
            case "j":
                this.op = 2;
                this.target = Integer.parseInt(entrada[1]);
                return 'J';
            //#endregion
            //#region Tipo D
            case "get_tc": // "get_tc" não tem código MIPS, então eu coloquei com o ultimo código possível
                this.op = 63;
                this.target = Integer.parseInt(entrada[1]);
            case "noop": // Todas as informações são zeradas, não faz nada
                return 'D';
            //#endregion

            default: return ' ';
        }
    }
}