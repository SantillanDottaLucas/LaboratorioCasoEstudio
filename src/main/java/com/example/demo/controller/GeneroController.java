package com.example.demo.controller;

import com.example.demo.entity.Genero;
import com.example.demo.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable; // 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; //

@Controller
@RequestMapping("/generos")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;
	
	// 1. LISTAR Y BUSCAR
	@GetMapping
	public String listarGeneros(@RequestParam(name = "buscarNombre", required = false) String buscarNombre, Model model) {
		
		// Se decide si filtrar o listar todo
		if (buscarNombre != null && !buscarNombre.trim().isEmpty()) {
			model.addAttribute("listaGeneros", generoService.buscarNombre(buscarNombre));
		} else {
			model.addAttribute("listaGeneros", generoService.listar());
		}
		
		// Truco clave: Si ya viene cargado un género para editar (desde el método de abajo), no lo pisamos con uno vacío
		if (!model.containsAttribute("nuevoGenero")) {
			model.addAttribute("nuevoGenero", new Genero());
		}
		
		return "generos";
	}
	
	// 2. GUARDAR 
	@PostMapping("/guardar")
    public String guardarGenero(@ModelAttribute("nuevoGenero") Genero genero) {
        try {
            generoService.guardar(genero);
        } catch (IllegalArgumentException e) {
            return "redirect:/generos?error=" + e.getMessage();
        }
        return "redirect:/generos"; 
    }

	// 3. EDITAR Recupera el género de la BD y lo manda de vuelta al formulario de la misma página
	@GetMapping("/editar/{id}")
	public String prepararEdicion(@PathVariable("id") Integer id, Model model) {
		Genero generoAEditar = generoService.buscarId(id);
		
		model.addAttribute("nuevoGenero", generoAEditar); // Al meterlo con el mismo nombre, el form se autofila solo
		model.addAttribute("listaGeneros", generoService.listar()); // Recarga la lista
		
		return "generos"; // Renderiza la misma vista, pero con los campos llenos
	}

	// 4. ELIMINAR
	@GetMapping("/eliminar/{id}")
	public String eliminarGenero(@PathVariable("id") Integer id) {
		try {
			generoService.eliminar(id);
		} catch (Exception e) {
			// Por si se quiere borrar un género que está agarrado de un libro en la BD (Foreign Key)
			return "redirect:/generos?error=No se puede eliminar: existen libros asociados a este genero.";
		}
		return "redirect:/generos";
	}
}