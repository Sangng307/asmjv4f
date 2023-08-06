<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Forgot password</title>
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


		<div class="col-lg-12 col-12 mb-5">

			<h2 class="tm-text-primary text-center mb-5">Forgot Page</h2>
			<div class="form-group">
				<input type="text" id="email1" name="email1" class="form-control rounded-0"
					placeholder="Email" required />
			</div>
			<div class="form-group tm-text-right d-flex justify-content-center">
				<button type="submit" Id="forgotBtn" class="btn btn-primary ">QMK</button>
			</div>
			<h5 style="color: red" id="message"></h5>
		</div>



	</div>
	<!-- row -->


	<%@ include file="/common/footer.jsp"%>

	<script>
	$(document).ready(function() {
    $('#forgotBtn').click(function() {
        $('#message').text('');
        var email = $('#email1').val();
        var form = {'email1': email};
        $.ajax({
            url: 'forgot',
            type: 'POST',
            data: form
        }).then(function(data) {
            $('#message').text('Xin kiem tra email');
            setTimeout(function() {
                window.location.href = 'http://localhost:8082/asm-java4/index';
            }, 5 * 1000);
        }).fail(function(error) {
            $('#message').text('Email khong chinh xac xin thu lai');
            console.log(email,form);
        });
    });
	});
	</script>
</body>
</html>