package br.com.fadergs.mercado.produto.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "codigo", length = 8)
	private Integer id;

	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Column(name = "tipo", nullable = false, length = 200)
	private String tipo;

	@Column(name = "valor", nullable = false)
	private Double valor;

	@Column(name = "estoque_entrada", nullable = false)
	private Integer estoque_entrada;
	
	@Column(name = "vendas", nullable = false)
	private Integer vendas;

	@Column(name = "estoque_loja", nullable = false)
	private Integer estoque_loja;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}

	/**
	 * @return the estoque
	 */
	public Integer getEstoque_entrada() {
		return estoque_entrada;
	}

	/**
	 * @param estoque_entrada the estoque_entrada to set
	 */
	public void setEstoque_entrada(Integer estoque_entrada) {
		this.estoque_entrada = estoque_entrada;
	}

	/**
	 * @return the estoque_loja
	 */
	public Integer getEstoque_loja() {
		return estoque_loja;
	}


	/**
	 * @param estoque_loja the estoque_loja to set
	 */
	public void setEstoque_loja(Integer estoque_loja) {
		this.estoque_loja = estoque_loja;
	}
	

	/**
	 * @return the vendas
	 */
	public Integer getVendas() {
		return vendas;
	}


	/**
	 * @param vendas the vendas to set
	 */
	public void setVendas(Integer vendas) {
		this.vendas = vendas;
	}

}