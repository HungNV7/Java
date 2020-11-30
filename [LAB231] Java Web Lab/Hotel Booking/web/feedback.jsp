<%-- 
    Document   : feedback
    Created on : Oct 30, 2020, 3:59:03 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <title>Feedback Page</title>
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
            .star{
                display: flex;
                align-items: center;
                justify-content: center;
            }
            a{
                color: black;
            }
        </style>
    </head>
    <body>

        <div class="container-fuild row">
            <div class="col-3"></div>
            <div class="col-6">
                <div class="card">
                    <div class="card-body">
                        <hr>
                        <h5 class="card-title">Feedback Form</h5>
                        <form action="feedback" method="POST">
                            <div class="form-group">
                                <label>Your feedback</label>
                                <textarea type="text" class="form-control"placeholder="Type your feedback" name="feedback" rows="5"></textarea>
                            </div> 
                            <div class="star">
                                <c:forEach var="count" begin="1" end="5">
                                    <c:choose>
                                        <c:when test="${not empty param.index && param.index >= count}">
                                            <a href="feedback.jsp?index=${count}&id=${param.id}">
                                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-star-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
                                                </svg>
                                            </a>
                                        </c:when>
                                        <c:when test="${not empty param.index && param.index < count}">
                                            <a href="feedback.jsp?index=${count}&id=${param.id}">
                                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-star" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.523-3.356c.329-.314.158-.888-.283-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767l-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288l1.847-3.658 1.846 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.564.564 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z"/>
                                                </svg>
                                            </a>
                                        </c:when>
                                        <c:when test="${empty param.index}">
                                            <a href="feedback.jsp?index=${count}&id=${param.id}">
                                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-star" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.523-3.356c.329-.314.158-.888-.283-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767l-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288l1.847-3.658 1.846 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.564.564 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z"/>
                                                </svg>
                                            </a>
                                        </c:when>
                                    </c:choose>
                                    <c:if test="${param.index == count}">
                                        <input type="hidden" value="${count}" name="star"/>
                                        <input type="hidden" value="${param.id}" name="id"/>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <br>
                            <button type="submit" class="btn btn-primary btn-block">Submit</button>  
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-3"></div>
        </div>
    </body>
</html>
