package web.ecommerce.tpfinal.ecommerce_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import web.ecommerce.tpfinal.ecommerce_web.clasesDominio.Compra;
import web.ecommerce.tpfinal.ecommerce_web.forms.CompraForm;
import web.ecommerce.tpfinal.ecommerce_web.repository.OrdenDeCompraRepository;



@Controller
@RequestMapping(value="/ordendecompra/**")
public class UserAdminController {

	@Autowired
	private OrdenDeCompraRepository ordenDeCompraRepository;

	//Carga del changuito a la base de datos echo por otro grupo. Con su boton aceptar pueda cargar este controler.
	@RequestMapping(value="crearChanguito", method=RequestMethod.POST)
	public ModelAndView crearChanguito(@ModelAttribute Compra compra){

		ModelAndView mav = new ModelAndView();
		ordenDeCompraRepository.cargarChanguitoDB(compra);
		return mav;
	}



	/************************************************************************************
	 *
	 ***********************************************************************************/
	//crea la vista del admin
	@RequestMapping(value="admin", method=RequestMethod.GET)
	public ModelAndView vistaAdmin(){

		ModelAndView mav = new ModelAndView();


		mav.getModelMap().addAttribute("compras", ordenDeCompraRepository.extraerCompras());

		return mav;
	}

	//crea la vista del adminconfirmado
	@RequestMapping(value="adminconfirmado", method=RequestMethod.GET)
	public ModelAndView vistaAdminconfirmado(){

		ModelAndView mav = new ModelAndView();


		mav.getModelMap().addAttribute("compras", ordenDeCompraRepository.extraerCompras());

		return mav;
	}

	//Obtiene los datos del admin.jsp y subirlo a la tabla que se va a llamar comprasVerificadas
	@RequestMapping(value="verificarCompras", method=RequestMethod.POST)
	public ModelAndView verificarCompras(@RequestParam long id, @RequestParam String estadoCompra){
		ModelAndView mav = new ModelAndView("redirect:/ordendecompra/admin");

		ordenDeCompraRepository.cargarCompras(id,estadoCompra);

		return mav;
	}

	
	/************************************************************************************
	 *
	 ***********************************************************************************/
	
	//crea vista del user
	@RequestMapping(value="user", method=RequestMethod.GET)
	public ModelAndView vistaUser (){
		ModelAndView mav = new ModelAndView();
		
		mav.getModelMap().addAttribute("usuarios", ordenDeCompraRepository.extraerUsuarios());
		
		return mav;
	}
	
	

}
