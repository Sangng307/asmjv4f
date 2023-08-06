<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
	<%@ include file="/common/head.jsp" %>
<!--
    
TemplateMo 556 Catalog-Z

https://templatemo.com/tm-556-catalog-z

-->
</head>
<body>
    <!-- Page Loader -->

	<%@ include file="/common/header.jsp" %>
    <div class="container-fluid tm-container-content tm-mt-60">
        
        	
        	<div class="col-lg-12 col-12 mb-5">
                
                <form id="contact-form" action="" method="POST" class="tm-contact-form mx-auto ">
                <h2 class="tm-text-primary text-center mb-5">Login Page</h2>
                    <div class="form-group">
                        <input type="text" name="username" class="form-control rounded-0" placeholder="Username" required />
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control rounded-0" placeholder="Password" required />
                    </div>
                    

                    <div class="form-group tm-text-right d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary ">Đăng nhập</button>
                       
                    </div>
                </form>                
            </div>
        	
            
                     
        </div> <!-- row -->
        
  
	<%@ include file="/common/footer.jsp" %>
    
</body>
</html>