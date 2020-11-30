
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <title>Login Page</title>
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
            hr{
                width:80%;
                margin-top: 20px;
                border-top: 1px solid rgba(0,0,0,1);
            }
            .o{
                background: #fff;
                width:25px;
                margin:-29px auto 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <c:if test="${sessionScope.EMAIL_INVALID != null}">
                <div class="alert alert-danger" role="alert">              
                    ${sessionScope.EMAIL_INVALID}  
                </div>
            </c:if>
            <c:remove var="EMAIL_INVALID" scope="session"></c:remove>
            <div class="card" style="width: 22rem;">
                <div class="card-body">
                    <h5 class="card-title">Sign in</h5>
                    <form action="login" method="POST">
                        <div class="form-group">
                            <label>Email</label>
                            <input type="text" class="form-control" placeholder="Enter email" name="txtUserID"/>                           
                        </div>    

                        <label>Password</label>
                        <input type="password" class="form-control" placeholder="Enter password" name="txtPassword">
                        <c:if test="${not empty sessionScope.LOGIN_FAIL}">
                            <font color="red"><small>${sessionScope.LOGIN_FAIL}</small></font> 
                            </c:if>
                        <br/>
                        <c:remove var="LOGIN_FAIL" scope="session"></c:remove>

                        <button type="submit" class="btn btn-primary btn-block" name="btnAction" value="Login">Sign in</button>
                        <hr>
                        <p class="o">OR</p>
                        <a href="register.jsp" class="btn btn-primary btn-block">Register</a>  
                        </br>
                        </br>



                    </form>      
                    <a href="forgetPass.jsp" data-toggle="modal" data-target="#ForgetPassword">Forget password</a>
                    <!-- Modal -->
                    <div class="modal fade" id="ForgetPassword" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Forget Password</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form action="sendReqChangePass">
                                    <div class="modal-body">
                                        Please enter your email!
                                        <input type="text" name="email" class="form-control">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <input type="submit" class="btn btn-primary" value="Submit"/>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
