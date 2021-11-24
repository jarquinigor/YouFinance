package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Movement;
import pe.edu.upc.spring.model.MovementType;
import pe.edu.upc.spring.model.Portfolio;
import pe.edu.upc.spring.model.Users;
import pe.edu.upc.spring.service.ICurrencyExchangeService;
import pe.edu.upc.spring.service.ICurrencyService;
import pe.edu.upc.spring.service.IMovementService;
import pe.edu.upc.spring.service.IMovementTypeService;
import pe.edu.upc.spring.service.IPortfolioService;
import pe.edu.upc.spring.service.IUsersService;

@Controller
@RequestMapping("/cartera")
public class PortfolioController {
	@Autowired
	private IPortfolioService pService;
	@Autowired
	private IUsersService uService;
	@Autowired
	private IMovementTypeService mtService;
	@Autowired
	private IMovementService mService;
	@Autowired
	private ICurrencyService cService;
	@Autowired
	private ICurrencyExchangeService ceService;

	@RequestMapping("/")
	public String goPortfolioPage() {
		return "menuCarteras";
	}

	@RequestMapping("/registrar")
	public String register(@ModelAttribute("portfolio") Portfolio objPortfolio, @RequestParam("idUser") Integer idUser,
			@RequestParam("idMovementType") Integer idMovementType, RedirectAttributes redirectAttributes,
			BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "welcomeUser";
		else {
			Optional<Users> objUser = uService.findById(idUser);
			Optional<MovementType> objMovementType = mtService.findById(idMovementType);

			if (objMovementType.isPresent())
				objMovementType.ifPresent(o -> objPortfolio.setMovementType(o));

			if (objUser.isPresent())
				objUser.ifPresent(o -> objPortfolio.setUser(o));

			boolean flag = pService.save(objPortfolio);
			if (flag) {
				redirectAttributes.addAttribute("idUser", idUser);
				redirectAttributes.addAttribute("idMovementType", idMovementType);
				return "redirect:/cartera/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
				return "redirect:/cartera/irRegistrar";
			}
		}
	}

	@RequestMapping("/eliminar") // AÚN NO FUNCIONARÍA POR PK
	public String delete(Map<String, Object> model, @RequestParam("idMovementType") Integer idMovementType,@RequestParam(value = "idPortfolio") Integer idPortfolio,
			@RequestParam("idUser") Integer idUser, RedirectAttributes redirectAttribute) {
		try {
			if (idPortfolio != null && idPortfolio > 0) {
				pService.delete(idPortfolio);
				redirectAttribute.addAttribute("idUser", idUser);
				redirectAttribute.addAttribute("idMovementType", idMovementType);
				return "redirect:/cartera/listar";
			}
		} catch (Exception ex) {
			redirectAttribute.addAttribute("idUser", idUser);
			redirectAttribute.addAttribute("idMovementType", idMovementType);
			return "redirect:/cartera/listar";
		}
		return "listPortfolio";
	}

	@RequestMapping("/verCartera")
	public String portfolio(Map<String, Object> model, @RequestParam(value = "idPortfolio") Integer idPortfolio,
			HttpSession session) {

		Optional<Portfolio> objPortfolio = pService.findById(idPortfolio);
		if (objPortfolio.isPresent())
			objPortfolio.ifPresent(o -> session.setAttribute("portfolio", o));
		model.put("movement", new Movement());
		model.put("listMovements", mService.findByPortfolioId(idPortfolio));

		return "cartera";
	}

	@RequestMapping("/verCarteraDiv")
	public String portfolioDiv(@RequestParam("idPortfolio") Integer idPortfolio, Model model, HttpSession session) {

		List<Movement> movements = mService.findByPortfolioId(idPortfolio);
		//
		Portfolio portfolio = pService.findById(idPortfolio).get();
		if (portfolio.getCurrency().getIdCurrency() == 1)// sol
		{
			double tipoCambio = ceService.findCurrencyExchange(2, 1).get(0).getAmountCurrencyExchange();
			for (int i = 0; i < movements.size(); i++) {
				movements.get(i).setValorNominalMovement(movements.get(i).getValorNominalMovement() / tipoCambio);
				movements.get(i).setRetencionMovement(movements.get(i).getRetencionMovement() / tipoCambio);
				movements.get(i).setDescuentoMovement(movements.get(i).getDescuentoMovement() / tipoCambio);
				movements.get(i).setCostoInicialMovement(movements.get(i).getCostoInicialMovement() / tipoCambio);
				movements.get(i).setCostoFinalMovement(movements.get(i).getCostoFinalMovement() / tipoCambio);
				movements.get(i).setValorNetoMovement(movements.get(i).getValorNetoMovement() / tipoCambio);
				movements.get(i).setValorRecibidoMovement(movements.get(i).getValorRecibidoMovement() / tipoCambio);
				movements.get(i).setValorEntregadoMovement(movements.get(i).getValorEntregadoMovement() / tipoCambio);
			}
		} else {
			double tipoCambio = ceService.findCurrencyExchange(1, 2).get(0).getAmountCurrencyExchange();
			for (int i = 0; i < movements.size(); i++) {
				movements.get(i).setValorNominalMovement(movements.get(i).getValorNominalMovement() / tipoCambio);
				movements.get(i).setRetencionMovement(movements.get(i).getRetencionMovement() / tipoCambio);
				movements.get(i).setDescuentoMovement(movements.get(i).getDescuentoMovement() / tipoCambio);
				movements.get(i).setCostoInicialMovement(movements.get(i).getCostoInicialMovement() / tipoCambio);
				movements.get(i).setCostoFinalMovement(movements.get(i).getCostoFinalMovement() / tipoCambio);
				movements.get(i).setValorNetoMovement(movements.get(i).getValorNetoMovement() / tipoCambio);
				movements.get(i).setValorRecibidoMovement(movements.get(i).getValorRecibidoMovement() / tipoCambio);
				movements.get(i).setValorEntregadoMovement(movements.get(i).getValorEntregadoMovement() / tipoCambio);
			}
		}
		//

		Optional<Portfolio> objPortfolio = pService.findById(idPortfolio);
		if (objPortfolio.isPresent())
			objPortfolio.ifPresent(o -> session.setAttribute("portfolio", o));
		model.addAttribute("movement", new Movement());
		model.addAttribute("listMovements", movements);

		return "cartera";
	}

	@RequestMapping("/listar")
	public String list(Map<String, Object> model, @RequestParam("idUser") Integer idUser,
			@RequestParam("idMovementType") Integer idMovementType) {
		model.put("portfolio", new Portfolio());
		model.put("listCurrencies", cService.findAllSortIdAsc());
		model.put("listPortfolios", pService.findByUserAndMovementTypeId(idUser, idMovementType));

		if (idMovementType == 1) {
			model.put("movementType", "FACTURAS");
			model.put("idMovementType", idMovementType);
		} else {
			if (idMovementType == 2) {
				model.put("movementType", "LETRAS");
				model.put("idMovementType", idMovementType);
			} else {
				model.put("movementType", "RECIBOS POR HONORARIOS");
				model.put("idMovementType", idMovementType);
			}
		}

		return "listPortfolio";
	}
}
