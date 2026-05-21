
package model;

import java.util.ArrayList;
import java.util.List;

public class ItemVenda {
    private int codigoItemVenda;
    private int codigoProduto;
    private int codigoVenda;
    private String descricaoProduto;
    private int quantidade;
    private float valorUnitario;
    private float subtotal;
    private List<ItemVenda> itens = new ArrayList<>();

    public ItemVenda(int codigoItemVenda, int codigoProduto, int codigoVenda, String descricaoProduto, int quantidade, float valorUnitario, float subtotal) {
        this.codigoItemVenda = codigoItemVenda;
        this.codigoProduto = codigoProduto;
        this.codigoVenda = codigoVenda;
        this.descricaoProduto = descricaoProduto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.subtotal = subtotal;
    }

    public ItemVenda(){
        
    }
    /**
     * @return the codigoItemVenda
     */
    public int getCodigoItemVenda() {
        return codigoItemVenda;
    }

    /**
     * @param codigoItemVenda the codigoItemVenda to set
     */
    public void setCodigoItemVenda(int codigoItemVenda) {
        this.codigoItemVenda = codigoItemVenda;
    }

    /**
     * @return the codigoProduto
     */
    public int getCodigoProduto() {
        return codigoProduto;
    }

    /**
     * @param codigoProduto the codigoProduto to set
     */
    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    /**
     * @return the codigoVenda
     */
    public int getCodigoVenda() {
        return codigoVenda;
    }

    /**
     * @param codigoVenda the codigoVenda to set
     */
    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    /**
     * @return the descricaoProduto
     */
    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    /**
     * @param descricaoProduto the descricaoProduto to set
     */
    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the valorUnitario
     */
    public float getValorUnitario() {
        return valorUnitario;
    }

    /**
     * @param valorUnitario the valorUnitario to set
     */
    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * @return the subtotal
     */
    public float getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the itens
     */
    public List<ItemVenda> getItens() {
        return itens;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }
    
}
