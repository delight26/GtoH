<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<script type="text/javascript">
var message = '${message}';
var returnUrl = '${returnUrl}';
alert(message);
document.location.href = returnUrl;
</script>
</body>
</html>