package model; // Pacote de entidades (representam tabelas do banco)

public class Estado {

    // =========================
    // ATRIBUTOS
    // =========================

    private int codigo; // Identificador único (chave primária no banco)
    private String nome; // Nome do estado (ex: São Paulo)
    private String sigla; // Sigla do estado (ex: SP)

    // =========================
    // CONSTRUTOR PADRÃO
    // =========================

    public Estado() {
        // Necessário para frameworks, JDBC e criação manual
    }

    // =========================
    // CONSTRUTOR COMPLETO
    // =========================

    public Estado(int codigo, String nome, String sigla) {
        this.codigo = codigo;
        this.nome = nome;
        this.sigla = sigla;
    }

    // =========================
    // GETTERS E SETTERS
    // =========================

    // Retorna o código do estado
    public int getCodigo() {
        return codigo;
    }

    // Define o código do estado
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Retorna o nome do estado
    public String getNome() {
        return nome;
    }

    // Define o nome do estado
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna a sigla do estado
    public String getSigla() {
        return sigla;
    }

    // Define a sigla do estado
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}