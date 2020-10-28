/**
 * factorVO.java
 *
 * Versión V.v 21-oct-2020
 * Javier Herrer Torres
 */
package model;

import java.util.Date;


/**
 * factorVO
 *
 */
public class FactorVO {
    private Date fecha;
    private float valor;
    private int id_region;

    public FactorVO(Date fecha, float valor, int id_region) {
        this.fecha = fecha;
        this.valor = valor;
        this.id_region = id_region;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

	@Override
	public String toString() {
		return "FactorVO [fecha=" + fecha + ", valor=" + valor + ", id_region=" + id_region + "]";
	}

    
}
