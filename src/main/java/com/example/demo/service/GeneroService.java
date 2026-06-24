package com.example.demo.service;

import com.example.demo.entity.Genero;
import com.example.demo.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

	@Autowired
	private GeneroRepository generoRepository;
	
	
	public List<Genero> listar(){
		return generoRepository.findAll();
	}
	
	public Genero guardar(Genero genero) {
		//Libro nuevo (INSERT)
		if(genero.getIdGenero() == null) {
			
			if(generoRepository.existsByGenero(genero.getGenero())) { 
				throw new IllegalArgumentException("Genero repetido");
			}
		}else { //Actualizacion (UPDATE)
			if(!generoRepository.existsById(genero.getIdGenero())) {
				throw new IllegalArgumentException("Genero inexistente");
			}
		}
		
		return generoRepository.save(genero);
	}
	
	public List<Genero> buscarNombre(String genero) {
		return generoRepository.findByGenero(genero);
	}
	
	
	
	public Genero buscarId(Integer id) {
		Optional<Genero> resultado = generoRepository.findById(id);
		
		return resultado.orElse(null);
	}
	
	public void eliminar(Integer id) {
		generoRepository.deleteById(id);
	}
}
