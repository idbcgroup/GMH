package org.fourgeeks.gha.webclient.client.UI;

import gwtupload.client.MultiUploader;

public class GHAUploadPhotographs extends MultiUploader{

	public GHAUploadPhotographs(int cantidadMaximaArchivos, String tamano, String titulo)
	{
		super();
		setMaximumFiles(cantidadMaximaArchivos);
		setHeight(tamano);
		setTitle(titulo);
	}
}
