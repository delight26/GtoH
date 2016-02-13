<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
         Modal content
            <div class="modal-header" style="font-size: 25px; background: #E4E3F3; color: #7092BE;border-radius: 8px;">
               <button type="button" class="close">&times;</button>
               <span><b>MY POINT</b></span>
            </div>
            <div class="modal-body">
           	<p> 총 ${ loginUser.point }포인트 획득 후 ${ loginUser.usepoint }포인트 사용 </p>
            </div>
            <div class="modal-footer">
            </div>
</body>
</html>