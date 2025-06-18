package app.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import app.dto.AlunoRequest;
import app.dto.AlunoResponse;
import app.dto.MatriculaDto;
import app.entity.Aluno;
import app.entity.Matricula;


@Component
public class AlunoMapper {

    public Aluno toEntity(AlunoRequest request){
        Aluno aluno = new Aluno();
        aluno.setNome(request.nome());
        aluno.setDataNascimento(request.dataNascimento());
        aluno.setTelefone(request.telefone());
        List<Matricula> matriculas = request.matriculas().stream().map(m ->{
            Matricula matricula = new Matricula();
            matricula.setCodigoMatricula(m.codigoMatricula());
            matricula.setDataMatricula(m.dataInicio());
            matricula.setNomeCurso(m.nomeCurso());
            return matricula;
        }).toList();
        aluno.setMatriculas(matriculas);

        return aluno;
    } 

    public AlunoResponse toResponse(Aluno aluno){
        List<MatriculaDto> matriculaDtos = aluno.getMatriculas().stream().map(m -> {
            return new MatriculaDto(m.getCodigoMatricula(), m.getNomeCurso(), m.getDataMatricula());
        }).toList();
        return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getTelefone(), aluno.getDataNascimento(), matriculaDtos);
    }
}
