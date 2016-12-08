<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags/template" prefix="template" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Character Detail</title>
		<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap-theme.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/index.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/forms.css'/>">
		<script src="<c:url value='/assets/js/jquery-2.1.4.min.js'/>"></script>
		<script src="<c:url value='/assets/js/bootstrap.min.js'/>"></script>
	</head>
	<body>
		<div class="container" align="center">
			<form:form role="form" commandName="character" action="/MarvelWeb/listCharacters?page=${backTo}" method="POST">
				<table class="table">
					<tr>
						<td rowspan="2" width="15%"><img alt="${character.name}" src="${character.thumbnail.path}/portrait_xlarge.${character.thumbnail.extension}"></td>
						<td><h2>${character.name}</h2></td>
					</tr>
					<tr>
						<td><textarea rows="3" cols="100" disabled="disabled">${character.description}</textarea></td>
						<td><button type="submit" class="btn btn-primary btn-lg">Voltar</button></td>
					</tr>
				</table>
			</form:form>

			<h2 class="basic-title">Fascículos</h2>
			<hr>

			<div class="well" style="overflow-y: scroll; height: 300px">
				<table class="table">
					<c:forEach items="${characterComics}" var="comic">
						<tr>
							<td rowspan="2"><img alt="${comic.title}" src="${comic.thumbnail.path}/portrait_medium.${comic.thumbnail.extension}"></td>
							<td>
								<font style="font-weight: bold;">Título:</font> ${comic.title}
									&nbsp;
								<font style="font-weight: bold;">Número de capa:</font> ${comic.issueNumber}
							</td>
						</tr>
						<tr>
							<td><textarea rows="3" cols="100" disabled="disabled">${comic.description}</textarea></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</body>
</html>