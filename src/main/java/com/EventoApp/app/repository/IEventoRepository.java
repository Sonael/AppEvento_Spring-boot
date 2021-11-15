package com.EventoApp.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.EventoApp.app.models.Evento;

public interface IEventoRepository extends CrudRepository<Evento, String> {

	Evento findByCodigo(long codigo);
	
}
