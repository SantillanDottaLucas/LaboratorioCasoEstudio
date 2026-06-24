package com.example.demo.service;

import com.example.demo.entity.LibrosDigitales;
import com.example.demo.repository.LibrosDigitalesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; //


//Service es capa intermedia, recibe del controller, ejecuta la lógica del negocio
//y llama al repositorio para guardar o traer datos



@Service
public class LibrosDigitalesService {

	@Autowired
	private LibrosDigitalesRepository libroRepository;
	
	//Leer todo
	public List<LibrosDigitales> listar(){
		return libroRepository.findAll();
	}
	
	//Guardar/Mod
	public LibrosDigitales guardar(LibrosDigitales libro) {
		//REALIZAR VALIDACION
		
		if(libro.getId()==null) {
			if(libroRepository.existsByTitulo(libro.getTitulo())) {
				throw new IllegalArgumentException("El titulo ya se encuentra");
			}
		}else { //Actualizar
			if(!libroRepository.existsById(libro.getId())) {
				throw new IllegalArgumentException("El libro no se encuentra");
			}
		}
		return libroRepository.save(libro);
	}
	
	//Buscar
	public LibrosDigitales buscarId(Integer id) {
		Optional<LibrosDigitales> resultado = libroRepository.findById(id);
		
		return resultado.orElse(null);
	}
	
	//Buscar por autor
	public List<LibrosDigitales> buscarAutor(String autor){
		return libroRepository.findByAutor(autor);
	}
	
	//Buscar por nombre 
	public List<LibrosDigitales> buscarNombre(String nombre){
		return libroRepository.findByTitulo(nombre);
	}
	
	//Eliminar
	public void eliminar(Integer id) {
		libroRepository.deleteById(id);
	}
}
