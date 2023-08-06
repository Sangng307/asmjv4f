<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="loader-wrapper">
		<div id="loader"></div>

		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>

	</div>
	<nav class="navbar navbar-expand-lg">
		<div class="container-fluid">
			<a class="navbar-brand" href="<c:url value='/index'/>"> <i
				class="fas fa-film mr-2"></i> Video
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto mb-2 mb-lg-0">

					<c:choose>
						<c:when test="${empty sessionScope.currentUser}">
							<li class="nav-item"><a class="nav-link nav-link-2"
								href="login">Đăng nhập</a></li>

							<li class="nav-item"><a class="nav-link nav-link-3"
								href="register">Đăng ký</a></li>
							<li class="nav-item"><a class="nav-link nav-link-4"
								href="forgot">Quên mật khẩu</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link nav-link-1 active"
								aria-current="page">Wellcome,
									${sessionScope.currentUser.username} </a></li>
							<c:choose>
                                <c:when test="${sessionScope.currentUser.isAdmin == true}">
                                    <li class="nav-item"><a class="nav-link nav-link-3"
                                        href="admin">Admin</a></li>
                                </c:when>
                            </c:choose>
							<li class="nav-item"><a class="nav-link nav-link-2"
								href="history">Lịch sử</a></li>
							<li class="nav-item"><a class="nav-link nav-link-2"
								href="favourite">Yêu thích</a></li>
							<li class="nav-item"><a class="nav-link nav-link-2" href=""
								data-toggle="modal" data-target="#changePassModal">Đổi
									mật khẩu</a></li>
									<li class="nav-item"><a class="nav-link nav-link-2" href=""
								data-toggle="modal" data-target="#changeInfo">Edit thông tin</a></li>
							<li class="nav-item"><a class="nav-link nav-link-2"
								href="logout">Đăng xuất</a></li>
						</c:otherwise>
					</c:choose>





				</ul>
			</div>
		</div>
	</nav>

	<div class="tm-hero d-flex justify-content-center align-items-center"
		data-parallax="scroll" data-image-src="templates/user/img/hero.jpg">
		<form class="d-flex tm-search-form">
			<input class="form-control tm-search-input" type="search"
				placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success tm-search-btn" type="submit">
				<i class="fas fa-search"></i>
			</button>
		</form>
	</div>

	<div class="modal fade" id="changePassModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Đổi mật khẩu</h5>
					<button type="button" class="btn-close" data-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
				<input type="password" name="crPass" id="crPass" class="form-control rounded-0" placeholder="Current Password" required />
				<br>
				<input type="password" name="nwPass" id="nwPass" class="form-control rounded-0" placeholder="New Password" required />
				<h5 style="color: red" id="messageChangePass"></h5>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary" id="changePassBtn">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="changeInfo" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Thay đổi thông tin</h5>
					<button type="button" class="btn-close" data-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<label>Username: ${sessionScope.currentUser.username}</label>
					<br>
					<input type="text" name="email" id="email" class="form-control rounded-0" placeholder="email" required value="${sessionScope.currentUser.email}"/>
					<br>
					<input type="text" name="fullname" id="fullname" class="form-control rounded-0" placeholder="fullname" required value="${sessionScope.currentUser.fullname}"/>	
					<br>
					<input type="password" name="password" id="password" class="form-control rounded-0" placeholder="password" required />
						
						
						
					<h5 style="color: red" id="messageEdit"></h5>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary" id="btnEdit">Save
						changes</button>
				</div>
			</div>
		</div>
	</div>
</body>


</html>