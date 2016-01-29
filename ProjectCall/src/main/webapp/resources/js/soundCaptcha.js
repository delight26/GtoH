/*---------------------------------------------------------------------------------------------
@ 파일 이름: soundCaptcha.js
@ 파일 설명: 모바일 브라우저별 css 적용
@ 작성자: 정현철(created by 전미경)
  ---------------------------------------------------------------------------------------------*/

//function clearAudio(parentNode) {
//  if(playTimer != null) {
//    clearTimeout(playTimer);
//  }
//  
//  var targetAudio = parentNode.getElementsByTagName('EMBED');
//  if (targetAudio != null && targetAudio.length > 0) {
//    targetAudio[0].stop();
//    parentNode.removeChild(targetAudio[0]);
//    return;
//  }
//
//  targetAudio = parentNode.getElementsByTagName('AUDIO');
//  if (targetAudio != null && targetAudio.length > 0) {
//    parentNode.removeChild(targetAudio[0]);
//  }
//}

var playTimer = null;
function clearAudio(parentNode) {
	if (playTimer != null) {
		clearTimeout(playTimer);
		playTimer = null;
	}

	var targetAudio = parentNode.getElementsByTagName('EMBED');
	if (targetAudio != null && targetAudio.length > 0) {
		targetAudio[0].stop();
		parentNode.removeChild(targetAudio[0]);
		return;
	}

	targetAudio = parentNode.getElementsByTagName('AUDIO');
	if (targetAudio != null && targetAudio.length > 0) {
		parentNode.removeChild(targetAudio[0]);
	}
}

function changeCaptchaMode(locale) {
  var toggle = function(div, legend) {
    if (div.style.display == 'none') {
      div.style.display = 'block';
      legend.style.display = 'block';
    } else {
      div.style.display = 'none';
      legend.style.display = 'none';
    }
  };

  var imageDiv = document.getElementById('image_captcha_div');
  var soundDiv = document.getElementById('sound_captcha_div');
  toggle(imageDiv, document.getElementById('captcha_image_legend'));
  toggle(soundDiv, document.getElementById('captcha_sound_legend'));

   var isImageMode = (imageDiv.style.display == "block");
	if (isImageMode) {
		  document.getElementById('captcha_type').value = "image";
		  clearAudio(document.getElementById('play_audio'));
		  try {document.getElementById('aReCaptcha').focus();}catch(e){}
	} else {
		  document.getElementById('captcha_type').value = "sound";
		  playSoundCaptcha(locale);
		  try {document.getElementById('play_audio').focus();}catch(e){}
	}
}

//var playTimer = null;                                                                                                                 

//function playSoundCaptcha() { 
//  var delay = 4000;
//  var playerArea = $('play_audio');                                                                                                    
//
//  clearAudio(playerArea);                                                                                                              
//
//  var key = $('chptchakey').value;
//  var wavURL = "/login/soundcaptcha/soundCaptcha.wav?key=" + key + "&replay=" + new Date().valueOf();                                  
//  try {
//    var audio = new Audio();                                                                                                           
//    if (!audio.canPlayType('audio/wav')) {                                                                                             
//      throw new Error('');
//    }
//    audio = new Audio(wavURL);                                                                                                         
//    playTimer = setTimeout(function(){                                                                                                 
//        playerArea.appendChild(audio);                                                                                                 
//        audio.play();
//      }, delay);
//  } catch(e) {                                                                                                                         
//    playTimer = setTimeout(function() {
//      var audio = document.createElement('EMBED');                                                                                     
//      audio.setAttribute('src', wavURL);                                                                                               
//      audio.setAttribute('hidden', true);
//      audio.setAttribute('autostart', true);                                                                                           
//      audio.setAttribute('showstatusbar', false);                                                                                      
//      audio.setAttribute('showcontrols', false);                                                                                       
//      audio.setAttribute('showaudiocontrols', false);                                                                                  
//      audio.setAttribute('showtracker', false);                                                                                        
//      playerArea.appendChild(audio);                                                                                                   
//    }, delay);
//  }                                                                                                                                    
//}    
var soundDelay = 4000;
function playSoundCaptcha(locale){
	var key = document.getElementById('chptchakey').value;
	var waveURL = "";
	if (locale == "en_US" || locale == "zh-Hans_CN" || locale == "zh-Hant_TW") {
		waveURL = "/login/soundcaptcha/soundCaptchaEn.wav?key=" + key + "&replay=" + new Date().valueOf();
	} else {
		waveURL = "/login/soundcaptcha/soundCaptcha.wav?key=" + key + "&replay=" + new Date().valueOf();
	}

	var playerArea = document.getElementById('play_audio');
	clearAudio(playerArea);

	try {
	    /*  var audio = new Audio("");
        var playable = false;
        if (audio.canPlayType('audio/wav')) {
            playable = true;
        } else if (!audio.canPlayType('audio/x-wav')) {
            playable = true;
        } else if (!audio.canPlayType('audio/wave')) {
            playable = true;
        }
        
        if (playable == false) {
	    	if (locale == "en_US") {
	    		alert("브라우저에서 wav 재생이 지원되지 않습니다. 다운로드를 이용해주세요.");
	    	} else {
	    		alert("Wav playback is not supported on your browser. Please use the download.");
	    	}
	    	
            throw new Error('');
        } */

        var audio = new Audio("");
        if (!audio.canPlayType('audio/wav')) {
            throw new Error('');
        }
	  	    

	    audio.style.width="0px";
	    audio.style.height="0px";
		
		audio = new Audio(waveURL);
		if (soundDelay === 0) {
			playerArea.appendChild(audio);
			audio.play();
		} else {
			playTimer = setTimeout(function() {
				playerArea.appendChild(audio);
				audio.play();
			}, soundDelay);
		}
	} catch(e) {
		if (soundDelay === 0) {
			var audio = document.createElement('EMBED');
			audio.setAttribute('src', waveURL);
			audio.setAttribute('hidden', true);
			audio.setAttribute('autostart', true);
			playerArea.appendChild(audio);
		} else {
			playTimer = setTimeout(function() {
				var audio = document.createElement('EMBED');
				audio.setAttribute('src', waveURL);
				audio.setAttribute('hidden', true);
				audio.setAttribute('autostart', true);
				audio.setAttribute('showstatusbar', false);
				audio.setAttribute('showcontrols', false);
				audio.setAttribute('showaudiocontrols', false);
				audio.setAttribute('showtracker', false);
				playerArea.appendChild(audio);
			}, soundDelay);
		}
	}
}

function addCaptchaRefreshKeyEvent() {                                                                                                 
  var refreshHotKey = function(e) {                                                                                                    
    var event = e || window.event;                                                                                                     
    var codeR = 82;                                                                                                                    
    if (event.ctrlKey && event.altKey && event.keyCode == codeR) {                                                                     
      if (document.getElementById('captcha_type').value == "image") {                                                                                        
        reCaptcha(); 
      } else {
        playSoundCaptcha();
      }
    }
  };

  var bodyElement = document.getElementsByTagName('body')[0];                                                                          
  if (bodyElement.attachEvent) {                                                                                                       
    bodyElement.attachEvent("onkeydown", refreshHotKey);                                                                               
  } else if (bodyElement.addEventListener) {
    bodyElement.addEventListener("keydown", refreshHotKey);
  }
}

function addDownloadSoundEvent() {
	document.getElementById('captcha_download').onclick = function() {
    clearAudio(document.getElementById('play_audio'));
    return true;
  }
}