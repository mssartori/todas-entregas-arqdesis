<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Visualizar Empresa</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">Visualizar Empresa #${empresa.id}</h3>
		<div class="row">
			<div class="col-md-12">
				<p>
					<strong>Nome</strong>
				</p>
				<p>${empresa.nome}</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<p>
					<strong>Conjunto</strong>
				</p>
				<p>${empresa.conjunto}</p>
			</div>
			<div class="col-md-6">
				<p>
					<strong>CNPJ</strong>
				</p>
				<p>${empresa.cnpj}</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<p>
					<strong>Razão Social</strong>
				</p>
				<p>${empresa.razaoSocial}</p>
			</div>
			<div class="col-md-6">
				<p>
					<strong>Horário de Funcionamento</strong>
				</p>
				<p>${empresa.horarioFuncionamento}</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<p>
					<strong>Autorização</strong>
				</p>
				<p>${empresa.autorizacao}</p>
			</div>
			<div class="col-md-6">
				<p>
					<strong>Temperatura</strong>
				</p>
				<p>${empresa.temperatura}</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<p>
					<strong>Horário Ar Condicionado</strong>
				</p>
				<p>${empresa.horarioArCondicionado}</p>
			</div>
			<div class="col-md-6">
				<p>
					<strong>Cancelado</strong>
				</p>
				<p>${empresa.cancelado}</p>
			</div>
		</div>
		<hr />
		<div id="actions" class="row">
			<div class="col-md-12">
				<a href="index.jsp" class="btn btn-default">Voltar</a>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>