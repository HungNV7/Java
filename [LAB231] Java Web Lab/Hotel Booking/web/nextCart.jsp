<%-- 
    Document   : cart
    Created on : Oct 27, 2020, 5:10:06 PM
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
        <title>Cart Page</title>
        <style>
            body{
                background-color: whitesmoke;
            }
            h2{
                text-align: center;
            }
            .container{
                margin-top: 5%;
            }
            .navbar{
                overflow: hidden;
                position: fixed;
                top: 0;
                width: 100%;
                z-index: 100;
            }
            .pagination{
                margin-top: 2%;
            }
            h3{
                display: inline-block;
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
            <c:if test="${sessionScope.CART != null && sessionScope.CART.cart.size() != 0}">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label>Check in</label>
                        <input type="date" name="txtArriveDate" class="form-control" value="${sessionScope.ARRIVE_DATE}" disabled="">
                    </div>
                    <div class="col-md-6 mb-3">
                        <label>Check out</label>
                        <input type="date" name="txtReturnDate" class="form-control" value="${sessionScope.RETURN_DATE}" disabled="">
                    </div>
                </div>
                <table class="table table-striped">
                    <thead>
                        <tr>               
                            <th>No</th>
                            <th>Hotel</th>
                            <th>Category</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="room" items="${sessionScope.CART.cart.values()}" varStatus="counter">   
                            <tr>
                                <td>${counter.count}</td>
                                <td>${room.hotelName}</td>
                                <td>${room.categoryName}</td>
                                <td>
                                    <input type="number" name="txtQuantity" value="${room.quantity}" min="1"/>        
                                </td>
                                <td>${room.price}</td>
                                <td>${room.price * room.quantity * sessionScope.GAP}</td>
                            </tr>                    
                        </c:forEach>
                    </tbody>
                </table>
                <form action="apply">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label>Discount code</label>
                            <input type="text" class="form-control" name="txtDiscountCode" value="${param.txtDiscountCode}">
                            <c:if test="${not empty sessionScope.DISCOUNT_FAIL}">
                                <font color="red"><small>${sessionScope.DISCOUNT_FAIL}</small></font> 
                                </c:if>
                            <br/>
                            <c:remove var="DISCOUNT_FAIL" scope="session"></c:remove>
                            </div>
                            <div class="col-md-3 mb-3">
                                <label>&emsp;</label>
                                <input type="submit" class="btn btn-info form-control" value="Apply">
                            </div>
                        </div>
                    </form>

                    <div class="row">
                        <div class="col-6">
                            <h3>Total: ${sessionScope.CART.finalTotal} $</h3>
                    </div>
                    <div class="col-3"></div>
                    <div class="col-3">
                        <a href="checkout" class="btn btn-info btnCheckout" data-toggle="modal" data-target="#email">Checkout</a>
                    </div>
                </div>   

                <div class="modal fade" id="email" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Enter email</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form action="checkout" method="POST">
                                <div class="modal-body">
                                    <input type="text" name="email" class="form-control">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>

            </c:if>
            <c:if test="${sessionScope.CART == null || sessionScope.CART.cart.values().size() == 0}">
                <h2>Your cart is empty.</h2>      
            </c:if>
        </div>
    </body>
</html>
