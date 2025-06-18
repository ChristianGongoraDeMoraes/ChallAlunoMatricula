package app.dto;

import java.time.LocalDate;

public record MatriculaDto(
    String codigoMatricula,
    String nomeCurso,
    LocalDate dataInicio
) {

}
