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

import pe.edu.upc.spring.model.RateTerm;
import pe.edu.upc.spring.service.IRateTermService;

@Controller
@RequestMapping("/plazoTasa")
public class RateTermController {
	@Autowired
	private IRateTermService rtService;
	
	@RequestMapping("/bienvenido")
	public String goWelcomePage() {
		return "welcome"; 
	}
	
	@RequestMapping("/registrar")
	public String register(@ModelAttribute("rateTerm") RateTerm objRateTerm, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "rateTerm";
		else {
			boolean flag = rtService.save(objRateTerm);
			if(flag)
				return "redirect:/plazoTasa/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
				return "redirect:/plazoTasa/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modify(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<RateTerm> objRateTerm = rtService.findById(id);
		if (objRateTerm == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche, LUZ ROJA");
			return "redirect:/plazoTasa/listar";
		}
		else {
			model.addAttribute("rateTerm", objRateTerm);
			model.addAttribute("rateTermbusqueda", new RateTerm());
			model.addAttribute("listRateTerms",rtService.findAllSortIdAsc());
			return "listRateTerm";                   
		}
	}
	
	@RequestMapping("/eliminar")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				rtService.delete(id);
				model.put("rateTerm",new RateTerm()); //importante
				model.put("rateTermbusqueda", new RateTerm()); //importante
				model.put("listRateTerms", rtService.findAllSortIdAsc());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("Mensaje", "Ocurrio un error");
			model.put("rateTerm",new RateTerm()); //importante
			model.put("rateTermbusqueda", new RateTerm()); //importante
			model.put("listRateTerms", rtService.findAllSortIdAsc());
		}
		return "listRateTerm";
	}
	
	@RequestMapping("/listar")
	public String list(Map<String, Object> model) {
		model.put("rateTerm",new RateTerm()); //importante
		model.put("rateTermbusqueda", new RateTerm()); //importante
		model.put("listRateTerms", rtService.findAllSortIdAsc());
		return "listRateTerm";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model,@ModelAttribute("rateTermbusqueda") RateTerm rateTerm) 
		throws ParseException
	{
		List<RateTerm>listRateTerm;
		listRateTerm = rtService.findByName(rateTerm.getNameRateTerm());
		
		model.put("rateTerm", new RateTerm());
		model.put("listRateTerms", listRateTerm);
		
		return "listRateTerm";
	}
}
