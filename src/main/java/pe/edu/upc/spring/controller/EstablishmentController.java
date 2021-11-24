package pe.edu.upc.spring.controller;

import java.util.List;
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

import pe.edu.upc.spring.model.Establishment;
import pe.edu.upc.spring.model.Users;
import pe.edu.upc.spring.service.IEstablishmentService;
import pe.edu.upc.spring.service.IEstablishmentTypeService;
import pe.edu.upc.spring.service.IUsersService;

@Controller
@RequestMapping("/establecimiento")
public class EstablishmentController {
	@Autowired
	private IEstablishmentService eService;
	@Autowired
	private IUsersService uService;
	@Autowired
	private IEstablishmentTypeService etService;
	
	@RequestMapping("/bienvenido")
	public String goWelcomePage() {
		return "welcome";
	}

	@RequestMapping("/registrar")
	public String register(@ModelAttribute("establishment") Establishment objEstablishment, @RequestParam("idUser") Integer idUser, BindingResult binRes,
			Model model, RedirectAttributes redirectAttributes) throws ParseException {
		if (binRes.hasErrors())
			return "establishment";
		else {
			Optional<Users>objUsers = uService.findById(idUser);
			if(objUsers.isPresent())
				objUsers.ifPresent(o -> objEstablishment.setUser(o));
		
			boolean flag = eService.save(objEstablishment);
			
			if (flag){
				Optional<Users>users = uService.findById(idUser);
				if(users.isPresent())
					users.ifPresent(o -> redirectAttributes.addAttribute("idUser", o));
				return "redirect:/establecimiento/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
				return "redirect:/establecimiento/listar";
			}
		}
	}

	@RequestMapping("/modificar")
	public String modify(Model model, RedirectAttributes objRedir, @RequestParam("id") Integer id, @RequestParam("idUser") Integer idUser) throws ParseException {
		Optional<Establishment> objEstablishment = eService.findById(id);
		if (objEstablishment == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche, LUZ ROJA");
			return "redirect:/establecimiento/listar";
		} else {
			Optional<Establishment> establishment = eService.findById(id);
			if(establishment.isPresent())
				establishment.ifPresent(o -> model.addAttribute("establishment", o)); //RECORDAR SI UN OBJETO CONTIENE OTRO OBJETO, ES NECESARIO EXTRAERLO
			model.addAttribute("establishmentbusqueda", new Establishment());
			model.addAttribute("listEstablishments", eService.findByUserId(idUser));
			model.addAttribute("listEstablishmentTypes", etService.findAllSortNameAsc());
			return "listEstablishment";
		}         
	}

	@RequestMapping("/eliminar")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id, @RequestParam("idUser") Integer idUser) {
		try {
			if (id != null && id > 0) {
				eService.delete(id);
				model.put("establishment", new Establishment());
				model.put("establishmentbusqueda", new Establishment());
				model.put("listEstablishments", eService.findByUserId(idUser));
				model.put("listEstablishmentTypes", etService.findAllSortNameAsc());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("Mensaje", "Ocurrio un error");
			model.put("establishment", new Establishment());
			model.put("establishmentbusqueda", new Establishment());
			model.put("listEstablishments", eService.findByUserId(idUser));
			model.put("listEstablishmentTypes", etService.findAllSortNameAsc());
		}
		return "listEstablishment";
	}

	@RequestMapping("/listar")
	public String list(Map<String, Object> model, @RequestParam("idUser") Integer idUser) {
		model.put("establishment", new Establishment());
		model.put("establishmentbusqueda", new Establishment());
		model.put("listEstablishments", eService.findByUserId(idUser));
		model.put("listEstablishmentTypes", etService.findAllSortNameAsc());
		return "listEstablishment";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model,@ModelAttribute("establishmentbusqueda") Establishment establishment, @RequestParam("idUser") Integer idUser) 
		throws ParseException
	{
		List<Establishment>listEstablishments;
		listEstablishments = eService.findByName(establishment.getNameEstablishment(), idUser);
		
		model.put("establishment", new Establishment());
		model.put("listEstablishments", listEstablishments);
		model.put("listEstablishmentTypes", etService.findAllSortNameAsc());
		
		return "listEstablishment";
	}
}
