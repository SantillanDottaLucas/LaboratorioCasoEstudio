package com.example.demo.repository;

import com.example.demo.entity.Genero;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneroRepository  extends JpaRepository<Genero, Integer>{


	public List<Genero> findByGenero(String genero);
	
	public boolean existsByGenero(String genero);
	
}