package model;

public class Extrato {
	private String nome;
	private double transacoes;
	private String data;
	private int id;
	private String fone;
	private String email;
	
	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Extrato(String nome, double transacoes, String data, int id) {
		this.nome = nome;
		this.transacoes = transacoes;
		this.data = data;
		this.id = id;
	}
	
	public Extrato() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(double transacoes) {
		this.transacoes = transacoes;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Extrato [nome=" + nome + ", transacoes=" + transacoes + ", data=" + data + "]";
	}
	
	public String getExtratos() {
		return toString();
	}
	
	public String getExtratos(String chave, String periodo) {
		Extrato extrato = new Extrato();
		if(extrato.getData().equals(periodo)) {
			return extrato.toString();
		}
		return "";
	}
}
