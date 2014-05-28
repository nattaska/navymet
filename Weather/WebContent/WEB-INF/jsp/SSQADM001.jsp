<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<script type="text/javascript" src="${contextPath}/scripts/sq/SSQADM001.js"></script>

<script>
    var secondsBeforeExpire = ${pageContext.session.maxInactiveInterval};
    var timeToDecide = 30; // Give client 15 seconds to choose.
    setTimeout(function() {
        alert('Your session will timeout in ' + timeToDecide + ' seconds!, Please save before session abort.')
    }, (secondsBeforeExpire - timeToDecide) * 1000);
</script>

<br><br><br><br>
<div id="panel" align="center"></div>
