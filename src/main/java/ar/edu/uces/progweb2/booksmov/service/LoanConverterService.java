package ar.edu.uces.progweb2.booksmov.service;

import ar.edu.uces.progweb2.booksmov.dto.LoanDto;
import ar.edu.uces.progweb2.booksmov.model.LoanRequest;

public interface LoanConverterService {
	
	LoanDto toDto(LoanRequest loanRequest);
}
