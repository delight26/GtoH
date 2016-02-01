
var lua_cnt = 0;
var lua_bi = {};
var lua_sDate = new Date();

var ERROR_NO_VALUE = "ERROR_NO_VALUE";

var ERROR_NOT_ENOUGH_LENGTH = "ERROR_NOT_ENOUGH_LENGTH";
var ERROR_AUTH_REQUIRED = "ERROR_AUTH_REQUIRED";
var ERROR_AGREE_REQUIRED = "ERROR_AGREE_REQUIRED";

var ERROR_ID_REQUIRED   = "ERROR_ID_REQUIRED";
var ERROR_PSWD1_REQUIRED = "ERROR_PSWD1_REQUIRED";
var ERROR_PSWD2_REQUIRED = "ERROR_PSWD2_REQUIRED";
var ERROR_NAME_REQUIRED = "ERROR_NAME_REQUIRED";
var ERROR_NAME_FORMAT   = "ERROR_NAME_FORMAT";
var ERROR_DATE_FORMAT   = "ERROR_DATE_FORMAT";
var ERROR_AUTH_REQUIRED = "ERROR_AUTH_REQUIRED";

var INFO_CHECK_POINT_10 = "INFO_CHECK_POINT_10";
var INFO_CHECK_POINT_20 = "INFO_CHECK_POINT_20";
var INFO_CHECK_POINT_30 = "INFO_CHECK_POINT_30";
var INFO_CHECK_POINT_40 = "INFO_CHECK_POINT_40";
var INFO_CHECK_POINT_50 = "INFO_CHECK_POINT_50";
var INFO_CHECK_POINT_60 = "INFO_CHECK_POINT_60";
var INFO_CHECK_POINT_70 = "INFO_CHECK_POINT_70";
var INFO_CHECK_POINT_80 = "INFO_CHECK_POINT_80";
var INFO_CHECK_POINT_90 = "INFO_CHECK_POINT_90";
var INFO_CHECK_POINT_SU = "INFO_CHECK_POINT_SU";
var INFO_CHECK_POINT_SUBMIT = "INFO_CHECK_POINT_SUBMIT";
var INFO_CHECK_POINT_RETRY  = "INFO_CHECK_POINT_RETRY";

var ERROR_NO_VALUE_SEX     = "ERROR_NO_VALUE_SEX";
var ERROR_NO_VALUE_NAME    = "ERROR_NO_VALUE_NAME";
var ERROR_NO_VALUE_YEAR    = "ERROR_NO_VALUE_YEAR";
var ERROR_NO_VALUE_MONTH   = "ERROR_NO_VALUE_MONTH";
var ERROR_NO_VALUE_DAY     = "ERROR_NO_VALUE_DAY";
var ERROR_NO_VALUE_DATE    = "ERROR_NO_VALUE_DATE";
var ERROR_NO_VALUE_PHONENO = "ERROR_NO_VALUE_PHONENO";
var ERROR_NO_VALUE_EMAIL = "ERROR_NO_VALUE_EMAIL";
var ERROR_NO_VALUE_AUTHNO  = "ERROR_NO_VALUE_AUTHNO";
var ERROR_NO_VALUE_AGREE1  = "ERROR_NO_VALUE_AGREE1";
var ERROR_NO_VALUE_CAPTCHA = "ERROR_NO_VALUE_CAPTCHA";

var ERROR_FORMAT_ID    	 = "ERROR_FORMAT_ID";
var ERROR_FORMAT_PSWD1 	 = "ERROR_FORMAT_PSWD1";
var ERROR_FORMAT_PSWD2 	 = "ERROR_FORMAT_PSWD2";
var ERROR_FORMAT_NAME    = "ERROR_FORMAT_NAME";
var ERROR_FORMAT_YEAR    = "ERROR_FORMAT_YEAR";
var ERROR_FORMAT_MONTH   = "ERROR_FORMAT_MONTH";
var ERROR_FORMAT_DAY     = "ERROR_FORMAT_DAY";
var ERROR_FORMAT_DATE    = "ERROR_FORMAT_DATE";
var ERROR_FORMAT_PHONENO = "ERROR_FORMAT_PHONENO";
var ERROR_FORMAT_EMAIL 	 = "ERROR_FORMAT_EMAIL";
var ERROR_FORMAT_AUTHNO  = "ERROR_FORMAT_AUTHNO";

var ERROR_JOIN_SUBMIT	= "ERROR_JOIN_SUBMIT";
var SUCCESS_JOIN_SUBMIT	= "SUCCESS_JOIN_SUBMIT";

function lua_do(si, ai, r, token, doFlag, v) {
	try {
		if(doFlag == true){
			var rs = "/user2/lua.nhn?a=log";
			var index;
			var doc = document;
			var wlt = window.location;
			
			if(lua_cnt < 1){
				lua_getBrowser();
			}
			lua_cnt++;
			lua_getTime();
			lua_setValues(si, ai, r, token, v);
			
			for( var index in lua_bi )
			{
				if( typeof lua_bi[index] != 'function' ) 
					rs += "&" + index + "=" + encodeURIComponent(lua_bi[index]);
			}
			
			
			if (doc.images) {
				var obj = (new Image());
				obj.src = rs;
				obj.onload = function() { obj.onload = null; return; };
			} else {
				doc.write( '<im' + 'g sr' + 'c="' + rs + '" width="1" height="1" border="0" />');
			}
		}
	} catch (e) {
	}
	
}
function lua_do2(si, ai, r, token, doFlag, v) {
	try {
		if(doFlag == true){
			var rs = "/user2/lua.nhn?m=logB";
			var index;
			var doc = document;
			var wlt = window.location;
			
			if(lua_cnt < 1){
				lua_getBrowser();
			}
			lua_cnt++;
			lua_getTime();
			lua_setValues(si, ai, r, token, v);
			
			for( var index in lua_bi )
			{
				if( typeof lua_bi[index] != 'function' ) 
					rs += "&" + index + "=" + encodeURIComponent(lua_bi[index]);
			}
			
			
			if (doc.images) {
				var obj = (new Image());
				obj.src = rs;
				obj.onload = function() { obj.onload = null; return; };
			} else {
				doc.write( '<im' + 'g sr' + 'c="' + rs + '" width="1" height="1" border="0" />');
			}
		}
	} catch (e) {
	}
	
}
function lua_setValues(si, ai, r, token, v){
	lua_bi["si"] = si;
	lua_bi["ai"] = ai;
//		lua_bi["ai"] = ai.callee.toString().match(/function ([^\(]+)/)[1];
	lua_bi["r"] = r;
	lua_bi["token"] = token;
	lua_bi["v"] = v;
}

function lua_getBrowser(){
	lua_getOS();
	lua_getlanguage();
	lua_getCookieEnabled();
	lua_getAgent();
}

function lua_getOS() {
	var lua_os = "";
	try {
		(navigator.platform ? lua_os = navigator.platform : "");
	} catch (e) {
	}
	lua_bi["os"] = lua_os;
}

function lua_getlanguage() {
	var lua_lang = "";
	try {
		(navigator.userLanguage? lua_lang = navigator.userLanguage : (navigator.language)? lua_lang = navigator.language : "");
	} catch (e) {
	}

	lua_bi["ln"] = lua_lang;
}

function lua_getCookieEnabled() {
	lua_bi["ck"] = "";
	try {
		lua_bi["ck"]= navigator.cookieEnabled ? "Y":"N";
	} catch (e) {
	}

}

function lua_getTime() {
	var currentDate = new Date();
	try {
		lua_bi["t1"] = lua_sDate.getTime();
		lua_bi["t2"] = currentDate.getTime();
	} catch (e) {
	}

}

function lua_getAgent() {
	var lua_ua = navigator.userAgent.toLowerCase();
	var lua_browser = "";

	if ( lua_ua.indexOf('msie 7') ) {
		browser = "ie7";
	}else if ( lua_ua.indexOf('msie 8') ) {
			browser = "ie8";
	}else if ( lua_ua.indexOf('msie 9') ) {
		browser = "ie9";
	}else if ( lua_ua.indexOf('msie 10') ) {
		browser = "ie10";
	}else if ( lua_ua.indexOf('msie 11') ) {
		browser = "ie11";
	}else if ( lua_ua.indexOf('ipad') ) {
		browser = "ipad";
	}else if ( lua_ua.indexOf('iphone') ) {
		browser = "iphone";
	}else if ( lua_ua.indexOf('android') ) {
		browser = "android";
	}else {
		browser = "others";
	}
	lua_bi["agent"] = browser;
}




