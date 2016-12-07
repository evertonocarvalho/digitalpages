<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags/template" prefix="template"%>

<div class="container min-container">
	<h2 class="basic-title">Dados de acesso</h2>
	<form:form role="form" cssClass="well" commandName="login" action="/MarvelWeb/login" method="POST">
		<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon">
					<i class="glyphicon glyphicon-unchecked"></i>
				</span>
				<form:input path="privateKey" type="text" placeholder="private_key" />
			</div>
		</div>
		<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon">
					<i class="glyphicon glyphicon-unchecked"></i>
				</span>
				<form:input path="publicKey" type="text" placeholder="public_key" />
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Acessar</button>
	</form:form>
</div>