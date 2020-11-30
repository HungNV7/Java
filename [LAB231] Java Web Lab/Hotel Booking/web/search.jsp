<%-- 
    Document   : search.jsp
    Created on : Oct 26, 2020, 5:40:17 PM
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
        <title>Search Page</title>
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
            a{
                color: black;
            }
            a:hover {
                text-decoration: none;
                color: black;
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

        <div class="album py-5 bg-light">
            <div class="container">
                <form action="search">
                    <div class="row">
                        <div class="form-group col-3">
                            <input type="text" class="form-control" name="txtNameOrArea" placeholder="Enter hotel name or area" style="border: 1px solid;">
                        </div>

                        <div class="form-group col-3">
                            <input type="date" class="form-control" name="txtArriveDate" style="border: 1px solid;">
                        </div>
                        <div class="form-group col-3">
                            <input type="date" class="form-control" name="txtReturnDate" style="border: 1px solid;">
                        </div>
                        <div class="form-group col-2">
                            <select name="txtRooms" class="form-control" style="border: 1px solid;">
                                <option value="1">1 Room(s)</option>
                                <option value="2">2 Room(s)</option>
                                <option value="3">3 Room(s)</option>
                                <option value="4">4 Room(s)</option>
                                <option value="5">5 Room(s)</option>
                            </select>
                        </div>
                        <div class="col-1">
                            <input type="submit" value="Search" class="btn btn-primary"/>
                        </div>
                    </div>
                </form>
                <div class="row">
                    <c:if test="${sessionScope.LIST_SEARCH.size() != 0}">
                        <c:forEach var="hotel" items="${sessionScope.LIST_SEARCH}">
                            <div class="col-md-4">
                                <div class="card mb-4 shadow-sm">
                                    <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><image href="Images/${hotel.image}" height="100%" width="100%"/></svg>

                                    <div class="card-body">
                                        <a href="detail?id=${hotel.id}"><b>${hotel.name}</b></a>
                                        <p class="card-text"><b>Address:</b> ${hotel.address}</p>
                                        <p class="card-text"><b>Phone:</b> ${hotel.phone}</p>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
