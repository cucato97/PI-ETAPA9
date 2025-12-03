package com.cucato.controller;

import com.cucato.model.Servico;
import com.cucato.repository.ServicoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional  
@Rollback       
public class ServicoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ServicoRepository repo;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void criarListarExcluirServico() throws Exception {
        

        Servico s = new Servico();
        s.setPet("Rex_Teste");  
        s.setTipo("Banho");
        s.setHorario("09:00");
        s.setTutor("João");
        s.setObservacao("Sem alergias");

        String json = mapper.writeValueAsString(s);

        // 1. CRIAR - Verifica se o serviço foi criado
        mockMvc.perform(post("/api/servicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.pet").value("Rex_Teste"));

        // 2. LISTAR - Busca específica pelo pet do teste
        mockMvc.perform(get("/api/servicos/search?termo=Rex_Teste"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].pet").value("Rex_Teste"));

        // 3. DELETAR - Busca o ID correto do registro de teste
        Servico servicoCriado = repo.findByPetContainingIgnoreCase("Rex_Teste").get(0);
        Long id = servicoCriado.getId();
        
        mockMvc.perform(delete("/api/servicos/" + id))
                .andExpect(status().isNoContent());

        // 4. VERIFICAR que foi deletado
        mockMvc.perform(get("/api/servicos/search?termo=Rex_Teste"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}
