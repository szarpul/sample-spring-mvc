package com.intive.samples.spring.mvc.samples;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return Account.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {

        Account account = (Account) o;

        if (!account.getNumber().startsWith("PL")){
            errors.rejectValue("number", "not.polish.value");
        }
    }
}
