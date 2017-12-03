package model;

public class Empresa
{
	private int idEmpresa=0;
	private String nome="";
	private String Conjunto="";
	private String Cnpj="";
	private String RazaoSocial="";
	private String HorarioFuncionamento="";
	private String autorizacao="";
	private String HorarioArCondicionado="";
	private Object result;
	private String temperatura="";

	public Empresa()
	{

	}

	public Empresa(int idEmpresa)
	{
		setidEmpresa(idEmpresa);
	}

	public Empresa(int idEmpresa, String temp)
	{
		setidEmpresa(idEmpresa);
		setTemperatura(temp);
	}

	public Empresa(String n, String cj, String cnj, String Rs, String hf, String au,String temp, String ha)
	{
		setNome(n);
		setConjunto(cj);
		setCnpj(cnj);
		setRazaoSocial(Rs);
		setHorarioFuncionamento(hf);
		setAutorizacao(au);
		setTemperatura(temp);
		setHorarioArCondicionado(ha);
	}

	public Empresa(int id,String n, String cj, String cnj, String Rs, String hf, String au,String temp, String ha)
	{
		setidEmpresa(id);
		setNome(n);
		setConjunto(cj);
		setCnpj(cnj);
		setRazaoSocial(Rs);
		setHorarioFuncionamento(hf);
		setAutorizacao(au);
		setTemperatura(temp);
		setHorarioArCondicionado(ha);
	}

	//id
	public void setidEmpresa(int id)
	{
		idEmpresa= id;
	}
	public int getidEmpresa()
	{
		return idEmpresa;
	}


	//nome
	public void setNome(String n)
	{
		nome = n;
	}
	public String getNome()
	{
		return nome;   
	}

	//Conjunto
	public void setConjunto(String c)
	{
		Conjunto = c;
	}
	public String getConjunto()
	{
		return Conjunto;   
	}

	//Cnpj
	public void setCnpj(String cnj)
	{
		Cnpj = cnj;
	}
	public String getCnpj()
	{
		return Cnpj;   
	}

	//Razao Social
	public void setRazaoSocial(String rs)
	{
		RazaoSocial = rs;
	}
	public String getRazaoSocial()
	{
		return RazaoSocial;   
	}

	//Horario Funcionamento
	public void setHorarioFuncionamento(String hf)
	{
		HorarioFuncionamento = hf;
	}
	public String getHorarioFuncionamento()
	{
		return HorarioFuncionamento;   
	}

	//Autorizacao
	public void setAutorizacao(String au)
	{
		autorizacao = au;
	}
	public String getAutorizacao()
	{
		return autorizacao;   
	}

	//Temperatura
	public void setTemperatura(String temp)
	{
		temperatura = temp;
	}
	public String getTemperatura()
	{
		return temperatura;   
	}

	//Horario Ar condicionado
	public void setHorarioArCondicionado(String hac)
	{
		HorarioArCondicionado = hac;
	}
	public String getHorarioArCondicionado()
	{
		return HorarioArCondicionado;   
	}


	// Mostrar Tudo Empresa
	public String dados()
	{
		String msg = "         " + getidEmpresa()+ "          "+"             "+getNome()+"                   "+getConjunto()+"                        "+getAutorizacao()+"                        "+getHorarioArCondicionado()+"\n";
		return msg;
	}

	public Object Campos(int i){
		result="";
		if(i==0){

			result=getidEmpresa();
		}
		if(i==1){

			result=getNome();
		}
		if(i==2){

			result=getConjunto();
		}
		if(i==3){

			result=getTemperatura();
		}
		if(i==4){

			result=getAutorizacao();;
		}
		if(i==5){

			result=getHorarioArCondicionado();;
		}
		return result;

	}


}
