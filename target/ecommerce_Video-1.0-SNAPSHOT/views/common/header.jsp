<!-- Page Loader -->
<div id="loader-wrapper">
    <div id="loader"></div>

    <div class="loader-section section-left"></div>
    <div class="loader-section section-right"></div>

</div>
<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
        <a class="navbar-brand" href="index">
            <i class="fas fa-film mr-2"></i>
            Online entertainment
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
                <c:choose>
                    <c:when test="${not empty sessionScope.currentUser}">
                        <li class="nav-item">
                            <a class="nav-link nav-link-1 active"
                               data-toggle="modal" data-target="#changePassModel"
                               style="cursor: pointer"
                            >Welcome, ${sessionScope.currentUser.username}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-4" href="favorites">My favorites</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-4" href="history">History</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-4" href="logout">Logout</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link nav-link-2" href="login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-3" href="register">Register</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-4" href="forgotPass">Forgot Pasword</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

<div class="tm-hero d-flex justify-content-center align-items-center" data-parallax="scroll"
     data-image-src="${pageContext.servletContext.contextPath}/views/template/users/img/hero.jpg">
    <form class="d-flex tm-search-form">
        <input class="form-control tm-search-input" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success tm-search-btn" type="submit">
            <i class="fas fa-search"></i>
        </button>
    </form>

    <!-- Modal -->
    <div class="modal fade" id="changePassModel" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Change Password</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <input type="password" name="currentPass" id="currentPass" class="form-control rounded-0"
                               placeholder="Old password" required/>
                    </div>
                    <div class="form-group">
                        <input type="password" name="newPass" id="newPass" class="form-control rounded-0"
                               placeholder="New password"
                               required/>
                    </div>
                    <h5 class="text-danger" id="messageChangePass"></h5>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-warning" id="changePassBtn">Save changes</button>
                </div>
            </div>
        </div>
    </div>

</div>