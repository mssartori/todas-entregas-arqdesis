<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Empresa"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Empresa</title>
</head>
<body>
	<% Empresa empresa = (Empresa)request.getAttribute("empresa");%>
	Id:	<%=empresa.getId()%><br>
	Nome: <%=empresa.getNome()%><br>
	Conjunto: <%=empresa.getConjunto()%><br>
	CNPJ: <%=empresa.getCnpj()%><br>
	Razão Social: <%=empresa.getRazaoSocial()%><br>
	Horário de Funcionamento: <%=empresa.getHorarioFuncionamento()%><br>
	Autorização: <%=empresa.getAutorizacao()%><br>
	Temperatura: <%=empresa.getTemperatura()%><br>
	Horário Ar Condicionado: <%=empresa.getHorarioArCondicionado()%><br>
	Cancelado: <%=empresa.getCancelado()%>
</body>
</html>