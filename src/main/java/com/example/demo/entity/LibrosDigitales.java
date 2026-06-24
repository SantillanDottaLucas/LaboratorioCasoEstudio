package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;



@Entity
@Table(name="librosdig")
public class LibrosDigitales {
	
	@Id //Indica que es ID
	@GeneratedValue(strategy=GenerationType.IDENTITY) //estrategia para generar automaticamente id's
	private Integer id; //uso Integer para que el valor por defecto sea NULL y no 0
	
	@Column(name="titulo", length=50, nullable=false)
	private String titulo;
	
	@Column(name="autor", length=30, nullable=false)
	private String autor;
	
	@Column(name="editorial", length=30, nullable=false)
	private String editorial;
	
	@Column(name="anioEdicion", precision=4, nullable=false)
	private Integer anioEdicion;
	
	@ManyToOne
	@JoinColumn(name="id_genero", nullable=false)
	private Genero genero;
	
	

	public Integer getId() {return id;}
	public String getTitulo() {return titulo;}
	public String getAutor(){return autor;}
	public String getEditorial() {return editorial;}
	public Integer getAnio() {return anioEdicion;}
	public Genero getGenero() {return genero;}
	
	public void setId(Integer id) {this.id=id;}
	public void setTitulo(String titulo) {this.titulo=titulo;}
	public void setAutor(String autor) {this.autor=autor;}
	public void setEditorial(String editorial) {this.editorial=editorial;}
	public void setAnio(Integer anio) {anioEdicion = anio;}
	public void setGenero(Genero genero) {this.genero=genero;}





}
