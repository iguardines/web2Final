package ar.edu.uces.progweb2.booksmov.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import ar.edu.uces.progweb2.booksmov.dto.LoanRequestDto;
import ar.edu.uces.progweb2.booksmov.model.LoanRequest;
import ar.edu.uces.progweb2.booksmov.model.LoanStateEnum;
import ar.edu.uces.progweb2.booksmov.model.Product;
import ar.edu.uces.progweb2.booksmov.model.User;
import ar.edu.uces.progweb2.booksmov.service.LoanService;
import ar.edu.uces.progweb2.booksmov.service.ProductService;
import ar.edu.uces.progweb2.booksmov.service.UserService;
import ar.edu.uces.progweb2.booksmov.utils.MessageUtils;
import ar.edu.uces.progweb2.booksmov.validator.LoanValidator;

@Controller
@SessionAttributes("user")
@RequestMapping("/app/loan")
public class LoanController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private LoanService loanService;
	@Autowired
	private MessageUtils messageUtils;
	@Autowired
	private LoanValidator loanValidator;
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/request/{id}", method=RequestMethod.GET)
	public String requestLoan(@PathVariable("id") Long productId, @RequestParam("owner") Long ownerId, ModelMap model){
		
		if(productId != null && ownerId != null){
			Long userId = ((User) model.get("user")).getId();
			List<LoanRequestDto> loans = loanService.getLoanRequestsByProductAndUserId(productId, userId);
			if(loanService.canRequestLoan(loans)){
				LoanRequestDto loanDto = new LoanRequestDto();
				loanDto.setConsigneeId(ownerId);
				loanDto.setProductId(productId);
				model.addAttribute("loanDto", loanDto);
				return "loanRequest";
			}
			model.addAttribute("message", messageUtils.getMessage("loan.not.allowed"));
		}
		
		return "forward:/app/search";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/request", method=RequestMethod.POST)
	public String requestLoan(@ModelAttribute("loanDto") LoanRequestDto dto, BindingResult result, ModelMap model){
		
		loanValidator.validate(dto, result);
		User requester = (User) model.get("user");	
		List<LoanRequestDto> loans = loanService.getLoanRequestsByProductAndUserId(dto.getProductId(), requester.getId());
		if(!loanService.canRequestLoan(loans)){
			model.addAttribute("message", messageUtils.getMessage("loan.not.allowed"));
			return "loanRequest";
		}
		if(!result.hasErrors()){
			
			User consignee = userService.getUserById(dto.getConsigneeId());
			Product product = productService.getProductById(dto.getProductId());
			Date requestDate = new Date();
			LoanRequest loan = new LoanRequest(product, dto.getRequestDescription(), LoanStateEnum.PENDING, requester, consignee, requestDate, null);
			loanService.requestLoan(loan);
			model.addAttribute("message", messageUtils.getMessage("loan.submit.successfully"));
		}
		return "loanRequest";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String displayMyRequestedLoans(ModelMap model) throws IOException{
		Long userId = ((User) model.get("user")).getId();
		List<LoanRequest> loans = loanService.getMyRequestedLoans(userId);
		model.addAttribute("loans", loans);
		return "myLoans";
	}

	@RequestMapping(value="/notifications", method=RequestMethod.GET)
	public String displayMyNotifiedLoans(ModelMap model) throws IOException{
		Long userId = ((User) model.get("user")).getId();
		List<LoanRequest> loans = loanService.getMyNotifiedLoans(userId);
		model.addAttribute("loans", loans);
		return "myLoanNotifications";
	}
	
	@RequestMapping(value="/accept/{id}", method=RequestMethod.GET)
	public String acceptLoan(@PathVariable("id") Long id, ModelMap model){
		loanService.acceptLoan(id);
		return "redirect:/app/loan/notifications";
	}
	
	@RequestMapping(value="/reject/{id}", method=RequestMethod.GET)
	public String rejectLoan(@PathVariable("id") Long id, ModelMap model){
		loanService.rejectLoan(id);
		return "redirect:/app/loan/notifications";
	}
	
	@RequestMapping(value="/deliver/{id}", method=RequestMethod.GET)
	public String deliverLoan(@PathVariable("id") Long id, ModelMap model){
		loanService.deliverLoan(id);
		return "redirect:/app/loan/notifications";
	}
}
