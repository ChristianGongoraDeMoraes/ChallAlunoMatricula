package app.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dto.AlunoRequest;
import app.dto.AlunoResponse;
import app.dto.MatriculaDto;
import app.services.AlunoServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServices alunoServices;

    @PostMapping
    public ResponseEntity<AlunoResponse> criar(@RequestBody AlunoRequest request) {
        return ResponseEntity.ok().body(alunoServices.salvar(request));
    }
    
    @GetMapping()
    public ResponseEntity<List<AlunoResponse>> listarTodos() {
        return ResponseEntity.ok().body(alunoServices.listarTodos());
    }

    @GetMapping("/{id}/matriculas")
    public ResponseEntity<List<MatriculaDto>> listarMatriculas(@PathVariable Long id) {
        return ResponseEntity.ok().body(alunoServices.listarMatriculas(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> atualizar(@PathVariable Long id, @RequestBody AlunoRequest request) {
        return ResponseEntity.ok().body(alunoServices.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        alunoServices.remover(id);
        return ResponseEntity.ok().body(null);
    }
}
