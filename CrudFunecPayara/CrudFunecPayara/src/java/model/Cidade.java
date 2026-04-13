package model; // Pacote de entidades do sistema

public class Cidade {

    // =========================
    // ATRIBUTOS
    // =========================

    private int codigo;       // Código da cidade (chave primária)
    private String nome;      // Nome da cidade
    private Estado estado;    // Estado ao qual a cidade pertence

    // =========================
    // CONSTRUTOR PADRÃO
    // =========================

    public Cidade() {
        // Construtor vazio necessário para criação manual do objeto
    }

    // =========================
    // CONSTRUTOR COMPLETO
    // =========================

    public Cidade(int codigo, String nome, Estado estado) {
        this.codigo = codigo;
        this.nome = nome;
        this.estado = estado;
    }

    // =========================
    // GETTERS E SETTERS
    // =========================

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
