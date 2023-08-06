<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${video.title}</title>
<%@ include file="/common/head.jsp"%>
<!--
    
TemplateMo 556 Catalog-Z

https://templatemo.com/tm-556-catalog-z

-->
</head>
<body>
	<!-- Page Loader -->

	<%@ include file="/common/header.jsp"%>

	<div class="container-fluid tm-container-content tm-mt-60">
		<div class="container-fluid tm-container-content tm-mt-60">
			<div class="row mb-4">
				<h2 class="col-12 tm-text-primary">${video.title}</h2>

			</div>
			<div class="row tm-mb-90">

				<div class="col-xl-8 col-lg-7 col-md-6 col-sm-12">
					<iframe width="858" height="416"
						src="https://www.youtube.com/embed/${video.href}"
						title="YouTube video player" frameborder="0"
						allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
						allowfullscreen></iframe>
					<h3 class="tm-text-gray-dark mb-3">Description</h3>
					<div class="mr-4 mb-2">
						<span class="tm-text-gray-dark">${video.description}</span> <input
							type="text" name="href" id="href" class="form-control rounded-0"
							placeholder="link"
							value="https://www.youtube.com/watch?v=${video.href}" required />

					</div>
					<c:if test="${not empty sessionScope.currentUser}">
						<button id="likeOrUnlikeBtn" class="btn btn-primary tm-btn-big ">
							<c:choose>
								<c:when test="${flagLikedBtn == true}">
									Unlike
							</c:when>
								<c:otherwise>
								Like
							</c:otherwise>
							</c:choose>
						</button>
						<a class="btn btn-primary tm-btn-big " href="" data-toggle="modal"
							data-target="#share">Share</a>
					</c:if>

					<input id="videoIdHdn" name="videoIdHdn" type="hidden"
						value="${video.href}" />
				</div>
				<div class="col-xl-4 col-lg-5 col-md-6 col-sm-12">
					<div class="tm-bg-gray tm-video-details">
						<c:forEach items="${videos}" var="video">
							<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-5">
								<h5 class="tm-text-secondary text-center">${video.title}</h5>
								<a href="<c:url value='/video?action=watch&id=${video.href}' />"><img
									src="<c:url value='${video.poster}'/>" alt="Image"
									class="img-fluid"></a>
								<div class="d-flex justify-content-between tm-text-gray">
									<span class="tm-text-gray-light">Shares: ${video.shares}</span>
									<span>${video.views} view</span>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<!-- row -->
			<div class="mb-5">
				<br> <br> <br>
			</div>
		</div>



	</div>
	<div class="modal fade" id="share" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Share</h5>
					<button type="button" class="btn-close" data-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">

					<input type="text" name="email2" id="email2"
						class="form-control rounded-0" placeholder="Email..." required />


					<h5 style="color: red" id="message"></h5>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary" id="shareBtn">Share</button>
				</div>
			</div>
		</div>
	</div>
	<!-- row -->


	<%@ include file="/common/footer.jsp"%>

	<script>
		$('#likeOrUnlikeBtn').click(function() {
			var videoId = $('#videoIdHdn').val();
			$.ajax({
				url : 'video?action=like&id=' + videoId
			}).then(function(data) {
				var text = $('#likeOrUnlikeBtn').text();
				if (text.indexOf('Like') != -1) {
					$('#likeOrUnlikeBtn').text('Unlike');
				} else {
					$('#likeOrUnlikeBtn').text('Like');
				}
			}).fail(function(error) {
				alert('error');
			})
		});
	</script>

	<script>
		$(document).ready(function() {
			$('#shareBtn').click(function() {
				$('#message').text('');
				var email = $('#email2').val();
				var href = $('#href').val();
				var form = {
					'email2' : email,
					'href' : href
				};
				$.ajax({
					url : 'share',
					type : 'POST',
					data : form
				}).then(function(data) {
					$('#message').text('Da share thanh cong');
				}).fail(function(error) {
					$('#message').text('Email khong chinh xac xin thu lai');
					console.log(email, form);
				});
			});
		});
	</script>
</body>
</html>