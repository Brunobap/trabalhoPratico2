import java.util.ArrayList;

public class Instrucao {
    private String strCmd;
    private int arrayBits[]; 
    private ArrayList<Registrador> arrayReg;

    public ArrayList<Registrador> getArrayReg() {
        return arrayReg;
    }
    public void setArrayReg(ArrayList<Registrador> arrayReg) {
        this.arrayReg = arrayReg;
    }
    public int[] getArrayBits() {
        return arrayBits;
    }
    public void setArrayBits(int[] arrayBits) {
        this.arrayBits = arrayBits;
    }
    public String getStrCmd() {
        return strCmd;
    }
    public void setStrCmd(String strCmd) {
        this.strCmd = strCmd;
    }

    public Instrucao(String strCmd) {
        this.strCmd = strCmd.split(" ")[0];
        
    }
}