
package Entidade.Portal;

public class Image {
    
    private int id_image;
    private int id_portifolio;
    private String alt;
    private String title;
    private String descricao;
    private int ordem;

    /**
     * @return the id_image
     */
    public int getId_image() {
        return id_image;
    }

    /**
     * @return the id_portifolio
     */
    public int getId_portifolio() {
        return id_portifolio;
    }

    /**
     * @return the alt
     */
    public String getAlt() {
        return alt;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the ordem
     */
    public int getOrdem() {
        return ordem;
    }

    /**
     * @param id_image the id_image to set
     */
    public void setId_image(int id_image) {
        this.id_image = id_image;
    }

    /**
     * @param id_portifolio the id_portifolio to set
     */
    public void setId_portifolio(int id_portifolio) {
        this.id_portifolio = id_portifolio;
    }

    /**
     * @param alt the alt to set
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @param ordem the ordem to set
     */
    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
    
}
