package br.com.fadergs.mercado.produto.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

// import br.com.fadergs.mercado.produto..persistence.model.produto;
import br.com.fadergs.mercado.produto.persistence.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>, JpaSpecificationExecutor<Produto>{
    // Iterable<Produto> findNome(String nome);
    // Iterable<Produto> findTipo(String tipo);
}