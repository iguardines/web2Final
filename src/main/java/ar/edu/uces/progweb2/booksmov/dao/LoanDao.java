package ar.edu.uces.progweb2.booksmov.dao;

import java.util.List;

import ar.edu.uces.progweb2.booksmov.model.LoanRequest;

public interface LoanDao {
	
	List<LoanRequest> getLoanRequestsByProductAndUserId(Long productId, Long userId);
	List<LoanRequest> getLoanRequestsByUserId(Long userId);
	List<LoanRequest> getMyRequestedLoans(Long userId);
	List<LoanRequest> getMyNotifiedLoans(Long userId);
	void save(LoanRequest loan);
	void acceptLoan(Long id);
	void rejectLoan(Long id);
	void deliverLoan(Long id);
}
