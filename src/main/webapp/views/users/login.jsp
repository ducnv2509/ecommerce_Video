<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ducit
  Date: 4/23/2022
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/taglib.jsp"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<!-- Page Loader -->

<%@include file="../common/header.jsp" %>

<div class="container-fluid tm-mt-60">
    <div class="row tm-mb-50">
        <div class="col-lg-12 col-12 mb-5">
            <h2 class="tm-text-primary mb-5 text-center">Login</h2>
            <form id="contact-form" action="login" method="POST" class="tm-contact-form mx-auto">
                <div class="form-group">
                    <input type="text" name="username" class="form-control rounded-0" placeholder="username" required/>
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control rounded-0" placeholder="password" required/>
                </div>
                <div class="form-group tm-text-right">
                    <button type="submit" class="btn btn-primary">Send</button>
                </div>
            </form>
        </div>

    </div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
