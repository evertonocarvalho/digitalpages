<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags/template" prefix="template" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Characters</title>
		<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap-theme.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/index.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/forms.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/pagination/jqpagination.css'/>" />
		<script src="<c:url value='/assets/js/jquery-2.1.4.min.js'/>"></script>
		<script src="<c:url value='/assets/js/bootstrap.min.js'/>"></script>
		<script src="<c:url value='/assets/js/jquery.jqpagination.js'/>"></script>
	</head>
	<body>
		<div class="container" align="center">
			<h2 class="basic-title">Characters</h2>
			<div class="well" style="overflow-y: scroll; height: 400px">
				<table class="table table-condensed table-bordered table-striped">
					<thead>
						<tr>
							<td>Nome</td>
							<td>Descrição</td>
							<td width="13%">Última Atualização</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items='${paginatedList.currentList}' var="character">
							<tr>
								<td><a href="/MarvelWeb/characterDetail/${empty param.page ? 0 : param.page}/${character.id}">${character.name}</a></td>
								<td>${character.description}</td>
								<td>${character.dataModificacao}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<template:paginationComponent paginatedList="${paginatedList}" page="${param.page}" action="/MarvelWeb/listCharacters" />
		</div>
	</body>
</html>