<%--
  Created by IntelliJ IDEA.
  User: MAVIRI 3
  Date: 2/4/2017
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="291465610520-pnb9ums0tmkhdos0pb4m2p5mptuol8l2.apps.googleusercontent.com">
    <title>To Do List Maker</title>
    <style>
        /**
        The CSS styling for the background of the page
        */
        .bodyBackground {
            background-color: #f1f1f1;
        }

        /**
        The CSS Styling for the top header text
         */
        .welcomeText {
            color:#4d4d4d;
            text-align:center;
            font-family:"Arial", Helvetica, sans-serif;
            font-size:30px;
        }

        /**
        The CSS Styling for the top header subtext
         */
        .promptText {
            color:#4d4d4d;
            text-align:center;
            font-family:"Arial", Helvetica, sans-serif;
            font-size:20px;
        }

        /**
        The CSS styling for the login button while unused, hovered, and clicked in
         */
        .loginButton {
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
        .loginButton:hover {
            background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f6f6f6), color-stop(1, #ffffff));
            background:-moz-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
            background:-webkit-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
            background:-o-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
            background:-ms-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
            background:linear-gradient(to bottom, #f6f6f6 5%, #ffffff 100%);
            filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#f6f6f6', endColorstr='#ffffff',GradientType=0);
            background-color:#f6f6f6;
        }
        .loginButton:active {
            position:relative;
            top:1px;
            border:1px solid #f931ac;
        }


        /**
        The CSS Styling for the container holding the form elements
            all this does is center the form elements so they are in the center of the page
         */
        .container {
            width: 50%;
            margin: 0 auto;
        }

        /**
        The CSS styling for the text fields normally and while being used (focus).
         */
        input[type=text] {
            width: 250px;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #cccccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type=text]:focus {
            border: 3px solid #f931ac;
        }

        /**
        The CSS styling for the password fields normally and while being used (focus).
         */
        input[type=password] {
            width: 250px;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #cccccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type=password]:focus {
            border: 2px solid #f931ac;
        }
    </style>
</head>
<body class="bodyBackground">
    <div>
        <h1 class="welcomeText">${message}</h1>
        <h3 class="promptText">Please <a href="register.jsp">sign up</a> or log in to continue.</h3>
    </div>
    <br />
    <form method="post" id="login_form" action="home.jsp">
        <div class="container">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" maxlength="30" required />
            <br /><br />
            <label for="password">Password</label>
            <input type="password" name="password" id="password" required />
            <br /><br />
            <button class="loginButton" type="submit">Login</button>
        </div>
    </form>
    <div align="center">
        <div class="g-signin2" data-width="300" data-height="50" data-longtitle="true" data-onsuccess="onSignIn" data-theme="dark"></div>
        <script>
            function onSignIn(googleUser) {
                // Useful data for your client-side scripts:
                var profile = googleUser.getBasicProfile();

                //get information from the profile
                console.log("ID: " + profile.getId()); // Don't send this directly to your server!
                console.log('Full Name: ' + profile.getName());
                console.log('Given Name: ' + profile.getGivenName());
                console.log('Family Name: ' + profile.getFamilyName());
                console.log("Image URL: " + profile.getImageUrl());
                console.log("Email: " + profile.getEmail());

                // The ID token you need to pass to your backend:
                var id_token = googleUser.getAuthResponse().id_token;
                console.log("ID Token: " + id_token);

                document.getElementById("username").value = profile.getEmail();
                document.getElementById("password").value = profile.getId();
                document.getElementById("login_form").submit();

            }
        </script>
    </div>
</body>
</html>