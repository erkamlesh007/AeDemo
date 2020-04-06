<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Arithmetic Expression</title>
<link href="/css/main.css" rel="stylesheet">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ctg" uri="mytag.tld"%>
<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<script src="/js/main.js"></script>
	<h2 class="form-heading">
		<ctg:CustomTag />
	</h2>
	<div class="container">
		<form:form method="POST" action="${contextPath}/calc"
			class="form-signin" modelAttribute="inputForm">
			<div class="form-group">
				<form:input path="name" type="text" class="form-control" placeholder="enter expression" autofocus="true" />
				<form:button class="btn btn-lg btn-primary btn-block" type="submit">Calc</form:button>
			</div>
		</form:form>
		<h2 class="arithmetic-exp">Below is the Result for input ${input} </h2>
		<h2 class="arithmetic-exp">${name}</h2>
	</div>
</body>
</html>