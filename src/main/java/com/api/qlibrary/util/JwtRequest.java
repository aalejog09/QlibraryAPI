package com.api.qlibrary.util;

import lombok.Data;

/**
 * Clase para identificar la consulta de Token/login para la aplicacion.
 * @author AAlejo
 *
 */
@Data
public class JwtRequest {

    private String username;
    private String password;

    public JwtRequest(){
    	super();
    }

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
