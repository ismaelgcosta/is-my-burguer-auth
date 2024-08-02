package br.com.ismyburguer.cliente.adapters.repository;

import br.com.ismyburguer.cliente.adapters.model.SolicitacaoExclusaoModel;
import br.com.ismyburguer.core.adapter.out.PersistenceDriver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

@PersistenceDriver
public interface SolicitacaoExclusaoRepository extends MongoRepository<SolicitacaoExclusaoModel, UUID> {

    List<SolicitacaoExclusaoModel> findAllByCpf(String cpf);

}
