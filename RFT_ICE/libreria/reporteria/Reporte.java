package libreria.reporteria;

import libreria.utileria.Tipo;
/**
 * Descripcion	: Entidad que representa al reporte. 
 * @author jrosario - Modificado x ssastre para agregar tipo dato, cambiar nombre y ubicacion de archivo, agregar tipo error
 */

public class Reporte {
	private String nombre;
	private Tipo tipo;
	private String info;
	private String valor;
	
	/**
	 * Descriccion: Constructor de la clase.
	 * @param nombre, tipo, valor
	 */
	public Reporte(String nombre, Tipo tipo,String info, String valor) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.info = info;
		this.valor = valor;
	}
	// Seteo del set y get nombre del dato del log
	public String getNombre() {
		return nombre; 	}
	public void setNombre(String nombre) {
		this.nombre = nombre; 	}
	// Seteo del set y get Tipo del dato del log
	public Tipo getTipo() {
		return tipo; 	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo; }
	// Seteo del set y get Info del dato del log
	public String getInfo() {
		return info; 	}
	public void setInfo(String info) {
		this.info = info; 	}
	// Seteo del set y get valor del dato del log
	public String getValor() {
		return valor; 	}
	public void setValor(String valor) {
		this.valor = valor; 	}
}
