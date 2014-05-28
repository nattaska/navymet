<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<script>
    var secondsBeforeExpire = ${pageContext.session.maxInactiveInterval};
    var timeToDecide = 30; // Give client 15 seconds to choose.
    setTimeout(function() {
        alert('Your session will timeout in ' + timeToDecide + ' seconds!.')
    }, (secondsBeforeExpire - timeToDecide) * 1000);
</script>

<center>
	<font size="30">
	Access Denied
	</font>
</center>