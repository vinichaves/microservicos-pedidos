package br.com.fiap.pagamento_service.infrastructure.persistence;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, UUID> {}