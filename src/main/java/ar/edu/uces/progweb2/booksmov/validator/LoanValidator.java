package ar.edu.uces.progweb2.booksmov.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.progweb2.booksmov.dto.LoanDto;

@Component
public class LoanValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LoanDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "requestDescription", "errors.loan.description.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productId", "errors.loan.product.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "consigneeId", "errors.loan.consignee.empty");
		
		LoanDto loan = (LoanDto) object;
		
		if(!errors.hasFieldErrors(loan.getRequestDescription())){
			if(loan.getRequestDescription().length() > 500){
				errors.rejectValue("requestDescription", "errors.loan.description.length");
			}
		}
		
	}

}
