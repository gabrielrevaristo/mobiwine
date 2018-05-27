<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

	<style>
	
	html, body{position: absolute; width: 100%; height: 100%; margin: 0; padding: 0; top:0; left:0;}
</style>

<title>Carta de Vinhos</title>
</head>
<body style="height: 100%;">
	
	<div class="container-fluid" style="margin: 0px">
		<div class="row">
			<div class="col" style="background: #D6394B;height:10vh;"><img src="imagens/baleia.png" style="background: #D6394B;height:10vh; float:left;margin-right:5%"/><h1 style="margin-top:1%;">MOBIWINE</h1></div>
			
			<div class="w-100"></div>
			
			<div class="col-sm-2" style=" background: #C9B8D3; word-wrap: break-word;"><div>
			</div>
			
		</div>
		
		<div class="col" style="background: #EDC0D2">
			
			<div class="row">
				
				<h1 class="display-4" style="margin-top: 2vh; margin-left: 2vh" >Carta de Vinhos</h1>
				
				
			</div>
			<a href="Cadastro"><button type="button" class="btn btn-danger" style="margin-left: 80%;margin-bottom: 2vh">Cadastrar Novo +</button></a>
			<table class="table">
				  <thead>
				    <tr>
					  <th scope="col" style="text-align:center">Foto</th>
					  <th scope="col">Nome/Data</th>
					  <th scope="col">Tipo</th>
					  <th scope="col">Preço</th>
					  <th scope="col"></th>
					</tr>
				  </thead>
				  <tbody>
				    <c:forEach var="vinhos" items="${vinhos}"> 
				    	<tr>
							  <td scope="row">
								  <img id="img" name="img" src="GetImagem?id=${vinhos.id}" width="99" height="200" style="display:block;margin:auto; position:relative; top:50%;background:#D6394B;"/>
							  </td>
							  <td scope="row">${vinhos.nome} - ${vinhos.anoSafra}</td>
							  <td scope="row">
								  <c:choose>
									  <c:when test="${vinhos.tipoVinho == '1' }">
										  <c:out value="Tinto" />
									  </c:when>
									  <c:when test="${vinhos.tipoVinho == '2' }">
										  <c:out value="Branco" />
									  </c:when>
									  <c:when test="${vinhos.tipoVinho == '3'}">
										  <c:out value="Rosé" />
									  </c:when>
								  </c:choose>
							  </td>
							  <td scope="row">R$ ${vinhos.preco}</td>
							  <td scope="row"><a href="Editar?id=${vinhos.id}"><img src="imagens/edit.png" style="height:5vh"/></a><a onclick="excluir(${vinhos.id})"><img src="imagens/delete.png" style="height:5vh; margin-left:1vh"/></a></td>
						</tr>
					</c:forEach>
				  </tbody>
				</table>
		</div><div class="col-sm-2" style=" background: #C9B8D3; word-wrap: break-word;">
		<div>
	</div>
</div>	
<!-- Modal -->
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script>
function excluir(id) {
    var r = confirm("Deseja mesmo excluir esse vinho?");
    if (r == true) {
    	window.location.replace("Deletar?id="+id);
    }
}
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script>
$(document).ready(function() {
	var imgs = document.getElementsByTagName("img");
	[].forEach.call(imgs, function(img) {
		if (img.name != "img")
			return;
		img.width = img.naturalWidth;
		img.height = img.naturalHeight;
		while (img.width > 200 || img.height > 200) {
			img.width *= 0.9;
			img.height *= 0.9;
		}
	});
});
</script>
</body>
</html>