package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Long>{

}
