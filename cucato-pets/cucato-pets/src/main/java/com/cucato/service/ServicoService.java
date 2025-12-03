package com.cucato.service;

import com.cucato.model.Servico;
import com.cucato.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    private final ServicoRepository repo;

    public ServicoService(ServicoRepository repo) {
        this.repo = repo;
    }

    public List<Servico> findAll() {
        return repo.findAll();
    }

    public Optional<Servico> findById(Long id) {
        return repo.findById(id);
    }

    public Servico save(Servico s) {
        return repo.save(s);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public List<Servico> searchByPet(String pet) {
        return repo.findByPetContainingIgnoreCase(pet);
    }

    public List<Servico> searchByTutor(String tutor) {
        return repo.findByTutorContainingIgnoreCase(tutor);
    }

    public List<Servico> searchByTipo(String tipo) {
        return repo.findByTipoContainingIgnoreCase(tipo);
    }
}



