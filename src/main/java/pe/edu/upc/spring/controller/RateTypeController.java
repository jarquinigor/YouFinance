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

import pe.edu.upc.spring.model.RateType;
import pe.edu.upc.spring.service.IRateTypeService;

@Controller
@RequestMapping("/tipoTasa")
public class RateTypeController {
	@Autowired
	private IRateTypeService rtService;
	
	@RequestMapping("/bienvenido")
	public String goWelcomePage() {
		return "welcome"; 
	}
	
	@RequestMapping("/registrar")
	public String register(@ModelAttribute("rateType") RateType objRateType, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "rateType";
		else {
			boolean flag = rtService.save(objRateType);
			if(flag)
				return "redirect:/tipoTasa/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
				return "redirect:/tipoTasa/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modify(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<RateType> objRateType = rtService.findById(id);
		if (objRateType == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche, LUZ ROJA");
			return "redirect:/tipoTasa/listar";
		}
		else {
			model.addAttribute("rateType", objRateType);
			model.addAttribute("rateTypebusqueda", new RateType());
			model.addAttribute("listRateTypes",rtService.findAllSortIdAsc());
			return "listRateType";                   
		}
	}
	
	@RequestMapping("/eliminar")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				rtService.delete(id);
				model.put("rateType",new RateType()); //importante
				model.put("rateTypebusqueda", new RateType()); //importante
				model.put("listRateTypes", rtService.findAllSortIdAsc());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("Mensaje", "Ocurrio un error");
			model.put("rateType",new RateType()); //importante
			model.put("rateTypebusqueda", new RateType()); //importante
			model.put("listRateTypes", rtService.findAllSortIdAsc());
		}
		return "listRateType";
	}
	
	@RequestMapping("/listar")
	public String list(Map<String, Object> model) {
		model.put("rateType",new RateType()); //importante
		model.put("rateTypebusqueda", new RateType()); //importante
		model.put("listRateTypes", rtService.findAllSortIdAsc());
		return "listRateType";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model,@ModelAttribute("rateTypebusqueda") RateType rateType) 
		throws ParseException
	{
		List<RateType>listRateTypes;
		listRateTypes = rtService.findByName(rateType.getNameRateType());
		
		model.put("rateType", new RateType());
		model.put("listRateTypes", listRateTypes);
		
		return "listRateType";
	}
}
