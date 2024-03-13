package br.com.alunoonline.api.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class CursoValidator implements
        ConstraintValidator<CursoValidation, String> {


    private String message;

    @Override
    public void initialize(CursoValidation constraintAnnotation) {
        message = constraintAnnotation.message();
    }


    @Override
    public boolean isValid(String nome, ConstraintValidatorContext constraintValidatorContext) {
        if(nome.contains("Ciências da Computação")||(nome.contains("TI"))){

            return true;
        }
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(message + nome)//
                .addConstraintViolation();
        return false;
    }
}