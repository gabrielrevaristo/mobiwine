<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cadastro de Aluno</title>
	</head>
	<body>
		<h1>Cadastro de Aluno</h1><hr>
		<form method="post" action="novo" enctype="multipart/form-data">
			RGM: <input type="text" name="txtRgm"><br>
			NOME: <input type="text" name="txtNome"><br>
			E-MAIL: <input type="text" name="txtEmail"><br>
			FOTO: <input type="file" name="txtArquivo"><br>
			<input type="submit" name="btnSalvar" value="Salvar Cadastro">			
		</form>
		<a href="lista">Voltar</a>
	</body>
</html>