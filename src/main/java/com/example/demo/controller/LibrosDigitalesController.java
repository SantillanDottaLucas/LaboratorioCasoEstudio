package com.example.demo.controller;

import com.example.demo.entity.LibrosDigitales;
import com.example.demo.service.LibrosDigitalesService;
import com.example.demo.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/libros") // Mapea todas las URLs de este controlador bajo /libros
public class LibrosDigitalesController {

	
	//Me conecto con los dos services
    @Autowired
    private LibrosDigitalesService libroService;

    @Autowired
    private GeneroService generoService; 

    @GetMapping
    public String index(Model model, 
                        @RequestParam(value = "nombre", required = false) String nombre,
                        @RequestParam(value = "autor", required = false) String autor) {
        
        List<LibrosDigitales> libros;

        // Filtramos según lo que el usuario haya escrito en los buscadores del HTML
        if (nombre != null && !nombre.trim().isEmpty()) {
            libros = libroService.buscarNombre(nombre);
        } else if (autor != null && !autor.trim().isEmpty()) {
            libros = libroService.buscarAutor(autor);
        } else {
            libros = libroService.listar(); // Si no hay filtros trae la lista completa
        }

        model.addAttribute("listaLibros", libros);
        return "index"; // Abre el archivo index.html
    }

    //Alta
    //http://localhost:8080/libros/nuevo
    @GetMapping("/nuevo")
    public String mostrarFormularioAlta(Model model) {
        model.addAttribute("libro", new LibrosDigitales()); // Objeto vacío con ID null
        model.addAttribute("listaGeneros", generoService.listar()); // Para el desplegable de géneros
        return "formulario-libro"; // Abre formulario-libro.html
    }

    // 3. RECIBIR DATOS DEL FORMULARIO Y GUARDAR (SIRVE PARA ALTA Y MODIFICACIÓN)
    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute("libro") LibrosDigitales libro, Model model) {
        try {
            libroService.guardar(libro);
        } catch (IllegalArgumentException e) {
            // Si el Service tira error vuelve al formulario mostrando el mensaje
            model.addAttribute("error", e.getMessage());
            model.addAttribute("listaGeneros", generoService.listar());
            return "formulario-libro";
        }
        return "redirect:/libros"; //vuelve a la tabla principal (si guardó bien)
    }

    //EDITAR UN LIBRO EXISTENTE
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        LibrosDigitales libroExistente = libroService.buscarId(id);
        
        if (libroExistente == null) {
            return "redirect:/libros?error=Libro+no+encontrado";
        }
        
        model.addAttribute("libro", libroExistente); // Pasa el libro con sus datos actuales e ID cargado
        model.addAttribute("listaGeneros", generoService.listar());
        return "formulario-libro"; // Reutiliza el mismo HTML de alta
    }

    //ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable("id") Integer id) {
        libroService.eliminar(id);
        return "redirect:/libros"; // Vuelve a recargar la tabla ya sin el libro
    }
}