package br.com.fiap.estoque_service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<EstoqueEntity, String> {}