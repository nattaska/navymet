<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<script type="text/javascript" src="${contextPath}/scripts/sq/login.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="${contextPath}/images/faviconxxx.ico">
<br><br><br><br>
<center>
<form id="logInForm" method="post" action="${pageContext.request.contextPath}/main.htm">
	<table>
		<tr>
			<td>UserName :</td>
			<td colspan="3"><input type="text" id="username" name="username"></td>
		</tr>
		<tr>
			<td>Password  :</td>
			<td colspan="3"><input type="password" id="j_password" name="j_password"></td>
		</tr>
		<tr><td colspan="4"><input type="hidden" id="j_username" name="j_username"></td></tr>
		<tr>
			<td></td>
			<td><div id="submitbtn"></div></td>
			<td><div id="clearbtn"></div></td>
		</tr>
	</table>
</form>
<center>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
<script type="text/javascript">
          
Ext.Msg.alert('Warning','Wrong Username or Password !');
          
</script>
<c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>
</c:if>
