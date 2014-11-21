package ar.edu.uces.progweb2.booksmov.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import ar.edu.uces.progweb2.booksmov.dao.LoanDao;
import ar.edu.uces.progweb2.booksmov.dto.LoanRequestDto;
import ar.edu.uces.progweb2.booksmov.dto.ProductDto;
import ar.edu.uces.progweb2.booksmov.model.LoanRequest;
import ar.edu.uces.progweb2.booksmov.model.LoanStateEnum;
import ar.edu.uces.progweb2.booksmov.service.LoanConverterService;
import ar.edu.uces.progweb2.booksmov.service.LoanService;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	private LoanDao loanDao;
	@Autowired
	private LoanConverterService loanConverter;
	
	@Override
	public List<LoanRequestDto> getLoanRequestsByProductAndUserId(Long productId, Long userId) {
		List<LoanRequest> loans = loanDao.getLoanRequestsByProductAndUserId(productId, userId);
		return transform(loans);
	}

	private List<LoanRequestDto> transform(List<LoanRequest> loans) {
		List<LoanRequestDto> loanDtos = new ArrayList<LoanRequestDto>();
		for (LoanRequest loanRequest : loans) {
			loanDtos.add(loanConverter.toDto(loanRequest));
		}
		return loanDtos;
	}

	@Override
	public boolean canRequestLoan(List<LoanRequestDto> loans) {
		
		if(CollectionUtils.isEmpty(loans)){
			return true;
		}
		for (LoanRequestDto loanRequestDto : loans) {
			if(!loanRequestDto.getState().canRequestProductLoan()){
				return false;
			}
		}
		return true;
	}

	@Override
	public void requestLoan(LoanRequest loan) {
		loanDao.save(loan);
	}

	@Override
	public List<LoanRequest> getMyRequestedLoans(Long userId) {
		return loanDao.getMyRequestedLoans(userId);
	}

	@Override
	public List<LoanRequest> getMyNotifiedLoans(Long userId) {
		return loanDao.getMyNotifiedLoans(userId);
	}

	@Override
	public void acceptLoan(Long id) {
		loanDao.acceptLoan(id);
	}

	@Override
	public void rejectLoan(Long id) {
		loanDao.rejectLoan(id);
	}

	@Override
	public void deliverLoan(Long id) {
		loanDao.deliverLoan(id);
	}

	@Override
	public void setRequestableForLoan(List<ProductDto> products, Long userId) {
		
		List<LoanRequestDto> loans = getLoanRequestsByUserId(userId);
		
		for (ProductDto productDto : products) {
			for (LoanRequestDto loanRequest : loans) {
				if(productDto.getId().equals(loanRequest.getProductId())
						&& (LoanStateEnum.PENDING == loanRequest.getState()
						|| LoanStateEnum.REJECTED == loanRequest.getState())){
					productDto.setRequestableForLoan(false);
				}
			}
		}
	}

	@Override
	public List<LoanRequestDto> getLoanRequestsByUserId(Long userId) {
		List<LoanRequest> loans = loanDao.getLoanRequestsByUserId(userId);
		return transform(loans);
	}
	
	
}
