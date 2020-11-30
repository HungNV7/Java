<%-- 
    Document   : searchHistory
    Created on : Oct 29, 2020, 6:16:19 PM
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
        <title>Search History Page</title>
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
            #expirateDate{
                padding-left: 15%;
            }
            .pagination{
                margin-top: 2%;
            }
            .card{
                height: 55vh;
            }
            .order{
                margin-top: 1%;
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
            <form action="searchHistory" method="POST">
                <div class="row">
                    <div class="col-2"></div>
                    <div class="col-4">
                        <input type="text" class="form-control" name="id" value="${param.id}" style="border: 1px solid;">
                    </div>
                    <div class="col-3">
                        <input type="date" class="form-control" name="date" value="${param.date}" style="border: 1px solid;">
                    </div>
                    <div class="col-1">
                        <input type="submit" value="Search" class="btn btn-primary"/>
                    </div>
                    <div class="col-2"></div>
                </div>
            </form>
            <c:if test="${sessionScope.SEARCH_HISTORY != null}">
                <c:forEach var="cart" items="${sessionScope.SEARCH_HISTORY}">
                    <div class="alert alert-secondary order" role="alert">
                        <div class="row">
                            <div class="col-6">
                                <h5 class="alert-heading">Order ID: ${cart.id}</h5>
                                <h5 class="alert-heading">Check in: ${cart.arriveDate}</h5>
                                <h5 class="alert-heading">Discount code: ${cart.discountID}</h5>
                                <h5 class="alert-heading">Booking date: ${cart.bookingDate}</h5>
                            </div>
                            <div class="col-6">
                                <c:if test="${cart.status == 5}">
                                    <a type="button" data-toggle="modal" class="close" aria-label="Close" data-target="#Remove${cart.id}">
                                        <span aria-hidden="true">&times;</span>
                                    </a>
                                </c:if>
                                <!-- Modal -->
                                <div class="modal fade" id="Remove${cart.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Cancel Booking</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                            </div>
                                            <div class="modal-body">
                                                Do you really want to cancel?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                <a class="btn btn-primary" href="delete?id=${cart.id}">Cancel</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <h5>&emsp;</h5>
                                <h5 class="alert-heading">Check out: ${cart.returnDate}</h5>
                            </div>
                        </div>
                        <hr>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Hotel</th>
                                    <th>Category</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="room" items="${cart.cart.values()}" varStatus="count">
                                    <tr>
                                        <td>${count.count}</td>
                                        <td>${room.hotelName}</td>
                                        <td>${room.categoryName}</td>
                                        <td>${room.quantity}</td>
                                        <td>${room.price}</td>
                                        <td>${room.price * room.quantity}</td>
                                        <td>       
                                            <c:if test="${cart.status == 7}">
                                                <a class="btn btn-secondary" href="feedback.jsp?index=1&id=${room.id}">Feedback</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><b>Total: </b> </td>
                            <td>${cart.finalTotal} $</td>
                            </tbody>
                        </table>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${sessionScope.SEARCH_HISTORY == null}">
                <br>
                <h2>No result</h2>
            </c:if>
        </div>         
    </body>
</html>
