package pe.edu.upc.spring.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.apache.poi.ss.formula.functions.Irr;


import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Expense;
import pe.edu.upc.spring.model.Movement;
import pe.edu.upc.spring.model.MovementType;
import pe.edu.upc.spring.model.Portfolio;
import pe.edu.upc.spring.service.ICapitalizationService;
import pe.edu.upc.spring.service.ICurrencyService;
import pe.edu.upc.spring.service.IEstablishmentService;
import pe.edu.upc.spring.service.IExpenseService;
import pe.edu.upc.spring.service.IExpenseTypeService;
import pe.edu.upc.spring.service.IMovementService;
import pe.edu.upc.spring.service.IMovementTypeService;
import pe.edu.upc.spring.service.IPortfolioService;
import pe.edu.upc.spring.service.IRateTermService;
import pe.edu.upc.spring.service.IRateTypeService;

@Controller
@RequestMapping("/movimiento")
public class MovementController {
	@Autowired
	private IMovementService mService;
	@Autowired
	private IMovementTypeService mtService;
	@Autowired
	private IEstablishmentService eService;
	@Autowired
	private IRateTermService rtService;
	@Autowired
	private ICurrencyService cService;
	@Autowired
	private IRateTypeService rtypeService;
	@Autowired
	private IExpenseService expService;
	@Autowired
	private IExpenseTypeService epService;
	@Autowired
	private IPortfolioService pService;
	@Autowired
	private ICapitalizationService rcService;

	@RequestMapping("/")
	public String goFacturaPage(Model model) {
		model.addAttribute("movement", new Movement());
		model.addAttribute("listEstablishments", eService.findAllSortNameAsc());
		model.addAttribute("listRateTypes", rtypeService.findAllSortNameAsc());
		return "dataFactura";
	}

	@RequestMapping("/letra")
	public String goLetraPage() {
		return "dataLetra";
	}

	@RequestMapping("/registrar") //SE ACTIVA EN DATAFACTURA
	public String register(@ModelAttribute("movement") Movement objMovement,
			@RequestParam("idPortfolio") Integer idPortfolio, BindingResult binRes, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) throws ParseException {
		if (binRes.hasErrors())
			return "movement";
		else {	
			Optional<Portfolio>objPortfolio = pService.findById(idPortfolio);
			if(objPortfolio.isPresent())
				objPortfolio.ifPresent(o -> objMovement.setPortfolio(o));
			
			boolean flag = mService.save(objMovement);

			if (flag) {

				if (objMovement.getRateType().getIdRateType() == 1) // EFECTIVA
				{
					session.setAttribute("movimiento", objMovement);
					return "redirect:/movimiento/tasaEfectiva";
				} else { //NOMINAL
					session.setAttribute("movimiento", objMovement);
					return "redirect:/movimiento/tasaNominal";
				}
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
				return "redirect:/movimiento/irRegistrar";
			}
		}
	}

	@RequestMapping("/tasaEfectiva")
	public String rateEfective(Map<String, Object> model) {
		model.put("movement", new Movement());
		model.put("listRateTerms", rtService.findAllSortIdAsc());
		return "dataTasaEfectiva";
	}
	
	@RequestMapping("/tasaNominal")
	public String rateNominal(Map<String, Object> model) {
		model.put("movement", new Movement());
		model.put("listRateTerms", rtService.findAllSortIdAsc());
		model.put("listCapitalizations", rcService.findAllSortIdAsc());
		return "dataTasaNominal";
	}

	@RequestMapping("/registraDatosTasa")
	public String rateDataDefault(@ModelAttribute("movement") Movement objMovement,
			@RequestParam("idMovement") Integer idMovement, RedirectAttributes redirectAttributes, Model model) {
		// El objeto viene con DiasPorAño, PlazoTasa(FK) y PorcentajeTasaEfectiva
		if(objMovement.getRateTerm().getIdRateTerm()==1) {
			Optional<Movement> movement = mService.findById(idMovement);
			if (movement.isPresent())
				movement.ifPresent(o -> o.setDiasAnioMovement(objMovement.getDiasAnioMovement()));
			if (movement.isPresent())
				movement.ifPresent(o -> o.setRateTerm(objMovement.getRateTerm()));
			if (movement.isPresent())
				movement.ifPresent(o -> o.setPercentTasaMovement(objMovement.getPercentTasaMovement()));
			if (movement.isPresent()) 
				movement.ifPresent(o -> o.setDiasPlazoTasaMovement(objMovement.getDiasPlazoTasaMovement()));
			if (movement.isPresent())
				movement.ifPresent(o -> mService.save(o)); // REDIRECT(POR CADA SAVE, SU REDIRECT) BUSCA LA FORMA URGENTE
		}
		else {
			Optional<Movement> movement = mService.findById(idMovement);
			if (movement.isPresent())
				movement.ifPresent(o -> o.setDiasAnioMovement(objMovement.getDiasAnioMovement()));
			if (movement.isPresent())
				movement.ifPresent(o -> o.setRateTerm(objMovement.getRateTerm()));
			if (movement.isPresent())
				movement.ifPresent(o -> o.setPercentTasaMovement(objMovement.getPercentTasaMovement()));
			if (movement.isPresent()) 
				movement.ifPresent(o -> o.setDiasPlazoTasaMovement(objMovement.getDiasPlazoTasaMovement()));
			if (movement.isPresent()) 
				movement.ifPresent(o -> o.setCapitalization(objMovement.getCapitalization()));
			if (movement.isPresent()) 
				movement.ifPresent(o -> o.setDiasCapitalizacionTasaMovement(objMovement.getDiasCapitalizacionTasaMovement()));
			if (movement.isPresent())
				movement.ifPresent(o -> mService.save(o));
		}
		
		redirectAttributes.addAttribute("idMovement", idMovement);
		return "redirect:/movimiento/listaGastos";
	}

	@RequestMapping("/listaGastos")
	public String listExpense(Map<String, Object> model, @RequestParam("idMovement") Integer idMovement) {

		model.put("expense1", new Expense());
		model.put("expense2", new Expense());
		model.put("listExpenseTypes1", epService.findByMoment(1));// INICIALES
		model.put("listExpenseTypes2", epService.findByMoment(2));// FINALES
		model.put("listExpenses1", expService.findByMovementId(idMovement, 1));
		model.put("listExpenses2", expService.findByMovementId(idMovement, 2));

		return "dataGastos";
	}
	
	@RequestMapping("/registraGasto")
	public String registerExpense(@ModelAttribute("expense") Expense objExpense,
			@RequestParam("idMovement") Integer idMovement, RedirectAttributes redirectAttributes, Model model) {

		Optional<Movement> objMovement = mService.findById(idMovement);
		if (objMovement.isPresent())
			objMovement.ifPresent(o -> objExpense.setMovement(o));

		boolean flag = expService.save(objExpense);
		if (flag) {
			redirectAttributes.addAttribute("idMovement", idMovement);
			return "redirect:/movimiento/listaGastos";
		} else {
			model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
			return "dataGastos";
		}
	}

	@RequestMapping("/eliminarGasto")
	public String delete(Map<String, Object> model, @RequestParam(value = "idExpense") Integer idExpense,
			@RequestParam("idMovement") Integer idMovement) {
		try {
			if (idExpense != null && idExpense > 0) {
				expService.delete(idExpense);
				model.put("expense1", new Expense());
				model.put("expense2", new Expense());
				model.put("listExpenseTypes1", epService.findByMoment(1));// INICIALES
				model.put("listExpenseTypes2", epService.findByMoment(2));// FINALES
				model.put("listExpenses1", expService.findByMovementId(idMovement, 1));
				model.put("listExpenses2", expService.findByMovementId(idMovement, 2));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("Mensaje", "Ocurrio un error");
			model.put("expense1", new Expense());
			model.put("expense2", new Expense());
			model.put("listExpenseTypes1", epService.findByMoment(1));// INICIALES
			model.put("listExpenseTypes2", epService.findByMoment(2));// FINALES
			model.put("listExpenses1", expService.findByMovementId(idMovement, 1));
			model.put("listExpenses2", expService.findByMovementId(idMovement, 2));
		}
		return "dataGastos";
	}
	
	
	@RequestMapping("/eliminar") 															
	public String deleteMovement(@RequestParam("idMovement") Integer idMovement, @RequestParam("idPortfolio") Integer idPortfolio,RedirectAttributes redirectAttributes) {
		
		expService.deleteAllByMovementId(idMovement); //ELIMINAMOS LOS GASTOS
		//AHORA ELIMINAMOS EL MOVIMIENTO
		
		mService.delete(idMovement);
		
		redirectAttributes.addAttribute("idPortfolio",idPortfolio);
		return "redirect:/cartera/verCartera";
	}
	
	
	@RequestMapping("/registraResultados")
	public String updateMovement(@RequestParam("idMovement") Integer idMovement, @RequestParam("idPortfolio") Integer idPortfolio, Model model,
			RedirectAttributes redirectAttributes) throws ParseException {

		Movement movement = mService.findByMovementId(idMovement).get(0); //OBTENEMOS EL MOVIMIENTO CON SUS DATOS CARGADOS
		double diasAnio;
		if(movement.getDiasAnioMovement()==360)
		{
			diasAnio = 360.0;
		}	
		else //365
		{	
			diasAnio = 365.0;
		}
		
		List<Portfolio>portfolio = pService.findByPortfolioId(idPortfolio);

		Calendar dscto = Calendar.getInstance();
		Calendar vencimiento = Calendar.getInstance();

		dscto.setTime(portfolio.get(0).getDescuentoPortfolio());
		vencimiento.setTime(movement.getVencimientoMovement());

		long milisegundos = vencimiento.getTimeInMillis() - dscto.getTimeInMillis();

		int dias = (int) ((((milisegundos / 1000) / 60) / 60) / 24);

		movement.setCantidadDiasMovement(dias);// (3) - dias

		System.out.println(dias);
		// (3) - TEP	
		System.out.println("TASA INGRESADA: "+movement.getPercentTasaMovement()+"%");
		double TE = movement.getPercentTasaMovement() / 100.0;
		System.out.println("TASA INGRESADA DIVIDIDA POR 100.0: "+TE);
		double TEP = 0.0;
		int diasTE;
		if (movement.getRateType().getIdRateType() == 1) { //EFECTIVA
						
			if (movement.getRateTerm().getIdRateTerm() != 9) //
			{
				diasTE = movement.getRateTerm().getDaysRateTerm();
				System.out.println("DIAS ACTUALES DE LA TASA: "+ diasTE);
				TEP = Math.pow((1 + TE), ((double)dias / (double)diasTE)) - 1.0;
				System.out.println("TASA DEL PERIODO SIN PORCENTAJE: "+ TEP);
				movement.setTasaEfectivaPeriodoMovement(TEP*100.0);// (3) - TEP
				System.out.println("Tasa Efectiva de los 99 DÍAS CON %" + TEP*100+"%");
			} else {
				diasTE = movement.getDiasPlazoTasaMovement();// ESPECIAL
				TEP = Math.pow((1 + TE), ((double)dias / (double)diasTE)) - 1.0;			
				movement.setTasaEfectivaPeriodoMovement(TEP*100.0);// (3) - TEP
			}
		}
		else {//NOMINAL
			double m = 0.0;
			double TEPc = 0.0;
			if (movement.getRateTerm().getIdRateTerm() != 9) //SI LA TASA NO TIENE TIEMPO ESPECIAL
			{
				if(movement.getCapitalization().getIdCapitalization()!=9) { //SI HAY CAPITALIZACIÓN NORMAL
					m = ((double)movement.getRateTerm().getDaysRateTerm() / (double)movement.getCapitalization().getDaysCapitalization());
					TEPc = TE / m;//TEPc
					TEP = Math.pow((1 + TEPc), ((double)dias / (double)movement.getCapitalization().getDaysCapitalization())) - 1.0;
					movement.setTasaEfectivaPeriodoMovement(TEP*100.0);// (3) - TEP
				}
				else {  // SI NO HAY CAPITALIZACIÓN NORMAL
					m = ((double)movement.getRateTerm().getDaysRateTerm() / (double)movement.getDiasCapitalizacionTasaMovement());
					TEPc = TE / m;//TEPc
					TEP = Math.pow((1 + TEPc), ((double)dias / (double)movement.getDiasCapitalizacionTasaMovement())) - 1.0;
					movement.setTasaEfectivaPeriodoMovement(TEP*100.0);// (3) - TEP
				}
			} else {// TASA DE TIEMPO ESPECIAL
				if(movement.getCapitalization().getIdCapitalization()!=9) {
					m = ((double)movement.getDiasPlazoTasaMovement() / (double)movement.getCapitalization().getDaysCapitalization());
					TEPc = TE / m;//TEPc
					TEP = Math.pow((1 + TEPc), ((double)dias / (double)movement.getCapitalization().getDaysCapitalization())) - 1.0;	
					movement.setTasaEfectivaPeriodoMovement(TEP*100.0);// (3) - TEP
				}
				else {
					m = ((double)movement.getDiasPlazoTasaMovement() / (double)movement.getDiasCapitalizacionTasaMovement());
					TEPc = TE / m;//TEPc
					TEP = Math.pow((1 + TEPc), ((double)dias / (double)movement.getDiasCapitalizacionTasaMovement())) - 1.0;	
					movement.setTasaEfectivaPeriodoMovement(TEP*100.0);// (3) - TEP
				}		
			}
		}
		// (3) - d%
		double d;
		d = TEP / (1.0 + TEP);
		movement.setTasaDescuentoPeriodoMovement(d*100.0);

		// (3) - COSTO INICIAL y COSTO FINAL
		double costoInicial = 0.0;
		double costoFinal = 0.0;
		for (int i = 0; i < expService.findByMovementId(idMovement, 1).size(); i++) {
			costoInicial = costoInicial + expService.findByMovementId(idMovement, 1).get(i).getAmountExpense();
		}
		for (int i = 0; i < expService.findByMovementId(idMovement, 2).size(); i++) {
			costoFinal = costoFinal + expService.findByMovementId(idMovement, 2).get(i).getAmountExpense();
		}
		movement.setCostoInicialMovement(costoInicial);
		movement.setCostoFinalMovement(costoFinal);

		// (3) - DESCUENTO
		double descuento = movement.getValorNominalMovement() * d;
		movement.setDescuentoMovement(descuento);

		// (3) - VALOR NETO
		movement.setValorNetoMovement(movement.getValorNominalMovement() - descuento);
		
		// (3) - VALOR RECIBIDO
		movement.setValorRecibidoMovement(movement.getValorNetoMovement() - costoInicial - movement.getRetencionMovement());

		// (3) - VALOR ENTREGADO
		movement.setValorEntregadoMovement(
				movement.getValorNominalMovement() + costoFinal - movement.getRetencionMovement());
		// (3) - VALOR TCEA

		double TCEA = Math.pow((movement.getValorEntregadoMovement() / movement.getValorRecibidoMovement()),
				(diasAnio / (double) movement.getCantidadDiasMovement())) - 1.0;
		movement.setTceaMovement(TCEA);
		movement.setTceaMovement(TCEA * 100.0);
		boolean flag = mService.save(movement);  //SE GUARDA EL MOVIMIENTO
		
		//DEBEMOS CARGAR LOS DATOS DE LA CARTERA (VALOR RECIBIDO TOTAL Y TCEA)
		//valor recibido cargado
		double valorTotalRecibido = 0.0;
		for (int i = 0; i < mService.findByPortfolioId(idPortfolio).size(); i++) {
			valorTotalRecibido = valorTotalRecibido + mService.findByPortfolioId(idPortfolio).get(i).getValorRecibidoMovement();
		}		
		
		List<Portfolio> objPortfolio = pService.findByPortfolioId(idPortfolio);
		objPortfolio.get(0).setValorTotalRecibidoPortfolio(valorTotalRecibido);
		
		//valor de TCEA cargado
		//(debemos crear una lista de doubles, debe contener full días, y pasarlo por la función calculaTIR)
		List<Double>lst = new ArrayList<Double>();
		Calendar auxiliar = Calendar.getInstance(); 
		List<Movement>movements = mService.findByPortfolioId(idPortfolio);
		
		//Hallamos mayor fecha de vencimiento
		Calendar max = Calendar.getInstance();
		Calendar aux = Calendar.getInstance();
		max.setTime(movements.get(0).getVencimientoMovement());
		for (int i = 0; i < movements.size(); i++) {
			if(i+1 == movements.size()) {
				break;
			}
			else {
				aux.setTime(movements.get(i+1).getVencimientoMovement());
				if(max.compareTo(aux)<0) //si max es menor que aux 
				{
					max.setTime(aux.getTime());//cambiamos su valor al mayor
				}
			}
		}

		double acum;
		lst.add(valorTotalRecibido*(-1.0));//lst.add(valorTotalRecibido);
		while(true) {
			dscto.add(Calendar.DATE, 1);
						
			if(dscto.compareTo(max) > 0)
				break;
			acum = 0.0;
			for (int i = 0; i < movements.size(); i++) {
				auxiliar.setTime(movements.get(i).getVencimientoMovement());
				if(dscto.compareTo(auxiliar)==0) {	//si son iguales
					acum = acum + movements.get(i).getValorEntregadoMovement();
				}
			}
			
			if(acum > 0.0) {
				lst.add(acum); //lst.add(acum*(-1.0));
			}
			else {
				lst.add(0.0);
			}
			
		}
		
		double[] cashFlows = new double[lst.size()];
		
		for (int i = 0; i < lst.size(); i++) {
			cashFlows[i] = lst.get(i);
		}
		
		double IRR = Irr.irr(cashFlows)*100.0;
		
		IRR = IRR/100.0;
		IRR =  Math.pow((IRR+1), (diasAnio / 1.0)) - 1.0;
		
		objPortfolio.get(0).setTceaPortfolio(IRR*100.0);	
		pService.save(objPortfolio.get(0));//FINAL	
		
		//VEMOS LA CARTERA
		if (flag) {
			
			redirectAttributes.addAttribute("idPortfolio",idPortfolio);
			return "redirect:/cartera/verCartera";
		}
		else {
			redirectAttributes.addAttribute("idPortfolio",idPortfolio);
			return "redirect:/cartera/verCartera";
		}
			
	}


}
