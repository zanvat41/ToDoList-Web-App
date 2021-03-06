<%--
  Created by IntelliJ IDEA.
  User: Tyler
  Date: 2/9/2017
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.

  This page is used as a form page to have the user to create a new list item.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>To Do List Maker - New List Item</title>
	<style>
		.bodyBackground {
			background-color: #f1f1f1;
		}

		.button {
			-moz-box-shadow:inset 0px 1px 0px 0px #ffffff;
			-webkit-box-shadow:inset 0px 1px 0px 0px #ffffff;
			box-shadow:inset 0px 1px 0px 0px #ffffff;
			background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #ffffff), color-stop(1, #f6f6f6));
			background:-moz-linear-gradient(top, #ffffff 5%, #f6f6f6 100%);
			background:-webkit-linear-gradient(top, #ffffff 5%, #f6f6f6 100%);
			background:-o-linear-gradient(top, #ffffff 5%, #f6f6f6 100%);
			background:-ms-linear-gradient(top, #ffffff 5%, #f6f6f6 100%);
			background:linear-gradient(to bottom, #ffffff 5%, #f6f6f6 100%);
			filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff', endColorstr='#f6f6f6',GradientType=0);
			background-color:#ffffff;
			-moz-border-radius:5px;
			-webkit-border-radius:5px;
			border-radius:5px;
			border:2px solid #969696;
			display:inline-block;
			cursor:pointer;
			color:#4d4d4d;
			font-family:"Arial", Helvetica, sans-serif;
			font-size:18px;
			font-weight:bold;
			padding:6px 24px;
			text-decoration:none;
			text-shadow:0px 1px 0px #ffffff;
		}
		.button:hover {
			background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f6f6f6), color-stop(1, #ffffff));
			background:-moz-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
			background:-webkit-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
			background:-o-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
			background:-ms-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
			background:linear-gradient(to bottom, #f6f6f6 5%, #ffffff 100%);
			filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#f6f6f6', endColorstr='#ffffff',GradientType=0);
			background-color:#f6f6f6;
		}
		.button:active {
			position:relative;
			top:1px;
			border:1px solid #f931ac;
		}

		.topText {
			color:#4d4d4d;
			text-align:left;
			font-family:"Arial", Helvetica, sans-serif;
			font-size:30px;
		}
		.promptText {
			color:#4d4d4d;
			text-align:left;
			font-family:"Arial", Helvetica, sans-serif;
			font-size:20px;
		}

		input {
			width: 250px;
			padding: 12px 20px;
			margin: 8px 0;
			display: inline-block;
			border: 1px solid #cccccc;
			border-radius: 4px;
			box-sizing: border-box;
		}
		input:focus {
			border: 3px solid #f931ac;
		}

		table, td, th {
			border: 1px solid #ddd;
			text-align: left;
		}
		table {
			border-collapse: collapse;
			width: 100%;
		}
		th, td {
			padding: 15px;
		}
	</style>
</head>
<body class="bodyBackground">
	<div>
		<h1 class="welcomeText">Select a list to load!</h1>
	</div>
	<br />
	<div class="details">
		<table class="table table-striped">
			<thead>
			<tr>
				<th>List Name</th>
				<th>Owner</th>
				<th></th>
			</tr>
			</thead>
			<tbody>

			<c:forEach items="${lists}" var="list">
				<tr>
					<td>${list.name}</td>
					<td>${list.owner}</td>
					<td>
						<a href="/load-list?name=${list.name}&owner=${list.owner}" class="btn btn-success">Load</a>
					</td>
				</tr>
			</c:forEach>

			</tbody>
		</table>

	</div>
</body>
</html>