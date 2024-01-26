package com.api.qlibrary.auxiliar.email;

import lombok.Data;

/***
 * Clase auxiliar para representar atributos publicos.
 * 
 * 
 * @author AAlejo
 *
 */
@Data
public class MailDTO {
	
	/**
	 *  Variable que indica el remitente del mail.
	 */
	String appMailFrom;
	
	/**
	 *  Variable que indica el password del mail del remitente.
	 */
	String password;
	
	/**
	 *  Variable que indica el servidor SMTP.
	 */
	String hostnameSMTP;
	
	/**
	 *  Variable que indica el servidor TLS port.
	 */
	String  startTslPort;
	
	/**
	 *  Variable que indica el status del TLS.
	 */
	String startTLS;
	
	/**
	 *  Variable que indica el status de autenticacion.
	 */
	String auth;
	

}
