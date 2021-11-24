package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Currency;
import pe.edu.upc.spring.service.ICurrencyService;

@Controller
@RequestMapping("/moneda")
public class CurrencyController {
	@Autowired
	private ICurrencyService cService;
	
	@RequestMapping("/bienvenido")
	public String goWelcomePage() {
		return "welcome"; 
	}
	
	@RequestMapping("/registrar")
	public String register(@ModelAttribute("currency") Currency objCurrency, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "currency";
		else {
			boolean flag = cService.save(objCurrency);
			if(flag)
				return "redirect:/moneda/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
				return "redirect:/moneda/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modify(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Currency> objCurrency = cService.findById(id);
		if (objCurrency == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche, LUZ ROJA");
			return "redirect:/moneda/listar";
		}
		else {
			model.addAttribute("currency", objCurrency);
			model.addAttribute("currencybusqueda", new Currency());
			model.addAttribute("listCurrencies",cService.findAllSortIdAsc());
			return "listCurrency";                   
		}
	}
	
	@RequestMapping("/eliminar")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				cService.delete(id);
				model.put("currency",new Currency()); //importante
				model.put("currencybusqueda", new Currency()); //importante
				model.put("listCurrencies", cService.findAllSortIdAsc());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("Mensaje", "Ocurrio un error");
			model.put("currency",new Currency()); //importante
			model.put("currencybusqueda", new Currency()); //importante
			model.put("listCurrencies", cService.findAllSortIdAsc());
		}
		return "listCurrency";
	}
	
	@RequestMapping("/listar")
	public String list(Map<String, Object> model) {
		model.put("currency",new Currency()); //importante
		model.put("currencybusqueda", new Currency()); //importante
		model.put("listCurrencies", cService.findAllSortIdAsc());
		return "listCurrency";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model,@ModelAttribute("currencybusqueda") Currency currency) 
		throws ParseException
	{
		List<Currency>listCurrencies;
		listCurrencies = cService.findByName(currency.getNameCurrency());
		
		model.put("currency", new Currency());
		model.put("listCurrencies", listCurrencies);
		
		return "listCurrency";
	}
}
