 var xhr;
  function createXhr(){
      if(window.ActiveXObject){   // IE 이전버전
          xhr = new ActiveXObject("Microsoft.XMLHTTP");
      }else{
          xhr = new XMLHttpRequest();
      }
  }
