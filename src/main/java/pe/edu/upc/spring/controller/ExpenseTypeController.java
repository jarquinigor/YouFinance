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

import pe.edu.upc.spring.model.ExpenseType;
import pe.edu.upc.spring.service.IExpenseTypeService;

@Controller
@RequestMapping("/tipoGasto")
public class ExpenseTypeController {
	@Autowired
	private IExpenseTypeService etService;
	
	@RequestMapping("/bienvenido")
	public String goWelcomePage() {
		return "welcome"; 
	}
	
	@RequestMapping("/registrar")
	public String register(@ModelAttribute("expenseType") ExpenseType objExpenseType, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "expenseType";
		else {
			boolean flag = etService.save(objExpenseType);
			if(flag)
				return "redirect:/tipoGasto/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
				return "redirect:/tipoGasto/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modify(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<ExpenseType> objExpenseType = etService.findById(id);
		if (objExpenseType == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche, LUZ ROJA");
			return "redirect:/tipoGasto/listar";
		}
		else {
			model.addAttribute("expenseType", objExpenseType);
			model.addAttribute("expenseTypebusqueda", new ExpenseType());
			model.addAttribute("listExpenseTypes",etService.findAllSortIdAsc());
			return "listExpenseType";                   
		}
	}
	
	@RequestMapping("/eliminar")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				etService.delete(id);
				model.put("expenseType",new ExpenseType()); //importante
				model.put("expenseTypebusqueda", new ExpenseType()); //importante
				model.put("listExpenseTypes", etService.findAllSortIdAsc());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("Mensaje", "Ocurrio un error");
			model.put("expenseType",new ExpenseType()); //importante
			model.put("expenseTypebusqueda", new ExpenseType()); //importante
			model.put("listExpenseTypes", etService.findAllSortIdAsc());
		}
		return "listExpenseType";
	}
	
	@RequestMapping("/listar")
	public String list(Map<String, Object> model) {
		model.put("expenseType",new ExpenseType()); //importante
		model.put("expenseTypebusqueda", new ExpenseType()); //importante
		model.put("listExpenseTypes", etService.findAllSortIdAsc());
		return "listExpenseType";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model,@ModelAttribute("expenseTypebusqueda") ExpenseType expenseType) 
		throws ParseException
	{
		List<ExpenseType>listExpenseTypes;
		listExpenseTypes = etService.findByName(expenseType.getNameExpenseType());
		
		model.put("expenseType", new ExpenseType());
		model.put("listExpenseTypes", listExpenseTypes);
		
		return "listExpenseType";
	}
}
