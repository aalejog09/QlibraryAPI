package com.api.qlibrary.auxiliar.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;



/***
 * 
 * Clase auxiliar para representar atributos publicos de un correo.
 * 
 * @author AAlejo
 *
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDTO {

    private String toUser;
    private String subject;
    private String message;
}
