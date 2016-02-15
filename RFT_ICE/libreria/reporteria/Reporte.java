package libreria.reporteria;

import libreria.utileria.Tipo;

/**
 * Descripcion	: Entidad que representa al reporte. 
 * @author jrosario
 */

public class Reporte {
	private String nombre;
	private Tipo tipo;
	private String info;
	private String valor;
	
	
	/**
	 * Descriccion: Constructor de la clase.
	 * @param nombre
	 * @param tipo
	 * @param valor
	 */
	public Reporte(String nombre, Tipo tipo,String info, String valor) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.info = info;
		this.valor = valor;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the tipo
	 */
	public Tipo getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
}
