/**
 * Sistema de Soporte a la Gestión de Servicios
 */
package org.fourgeeks.gha.webclient.server;

import java.io.Serializable;

/**
 * se utiliza para almacenar el nombre original, nombre temporal
 * y ubicacion de la foto
 * 
 * @author lcampo
 */
public class Photo implements Serializable {
	private static final long serialVersionUID = -8599160658711915849L;
	private String name;
	private String nameTemp;
	private String url;
	
	public Photo(String name, String nameTemp, String url){
		this.name = name;
		this.nameTemp = nameTemp;
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameTemp() {
		return nameTemp;
	}
	public void setNameTemp(String nameTemp) {
		this.nameTemp = nameTemp;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	} 
}