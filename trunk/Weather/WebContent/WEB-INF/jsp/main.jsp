<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<script type="text/javascript" src="${contextPath}/scripts/sq/main.js"></script>

<script>
    var secondsBeforeExpire = ${pageContext.session.maxInactiveInterval};
    var timeToDecide = 30; // Give client 15 seconds to choose.
    setTimeout(function() {
    	var curTime = new Date();
    	alert('Alert on ' + curTime.toLocaleTimeString() + ', Your session will timeout in ' + timeToDecide + ' seconds!.')
    }, (secondsBeforeExpire - timeToDecide) * 1000);
</script>
<br>
<div id="linkPanel"></div>
<br><br>
<div id="newPanel"></div>
</div>
