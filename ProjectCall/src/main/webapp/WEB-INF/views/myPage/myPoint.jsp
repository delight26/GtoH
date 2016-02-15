<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#pointTb {
	width: 450px;
	text-align: center;
	margin-left: 50px;
}
#pointTb td {margin: 50px;}
.point {
	font-size: 38px;
}
.tdTop {
	border: 7px solid lightgray;
	border-radius: 8px 8px 0 0;
	border-bottom: 1px solid lightgray;
	font-weight: bold;
	font-size: 20px;
	color: gray;
	padding: 10px 0 10px 0;
}
.tdBottom {
	border: 7px solid lightgray;
	border-radius: 0 0 8px 8px;
	border-top: none;
	padding: 60px 30px;
	background: #f5f6f7;
}
</style>
</head>
<body>
            <div class="modal-header" style="font-size: 25px; background: #E4E3F3; color: #7092BE;border-radius: 8px;">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <span><b>MY POINT</b></span>
            </div>
            <div class="modal-body">
            <table id="pointTb">
            	<tr>
            		<td class="tdTop"><span style="color: #D1B2FF">획득</span> 포인트</td>
            		<td width="20px"></td>
            		<td class="tdTop"><span style="color: #FFA7A7">사용</span> 포인트</td>
            	</tr>
            	<tr>
            		<td class="tdBottom"><b><span class="point">${ loginUser.point }P</span></b></td>
            		<td width="20px"></td>
            		<td class="tdBottom"><b><span class="point" style="color: orange;">-${loginUser.usepoint }P</span></b></td>
            	</tr>
            </table>
            </div>
            <div class="modal-footer">
            <p style="text-align: right; font-size: 18px;">사용 가능한 포인트 : <b><span style="color: red; font-size: 18px">${ loginUser.point - loginUser.usepoint }</span>P</b>&nbsp;&nbsp;&nbsp;</p>
            </div>
</body>
</html>