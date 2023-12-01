package mapeamento;

public class Paciente {
    private int id_pacinte;
    private String nome;
    private String cpf;
    private String email;
    private String sexo;
    private String telefone;
    private String datanasc;
    private String senha;
    private String perfil;

    public int getId_pacinte() {
        return id_pacinte;
    }

    public void setId_pacinte(int id_pacinte) {
        this.id_pacinte = id_pacinte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
    @Override
    public String toString() {
        return getNome(); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
