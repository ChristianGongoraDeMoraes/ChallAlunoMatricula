package app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.AlunoRequest;
import app.dto.AlunoResponse;
import app.dto.MatriculaDto;
import app.entity.Aluno;
import app.entity.Matricula;
import app.mapper.AlunoMapper;
import app.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AlunoServices {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private AlunoMapper alunoMapper;

    public AlunoResponse salvar(AlunoRequest request){
        Aluno aluno = alunoMapper.toEntity(request);
        alunoRepository.save(aluno);
        return alunoMapper.toResponse(aluno);
    }

    public List<AlunoResponse> listarTodos(){
        return alunoRepository.findAll().stream().map(alunoMapper::toResponse).toList();
    }

    public List<MatriculaDto> listarMatriculas(Long id){
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno nao encontrado;"));
        return aluno.getMatriculas().stream().map(m -> {
            return new MatriculaDto(
                m.getCodigoMatricula(),
                m.getNomeCurso(),
                m.getDataMatricula());
        }).toList();
    }

    public void remover(Long id){
        if(!alunoRepository.existsById(id)){
            throw new EntityNotFoundException("Aluno nao encontrado");
        }
        alunoRepository.deleteById(id);
    }

    public AlunoResponse atualizar(Long id, AlunoRequest request){
        Aluno a = alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno nao encontrado;"));
        a.setNome(request.nome());
        a.setTelefone(request.telefone());
        a.setDataNascimento(request.dataNascimento());

        for(MatriculaDto m : request.matriculas()){
            Matricula matricula = new Matricula();
            matricula.setCodigoMatricula(m.codigoMatricula());
            matricula.setDataMatricula(m.dataInicio());
            matricula.setNomeCurso(m.nomeCurso());
            
            a.getMatriculas().add(matricula);
        }

        return alunoMapper.toResponse(alunoRepository.save(a));
    }
}
