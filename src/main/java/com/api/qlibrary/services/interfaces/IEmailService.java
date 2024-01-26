package com.api.qlibrary.services.interfaces;

/**
 * Interface que implkementa el servicio de email.
 * @author AAlejo
 *
 */
public interface IEmailService {

	/**
	 * Metodo que implementa el servicio de envio de correo.
	 * @param toUser
	 * @param subject
	 * @param message
	 */
    void sendEmail(String toUser, String subject, String message);

}