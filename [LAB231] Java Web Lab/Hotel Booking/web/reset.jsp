<%-- 
    Document   : forgetPass
    Created on : Oct 30, 2020, 11:38:26 AM
    Author     : ASUS
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <title>Reset Password Page</title>
        <style>
            .card{
                margin:0 auto;
                margin-top: 15vh;
                background: #fff;
                box-shadow: 0px 0px 1px 0px #000;
            }
            .card-title{
                font-size:2rem;
                margin-bottom: 2rem;
                text-align: center;

            }
            label{
                font-weight: bold;
                margin-bottom: 0.2rem;
            }
            body{
                background-color: whitesmoke;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="card" style="width: 22rem;">
                <div class="card-body">
                    <h5 class="card-title">Reset Password</h5>
                    <form action="reset" method="POST">
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" class="form-control" placeholder="Enter new password" name="password"/>                           
                        </div>    
                        <input type="hidden" name="email" value="${param.none}"/>
                        <label>Re-Password</label>
                        <input type="password" class="form-control" placeholder="Enter password" name="rePassword">
                        <c:if test="${not empty sessionScope.RESET_FAIL}">
                            <font color="red"><small>${sessionScope.RESET_FAIL}</small></font> 
                        </c:if>
                        <br/>
                        <c:remove var="RESET_FAIL" scope="session"></c:remove>

                        <button type="submit" class="btn btn-primary btn-block">Submit</button>
                        </br>
                        </br>
                    </form>      
                </div>
            </div>
        </div>
    </body>
</html>
