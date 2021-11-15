package com.EventoApp.app.controlles;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.EventoApp.app.models.Convidado;
import com.EventoApp.app.models.Evento;
import com.EventoApp.app.repository.IConvidadoRepository;
import com.EventoApp.app.repository.IEventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private IEventoRepository er; 
	
	@Autowired
	private IConvidadoRepository cr;
	
	@RequestMapping(value = "/cadastrarEvento",method=RequestMethod.GET)
	public String form()
	{
		return "evento/formEvento";
	}
	
	@RequestMapping(value = "/cadastrarEvento",method=RequestMethod.POST)
	public String form(@Valid Evento evento,BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			attributes.addFlashAttribute("mensagem", "Verifique os Campos");
			return "redirect:/cadastrarEvento";
		}
		er.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso");
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos()
	{
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo)
	{
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);
		
		return mv;
	}
	
	@RequestMapping("/deletarEvento")
	public String deletarEvento(long codigo)
	{
		Evento evento = er.findByCodigo(codigo);
		er.delete(evento);
		return "redirect:/eventos";
	}
	
	@RequestMapping("/deletarConvidado")
	private String deletarConvidado(String rg)
	{
		Convidado convidado = cr.findByRg(rg);
		cr.delete(convidado);
		
		Evento evento = convidado.getEvento();
		long codigoLong = evento.getCodigo();
		String codigo = ""+codigoLong;
		return "redirect:/"+ codigo;
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public String detalhesEventoPOST(@PathVariable("codigo") long codigo,@Valid Convidado convidado, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			attributes.addFlashAttribute("mensagem", "Verifique os Campos");
			return "redirect:/{codigo}";
		}
		Evento evento = er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso");
		return "redirect:/{codigo}";
	}
	
	
	
}
