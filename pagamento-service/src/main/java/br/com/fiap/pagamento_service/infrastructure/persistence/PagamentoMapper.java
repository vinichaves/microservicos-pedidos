package br.com.fiap.pagamento_service.infrastructure.persistence;

import br.com.fiap.pagamento_service.domain.entities.Pagamento;

public class PagamentoMapper {

    public static PagamentoEntity toEntity(Pagamento pagamento) {
        PagamentoEntity entity = new PagamentoEntity();
        entity.setId(pagamento.getId());
        entity.setNumeroCartao(pagamento.getNumeroCartao());
        entity.setValor(pagamento.getValor());
        entity.setStatus(pagamento.getStatus());
        return entity;
    }

    public static Pagamento toDomain(PagamentoEntity entity) {
        return new Pagamento(entity.getId(), entity.getNumeroCartao(), entity.getValor(), entity.getStatus());
    }
}