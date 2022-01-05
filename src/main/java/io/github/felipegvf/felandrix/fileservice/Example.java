package io.github.felipegvf.felandrix.fileservice;

public class Example {

    private String campo;
    private double valor;
    private long valorLong;

    @ListToResource(type = DataType.STRING)
    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    @ListToResource(type = DataType.DOUBLE)
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @ListToResource(type = DataType.LONG)
    public long getValorLong() {
        return valorLong;
    }

    public void setValorLong(long valorLong) {
        this.valorLong = valorLong;
    }
}
