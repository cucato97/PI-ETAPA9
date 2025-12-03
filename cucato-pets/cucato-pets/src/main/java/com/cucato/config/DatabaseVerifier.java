package com.cucato.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Configuration
public class DatabaseVerifier {

    private static final Logger log = LoggerFactory.getLogger(DatabaseVerifier.class);

    @Bean
    public CommandLineRunner verifyDatabase(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        return args -> {
            try {
                // Testa conexão
                String url = dataSource.getConnection().getMetaData().getURL();
                log.info("🔗 Conectado ao: {}", url);
                
                // Lista todas as tabelas
                List<Map<String, Object>> tables = jdbcTemplate.queryForList("SHOW TABLES");
                log.info("📊 Tabelas no banco:");
                tables.forEach(table -> log.info("   - {}", table.values()));
                
                // Verifica dados na tabela servico
                List<Map<String, Object>> servicos = jdbcTemplate.queryForList("SELECT COUNT(*) as total FROM servico");
                log.info("📈 Total de serviços no banco: {}", servicos.get(0).get("total"));
                
            } catch (Exception e) {
                log.error("❌ ERRO na verificação do banco: {}", e.getMessage());
            }
        };
    }
}
