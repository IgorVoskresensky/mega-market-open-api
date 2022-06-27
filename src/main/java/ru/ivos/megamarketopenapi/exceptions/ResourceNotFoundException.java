package ru.ivos.megamarketopenapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author iVos 22.06.2022
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Item not found")
public class ResourceNotFoundException extends RuntimeException{
    private static final Long serialVersionUID = 1L;

    public ResourceNotFoundException(String message){
        super(message);
    }
}
