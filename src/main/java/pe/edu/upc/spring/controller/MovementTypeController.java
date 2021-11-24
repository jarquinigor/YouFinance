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

import pe.edu.upc.spring.model.MovementType;
import pe.edu.upc.spring.service.IMovementTypeService;

@Controller
@RequestMapping("/tipoMovimiento")
public class MovementTypeController {
	@Autowired
	private IMovementTypeService mtService;
	
	@RequestMapping("/bienvenido")
	public String goWelcomePage() {
		return "welcome"; 
	}
	
	@RequestMapping("/registrar")
	public String register(@ModelAttribute("movementType") MovementType objMovementType, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "movementType";
		else {
			boolean flag = mtService.save(objMovementType);
			if(flag)
				return "redirect:/tipoMovimiento/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
				return "redirect:/tipoMovimiento/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modify(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<MovementType> objMovementType = mtService.findById(id);
		if (objMovementType == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche, LUZ ROJA");
			return "redirect:/tipoMovimiento/listar";
		}
		else {
			model.addAttribute("movementType", objMovementType);
			model.addAttribute("movementTypebusqueda", new MovementType());
			model.addAttribute("listMovementTypes",mtService.findAllSortIdAsc());
			return "listMovementType";                   
		}
	}
	
	@RequestMapping("/eliminar")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				mtService.delete(id);
				model.put("movementType",new MovementType()); //importante
				model.put("movementTypebusqueda", new MovementType()); //importante
				model.put("listMovementTypes", mtService.findAllSortIdAsc());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("Mensaje", "Ocurrio un error");
			model.put("movementType",new MovementType()); //importante
			model.put("movementTypebusqueda", new MovementType()); //importante
			model.put("listMovementTypes", mtService.findAllSortIdAsc());
		}
		return "listMovementType";
	}
	
	@RequestMapping("/listar")
	public String list(Map<String, Object> model) {
		model.put("movementType",new MovementType()); //importante
		model.put("movementTypebusqueda", new MovementType()); //importante
		model.put("listMovementTypes", mtService.findAllSortIdAsc());
		return "listMovementType";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model,@ModelAttribute("movementTypebusqueda") MovementType movementType) 
		throws ParseException
	{
		List<MovementType>listMovementTypes;
		listMovementTypes = mtService.findByName(movementType.getNameMovementType());
		
		model.put("movementType", new MovementType());
		model.put("listMovementTypes", listMovementTypes);
		
		return "listMovementType";
	}
}
