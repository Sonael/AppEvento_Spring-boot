package com.EventoApp.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.EventoApp.app.models.Convidado;
import com.EventoApp.app.models.Evento;

public interface IConvidadoRepository extends CrudRepository<Convidado, String>{

	Iterable<Convidado> findByEvento(Evento evento);
	
	Convidado findByRg(String rg);
	
}
