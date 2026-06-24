package com.example.demo.repository;

import com.example.demo.entity.LibrosDigitales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibrosDigitalesRepository  extends JpaRepository<LibrosDigitales, Integer>{

	
	
	public List<LibrosDigitales> findByAutor(String autor);
	
	public List<LibrosDigitales> findByTitulo(String titulo);
	
	public boolean existsByAutor(String autor);
	
	public boolean existsByTitulo(String titulo);
}
