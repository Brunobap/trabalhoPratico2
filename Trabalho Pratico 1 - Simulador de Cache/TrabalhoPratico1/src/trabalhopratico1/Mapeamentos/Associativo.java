package trabalhopratico1.Mapeamentos;

import trabalhopratico1.*;

public class Associativo extends baseMap {
    
    public Associativo (int intAcesso, Conf conf){
        String strAcesso = util.intToBinaryString(intAcesso, conf.getTamEnd());

        this.setSpPalavra((int)(Math.log10(conf.getPalavra())/Math.log10(2)));
        this.setEndPalavra(strAcesso.substring(conf.getTamEnd()-getSpPalavra()));

        this.setEndLinha(null);

        this.setSpTag(conf.getTamEnd() - this.getSpPalavra());
        this.setEndTag(strAcesso.substring(0, (conf.getTamEnd()-this.getSpPalavra())));
    }
    

}
