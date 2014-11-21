package ar.edu.uces.progweb2.booksmov.service;

import ar.edu.uces.progweb2.booksmov.dto.LoanRequestDto;
import ar.edu.uces.progweb2.booksmov.model.LoanRequest;

public interface LoanConverterService {
	
	LoanRequestDto toDto(LoanRequest loanRequest);
}
