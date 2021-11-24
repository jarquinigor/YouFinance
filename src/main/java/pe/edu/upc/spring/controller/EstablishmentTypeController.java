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

import pe.edu.upc.spring.model.EstablishmentType;
import pe.edu.upc.spring.service.IEstablishmentTypeService;

@Controller
@RequestMapping("/tipoEstablecimiento")
public class EstablishmentTypeController {
	@Autowired
	private IEstablishmentTypeService eService;
	
	@RequestMapping("/bienvenido")
	public String goWelcomePage() {
		return "welcome"; 
	}
	
	@RequestMapping("/registrar")
	public String register(@ModelAttribute("establishmentType") EstablishmentType objEstablishmentType, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "establishment";
		else {
			boolean flag = eService.save(objEstablishmentType);
			if(flag)
				return "redirect:/tipoEstablecimiento/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
				return "redirect:/tipoEstablecimiento/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modify(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<EstablishmentType> objEstablishmentType = eService.findById(id);
		if (objEstablishmentType == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche, LUZ ROJA");
			return "redirect:/tipoEstablecimiento/listar";
		}
		else {
			model.addAttribute("establishmentType", objEstablishmentType);
			model.addAttribute("establishmentTypebusqueda", new EstablishmentType());
			model.addAttribute("listEstablishmentTypes",eService.findAllSortIdAsc());
			return "listEstablishmentType";                   
		}
	}
	
	@RequestMapping("/eliminar")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				eService.delete(id);
				model.put("establishmentType",new EstablishmentType()); //importante
				model.put("establishmentTypebusqueda", new EstablishmentType()); //importante
				model.put("listEstablishmentTypes", eService.findAllSortIdAsc());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("Mensaje", "Ocurrio un error");
			model.put("establishmentType", new EstablishmentType());
			model.put("establishmentTypebusqueda", new EstablishmentType());
			model.put("listEstablishmentTypes", eService.findAllSortIdAsc());
		}
		return "listEstablishmentType";
	}
	
	@RequestMapping("/listar")
	public String list(Map<String, Object> model) {
		model.put("establishmentType",new EstablishmentType());
		model.put("establishmentTypebusqueda", new EstablishmentType());
		model.put("listEstablishmentTypes", eService.findAllSortIdAsc());
		return "listEstablishmentType";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model,@ModelAttribute("establishmentTypebusqueda") EstablishmentType establishmentType) 
		throws ParseException
	{
		List<EstablishmentType>listEstablishments;
		listEstablishments = eService.findByName(establishmentType.getNameEstablishmentType());
		
		model.put("establishmentType", new EstablishmentType());
		model.put("listEstablishmentTypes", listEstablishments);
		
		return "listEstablishmentType";
	}
}
