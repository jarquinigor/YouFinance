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

import pe.edu.upc.spring.model.Capitalization;
import pe.edu.upc.spring.service.ICapitalizationService;

@Controller
@RequestMapping("/capitalizacion")
public class CapitalizationController {
	@Autowired
	private ICapitalizationService cService;
	
	@RequestMapping("/bienvenido")
	public String goWelcomePage() {
		return "welcome"; 
	}
	
	@RequestMapping("/registrar")
	public String register(@ModelAttribute("capitalization") Capitalization objCapitalization, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "capitalization";
		else {
			boolean flag = cService.save(objCapitalization);
			if(flag)
				return "redirect:/capitalizacion/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
				return "redirect:/capitalizacion/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modify(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Capitalization> objCapitalization = cService.findById(id);
		if (objCapitalization == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche, LUZ ROJA");
			return "redirect:/capitalizacion/listar";
		}
		else {
			model.addAttribute("capitalization", objCapitalization);
			model.addAttribute("capitalizationbusqueda", new Capitalization());
			model.addAttribute("listCapitalizations",cService.findAllSortIdAsc());
			return "listCapitalization";                   
		}
	}
	
	@RequestMapping("/eliminar")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				cService.delete(id);
				model.put("capitalization",new Capitalization()); //importante
				model.put("capitalizationbusqueda", new Capitalization()); //importante
				model.put("listCapitalizations", cService.findAllSortIdAsc());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("Mensaje", "Ocurrio un error");
			model.put("capitalization",new Capitalization()); //importante
			model.put("capitalizationbusqueda", new Capitalization()); //importante
			model.put("listCapitalizations", cService.findAllSortIdAsc());
		}
		return "listCapitalization";
	}
	
	@RequestMapping("/listar")
	public String list(Map<String, Object> model) {
		model.put("capitalization",new Capitalization()); //importante
		model.put("capitalizationbusqueda", new Capitalization()); //importante
		model.put("listCapitalizations", cService.findAllSortIdAsc());
		return "listCapitalization";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model,@ModelAttribute("capitalizationbusqueda") Capitalization capitalization) 
		throws ParseException
	{
		List<Capitalization>listCapitalization;
		listCapitalization = cService.findByName(capitalization.getNameCapitalization());
		
		model.put("capitalization", new Capitalization());
		model.put("listCapitalizations", listCapitalization);
		
		return "listCapitalization";
	}
}
