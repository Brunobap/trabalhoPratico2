package trabalhopratico1.Mapeamentos;

import trabalhopratico1.*;

public class Direto extends baseMap {

    private int spLinha;

    public int getSpLinha() {
        return spLinha;
    }

    public Direto (int intAcesso, Conf conf){
        String strAcesso = util.intToBinaryString(intAcesso, conf.getTamEnd());

        this.setSpPalavra((int)(Math.log10(conf.getPalavra())/Math.log10(2)));
        this.setEndPalavra(strAcesso.substring(conf.getTamEnd()-getSpPalavra()));

        this.spLinha = (int)(Math.log10(conf.getnLinhas())/Math.log10(2));
        this.setEndLinha(strAcesso.substring(conf.getTamEnd()-(spLinha + this.getSpPalavra()), (conf.getTamEnd()-getSpPalavra())));

        this.setSpTag(conf.getTamEnd() - (this.spLinha + this.getSpPalavra()));
        this.setEndTag(strAcesso.substring(0, (conf.getTamEnd()-(spLinha + this.getSpPalavra()))));
    }
    
}
