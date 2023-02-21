<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.simple.JSONArray"%>
<%
	JSONArray jsArr = (JSONArray)request.getAttribute("jsonArray");
%>
<%=jsArr.toString()%>