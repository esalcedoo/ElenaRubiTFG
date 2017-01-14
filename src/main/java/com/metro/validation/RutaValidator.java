/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metro.validation;

import com.metro.model.impl.StopPlaceImpl;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author elena.salcedo
 */
public class RutaValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return StopPlaceImpl.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Compruebo argumentos vacíos
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "origen", "origen.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "destino", "destino.required");
    }

}
