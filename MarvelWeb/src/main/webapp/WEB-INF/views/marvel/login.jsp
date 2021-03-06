<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dados de acesso</title>
		<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap-theme.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/index.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/forms.css'/>">
		<script src="<c:url value='/assets/js/jquery-2.1.4.min.js'/>"></script>
		<script src="<c:url value='/assets/js/bootstrap.min.js'/>"></script>
	</head>
	<body>
		<div class="container min-container" align="center">
			<h2 class="basic-title">Dados de acesso</h2>
			<form:form role="form" cssClass="well" commandName="login" action="/MarvelWeb/login" method="POST">
				<div class="form-group">
					<div class="input-group">
						<form:input path="privateKey" type="text" placeholder="private_key" size="50"/>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<form:input path="publicKey" type="text" placeholder="public_key" size="50"/>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">Acessar</button>
			</form:form>
		</div>
	</body>
</html>