 function find(){
      var name = document.getElementById("name").value;
      var birthday = document.getElementById("birthday").value;
      var data = "birthday="+birthday + "&name=" + name;
          createXhr();
          xhr.onreadystatechange = callbackFind; 
          xhr.open("POST", "findIdPassAjax", true);
          xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
          xhr.send(data); 
  }
  function callbackFind(){
      if(xhr.readyState==4){      
          if(xhr.status == 200){  
              var resTxt = xhr.responseText;  
              document.getElementById("findCheckLayer").innerHTML = resTxt;     
          }else{
              alert("요청 처리가 정상적으로 되지 않았습니다.\n"+xhr.status);
          }
      }
  }
 