package mx.com.vgati.framework.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Archivos extends AbstractBaseDTO {

	private int idServiciosDiplomado;
	private int idConsultoria;
	private List<File> uploads = new ArrayList<File>();
    private List<String> uploadFileNames = new ArrayList<String>();
    private List<String> uploadContentTypes = new ArrayList<String>();
    public List<String> descripcionArchivos = new ArrayList<String>();
    
	public int getIdServiciosDiplomado() {
		return idServiciosDiplomado;
	}
	public void setIdServiciosDiplomado(int idServiciosDiplomado) {
		this.idServiciosDiplomado = idServiciosDiplomado;
	}
	public int getIdConsultoria() {
		return idConsultoria;
	}
	public void setIdConsultoria(int idConsultoria) {
		this.idConsultoria = idConsultoria;
	}
	public List<File> getUpload() {
        return this.uploads;
    }
    public void setUpload(List<File> uploads) {
        this.uploads = uploads;
    }

    public List<String> getUploadFileName() {
        return this.uploadFileNames;
    }
    public void setUploadFileName(List<String> uploadFileNames) {
        this.uploadFileNames = uploadFileNames;
    }

    public List<String> getUploadContentType() {
        return this.uploadContentTypes;
    }
    public void setUploadContentType(List<String> contentTypes) {
        this.uploadContentTypes = contentTypes;
    }
	public void setDescripcionArchivos(List<String> descripcionArchivos) {
		this.descripcionArchivos = descripcionArchivos;
	}
	public List<String> getDescripcionArchivos() {
		return descripcionArchivos;
	}
	
}
