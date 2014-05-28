<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<script type="text/javascript" src="${contextPath}/scripts/sq/SSQADM004.js"></script>

<script>
    var secondsBeforeExpire = ${pageContext.session.maxInactiveInterval};
    var timeToDecide = 30; // Give client 15 seconds to choose.
    setTimeout(function() {
    	var curTime = new Date();
    	alert('Alert on ' + curTime.toLocaleTimeString() + ', Your session will timeout in ' + timeToDecide + ' seconds!, Please save before session abort.')
    }, (secondsBeforeExpire - timeToDecide) * 1000);
</script>

<br/><br/><br/><br/>
<input type="hidden" id="context" value="${pageContext.request.contextPath}">
<c:if test="${not empty jspRuntimeContent.dynamicScripts}">
<script type="text/javascript">
<c:forEach var="dynamicScript" items="${jspRuntimeContent.dynamicScripts}">
${dynamicScript}
</c:forEach>
</script>
</c:if>
<div id="panel" align="center"></div>
<input type="hidden" id="t1id" />
<input type="hidden" id="projectId" />
