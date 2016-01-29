  function idcheck(){
      var id = document.getElementById("id").value;
      var data = "id="+id;
      if(id.length<10){
          document.getElementById("idcheckLayer").innerHTML = "<font color=red>10자리 이상 입력하세요.</font>";     
      }else{
           // 1. XMLHttpReqeust 객체 생성
          createXhr();
          // 2. 이벤트 핸들러 등록
          xhr.onreadystatechange = callbackCheckId;  // callback 함수를 등록
          // 3. open()를 통해 요청관련 설정을 설정
          //jsp 구현 완료
          //xhr.open("POST", "resources/ajax/idCheck.jsp", true);
          
          xhr.open("POST", "idCheck", true);
          
          // 4. Header에 contentType 지정 - post
          xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
          // 5. send()를 통해 요청
          xhr.send(data); // 요청 쿼리를 보내준다. 
          
      }
  }
  function callbackCheckId(){
      if(xhr.readyState==4){      // 응답을 다 받은 경우
          if(xhr.status == 200){  // 응답코드가 200인 경우 - 정상인 경우
              var resTxt = xhr.responseText;  // 서버가 보낸 응답 text
              //alert(resTxt);
              document.getElementById("idcheckLayer").innerHTML = resTxt;         
              /*document.getElementById("check").innerHTML = resTxt;*/ 
          }else{
              alert("요청 처리가 정상적으로 되지 않았습니다.\n"+xhr.status);
          }
      }
  }