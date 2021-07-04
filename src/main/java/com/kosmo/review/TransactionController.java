package com.kosmo.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import transaction.TicketDAO;
import transaction.TicketDTO;

@Controller
public class TransactionController {

	private TicketDAO dao;
	
	@Autowired
	public void setDao(TicketDAO dao) {
		this.dao = dao;
		System.out.println("@Autowired => TicketDAO 주입성공");
	}
	
	@RequestMapping("/transaction/buyTicketMain.do")
	public String buyTicketMain() {
		
		return "08Transaction/buyTicketMain";
		
	}
	
	@RequestMapping("/transaction/buyTicketAction.do")
	public String buyTicketAction(TicketDTO ticketDTO,
			Model model) {
		
		dao.buyTicket(ticketDTO);
		model.addAttribute("ticketInfo",ticketDTO);
		
		return "08Transaction/buyTicketAction";
		
	}
	
	
	
}
