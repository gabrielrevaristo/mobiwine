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

    <title>Editar Vinho</title>
  </head>
  <body style="height: 100%;">
  

  <div class="container-fluid" style="margin: 0px">
		  <div class="row">
		    <div class="col" style="background: #D6394B;height:10vh;"><h1>Logotipo</h1></div>
		    
		    <div class="w-100"></div>
		   
		    <div class="col-sm-2" style="height:90vh; background: #C9B8D3; word-wrap: break-word;"><div>


			<a href="#" class="btn btn-primary btn-lg disabled" tabindex="-1" role="button" aria-disabled="true" style="background-color: #D6394B; border-color: white; margin-left:2vh; margin-top: 4vh">Cadastro de Vinho</a>
			<a href="#" class="btn btn-primary btn-lg disabled" tabindex="-1" role="button" aria-disabled="true" style="width: 26vh; background-color: #D6394B; border-color: white; margin-left:2vh; margin-top: 2vh">Listar Vinhos</a>

			</div> </div>
		    
		    <div class="col" style="background: #EDC0D2">
		    
		    <div class="row">
		    
		    <h1 class="display-4" style="margin-top: 2vh; margin-left: 2vh" >Editar Cadastro</h1>
		    
		    </div>
		    
		    <form name="editarVinho" action="Editar" method="POST" enctype="multipart/form-data">
		    
				<input name="id" type="hidden" value="${vinho.id}">		    
				<div class="row" style="margin-top:5vh">
				    <div class="col-sm-9" style="background: ;height:10vh;">
				    
						<div class="input-group mb-3">
						  <div class="input-group-prepend">
						    <span class="input-group-text" id="basic-addon3" >Nome</span>
						  </div>
						  <input type="text" required="" name="nome" class="form-control" aria-describedby="basic-addon3" value="${vinho.nome}">
						 
						 </div>
						 
						 <div class="input-group mb-3">
						  <div class="input-group-prepend">
						    <span class="input-group-text" id="basic-addon3" >País</span>
						  </div>
						  
						  <input type="text" name="pais" class="form-control" aria-describedby="basic-addon3"  value="${vinho.pais}" style="margin-right:2vh" pattern="[a-zA-Z]+" title="Utilize apenas caracteres alfabéticos" required>
						  
						  <div class="input-group-prepend">
						    <span class="input-group-text" id="basic-addon3">Região</span>
						  </div>
						  
						  
						  <input type="text" name="regiao" class="form-control" aria-describedby="basic-addon3"  value="${vinho.regiao}" pattern="[a-zA-Z]+" title="Utilize apenas caracteres alfabéticos" required>
						</div>
						
						
						<div class="row">
						
						
								<div class="col-sm-8">
								<div class="input-group mb-3">
								  
								  
								  <div class="input-group-prepend" >
								    <span class="input-group-text" id="basic-addon3">Nome do produtor</span>
								  </div>
								  
								  <input type="text" name="nome_produtor" class="form-control" aria-describedby="basic-addon3"  value="${vinho.nomeProdutor}" required>
								</div>
								</div>
								
								<div class="col-sm-4">
								<div class="input-group mb-3">
								  
								  
								  <div class="input-group-prepend" >
								    <span class="input-group-text" id="basic-addon3">Ano da Safra</span>
								  </div>
								  
								  <input type="text" name="ano_safra" class="form-control" aria-describedby="basic-addon3"  value="${vinho.anoSafra}" maxlength="4" pattern="[0-9]{4}" title="Entre com o ano" required>
								</div>
								</div>
								
						
						</div>
						
						
						<div class="input-group mb-3">
						  <div class="input-group-prepend">
						    <span class="input-group-text" id="basic-addon3">Descrição</span>
						  </div>
						  <textarea name="descricao" class="form-control" aria-label="With textarea" style="resize:none" required>${vinho.descricao}</textarea>
						 
						 </div>
						
						<div class="input-group mb-3">
						  <div class="input-group-prepend">
						    <span class="input-group-text">Upload</span>
						  </div>
						  <div class="custom-file">
						    <input onchange="loadFile(event)" id="imgFile" name="imgFile" type="file" class="custom-file-input" id="inputGroupFile01">
						    <label id="fileLabel" class="custom-file-label" for="inputGroupFile01">Imagem</label>
						  </div>
						</div>
						
						<div class="input-group mb-3">
							<div class="input-group-prepend">
						   	<label class="input-group-text" for="inputGroupSelect01">Tipo de Vinhos</label>
						</div>
							 <select name="tipoVinho" class="custom-select" id="inputGroupSelect01" style="max-width:30vh; margin-right: 5vh;">
							 	<%
							 		String tipo = (String) request.getAttribute("tipoVinho");
							 		out.println("Tipo " + tipo);
							 		switch (tipo) {
							 		case "1":
							 		default:
							 			out.println("<option value=\"1\" selected>Tinto</option>");
							 			out.println("<option value=\"2\">Branco</option>");
							 			out.println("<option value=\"3\">Rosé</option>");
							 			break;
							 		case "2":
							 			out.println("<option value=\"1\">Tinto</option>");
							 			out.println("<option value=\"2\" selected>Branco</option>");
							 			out.println("<option value=\"3\">Rosé</option>");
							 			break;
							 		case "3":
							 			out.println("<option value=\"1\">Tinto</option>");
							 			out.println("<option value=\"2\">Branco</option>");
							 			out.println("<option value=\"3\" selected>Rosé</option>");
							 			break;
							 		}
							 	%>
							   </option>
							 </select>
						  <div class="input-group-prepend">
						    <span class="input-group-text">Preço R$</span>
						    
						  </div>
						  <input required name="preco" type="text" class="form-control"  value="${vinho.preco}" style="max-width:20vh;" title="Entre com o valor em R$0.00" maxlength="11" pattern="^\d{0,9}(\.\d{1,2})?$">
						</div>
								
												
						<button type="submit" class="btn btn-primary" style="background-color: #D6394B;border-color: white">

						  Salvar
						
						</button>
						
				   		</div>
					<div class="col-sm-3" style="background: #D6394B;min-width:200px;min-height:200px">
				    	<img id="img" name="img" src="GetImagem?id=${ vinho.id }" width="99" height="200" style="display:block;margin:auto; position:relative; top:50%; transform:translateY(-50%); background:#D6394B;"/>
					</div>
				</div>
				
				</form>
			</div>
		  </div>
	</div>	
	
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
  </body>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
  <script type="text/javascript">
  		function validarExtensao()
  		{
  			var formatosPermitidos = /(\.jpg|\.jpeg|\.png)$/i;
  			if (!formatosPermitidos.exec($("#imgFile").val())) {
  				$("#imgFile").val("");
  				alert("Tipo de arquivo não permitido.\nSáo válidos apenas arquivos .jpg e .png");
  			}
  		}
		var loadFile = function(event) {
			validarExtensao();
		    var img = document.getElementById('img');
		    img.src = URL.createObjectURL(event.target.files[0]);
		    img.onload = function() {
				img.width = img.naturalWidth;
				img.height = img.naturalHeight;
				console.log("Tamanho original");
				console.log(img.width);
				console.log(img.height);
				while (img.width > 200 || img.height > 200) {
					img.width *= 0.9;
					img.height *= 0.9;
				}
				console.log("Tamanho redimensionado")
				console.log(img.width);
				console.log(img.height);
				var nomeArquivo = $("#imgFile").val();
				nomeArquivo = nomeArquivo.substring(nomeArquivo.lastIndexOf("\\") + 1);
				console.log(nomeArquivo);
				$("#fileLabel").text(nomeArquivo);
		    }
	    };
	    $(document).ready(function() {
			var img = document.getElementById('img');
			img.width = img.naturalWidth;
			img.height = img.naturalHeight;
			while (img.width > 200 || img.height > 200) {
				img.width *= 0.9;
				img.height *= 0.9;
			}
	    });
  </script>
</html>