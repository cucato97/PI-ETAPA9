package com.cucato.repository;

import com.cucato.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    List<Servico> findByPetContainingIgnoreCase(String pet);

    List<Servico> findByTutorContainingIgnoreCase(String tutor);

    List<Servico> findByTipoContainingIgnoreCase(String tipo);
}



