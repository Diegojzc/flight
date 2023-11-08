package com.tokioschool.flightapp.flight.mvc.validator;

import com.tokioschool.flightapp.flight.mvc.dto.FlightMvcDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Component
@RequiredArgsConstructor
public class FlightMvcDTOValidator implements Validator {

    private final LocalValidatorFactoryBean localValidatorFactoryBean;

    @Override
    public boolean supports(Class<?> clazz) {
        return localValidatorFactoryBean.supports(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        localValidatorFactoryBean.validate(target, errors);

        if(errors.hasErrors()){
            return;
        }

        if(!(target instanceof FlightMvcDTO flightMvcDTO)){
            return;
        }
        if(flightMvcDTO.getDeparture().equals(flightMvcDTO.getArrival())){
            errors.rejectValue(
                    "arrival",
                    "validation.flight.arrival_equals_departure",
                    "arrival cannot be the same as departure");
        }

    }
}
