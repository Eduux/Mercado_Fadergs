package br.com.fadergs.mercado.produto.persistence.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fadergs.mercado.produto.persistence.model.Produto;

public class ProdutoSpecBuilder {
     
    private final List<SearchCriteria> params;
 
    public ProdutoSpecBuilder() {
        params = new ArrayList<SearchCriteria>();
    }
 
    public ProdutoSpecBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }
 
    public Specification<Produto> build() {
        if (params.size() == 0) {
            return null;
        }
 
        List<Specification<Produto>> specs = new ArrayList<Specification<Produto>>();
        for (SearchCriteria param : params) {
            specs.add(new ProdutoSpecification(param));
        } 
 
        Specification<Produto> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}