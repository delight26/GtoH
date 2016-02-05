<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  <title>home</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/global.css" />
</head>
<body>

<div class="container-fluid">

		<div class="row">
			
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<p class="col-lg-12 col-md-12 col-sm-12 col-xs-12  subject">
					<span style="color: #444444;">최신 승부결과</span>
				</p>
				<p
					style="border-width: 1px; border-color: #b0b0b0; border-style: solid;"
					class="col-lg-12 col-md-12 col-sm-12 col-xs-12"></p>
				<c:forEach var="f" items="${ frbl }">
					<c:if test="${ f.isAdminCheck == 1 }">
						<div class="col-lg-3 col-md-3 col-sm-4 col-xs-6">
							<div class="con">
								<a
									href="fightResultBoardContent?no=${ f.no }">
									<img class="bookImg" src="${ f.photo }" alt=""/>
								</a>
								<div class="caption">
									<h4>
										<a
											href="fightResultBoardContent?no=${ f.no }">${ f.title }</a>
									</h4>
									<h6>승자 : ${ f.winner }</h6>
								</div>
							</div>
						</div>
					</c:if>
				</c:forEach>
				<div style="font-size: 30px; visibility: hidden;"
					class="col-lg-12 col-md-12 col-sm-12 col-xs-12">0</div>
			</div>
		</div>

	</div>





</body>
</html>
