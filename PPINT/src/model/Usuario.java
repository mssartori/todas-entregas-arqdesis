package model;

public class Usuario
{
	private String Usuario;
	private String Senha;
	private String Tipo;

	//Método Construtor
	public Usuario(String nome, String senha, String tipo)
	{
		setUsuario(nome);
		setSenha(senha);
		setTipo(tipo);
	} 

	//Método Modificador
	public void setUsuario(String nome)
	{
		Usuario = nome;
	} 

	public void setSenha(String senha)
	{
		Senha = senha;
	}

	public void setTipo(String tipo)
	{
		Tipo = tipo;
	}

	//Método De Acesso
	public String getUsuario()
	{
		return Usuario;
	}

	public String getSenha()
	{
		return Senha;
	}

	public String getTipo()
	{
		return Tipo;
	}
} 