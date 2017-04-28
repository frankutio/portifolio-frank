package Entidade.Portal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Usuario {
    
    public static final int INCLUSAO = 1;
    public static final int ALTERACAO = 2;
    public static final int VERIFICACAO = 3;

    private int id_user;
    private int tipo_id;
    private String login;
    private String nome;
    private String senha;
    private String email;
    private String foto;
    private String about;
    private Date data_nascimento;
    private String dataNascimento;
    private String bloq;
    private String superUsr;

    public String validaDados(int tipoDeValidacao) {

        String msgErro = "";

        if (tipoDeValidacao == INCLUSAO) {
            
            if (getLogin() == null || getLogin().equals("")) {
                msgErro += "Informe o login.<br />";
            }
            
            if (getNome() == null || getNome().equals("")) {
                msgErro += "Informe o nome.<br />";
            }
            
            if (getSenha() == null || getSenha().equals("")) {
                msgErro += "Informe a senha.<br />";
            }
            
            if (getEmail() == null || getEmail().equals("")) {
                msgErro += "Informe o e-mail.<br />";
            }
            
            if (getSuperUsr() == null || getSuperUsr().equals("")){             
                msgErro += "Informe se é Super Usuário. <br />";
            }

        }

        return msgErro;
    }

    /**
     * @return the id_user
     */
    public int getId_user() {
        return id_user;
    }

    /**
     * @return the tipo_id
     */
    public int getTipo_id() {
        return tipo_id;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @return the about
     */
    public String getAbout() {
        return about;
    }

    /**
     * @return the data_nascimento
     */
    public Date getData_nascimento() {
        return data_nascimento;
    }

    public String getData_nascimentoString() {
        if(data_nascimento == null){
            return null;
        }
        else{
            return (new SimpleDateFormat("dd/MM/yyyy")).format(data_nascimento);
        }        
    }

    public String getDataNascimento() {
        if(data_nascimento == null){
            return null;
        }
        else{
            return (new SimpleDateFormat("dd/MM/yyyy")).format(data_nascimento);
        }
    }

    /**
     * @param id_user the id_user to set
     */
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    /**
     * @param tipo_id the tipo_id to set
     */
    public void setTipo_id(int tipo_id) {
        this.tipo_id = tipo_id;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @param about the about to set
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     * @param data_nascimento the data_nascimento to set
     */
    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    /**
     * @return the bloq
     */
    public String getBloq() {
        return bloq;
    }

    /**
     * @param bloq the bloq to set
     */
    public void setBloq(String bloq) {
        this.bloq = bloq;
    }

    /**
     * @return the Super
     */
    public String getSuperUsr() {
        return superUsr;
    }

    /**
     * @param superUsr the Super to set
     */
    public void setSuperUsr(String superUsr) {
        this.superUsr = superUsr;
    }
}
