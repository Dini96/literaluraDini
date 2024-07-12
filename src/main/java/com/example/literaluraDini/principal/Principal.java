package com.example.literaluraDini.principal;

import com.example.literaluraDini.service.ConsumeAPI;

import java.util.Scanner;

public class Principal {
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private Scanner teclado = new Scanner(System.in);


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
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    getDatosTitulo();

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("No es una opcion valida, vuelve a intentar...");
            }
        }
    }
    private String getDatosTitulo(){
        System.out.println("Escribe el nombre del titulo");
        var nombreLibro = teclado.nextLine();
        var json = ConsumeAPI.obtenerDatos(URL_BASE + "/?search=" + nombreLibro.replace(" ", "%20"));
        System.out.println(json);
        return null;
    }
}
