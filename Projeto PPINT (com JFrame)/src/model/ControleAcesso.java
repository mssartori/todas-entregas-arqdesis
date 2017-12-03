package model;

public class ControleAcesso
{
	private String horarioAcesso="";
	private int id=0;
	private int idFuncionario=0;
	private String nome="";
	private Object result;
	public ControleAcesso()
	{

	}

	public ControleAcesso(String ha,int idFunc)
	{
		setHorarioAcesso(ha);
		setidFuncionario(idFunc);
	}

	public ControleAcesso(int idControle)
	{
		setidControleAcesso(idControle);
	}

	//Temperatura
	public void setHorarioAcesso(String ha)
	{
		horarioAcesso = ha;
	}
	public String getHorarioAcesso()
	{
		return horarioAcesso;   
	}

	//id
	public void setidControleAcesso(int i)
	{
		id = i;
	}
	public int getidControleAcesso()
	{
		return id;   
	}

	//idEmpresa
	public void setidFuncionario(int i)
	{
		idFuncionario = i;
	}
	public int getidFuncionario()
	{
		return idFuncionario;   
	}

	// nome Funcionario
	public void setNomeFuncionario(String nome)
	{
		this.nome = nome;
	} 
	public String getNomeFuncionario()
	{
		return this.nome;   
	}


	public Object Campos(int i){
		result="";
		if(i==0){

			result=getidControleAcesso();
		}
		if(i==1){

			result=getNomeFuncionario();
		}
		if(i==2){

			result=getHorarioAcesso();
		}
		return result;

	}


}