<%-- 
    Document   : createDiscount
    Created on : Oct 29, 2020, 8:05:07 PM
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
        <title>Create Discount Page</title>
        <style>
            body{
                background-color: whitesmoke;
            }
            h2{
                text-align: center;
            }
            .container{
                margin-top: 9%;
            }
            .navbar{
                overflow: hidden;
                position: fixed;
                top: 0;
                width: 100%;
                z-index: 100;
            }
            .btnCheckout{
                margin-right: 0;
                width: inherit;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                <a class="navbar-brand" href="home">RedDoorzz</a>
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item active">
                        <a class="nav-link" href="home">Home<span class="sr-only">(current)</span></a>
                    </li>
                    <c:if test="${sessionScope.USER != null && sessionScope.USER.roleID != 1}">
                        <li class="nav-item active">
                            <a class="nav-link" href="cart.jsp">CART<span class="sr-only"></span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="history">Booking history<span class="sr-only"></span></a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.USER != null && sessionScope.USER.roleID == 1}">
                        <li class="nav-item active">
                            <a class="nav-link" href="createDiscount.jsp">Create Discount<span class="sr-only"></span></a>
                        </li>
                    </c:if>
                    <li class="nav-item disable">
                        <c:if test="${sessionScope.USER != null}">  
                            <a class="nav-link">Welcome ${sessionScope.USER.name}</a>
                        </c:if>
                    </li>
                </ul>

                <c:if test="${sessionScope.USER != null}">  
                    <a href="logout" class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</a>
                </c:if>
                <c:if test="${sessionScope.USER == null}">  
                    <a href="login.jsp" class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</a>
                </c:if>

            </div>
        </nav>
        <div class="container">
            <form action="createDiscount" method="POST">
                <div class="row">
                    <div class="col-3"></div>
                    <div class="col-6">
                        <div class="form-group">
                            <label>Discount code</label>
                            <input type="text" class="form-control" name="code">
                            <c:if test="${not empty sessionScope.DISCOUNT_FAIL.errorCode}">
                                <font color="red"><small>${sessionScope.DISCOUNT_FAIL.errorCode}</small></font>
                                </c:if>     
                        </div>
                        <div class="form-group">
                            <label>Discount Name</label>
                            <input type="text" class="form-control" name="name">
                            <c:if test="${not empty sessionScope.DISCOUNT_FAIL.errorName}">
                                <font color="red"><small>${sessionScope.DISCOUNT_FAIL.errorName}</small></font>
                                </c:if>  
                        </div>
                        <div class="form-group">
                            <label>Percentage</label>
                            <input type="number" min="1" max="100" class="form-control" name="percentage" value="1">
                        </div>
                        <div class="form-group">
                            <label>Expiry date</label>
                            <input type="date" class="form-control" name="date">
                            <c:if test="${not empty sessionScope.DISCOUNT_FAIL.errorDate}">
                                <font color="red"><small>${sessionScope.DISCOUNT_FAIL.errorDate}</small></font>
                                </c:if>  
                        </div>
                        <input type="submit" class="btn btn-info btnCheckout" value="Submit">
                    </div>
                    <div class="col-3"></div>
                </div>
                <c:remove var="DISCOUNT_FAIL" scope="session"></c:remove>
            </form>

        </div>
    </body>
</html>
