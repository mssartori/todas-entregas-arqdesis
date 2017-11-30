package model;

import java.io.Serializable;

public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome, conjunto, cnpj, razaoSocial, horarioFuncionamento, autorizacao,
	temperatura, horarioArCondicionado, cancelado;

	public Empresa() {
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


	public String getConjunto() {
		return conjunto;
	}


	public void setConjunto(String conjunto) {
		this.conjunto = conjunto;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getRazaoSocial() {
		return razaoSocial;
	}


	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}


	public String getHorarioFuncionamento() {
		return horarioFuncionamento;
	}


	public void setHorarioFuncionamento(String horarioFuncionamento) {
		this.horarioFuncionamento = horarioFuncionamento;
	}


	public String getAutorizacao() {
		return autorizacao;
	}


	public void setAutorizacao(String autorizacao) {
		this.autorizacao = autorizacao;
	}


	public String getTemperatura() {
		return temperatura;
	}


	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}


	public String getHorarioArCondicionado() {
		return horarioArCondicionado;
	}


	public void setHorarioArCondicionado(String horarioArCondicionado) {
		this.horarioArCondicionado = horarioArCondicionado;
	}


	public String getCancelado() {
		return cancelado;
	}


	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nome=" + nome + ", conjunto=" + conjunto + ", cnpj=" + cnpj + ", razaoSocial="
				+ razaoSocial + ", horarioFuncionamento=" + horarioFuncionamento + ", autorizacao=" + autorizacao
				+ ", temperatura=" + temperatura + ", horarioArCondicionado=" + horarioArCondicionado + ", cancelado="
				+ cancelado + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autorizacao == null) ? 0 : autorizacao.hashCode());
		result = prime * result + ((cancelado == null) ? 0 : cancelado.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((conjunto == null) ? 0 : conjunto.hashCode());
		result = prime * result + ((horarioArCondicionado == null) ? 0 : horarioArCondicionado.hashCode());
		result = prime * result + ((horarioFuncionamento == null) ? 0 : horarioFuncionamento.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
		result = prime * result + ((temperatura == null) ? 0 : temperatura.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (autorizacao == null) {
			if (other.autorizacao != null)
				return false;
		} else if (!autorizacao.equals(other.autorizacao))
			return false;
		if (cancelado == null) {
			if (other.cancelado != null)
				return false;
		} else if (!cancelado.equals(other.cancelado))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (conjunto == null) {
			if (other.conjunto != null)
				return false;
		} else if (!conjunto.equals(other.conjunto))
			return false;
		if (horarioArCondicionado == null) {
			if (other.horarioArCondicionado != null)
				return false;
		} else if (!horarioArCondicionado.equals(other.horarioArCondicionado))
			return false;
		if (horarioFuncionamento == null) {
			if (other.horarioFuncionamento != null)
				return false;
		} else if (!horarioFuncionamento.equals(other.horarioFuncionamento))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
		if (temperatura == null) {
			if (other.temperatura != null)
				return false;
		} else if (!temperatura.equals(other.temperatura))
			return false;
		return true;
	}

}
