package com.example.literaluraDini.Model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    private String titulo;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autorClase;



    public Autor getAutorClase() {
        return autorClase;
    }

    public void setAutorClase(Autor autorClase) {
        this.autorClase = autorClase;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    private String idioma;
    private Integer descargas;

    public Libro(){}

   /* public Libro(DatosLibro datosLibro) {
        List<DatosLibro.Resultado> resultados = datosLibro.resultados();
        if (!resultados.isEmpty()) {
            DatosLibro.Resultado resultado = resultados.get(0);
            this.titulo = resultado.title();
            this.nombreAutor = resultado.authors().get(0).name(); // Asumiendo que hay al menos un autor
            this.idioma = resultado.languages().get(0); // Asumiendo que hay al menos un idioma
            this.descargas = resultado.download_count();

        }
    }*/

    public Libro(DatosLibro datosLibro, Autor autor) {
        this.autorClase = autor;
        this.titulo = datosLibro.resultados().get(0).title();
        this.idioma = datosLibro.resultados().get(0).languages().get(0);
        this.descargas = datosLibro.resultados().get(0).download_count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(titulo, libro.titulo) &&
                Objects.equals(autorClase, libro.autorClase) &&
                Objects.equals(idioma, libro.idioma) &&
                Objects.equals(descargas, libro.descargas);
    }

    @Override
public String toString() {
    return  "************LIBRO************\n" +
            "Titulo: " + titulo + "\n" +
            "Author: " + autorClase.getNombre() + "\n" +
            "Idioma: " + idioma + "\n" +
            "Descargas: " + descargas + "\n" +
            "*****************************\n";
    }
}
