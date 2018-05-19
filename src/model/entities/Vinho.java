package model.entities;

public class Vinho {
	
	private int id;
	private String nome;
	private String pais;
	private String regiao;
	private String nomeProdutor;
	private String anoSafra;
	private String descricao;
	private String preco;
	private String tipoVinho;
	
	
	public String getTipoVinho() {
		return tipoVinho;
	}
	public void setTipoVinho(String tipoVinho) {
		this.tipoVinho = tipoVinho;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	public String getNomeProdutor() {
		return nomeProdutor;
	}
	public void setNomeProdutor(String nomeProdutor) {
		this.nomeProdutor = nomeProdutor;
	}
	public String getAnoSafra() {
		return anoSafra;
	}
	public void setAnoSafra(String anoSafra) {
		this.anoSafra = anoSafra;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	
	public Vinho() {}
	
	public Vinho(String nome, String pais, String regiao, String nomeProdutor, String anoSafra, String descricao, String preco, String tipoVinho) {
		this.nome = nome;
		this.pais = pais;
		this.regiao = regiao;
		this.nomeProdutor = nomeProdutor;
		this.anoSafra = anoSafra;
		this.descricao = descricao;
		this.tipoVinho = tipoVinho;
		this.preco = preco;
	}
	
	public Vinho(int id, String nome, String pais, String regiao, String nomeProdutor, String anoSafra, String descricao, String preco, String tipoVinho) {
		this.nome = nome;
		this.pais = pais;
		this.regiao = regiao;
		this.nomeProdutor = nomeProdutor;
		this.anoSafra = anoSafra;
		this.descricao = descricao;
		this.tipoVinho = tipoVinho;
		this.preco = preco;
	}
	


}
