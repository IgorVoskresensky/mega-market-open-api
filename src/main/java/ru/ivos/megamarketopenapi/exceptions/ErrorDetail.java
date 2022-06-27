package ru.ivos.megamarketopenapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author iVos 22.06.2022
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorDetail {

    private int code;
    private String message;
}
