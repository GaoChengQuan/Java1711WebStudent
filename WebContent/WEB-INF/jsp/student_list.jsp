<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.min.css"/>
	</head>
	<body>
		<!--导航 begin -->
		<nav class="navbar navbar-default">
		  <div class="container">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="#">教务管理系统</a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li class="active"><a href="student_list.html"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;学生管理 <span class="sr-only">(current)</span></a></li>
		        <li><a href="banji_list.html"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;班级管理</a></li>
		        <li><a href="#"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;课程管理</a></li>
		        <li><a href="#"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span>&nbsp;&nbsp;教务管理</a></li>
		      </ul>
		      
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="#"><span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp;&nbsp;退出</a></li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
		<!--导航 end -->
		
		<!-- 内容部分 begin-->
		<div class="container">
			<div class="row">
				<!-- 左边部分 begin-->
				<div class="col-md-2">
					<div class="list-group">
					  <a href="#" class="list-group-item active">
					    学生列表
					  </a>
					  <a href="${pageContext.request.contextPath}/student?method=getStudentAdd" class="list-group-item">学生添加</a>
					</div>
				</div>
				<!-- 左边部分 end-->
				<!-- 右边部分 begin-->
				<div class="col-md-10">
					<table class="table table-hover">
				      <thead>
				        <tr>
				          <th>ID</th>
				          <th>姓名</th>
				          <th>年龄</th>
				          <th>性别</th>
				          <th>地址</th>
				        </tr>
				      </thead>
				      <tbody>
				      	<c:forEach items="${list}" var="student">
					        <tr>
					          <td>${student.id}</td>
					          <td>${student.name}</td>
					          <td>${student.age}</td>
					          <td>${student.gender}</td>
					          <td>${student.address}</td>
					        </tr>
				      	</c:forEach>
				      </tbody>
				    </table>
				</div>
				<!-- 右边部分 end-->
			</div>
			
		</div>
		<!-- 内容部分 end-->
	</body>
</html>