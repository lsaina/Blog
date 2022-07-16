<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileupload</title>
<style>
	.upSelectBtn{
		width: 100px;
		height: 50px;
		line-height: 50px;
		text-align: center; 
		background-color: #333;
		color: #fff;
		border: 1px solid #333;
	}
	.upSelectBtn:hover{
		background-color: #fff;
		color: #333;
		cursor: pointer;
	}
</style>
</head>
<body>
	<form action="/uploadTest.do" method="post" enctype="multipart/form-data">
		<input type="file" name="upfile" id="upSelect" multiple style="display:none;">
		<label for="upSelect">
			<div class="upSelectBtn">업로드하기</div>
		</label>
		<br><br>
		<input type="submit" value="보내기">
	</form>
</body>
</html>