package com.EventoApp.app.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.EventoApp.app.models.Evento;
import com.EventoApp.app.repository.IEventoRepository;

@Controller
public class IndexController {
	@Autowired
	private IEventoRepository er;
	
	@RequestMapping("/")
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
}
