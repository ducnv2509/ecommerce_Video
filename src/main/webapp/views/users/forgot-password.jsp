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
    <title>Forgot Password</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<!-- Page Loader -->

<%@include file="../common/header.jsp" %>

<div class="container-fluid tm-mt-60">
    <div class="row tm-mb-50">
        <div class="col-lg-12 col-12 mb-5">
            <h2 class="tm-text-primary mb-5 text-center">Forgot</h2>
            <div class="form-group">
                <input type="email" id="email" name="email" class="form-control rounded-0" placeholder="email"
                       required/>
            </div>
            <div class="form-group tm-text-right">
                <button type="submit" class="btn btn-primary" id="sendBtn">Send</button>
            </div>
            <h5 class="text-danger" id="message"></h5>
        </div>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
<script>
    $('#sendBtn').click(function () {
        $('#message').text('');
        let email = $('#email').val();
        let formData = {'email': email};
        $.ajax({
            url: 'forgotPass',
            type: 'POST',
            data: formData
        }).then(function (data) {
            $('#message').text('Password has been reset, please check your email');
            setTimeout(function () {
                window.location.href = 'http://localhost:8080/ecommerce_Video_war/index';
            }, 3000)
        }).fail(function (error) {
            $('#message').text('Your information is not correct, try again');
        });
    })
</script>
</html>
