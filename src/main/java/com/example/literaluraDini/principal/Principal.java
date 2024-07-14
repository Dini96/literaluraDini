package com.example.literaluraDini.principal;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.example.literaluraDini.Model.Autor;
import com.example.literaluraDini.Model.DatosLibro;
import com.example.literaluraDini.Model.Libro;
import com.example.literaluraDini.repository.AutorRepository;
import com.example.literaluraDini.repository.LibroRepository;
import com.example.literaluraDini.service.ConsumeAPI;
import com.example.literaluraDini.service.ConvierteDatos;

import java.util.*;

public class Principal {
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private Scanner teclado = new Scanner(System.in);

    private ConvierteDatos conversor = new ConvierteDatos();
    private List<Libro> listaLibros = new ArrayList<>();
    private List<Autor> listaAutores = new ArrayList<>();

    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;


    public Principal(AutorRepository autorRepository, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository  = libroRepository;
    }


    public void mostrarElMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija la opción escribiendo el numero, por favor
                                    
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                                    
                    0 - Salir
                    """;
            System.out.println(menu);

            try{
                opcion = teclado.nextInt();
                teclado.nextLine();

            switch (opcion){
                case 1:
                    getDatosTitulo();

                    break;
                case 2:
                    listaBuscados();
                    break;
                case 3:
                    listaAutores();
                    break;
                case 4:
                    listaAutoresVivos();
                    break;
                case 5:
                    listaPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("No es una opcion valida, vuelve a intentar...");
            }
        }catch (InputMismatchException e){
                System.out.println("Error: Solo se aceptan números enteros. Intente de nuevo.");
                teclado.next(); // Limpiar el buffer de entrada
            }
        }
    }




    private DatosLibro getDatosTitulo() {
        System.out.println("Escribe el nombre del titulo");
        var nombreLibro = teclado.nextLine();

        try {
            var json = ConsumeAPI.obtenerDatos(URL_BASE + "/?search=" + nombreLibro.replace(" ", "%20"));
            DatosLibro datosLibro = conversor.obtenerDatos(json, DatosLibro.class);

            if (datosLibro.resultados() == null || datosLibro.resultados().isEmpty()) {
                System.out.println("No se encontraron resultados para el libro.");
                return null;
            }

            // Buscar el autor existente
            /*
            Autor autor;
            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
                System.out.println("El autor ya ha sido registrado previamente");
            } else {
                autor = new Autor(datosLibro);
                System.out.println("Nuevo autor registrado");
                autorRepository.saveAndFlush(autor);
            }

            // Verificar si el libro ya existe en la base de datos
            Optional<Libro> libroExistente = libroRepository.findByTitulo(datosLibro.getTitulo());
            if (libroExistente.isPresent()) {
                System.out.println("El libro ya está registrado.");
                return null;
            }

            // Asociar el autor al libro*/
            // Obtener el primer resultado y el primer autor
            DatosLibro.Resultado resultado = datosLibro.resultados().get(0);
            DatosLibro.Resultado.Autor autorData = resultado.authors().get(0);
            String nombreAutor = autorData.name();

            // Buscar el autor existente
            Optional<Autor> autorExistente = autorRepository.findByNombre(nombreAutor);

            Autor autor;
            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
                System.out.println("El autor ya ha sido registrado previamente");
            } else {
                autor = new Autor(datosLibro);
                System.out.println("Nuevo autor registrado");
                autorRepository.save(autor);
            }

            Libro libro = new Libro(datosLibro, autor);
            System.out.println(libro);
            libroRepository.save(libro);

            return datosLibro;
        } catch (Exception e) {
            System.out.println("Error al obtener datos del libro: " + e.getMessage());
            return null;
        }
    }



    private void listaBuscados(){
        /*for (Libro libro : listaLibros){
            System.out.println(libro);
        }*/
        List<Libro> libros = libroRepository.findAll();
        libros.forEach(l-> System.out.println(l.toString()));
    }

    private void listaAutores(){
/*        for (Autor autor : listaAutores){
            System.out.println(autor);
        }*/
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(a-> System.out.println(a.toString()));
    }

    private void listaAutoresVivos() {
        System.out.println("Que año quieres revisar");
        Integer anio = teclado.nextInt();
        List<Autor> autoresVivos = autorRepository.findAutoresVivosEnElAnio(anio);
        autoresVivos.forEach(a-> System.out.println(a.toString()));
    }

    private void listaPorIdioma(){

        System.out.println("""
                Ingrese el idioma a filtrar
                en - ingles
                es - español
                pt - portugues
                """);
        String seleccion = teclado.nextLine();

        List<Libro> listaLibros = libroRepository.findLibroPorIdioma(seleccion);

        listaLibros.forEach(l-> System.out.println(l.toString()));

    }
}
