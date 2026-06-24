package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name="generos")
public class Genero {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer idGenero;

	@Column(name="genero", length=20, nullable=false)
	private String genero;
	
	public Integer getIdGenero() {return idGenero;}
	public String getGenero() {return genero;}
	
	public void setGenero(String genero) {this.genero=genero;}
	public void setId(Integer id) {this.idGenero=id;}

	
}
