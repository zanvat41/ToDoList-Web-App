<%--
  Created by IntelliJ IDEA.
  User: MAVIRI 3
  Date: 2/5/2017
  Time: 2:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="291465610520-pnb9ums0tmkhdos0pb4m2p5mptuol8l2.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <title>To Do List Maker</title>
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
    <div class="controlBarContainer">
        <button class=button title="Create a New To Do List">Create</button>
        <button class=button title="Load an Existing To Do List">Load</button>
        <button class=button title="Save this To Do List">Save</button>
        <form action="index.jsp" method="post">
            <div style="display:none" class="g-signin2" data-width="300" data-height="50" data-longtitle="true" data-onsuccess="onSignIn" data-theme="dark"></div>
            <button type="submit" class=button title="Logout" onclick="signOut()">Logout</button>
            <script>
                function signOut() {
                    var auth2 = gapi.auth2.getAuthInstance();
                    auth2.signOut().then(function () {
                        console.log('User signed out.');
                    });
                }
            </script>
        </form>
    </div>
    <h1 class="topText">To Do List</h1>
    <form name="details_form">
        <h2 class="promptText">Details</h2>
        <label for="form_name">Name of Todo List: </label>
        <input type="text" name="form_name" id="form_name" maxlength="30" />

        <label>Owner: </label>
        <label name="form_owner" id="form_owner"></label>
        <script>
            function onSignIn(googleUser) {
                // Useful data for your client-side scripts:
                var profile = googleUser.getBasicProfile();

                document.getElementById("form_owner").innerHTML = profile.getEmail();
            }

        </script>
    </form>
    <p></p>
    <div class="details">
        <h2>Items</h2>
        <div style="height:50px">
            <form action="addItem">
                <button class=button title="Add Item">+</button>
            </form>
        </div>
    </div>
    <div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>#ID</th>
                <th>Category</th>
                <th>Description</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Completed</th>
            </tr>
            </thead>

            <c:forEach var="item" items="${items}">
                <tr>
                    <td>
                            ${item.id}
                    </td>
                    <td>${user.}</td>
                    <td>${user.email}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>
                        <c:forEach var="framework" items="${user.framework}"
                                   varStatus="loop">
                            ${framework}
                            <c:if test="${not loop.last}">,</c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <spring:url value="/users/${user.id}" var="userUrl" />
                        <spring:url value="/users/${user.id}/delete" var="deleteUrl" />
                        <spring:url value="/users/${user.id}/update" var="updateUrl" />

                        <button class="btn btn-info"
                                onclick="location.href='${userUrl}'">Query</button>
                        <button class="btn btn-primary"
                                onclick="location.href='${updateUrl}'">Update</button>
                        <button class="btn btn-danger"
                                onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>