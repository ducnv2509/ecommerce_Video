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
    <title>${video.title}</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<!-- Page Loader -->

<%@include file="../common/header.jsp" %>
<div class="container-fluid tm-container-content tm-mt-60">
    <div class="row mb-4">
        <h2 class="col-12 tm-text-primary">${video.title}</h2>
    </div>
    <div class="row tm-mb-90">
        <div class="col-xl-8 col-lg-7 col-md-6 col-sm-12">
            <iframe id="tm-video" src="http://www.youtube.com/embed/${video.href}">

            </iframe>
        </div>
        <div class="col-xl-4 col-lg-5 col-md-6 col-sm-12" style="min-height: 500px!important;">
            <div class="tm-bg-gray tm-video-details">
                <c:if test="${not empty sessionScope.currentUser}">
                    <div class="text-center mb-5">
                        <button id = "likeOrUnlikeBtn" class="btn btn-primary tm-btn-big">
                            <c:choose>
                                <c:when test="${flagLikeBtn == true}">
                                    Unlike
                                </c:when>
                                <c:otherwise>Like</c:otherwise>
                            </c:choose>

                        </button>
                    </div>
                    <div class="text-center mb-5">
                        <a href="#" class="btn btn-primary tm-btn-big">Share</a>
                    </div>
                </c:if>

                <div class="mb-4">
                    <h3 class="tm-text-gray-dark mb-3">Description</h3>
                    <p>${video.description}</p>
                </div>

                <input type="hidden" value="${video.href}" id = "videoIdHdn">
            </div>
        </div>
    </div>
</div> <!-- container-fluid, tm-container-content -->

<%@include file="../common/footer.jsp" %>
<script>
    $('#likeOrUnlikeBtn').click(function () {
        let videoId = $('#videoIdHdn').val();
        $.ajax({
            url: 'video?action=like&id=' +videoId
        }).then(function (data) {
            let text = $('#likeOrUnlikeBtn').text();
            if(text.indexOf('Like') !== -1){
                $('#likeOrUnlikeBtn').text('Unlike')
            }else{
                $('#likeOrUnlikeBtn').text('Like')
            }
        }).fail(function (error) {

        })
    })
</script>
</body>
</html>
