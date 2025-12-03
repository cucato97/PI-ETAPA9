package com.cucato.controller;

import com.cucato.model.Servico;
import com.cucato.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@CrossOrigin(origins = "*") // durante dev permite chamadas do front local; ajuste em produção
public class ServicoController {
    private final ServicoService service;

    public ServicoController(ServicoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Servico> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscar(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Servico> criar(@Valid @RequestBody Servico s) {
        Servico salvo = service.save(s);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @Valid @RequestBody Servico s) {
        return service.findById(id).map(existing -> {
            existing.setPet(s.getPet());
            existing.setTipo(s.getTipo());
            existing.setHorario(s.getHorario());
            existing.setTutor(s.getTutor());
            existing.setObservacao(s.getObservacao());
            service.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.findById(id).map(existing -> {
            service.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }

   @GetMapping("/search")
public List<Servico> buscar(@RequestParam String termo) {
    List<Servico> lista = service.searchByPet(termo);

    if (!lista.isEmpty()) return lista;

    lista = service.searchByTutor(termo);

    if (!lista.isEmpty()) return lista;

    return service.searchByTipo(termo);
}


}
