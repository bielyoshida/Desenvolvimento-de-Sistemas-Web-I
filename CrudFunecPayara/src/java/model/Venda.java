package model;

public class Venda {
    private int codigo_venda;
    private int codigo_cliente;
    private float valor_compra;
    private float valor_desconto;
    private float valor_total;
    private int codigo_item_venda;
    private int codigo_produto;
    private int quantidade_vendida;

    public Venda() {
    }

    public Venda(int codigo_venda, int codigo_cliente, float valor_compra, float valor_desconto, float valor_total, int codigo_item_venda, int codigo_produto, int quantidade_venda) {
        this.codigo_venda = codigo_venda;
        this.codigo_cliente = codigo_cliente;
        this.valor_compra = valor_compra;
        this.valor_desconto = valor_desconto;
        this.valor_total = valor_total;
        this.codigo_item_venda = codigo_item_venda;
        this.codigo_produto = codigo_produto;
        this.quantidade_vendida = quantidade_vendida;
    }

    
    /**
     * @return the codigo_venda
     */
    public int getCodigo_venda() {
        return codigo_venda;
    }

    /**
     * @param codigo_venda the codigo_venda to set
     */
    public void setCodigo_venda(int codigo_venda) {
        this.codigo_venda = codigo_venda;
    }

    /**
     * @return the codigo_cliente
     */
    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    /**
     * @param codigo_cliente the codigo_cliente to set
     */
    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    /**
     * @return the valor_compra
     */
    public float getValor_compra() {
        return valor_compra;
    }

    /**
     * @param valor_compra the valor_compra to set
     */
    public void setValor_compra(float valor_compra) {
        this.valor_compra = valor_compra;
    }

    /**
     * @return the valor_desconto
     */
    public float getValor_desconto() {
        return valor_desconto;
    }

    /**
     * @param valor_desconto the valor_desconto to set
     */
    public void setValor_desconto(float valor_desconto) {
        this.valor_desconto = valor_desconto;
    }

    /**
     * @return the valor_total
     */
    public float getValor_total() {
        return valor_total;
    }

    /**
     * @param valor_total the valor_total to set
     */
    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    /**
     * @return the codigo_item_venda
     */
    public int getCodigo_item_venda() {
        return codigo_item_venda;
    }

    /**
     * @param codigo_item_venda the codigo_item_venda to set
     */
    public void setCodigo_item_venda(int codigo_item_venda) {
        this.codigo_item_venda = codigo_item_venda;
    }

    /**
     * @return the codigo_produto
     */
    public int getCodigo_produto() {
        return codigo_produto;
    }

    /**
     * @param codigo_produto the codigo_produto to set
     */
    public void setCodigo_produto(int codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    /**
     * @return the quantidade_venda
     */
    public int getQuantidade_vendida() {
        return quantidade_vendida;
    }

    /**
     * @param quantidade_venda the quantidade_venda to set
     */
    public void setQuantidade_vendida(int quantidade_vendida) {
        this.quantidade_vendida = quantidade_vendida;
    }

}
