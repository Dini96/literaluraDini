package com.example.literaluraDini.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/*@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
@JsonAlias("tittle") String titulo,
@JsonAlias("name") String autor,
@JsonAlias("download_count") Integer descargas,
@JsonAlias("languages") String idioma
) {}*/

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("results") List<Resultado> resultados
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Resultado(

            String title,
            List<Autor> authors,
            List<String> languages,
            int download_count
    ) {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Autor(
                String name,
                int birth_year,
                int death_year
        ) {}
    }
}

