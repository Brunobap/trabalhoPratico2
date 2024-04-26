package trabalhopratico1.Mapeamentos;

import trabalhopratico1.*;

public class AssocConj extends baseMap {

    private int spConj, nConj, linConj;

    public int getLinConj() {
        return linConj;
    }
    public int getnConj() {
        return nConj;
    }
    public int getspConj() {
        return spConj;
    }

    public AssocConj (int intAcesso, Conf conf){
        String strAcesso = util.intToBinaryString(intAcesso, conf.getTamEnd());

        this.setSpPalavra((int)(Math.log10(conf.getPalavra())/Math.log10(2)));
        this.setEndPalavra(strAcesso.substring(conf.getTamEnd()-getSpPalavra()));

        this.nConj = conf.getnConj();
        this.linConj = conf.getnLinConj();

        // Nesse caso, o endereço da linha serve para o conjunto
        this.spConj = (int)(Math.log10(nConj)/Math.log10(2));
        this.setEndLinha(strAcesso.substring(conf.getTamEnd()-(spConj + this.getSpPalavra()), (conf.getTamEnd()-getSpPalavra())));

        this.setSpTag(conf.getTamEnd() - (this.spConj + this.getSpPalavra()));
        this.setEndTag(strAcesso.substring(0, (conf.getTamEnd()-(spConj + this.getSpPalavra()))));
    }
    
}
