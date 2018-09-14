package br.com.fadergs.mercado.produto.controller;

import java.net.URI;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fadergs.mercado.produto.persistence.model.Produto;
import br.com.fadergs.mercado.produto.persistence.repo.ProdutoRepository;
import br.com.fadergs.mercado.produto.persistence.specification.ProdutoSpecBuilder;

@CrossOrigin
@RestController
@RequestMapping("/api/mercado/produto/v1")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public ResponseEntity<Object> findAllProdutos(@RequestParam(value = "search") String search) {

        ProdutoSpecBuilder builder = new ProdutoSpecBuilder();
        Pattern pattern = Pattern.compile("(\\w.+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Produto> spec = builder.build();

        Iterable<Produto> produtos = produtoRepository.findAll(spec);
        if (produtos == null || produtos.spliterator().getExactSizeIfKnown() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @PostMapping("/produto")
    public ResponseEntity<Object> create(@RequestBody Produto produto) {

        produtoRepository.save(produto);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().path("/")
                .path(produto.getId().toString()).toUriString());

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable Integer id) {

        if (!produtoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        produtoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/produto/{id}") 
    public ResponseEntity<Object> alteraValor(@RequestBody Produto produto, @PathVariable Integer id) {
      
      if (!produtoRepository.existsById(id)) { 
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      produto.setId(id);

      produtoRepository.save(produto);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }

    /*
     * @GetMapping("/organizacoes") public ResponseEntity<Object>
     * findAllOrganizacoes(@RequestParam(value = "search") String search, Pageable
     * pageable) {
     * 
     * OrganizacaoSpecBuilder builder = new OrganizacaoSpecBuilder(); Pattern
     * pattern = Pattern.compile("(\\w.+?)(:|<|>)(\\w+?),",
     * Pattern.UNICODE_CHARACTER_CLASS); Matcher matcher = pattern.matcher(search +
     * ","); while (matcher.find()) { builder.with(matcher.group(1),
     * matcher.group(2), matcher.group(3)); }
     * 
     * Specification<Organizacao> spec = builder.build();
     * 
     * Page<Organizacao> organizacoes = organizacaoRepository.findAll(spec,
     * pageable); if (organizacoes == null ||
     * organizacoes.spliterator().getExactSizeIfKnown() == 0) { return new
     * ResponseEntity<>(HttpStatus.NO_CONTENT); } return new
     * ResponseEntity<>(organizacoes, HttpStatus.OK); }
     * 
     * 
     * @GetMapping("/tipoOrganizacao/{id}") public ResponseEntity<Object>
     * findOneTipoOrganizacao(@PathVariable Integer id) {
     * 
     * if (!tipoOrganizacaoRepository.existsById(id)) { return new
     * ResponseEntity<>(HttpStatus.NOT_FOUND); } return new
     * ResponseEntity<>(tipoOrganizacaoRepository.findById(id), HttpStatus.OK); }
     * 
     * @GetMapping("/organizacao/{id}") public ResponseEntity<Object>
     * findOneOrganizacao(@PathVariable Integer id) {
     * 
     * if (!organizacaoRepository.existsById(id)) { return new
     * ResponseEntity<>(HttpStatus.NOT_FOUND); } return new
     * ResponseEntity<>(organizacaoRepository.findById(id), HttpStatus.OK); }
     * 
     * 
     * 
     * @PostMapping("/organizacao") public ResponseEntity<Object>
     * create(@RequestBody Organizacao organizacao) {
     * 
     * organizacao.setDataCriacao(new Date()); organizacao.setDataAlteracao(new
     * Date());
     * 
     * organizacaoRepository.save(organizacao);
     * 
     * URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri()
     * .path("/") .path(organizacao.getId().toString()) .toUriString());
     * 
     * return ResponseEntity.created(uri).build(); }
     * 
     *
     * 
     * 
     * @DeleteMapping("/organizacao/{id}") public ResponseEntity<Object>
     * deleteOrganizacao(@PathVariable Integer id) {
     * 
     * if (!organizacaoRepository.existsById(id)) { return new
     * ResponseEntity<>(HttpStatus.NOT_FOUND); }
     * organizacaoRepository.deleteById(id); return new
     * ResponseEntity<>(HttpStatus.NO_CONTENT); }
     */

    /*
     * @PutMapping("/tipoOrganizacao/{id}") public ResponseEntity<Object>
     * update(@RequestBody TipoOrganizacao tipoOrganizacao, @PathVariable Integer
     * id) {
     * 
     * if (!tipoOrganizacaoRepository.existsById(id)) { return new
     * ResponseEntity<>(HttpStatus.NOT_FOUND); }
     * tipoOrganizacaoRepository.save(merge(tipoOrganizacao, id)); return new
     * ResponseEntity<>(HttpStatus.NO_CONTENT); }
     */

    /*
     * private TipoOrganizacao merge (TipoOrganizacao tipoOrganizacao, Integer id) {
     * 
     * TipoOrganizacao tipoOrganizacaoToUpdate =
     * tipoOrganizacaoRepository.getOne(id);
     * 
     * tipoOrganizacaoToUpdate.setDataAlteracao(new Date());
     * tipoOrganizacaoToUpdate.setDataCriacao(tipoOrganizacaoToUpdate.getDataCriacao
     * () != null ? tipoOrganizacaoToUpdate.getDataCriacao() : new Date());
     * tipoOrganizacaoToUpdate.setNome(tipoOrganizacao.getNome() != null ?
     * tipoOrganizacao.getNome() : tipoOrganizacaoToUpdate.getNome());
     * tipoOrganizacaoToUpdate.setId(tipoOrganizacaoToUpdate.getId() != null ?
     * tipoOrganizacaoToUpdate.getId() : null);
     * tipoOrganizacaoToUpdate.setUsuario(tipoOrganizacao.getUsuario() != null ?
     * tipoOrganizacao.getUsuario() : tipoOrganizacaoToUpdate.getUsuario());
     * 
     * return tipoOrganizacaoToUpdate; }
     */
}