/**
 * factorVO.java
 *
 * Versión V.v 21-oct-2020
 * Javier Herrer Torres
 */
package model;

import org.json.simple.JSONObject;

import java.sql.Date;


/**
 * factorVO
 *
 */
public class FactorVO {
    private Date fecha;
    private int valor;
    private int id_region;

    public FactorVO(Date fecha, int valor, int id_region) {
        this.fecha = fecha;
        this.valor = valor;
        this.id_region = id_region;
    }

    public FactorVO(int valor, int id_region) {
        this.valor = valor;
        this.id_region = id_region;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
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

    public JSONObject toJSON() {
        JSONObject salida = new JSONObject();

        salida.put("idregion",this.id_region);
        salida.put("fecha",this.fecha);
        salida.put("valor", this.valor);
        return salida;
    }
}
