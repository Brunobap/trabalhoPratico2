package trabalhopratico1.Mapeamentos;

public abstract class baseMap {
    
    //  Especificações de endereçamento de cache (bits para linha, bits para palavra, bits de rótulo/tag)
    private String endLinha, endPalavra, endTag;
    private int spTag, spPalavra;

    public void setEndLinha(String endLinha) {
        this.endLinha = endLinha;
    }
    public void setEndPalavra(String endPalavra) {
        this.endPalavra = endPalavra;
    }
    public void setEndTag(String endTag) {
        this.endTag = endTag;
    }
    public void setSpTag(int spTag) {
        this.spTag = spTag;
    }
    public void setSpPalavra(int spPalavra) {
        this.spPalavra = spPalavra;
    }

    public String getEndLinha() {
        return endLinha;
    }
    public String getEndPalavra() {
        return endPalavra;
    }
    public String getEndTag() {
        return endTag;
    }
    public int getSpTag() {
        return spTag;
    }
    public int getSpPalavra() {
        return spPalavra;
    }

}
