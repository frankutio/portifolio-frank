package Entidade.Portal;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Log {
    
    private int cod;
    private String tipo;
    private Date data;
    private int item;

    /**
     * @return the cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }
    
    public String getDataString() {
        return (new SimpleDateFormat("dd/MM/yyyy")).format(data);
    }

    /**
     * @return the item
     */
    public int getItem() {
        return item;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(int cod) {
        this.cod = cod;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @param item the item to set
     */
    public void setItem(int item) {
        this.item = item;
    }
}
