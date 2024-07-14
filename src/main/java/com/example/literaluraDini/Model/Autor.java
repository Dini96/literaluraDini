package com.example.literaluraDini.Model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String nombre;
    private Integer nacimiento;
    private Integer muerte;

    @OneToMany(mappedBy = "autorClase",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Libro> libros;

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getMuerte() {
        return muerte;
    }

    public void setMuerte(Integer muerte) {
        this.muerte = muerte;
    }

    public Autor (){}

    /*public Autor (DatosLibro datosLibro){
        List<DatosLibro.Resultado> resultados = datosLibro.resultados();
        if (!resultados.isEmpty()) {
            DatosLibro.Resultado resultado = resultados.get(0);
            this.nombre = resultado.authors().get(0).name();
            this.nacimiento = resultado.authors().get(0).birth_year();
            this.muerte = resultado.authors().get(0).death_year();
        }
    }*/

    public Autor(DatosLibro datosLibro) {
        this.nombre = datosLibro.resultados().get(0).authors().get(0).name();
        this.nacimiento = datosLibro.resultados().get(0).authors().get(0).birth_year();
        this.muerte = datosLibro.resultados().get(0).authors().get(0).death_year();
    }
   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(nombre, autor.nombre) &&
                Objects.equals(nacimiento, autor.nacimiento) &&
                Objects.equals(muerte, autor.muerte);
    }*/

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Autor autor = (Autor) o;
            return Objects.equals(nombre, autor.nombre) &&
                    Objects.equals(nacimiento, autor.nacimiento) &&
                    Objects.equals(muerte, autor.muerte);
        }

    @Override
    public String toString() {

            StringBuilder librosNombres = new StringBuilder();

            for (Libro libro : libros){
                if (librosNombres.length() > 0){
                    librosNombres.append(", ");
                }
                librosNombres.append(libro.getTitulo());
            }
        return  "************AUTOR************\n" +
                "Nombre: " + nombre + "\n" +
                "Año de nacimiento: " + nacimiento + "\n" +
                "Año de fallecimiento: " + muerte + "\n" +
                "Libros: " + librosNombres.toString() + "\n" +
                "*****************************\n";
    }
}
