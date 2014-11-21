package ar.edu.uces.progweb2.booksmov.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.progweb2.booksmov.dto.LoanRequestDto;

@Component
public class LoanValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LoanRequestDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "requestDescription", "errors.loan.description.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productId", "errors.loan.product.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "consigneeId", "errors.loan.consignee.empty");
		
		LoanRequestDto loan = (LoanRequestDto) object;
		
		if(!errors.hasFieldErrors(loan.getRequestDescription())){
			if(loan.getRequestDescription().length() > 500){
				errors.rejectValue("requestDescription", "errors.loan.description.length");
			}
		}
		
	}

}
