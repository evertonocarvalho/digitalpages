<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap-theme.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/index.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/forms.css'/>">
		<script src="<c:url value='/assets/js/jquery-2.1.4.min.js'/>"></script>
		<script src="<c:url value='/assets/js/bootstrap.min.js'/>"></script>
	</head>
	<body>
		${exception.printStackTrace(pageContext.response.writer)}
	</body>
</html>