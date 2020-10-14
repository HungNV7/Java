<%-- 
    Document   : home
    Created on : Jun 25, 2020, 3:40:33 PM
    Author     : Admin
--%>

<%@page import="sample.daos.CartDAO"%>
<%@page import="java.util.List"%>
<%@page import="sample.dtos.BookDTO"%>
<%@page import="sample.daos.BookDAO"%>
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
            .card{
                display: inline-block;
                margin-bottom: 30px;
                margin-right: 1.5%;
                margin-left: 1.5%;
                border: 1px solid #efefef;
                box-shadow: 0 2px 10px 0 rgba(0,0,0,.16), 0 2px 5px 0 rgba(0,0,0,.26);
            }
            .card-title{
                height: 25px;    
                font-size: medium;
            }
            .card-text{
                height: 25px;
                font-size: small;
                margin-bottom: 5px;
            }
            .card-link{
                text-decoration: none;
                color: #000;
                margin-left: 0;
            }
            .btnAdd{
                margin-top: 0;
                margin-left: 25%;
                margin-bottom: 3%;
            }
            .card-body{
                padding-bottom: 0;
            }
            .listBook{
                margin-top: 30px;
            }
            .form-control{
                display: inline-block;
                border: 1px solid;
            }
            .card-link+.card-link {
                margin-left: 0;
            }
        </style>
        <title>Home Page</title>
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
                if (session.getAttribute("LIST_BOOK") == null) {
                    BookDAO book = new BookDAO();
                    List<BookDTO> list = list = book.getListBook();
                    session.setAttribute("LIST_BOOK", list);
                }

            %>


            <form action="MainController">
                <div class="row">
                    <div class="col-2"></div>
                    <div class="input-group col-7">

                        <input type="text" class="form-control" name="txtSearch" >
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" id="button-addon2" name="btnAction" value="Search"><img src="Resource/icon_search.png" style="height:20px;"/></button>
                        </div>
                    </div>
                    <div class="col-2">
                        <c:if test="${sessionScope.USER!=null && sessionScope.USER.roleID=='RL001'}">  
                            <button type="submit" class="btn btn-secondary btn-block" name="btnAction" value="Add New Book">Add New Book</button>  
                        </c:if>
                    </div>
                </div>
            </form>

            <form action="MainController">
                <div class="row">
                    <div class="col-2"></div>
                    <div class="input-group col-7">
                    </div>
                    <div class="col-2">


                    </div>
                </div>
            </form>



            <div class="listBook">
                <c:forEach var="book" items="${sessionScope.LIST_BOOK}" varStatus="counter">
                    <a class="card-link" href="MainController?btnAction=Detail&id=${book.id}">
                        <div class="card" style="width: 21%;">
                            <img src="Resource/${book.linkImage}" class="card-img" alt="..." height="280px">
                            <div class="card-body">
                                <h5 class="card-title">${book.name}</h5>
                                <p class="card-text">${book.author}<p/>
                            </div>

                            <c:if test="${sessionScope.USER.roleID!='RL001'}">
                                <c:if test="${book.status==true}">
                                    <a href="MainController?btnAction=Add to Cart&txtBookID=${book.id}" class="btn btn-primary btn-block">Add to Cart</a>
                                </c:if>
                                    
                                <c:if test="${book.status==false}">
                                    <a href="#" class="btn btn-dark btn-block">Unavailable</a>
                                </c:if>
                            </c:if>



                            <c:if test="${sessionScope.USER!=null && sessionScope.USER.roleID=='RL001'}">  
                                <form action="MainController">
                                    <button type="submit" class="btn btn-danger btn-block" name="btnAction" value="Update Book">Update</button>
                                    <input type="hidden" name="txtBookID" value="${book.id}"/>
                                </form>

                            </c:if>
                        </div>
                    </a>

                </c:forEach>
            </div>

        </div>       
    </body>
</html>
