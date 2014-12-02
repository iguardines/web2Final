package ar.edu.uces.progweb2.booksmov.dao;

import java.util.Date;
import java.util.List;

import ar.edu.uces.progweb2.booksmov.model.LoanRequest;

public interface LoanDao {
	
	List<LoanRequest> getLoanRequestsByProductAndUserId(Long productId, Long userId);
	List<LoanRequest> getLoanRequestsByUserId(Long userId);
	List<LoanRequest> getMyRequestedLoans(Long userId);
	List<LoanRequest> getMyNotifiedLoans(Long userId);
	LoanRequest getLoanById(Long id);
	void save(LoanRequest loan);
	void acceptLoan(Long id, Date responseDate);
	void rejectLoan(Long id, Date responseDate);
	void deliverLoan(Long id, Date deliveryDate);
}
