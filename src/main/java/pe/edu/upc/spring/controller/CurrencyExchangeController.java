package pe.edu.upc.spring.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.CurrencyExchange;
import pe.edu.upc.spring.service.ICurrencyExchangeService;
import pe.edu.upc.spring.service.ICurrencyService;

@Controller
@RequestMapping("/cambioDivisa")
public class CurrencyExchangeController {
	@Autowired
	private ICurrencyExchangeService ceService;
	@Autowired
	private ICurrencyService cService;
	
	@RequestMapping("/bienvenido")
	public String goWelcomePage() {
		return "welcome"; 
	}
	
	@RequestMapping("/registrar")
	public String register(@ModelAttribute("currencyExchange") CurrencyExchange objCurrencyExchange, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "currency";
		else {
			boolean flag = ceService.save(objCurrencyExchange);
			if(flag)
				return "redirect:/cambioDivisa/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
				return "redirect:/cambioDivisa/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar")
	public String modify(@RequestParam(value="id") Integer id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<CurrencyExchange> objCurrencyExchange = ceService.findById(id);
		if (objCurrencyExchange == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche, LUZ ROJA");
			return "redirect:/cambioDivisa/listar";
		}
		else {
			model.addAttribute("listCurrencies", cService.findAllSortNameAsc());
			
			if(objCurrencyExchange.isPresent())
				objCurrencyExchange.ifPresent(o -> model.addAttribute("currencyExchange", o));
			model.addAttribute("listCurrencyExchanges",ceService.findAllSortIdAsc());
			return "listCurrencyExchange";                   
		}
	}
	
	@RequestMapping("/eliminar")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				ceService.delete(id);
				model.put("listCurrencies", cService.findAllSortNameAsc());
				model.put("currencyExchange",new CurrencyExchange()); //importante
				model.put("listCurrencyExchanges", ceService.findAllSortIdAsc());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("Mensaje", "Ocurrio un error");
			model.put("listCurrencies", cService.findAllSortNameAsc());
			model.put("currencyExchange",new CurrencyExchange()); //importante
			model.put("listCurrencyExchanges", ceService.findAllSortIdAsc());
		}
		return "listCurrencyExchange";
	}
	
	@RequestMapping("/listar")
	public String list(Map<String, Object> model) {
		model.put("listCurrencies", cService.findAllSortNameAsc());
		model.put("currencyExchange",new CurrencyExchange()); //importante
		model.put("listCurrencyExchanges", ceService.findAllSortIdAsc());
		return "listCurrencyExchange";
	}
}
