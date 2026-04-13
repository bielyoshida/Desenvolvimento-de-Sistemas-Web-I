package model; // Pacote onde ficam as classes de domínio (entidades do sistema)

import java.math.BigDecimal; // Classe usada para representar valores monetários com precisão

public class Produto {

    // =========================
    // ATRIBUTOS (CAMPOS)
    // =========================

    private int id; // Identificador único do produto (chave primária no banco)

    private String descricao; // Nome ou descrição do produto

    private BigDecimal valorCompra; // Valor que o produto foi comprado (custo)
    private BigDecimal valorVenda;  // Valor que o produto será vendido

    private String marca; // Marca do produto (ex: Nike, Samsung, etc.)

    private int quantidade; // Quantidade em estoque

    // =========================
    // CONSTRUTOR PADRÃO
    // =========================

    public Produto() {}
    // Necessário para frameworks, JSP, JDBC e criação manual do objeto

    // =========================
    // CONSTRUTOR COMPLETO
    // =========================

    public Produto(int id, String descricao, BigDecimal valorCompra, BigDecimal valorVenda, String marca, int quantidade) {
        this.id = id;
        this.descricao = descricao;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.marca = marca;
        this.quantidade = quantidade;
    }

    // =========================
    // GETTERS E SETTERS
    // =========================

    // Retorna o ID do produto
    public int getId() { return id; }

    // Define o ID do produto
    public void setId(int id) { this.id = id; }

    // Retorna a descrição do produto
    public String getDescricao() { return descricao; }

    // Define a descrição do produto
    public void setDescricao(String descricao) { this.descricao = descricao; }

    // Retorna o valor de compra
    public BigDecimal getValorCompra() { return valorCompra; }

    // Define o valor de compra
    public void setValorCompra(BigDecimal valorCompra) { this.valorCompra = valorCompra; }

    // Retorna o valor de venda
    public BigDecimal getValorVenda() { return valorVenda; }

    // Define o valor de venda
    public void setValorVenda(BigDecimal valorVenda) { this.valorVenda = valorVenda; }

    // Retorna a marca do produto
    public String getMarca() { return marca; }

    // Define a marca do produto
    public void setMarca(String marca) { this.marca = marca; }

    // Retorna a quantidade em estoque
    public int getQuantidade() { return quantidade; }

    // Define a quantidade em estoque
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}