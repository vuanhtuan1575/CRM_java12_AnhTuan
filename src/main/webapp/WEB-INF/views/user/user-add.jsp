<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create User</title>
</head>
<body>
<c:choose>
  <c:when test="${isSuccess}">
    <h1>Create success </h1>
  </c:when>
  <c:when test="${isSuccess==false}">
    <h1>Create fail</h1>
  </c:when>
</c:choose>

	<div class="col-lg-8 card-form__body card-body">
		<form action="<c:url value="<%=UrlConst.USER_ADD%>" />" method="post" >
			<div class="form-group">
				<label for="exampleInputEmail1">User Name:</label> <input
					name="name" type="text" class="form-control" id="exampleInputEmail1"
					placeholder="Enter your email address ..">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Your password:</label> <input
					name="password" type="password" class="form-control" id="exampleInputPassword1"
					placeholder="Enter your password ..">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Email:</label> <input
					name="email" type="email" class="form-control" id="exampleInputPassword1"
					placeholder="Enter your password ..">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Address:</label> <input
					name="address" type="text" class="form-control" id="exampleInputPassword1"
					placeholder="Enter your password ..">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Phone:</label> <input
					name="phone" type="text" class="form-control" id="exampleInputPassword1"
					placeholder="Enter your password ..">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Role:</label> <input
					name="role" type="text" class="form-control" id="exampleInputPassword1"
					placeholder="Enter your password ..">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>