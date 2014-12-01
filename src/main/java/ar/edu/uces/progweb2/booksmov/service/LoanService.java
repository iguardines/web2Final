package ar.edu.uces.progweb2.booksmov.service;

import java.util.List;

import ar.edu.uces.progweb2.booksmov.dto.LoanDto;
import ar.edu.uces.progweb2.booksmov.dto.ProductDto;
import ar.edu.uces.progweb2.booksmov.model.LoanRequest;

public interface LoanService {
	
	List<LoanDto> getLoanRequestsByProductAndUserId(Long productId, Long userId);
	List<LoanDto> getLoanRequestsByUserId(Long userId);
	List<LoanRequest> getMyRequestedLoans(Long userId);
	List<LoanRequest> getMyNotifiedLoans(Long userId);
	LoanRequest getLoanById(Long id);
	boolean canRequestLoan(List<LoanDto> loans);
	void requestLoan(LoanRequest loan);
	void acceptLoan(Long id);
	void rejectLoan(Long id);
	void deliverLoan(Long id);
	void setRequestableForLoan(List<ProductDto> products, Long userId);
}
