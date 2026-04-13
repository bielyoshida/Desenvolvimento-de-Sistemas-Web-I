
package model;

public class Cliente {
    
    private int codigo;
    private String nome;
    private String sexo;
    private String telefone;
    private String cpf;
    private int codigo_cidade;


    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the codigo_cidade
     */
    public int getCodigo_cidade() {
        return codigo_cidade;
    }

    /**
     * @param codigo_cidade the codigo_cidade to set
     */
    public void setCodigo_cidade(int codigo_cidade) {
        this.codigo_cidade = codigo_cidade;
    }

    public Cliente(int codigo, String nome, String sexo, String telefone, String cpf, int codigo_cidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.sexo = sexo;
        this.telefone = telefone;
        this.cpf = cpf;
        this.codigo_cidade = codigo_cidade;
    }
    
    
}
