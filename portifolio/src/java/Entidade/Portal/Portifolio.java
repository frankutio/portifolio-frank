package Entidade.Portal;

import java.util.Date;

public class Portifolio {
    
    private int id_portifolio;
    private int usuario_id;
    private String nome;
    private String servico;
    private String tecnologia;
    private String descricao;
    private Date data_inicio;
    private Date data_fim;
    private String link;
    private String img_capa;
    private String id_img_capa;
    private String lixo;

    /**
     * @return the id_portifolio
     */
    public int getId_portifolio() {
        return id_portifolio;
    }

    /**
     * @return the usuario_id
     */
    public int getUsuario_id() {
        return usuario_id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the servico
     */
    public String getServico() {
        return servico;
    }

    /**
     * @return the tecnologia
     */
    public String getTecnologia() {
        return tecnologia;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the data_inicio
     */
    public Date getData_inicio() {
        return data_inicio;
    }

    /**
     * @return the data_fim
     */
    public Date getData_fim() {
        return data_fim;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @return the img_capa
     */
    public String getImg_capa() {
        return img_capa;
    }

    /**
     * @return the id_img_capa
     */
    public String getId_img_capa() {
        return id_img_capa;
    }

    /**
     * @param id_portifolio the id_portifolio to set
     */
    public void setId_portifolio(int id_portifolio) {
        this.id_portifolio = id_portifolio;
    }

    /**
     * @param usuario_id the usuario_id to set
     */
    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param servico the servico to set
     */
    public void setServico(String servico) {
        this.servico = servico;
    }

    /**
     * @param tecnologia the tecnologia to set
     */
    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @param data_inicio the data_inicio to set
     */
    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    /**
     * @param data_fim the data_fim to set
     */
    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @param img_capa the img_capa to set
     */
    public void setImg_capa(String img_capa) {
        this.img_capa = img_capa;
    }

    /**
     * @param id_img_capa the id_img_capa to set
     */
    public void setId_img_capa(String id_img_capa) {
        this.id_img_capa = id_img_capa;
    }

    /**
     * @return the lixo
     */
    public String getLixo() {
        return lixo;
    }

    /**
     * @param lixo the lixo to set
     */
    public void setLixo(String lixo) {
        this.lixo = lixo;
    }
    
}
