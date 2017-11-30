<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="model.Empresa" %>
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
    <%Empresa empresa = (Empresa)request.getAttribute("empresa"); %>
        <!-- Barra superior com os menus de navegação -->

        <!-- Container Principal -->
        <div id="main" class="container">
            <h3 class="page-header">Visualizar Empresa #<%=empresa.getId() %></h3>
            <div class="row">
                <div class="col-md-12">
                    <p><strong>Nome</strong>
                    </p>
                    <p>
                        <%=empresa.getNome() %>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <p><strong>Conjunto</strong>
                    </p>
                    <p>
                        <%=empresa.getConjunto() %>
                    </p>
                </div>
                <div class="col-md-6">
                    <p><strong>CNPJ</strong>
                    </p>
                    <p>
                        <%=empresa.getCnpj() %>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <p><strong>Razão Social</strong>
                    </p>
                    <p>
                        <%=empresa.getRazaoSocial() %>
                    </p>
                </div>
                <div class="col-md-6">
                    <p><strong>Horário de Funcionamento</strong>
                    </p>
                    <p>
                        <%=empresa.getHorarioFuncionamento() %>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <p><strong>Autorização</strong>
                    </p>
                    <p>
                        <%=empresa.getAutorizacao() %>
                    </p>
                </div>
                <div class="col-md-6">
                    <p><strong>Temperatura</strong>
                    </p>
                    <p>
                        <%=empresa.getTemperatura() %>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <p><strong>Horário ar condicionado</strong>
                    </p>
                    <p>
                        <%=empresa.getHorarioArCondicionado() %>
                    </p>
                </div>
                <div class="col-md-6">
                    <p><strong>Cancelado</strong>
                    </p>
                    <p>
                        <%=empresa.getCancelado() %>
                    </p>
                </div>
            </div>
            <hr />
            <div id="actions" class="row">
                <div class="col-md-12">
                    <a href="index.html" class="btn btn-default">Voltar</a>
                </div>
            </div>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
</body>
</html>