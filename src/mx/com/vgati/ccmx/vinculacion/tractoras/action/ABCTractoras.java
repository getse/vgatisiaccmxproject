package mx.com.vgati.ccmx.vinculacion.tractoras.action;

import mx.com.vgati.framework.action.AbstractBaseAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.Result;

/**
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
@Namespaces({ @Namespace(value = "tractoras/administracion/datos"),
		@Namespace(value = "tractoras/administracion/compradores"),
		@Namespace(value = "tractoras/administracion/requerimientos"),
		@Namespace(value = "tractoras/administracion/busquedas"),
		@Namespace(value = "tractoras/administracion/reportes"),
		@Namespace(value = "tractoras/datos"),
		@Namespace(value = "tractoras/compradores"),
		@Namespace(value = "tractoras/requerimientos"),
		@Namespace(value = "tractoras/busquedas"),
		@Namespace(value = "tractoras/reportes") })
public class ABCTractoras extends AbstractBaseAction {

	private static final long serialVersionUID = 6076350949482670437L;
	private int menu = 1;

	@Action(value = "/addDatAdm", results = { @Result(name = "success", location = "tractoras.administracion.datos.add", type = "tiles") })
	public String addDatAdm() {
		return SUCCESS;
	}

	@Action(value = "/showDatAdm", results = { @Result(name = "success", location = "tractoras.administracion.datos.show", type = "tiles") })
	public String showDatAdm() {
		return SUCCESS;
	}

	@Action(value = "/addComAdm", results = { @Result(name = "success", location = "tractoras.administracion.compradores.add", type = "tiles") })
	public String addComAdm() {
		return SUCCESS;
	}

	@Action(value = "/showComAdm", results = { @Result(name = "success", location = "tractoras.administracion.compradores.show", type = "tiles") })
	public String showComAdm() {
		return SUCCESS;
	}

	@Action(value = "/addReqAdm", results = { @Result(name = "success", location = "tractoras.administracion.requerimientos.add", type = "tiles") })
	public String addReqAdm() {
		return SUCCESS;
	}

	@Action(value = "/showReqAdm", results = { @Result(name = "success", location = "tractoras.administracion.requerimientos.show", type = "tiles") })
	public String showReqAdm() {
		return SUCCESS;
	}

	@Action(value = "/addBusAdm", results = { @Result(name = "success", location = "tractoras.administracion.busquedas.add", type = "tiles") })
	public String addBusAdm() {
		return SUCCESS;
	}

	@Action(value = "/showBusAdm", results = { @Result(name = "success", location = "tractoras.administracion.busquedas.show", type = "tiles") })
	public String showBusAdm() {
		return SUCCESS;
	}

	@Action(value = "/addRepAdm", results = { @Result(name = "success", location = "tractoras.administracion.reportes.add", type = "tiles") })
	public String addRepAdm() {
		return SUCCESS;
	}

	@Action(value = "/showRepAdm", results = { @Result(name = "success", location = "tractoras.administracion.reportes.show", type = "tiles") })
	public String showRepAdm() {
		return SUCCESS;
	}

	@Action(value = "/addDat", results = { @Result(name = "success", location = "tractoras.datos.add", type = "tiles") })
	public String addDat() {
		return SUCCESS;
	}

	@Action(value = "/showDat", results = { @Result(name = "success", location = "tractoras.datos.show", type = "tiles") })
	public String showDat() {
		return SUCCESS;
	}

	@Action(value = "/addCom", results = { @Result(name = "success", location = "tractoras.compradores.add", type = "tiles") })
	public String addCom() {
		return SUCCESS;
	}

	@Action(value = "/showCom", results = { @Result(name = "success", location = "tractoras.compradores.show", type = "tiles") })
	public String showCom() {
		return SUCCESS;
	}

	@Action(value = "/addReq", results = { @Result(name = "success", location = "tractoras.requerimientos.add", type = "tiles") })
	public String addReq() {
		return SUCCESS;
	}

	@Action(value = "/showReq", results = { @Result(name = "success", location = "tractoras.requerimientos.show", type = "tiles") })
	public String showReq() {
		return SUCCESS;
	}

	@Action(value = "/addBus", results = { @Result(name = "success", location = "tractoras.busquedas.add", type = "tiles") })
	public String addBus() {
		return SUCCESS;
	}

	@Action(value = "/showBus", results = { @Result(name = "success", location = "tractoras.busquedas.show", type = "tiles") })
	public String showBus() {
		return SUCCESS;
	}

	@Action(value = "/addRep", results = { @Result(name = "success", location = "tractoras.reportes.add", type = "tiles") })
	public String addRep() {
		return SUCCESS;
	}

	@Action(value = "/showRep", results = { @Result(name = "success", location = "tractoras.reportes.show", type = "tiles") })
	public String showRep() {
		return SUCCESS;
	}

	public void setMenu(int menu) {
		this.menu = menu;
	}

	public int getMenu() {
		return menu;
	}

}
