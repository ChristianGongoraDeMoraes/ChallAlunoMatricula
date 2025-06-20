package app.dto;

import java.time.LocalDate;
import java.util.List;


public record AlunoResponse(
    Long id,
    String nome,
    String telefone,
    LocalDate dataNascimento,
    List<MatriculaDto> matriculas
) {

}
