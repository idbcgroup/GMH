package org.fourgeeks.gha.webclient.client.UI;

import gwtupload.client.MultiUploader;
/**
 * Componente para la subida de fotografias
 * @author luis
 *
 */
public class GHAUploadPhotographs extends MultiUploader{

	public GHAUploadPhotographs(int cantidadMaximaArchivos, String tamano, String titulo)
	{
		super();
		setMaximumFiles(cantidadMaximaArchivos);
		setHeight(tamano);
		setTitle(titulo);
	}
}
