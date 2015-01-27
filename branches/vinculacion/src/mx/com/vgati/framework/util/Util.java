/*
 * Util.java        26/01/2015
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Util {

	public ArrayList<String> filtro(String org) {
		org = org == null ? "" : org;
		org = org.trim().toUpperCase();
		org = org.replace('Á', 'A').replace('À', 'A').replace('É', 'E')
				.replace('È', 'E').replace('Í', 'I').replace('Ì', 'I')
				.replace('Ó', 'O').replace('Ò', 'O').replace('Ú', 'U')
				.replace('Ù', 'U').replace('Ö', 'O').replace('Ü', 'U');
		StringTokenizer tmp = new StringTokenizer(org, " ");
		org = "";
		while (tmp.hasMoreElements()) {
			org += tmp.nextElement().toString() + " ";
		}
		org = org.trim();
		List<String> filtro = new ArrayList<String>();
		filtro.add(" POR LO TANTO ");
		filtro.add(" SIN EMBARGO ");
		filtro.add(" CON TODO ");
		filtro.add(" NO OBSTANTE ");
		filtro.add(" MAS BIEN ");
		filtro.add(" A PESAR DE ");
		filtro.add(" SINO QUE ");
		filtro.add(" ANTES BIEN ");
		filtro.add(" AL CONTRARIO ");
		filtro.add(" TANTO QUE ");
		filtro.add(" ASI QUE ");
		filtro.add(" A ");
		filtro.add(" E ");
		filtro.add(" I ");
		filtro.add(" O ");
		filtro.add(" U ");
		filtro.add(" ANTE ");
		filtro.add(" BAJO ");
		filtro.add(" CABE ");
		filtro.add(" CON ");
		filtro.add(" CONTRA ");
		filtro.add(" DE ");
		filtro.add(" DESDE ");
		filtro.add(" DURANTE ");
		filtro.add(" EN ");
		filtro.add(" ENTRE ");
		filtro.add(" EXCEPTO ");
		filtro.add(" HACIA ");
		filtro.add(" HASTA ");
		filtro.add(" MEDIANTE ");
		filtro.add(" PARA ");
		filtro.add(" POR ");
		filtro.add(" SALVO ");
		filtro.add(" SEGUN ");
		filtro.add(" SIN ");
		filtro.add(" SO ");
		filtro.add(" SOBRE ");
		filtro.add(" TRAS ");
		filtro.add(" EL ");
		filtro.add(" LAS ");
		filtro.add(" LA ");
		filtro.add(" LOS ");
		filtro.add(" LO ");
		filtro.add(" UN ");
		filtro.add(" UNO ");
		filtro.add(" UNA ");
		filtro.add(" UNOS ");
		filtro.add(" UNAS ");
		filtro.add(" SALVO ");
		filtro.add(" PERO ");
		filtro.add(" SINO ");
		filtro.add(" SINO ");
		filtro.add(" CONQUE ");
		filtro.add(" LUEGO ");
		filtro.add(" TAN ");
		filtro.add(" YO ");
		filtro.add(" TU ");
		filtro.add(" ELLA ");
		filtro.add(" ELLAS ");
		filtro.add(" ELLOS ");
		filtro.add(" MI ");
		filtro.add(" Y ");
		filtro.add(" EN ");
		filtro.add(" NI ");
		filtro.add(" QUE ");
		filtro.add(" DEL ");
		filtro.add(" MIS ");
		filtro.add(" TUS ");
		filtro.add(" SU ");
		filtro.add(" SUS ");
		filtro.add(" NUESTRO ");
		filtro.add(" NUESTRA ");
		filtro.add(" NUESTROS ");
		filtro.add(" NUESTRAS ");
		for (String chain : filtro) {
			while (prepara(org).contains(chain))
				org = new String(prepara(org)).replaceAll(chain, "  ");
			org.trim();
		}
		org = org.trim();
		StringTokenizer clr = new StringTokenizer(org, " ");
		org = "";
		List<String> res = new ArrayList<String>();
		while (clr.hasMoreElements()) {
			String s = clr.nextElement().toString();
			org += s + (clr.hasMoreElements() ? " " : "");
			res.add(s);
		}
		return (ArrayList<String>) res;
	}

	public String prepara(String org) {
		return " ".concat(org.trim()).concat(" ");
	}

}
