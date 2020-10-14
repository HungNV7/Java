<%-- 
    Document   : history
    Created on : Jul 4, 2020, 10:59:51 AM
    Author     : Admin
--%>

<%@page import="sample.dtos.HistoryDTO"%>
<%@page import="sample.dtos.CartDTO"%>
<%@page import="sample.dtos.UserDTO"%>
<%@page import="sample.daos.CartDAO"%>
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
        <style>
            .nav-link:hover{
                background-color: dimgray;
            }
            .nav-item{
                margin-right: 1rem;
            }
            .form-inline{
                margin-right: 2rem;
            }
            .navbar{
                overflow: hidden;
                position: fixed;
                top: 0;
                width: 100%;
                z-index: 100;
            }
            .main{
                margin-top: 100px;
            }
            h2{
                text-align: center;
            }
        </style>
        <title>History Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="home.jsp"><img src="Resource/logo_FPT.png" style="height: 30px"/> FPT Library</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="home.jsp">HOME <span class="sr-only">(current)</span></a>
                        </li>
                        <c:if test="${sessionScope.USER.roleID!='RL001'}">  
                            <li class="nav-item">
                                <a class="nav-link" href="history.jsp">YOUR HISTORY</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="viewCart.jsp">YOUR CART</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.USER.roleID=='RL001'}">  
                            <li class="nav-item">
                                <a class="nav-link" href="listOrder.jsp">ORDER LIST</a>
                            </li>
                        </c:if>
                    </ul>
                    <form class="form-inline my-2 my-lg-0" action="MainController">
                        <c:if test="${sessionScope.USER!=null}">
                            <c:if test="${not empty sessionScope.USER}">
                                <button class="btn btn-light my-2 my-sm-0 " type="submit" value="Logout" name="btnAction">LOGOUT</button>
                            </c:if>       
                        </c:if>
                        <c:if test="${sessionScope.USER==null}">
                            <button class="btn btn-light my-2 my-sm-0 btnRegister" type="submit" value="Logout" name="btnAction">LOGIN</button>
                        </c:if>
                    </form>
                </div>
            </div>

        </nav>


        <div class="container main">
            <%
                if (session.getAttribute("HISTORY") == null) {
                    HistoryDTO history = new HistoryDTO();
                    UserDTO user = (UserDTO) session.getAttribute("USER");
                    if (user != null) {
                        history.getHistory(user.getUserName());
                        session.setAttribute("HISTORY", history);
                    }
                }
            %>



            <c:if test="${sessionScope.HISTORY!=null}">
                <c:if test="${not emptysessionScope.HISTORY}">
                    <c:if test="${sessionScope.HISTORY.history.size() != 0}">

                        <c:forEach var="cart" items="${sessionScope.HISTORY.history.values()}">
                            <div class="alert alert-secondary" role="alert">
                                <h4 class="alert-heading">${cart.id}</h4>
                                <div class="row">
                                    <div class="col-4">Rent Date: ${cart.rentDate}</div>
                                    <div class="col-4"></div>
                                    <div class="col-4">Return Date: ${cart.returnDate}</div>
                                </div>
                                <hr>
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                        <tr>
                                            <th>No</th>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Author</th>
                                            <th>Quantity</th>
                                        </tr>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="book" items="${cart.cart.values()}" varStatus="counter">
                                            <tr>
                                                <td>${counter.count}</td>
                                                <td>${book.id}</td>
                                                <td>${book.name}</td>
                                                <td>${book.author}</td>
                                                <td>${book.quantity}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:forEach>


                    </c:if>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.USER==null || sessionScope.HISTORY.history.size()==0}">
            <div class="alert alert-danger" role="alert">
                Your History is empty!
            </div>
        </c:if>
        </div>  
        
    </body>
</html>
