package ar.edu.uces.progweb2.booksmov.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import ar.edu.uces.progweb2.booksmov.dto.LoanDto;
import ar.edu.uces.progweb2.booksmov.dto.LoanStateDtoHolder;
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
	private LoanValidator loanValidator;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/request/{id}", method=RequestMethod.GET)
	public String requestLoan(@PathVariable("id") Long productId, @RequestParam("owner") Long ownerId, ModelMap model){
		
		if(productId != null && ownerId != null){
			Long userId = ((User) model.get("user")).getId();
			List<LoanDto> loans = loanService.getLoanRequestsByProductAndUserId(productId, userId);
			if(loanService.canRequestLoan(loans)){
				LoanDto loanDto = new LoanDto();
				loanDto.setConsigneeId(ownerId);
				loanDto.setProductId(productId);
				model.addAttribute("loanDto", loanDto);
				return "loanRequest";
			}
			model.addAttribute("message", MessageUtils.getMessage("loan.not.allowed"));
		}
		
		return "forward:/app/search";
	}
	
	@RequestMapping(value="/request", method=RequestMethod.POST)
	public String requestLoan(@ModelAttribute("loanDto") LoanDto dto, BindingResult result, ModelMap model){
		
		loanValidator.validate(dto, result);
		User requester = (User) model.get("user");	
		List<LoanDto> loans = loanService.getLoanRequestsByProductAndUserId(dto.getProductId(), requester.getId());
		if(!loanService.canRequestLoan(loans)){
			model.addAttribute("message", MessageUtils.getMessage("loan.not.allowed"));
			return "loanRequest";
		}
		if(!result.hasErrors()){
			
			User consignee = userService.getUserById(dto.getConsigneeId());
			Product product = productService.getProductById(dto.getProductId());
			Date requestDate = new Date();
			LoanRequest loan = new LoanRequest(product, dto.getRequestDescription(), LoanStateEnum.PENDING, requester, consignee, requestDate, null);
			loanService.requestLoan(loan);
			model.addAttribute("message", MessageUtils.getMessage("loan.submit.successfully"));
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
	@ResponseBody
	public String acceptLoan(@PathVariable("id") Long id, ModelMap model, Locale locale) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		LoanRequest loan = loanService.getLoanById(id);
		LoanStateDtoHolder loanState = null;
		if(loan != null){
			if(loan.getState() == LoanStateEnum.PENDING){
				String message = MessageUtils.getMessage("loan.state.accepted");
				Date responseDate = new Date();
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
				loanService.acceptLoan(id, responseDate);
				loanState = new LoanStateDtoHolder(LoanStateEnum.ACCEPTED, "springgreen", message);
				loanState.setResponseDate(formatter.format(responseDate));
			}
		}
		return ow.writeValueAsString(loanState);
	}
	
	@RequestMapping(value="/reject/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String rejectLoan(@PathVariable("id") Long id, ModelMap model) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		LoanRequest loan = loanService.getLoanById(id);
		LoanStateDtoHolder loanState = null;
		if(loan != null){
			if(loan.getState() == LoanStateEnum.PENDING){
				String message = MessageUtils.getMessage("loan.state.rejected");
				Date responseDate = new Date();
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
				loanService.rejectLoan(id, responseDate);
				loanState = new LoanStateDtoHolder(LoanStateEnum.REJECTED, "tomato", message);
				loanState.setResponseDate(formatter.format(responseDate));
			}
		}
		return ow.writeValueAsString(loanState);
	}
	
	@RequestMapping(value="/deliver/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String deliverLoan(@PathVariable("id") Long id, ModelMap model) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		LoanRequest loan = loanService.getLoanById(id);
		LoanStateDtoHolder loanState = null;
		if(loan != null){
			if(loan.getState() == LoanStateEnum.ACCEPTED){
				String message = MessageUtils.getMessage("loan.state.delivered");
				Date deliveryDate = new Date();
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
				loanService.deliverLoan(id, deliveryDate);
				loanState = new LoanStateDtoHolder(LoanStateEnum.DELIVERED, "lightgray", message);
				loanState.setDeliveryDate(formatter.format(deliveryDate));
			}
		}
		return ow.writeValueAsString(loanState);
	}
}
