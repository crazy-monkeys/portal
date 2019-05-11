function createXMLHttpRequest(){
	var xmlHttpRequest = null;
	if(window.XMLHttpRequest){ //Mozilla
		xmlHttpRequest = new XMLHttpRequest();
		if (xmlHttpRequest.overrideMimeType) {//Set MIME type
			xmlHttpRequest.overrideMimeType("text/xml");
		}
	} else if(window.ActiveXObject){ //IE
		try{
			xmlHttpRequest = new ActiveXObject( "Msxm12.XMLHTTP" );
		}catch(e){
			try{
				xmlHttpRequest = new ActiveXObject( "Microsoft.XMLHTTP" );
			}catch(e){}
		}
	}
	return xmlHttpRequest;
}

function trim(s) {
	var tempStr;
	tempStr = s.replace(/\s+$/g,'');
	tempStr = tempStr.replace(/^\s+/g,'');
	return tempStr;
}
function isEmpty(s){
	return ((s == null) || (trim(s).length == 0))
}

function isValidComb(newPwd){
	var counter = 0;
	var letters = /[a-zA-Z]/;
	if (letters.exec(newPwd)) counter++;
	var number = /[0-9]/;
	if(number.exec(newPwd)) counter++;
	var spec = /[~`\!\?,\.\:;\-_\'\"\(\)\{\}\[\]\/@#$%\^&*+\|\\\=<>]/; 
	if(spec.exec(newPwd)) counter++;
	return counter == 3;
}

function checkPwdComb(newPwd){
	var letters = /[a-zA-Z]/;
	if (!letters.exec(newPwd)) {
		return 1;
	}  
	var number = /[0-9]/;
	if(!number.exec(newPwd)) {
		return 2;
	}
	var spec = /[~`\!\?,\.\:;\-_\'\"\(\)\{\}\[\]\/@#$%\^&*+\|\\\=<>]/; 
	if(!spec.exec(newPwd)) {
		return 3;
	}
	return 0;
}

function toAgree(obj){
	if(obj.checked){
		document.getElementById("regbtn").disabled=false; 	
		var errMsgNotAgreed = document.getElementById("errMsgNotAgreed");
		if(null != errMsgNotAgreed) {
			var content =$.trim(errMsgNotAgreed.innerText);
			if(null != content && "" != content){
				errMsgNotAgreed.style.visibility="hidden";
				errMsgNotAgreed.innerHTML = "";
			}
		}
	}else{
		document.getElementById("regbtn").disabled=true;
	}
}

function showTip(id) {
	var e = document.getElementById(id);
	e.style.display = "block";
}

function hideTip(id) {
	var e = document.getElementById(id);
	e.style.display = "none";
}

function trim( str ) {
	str = str.match(/\S+(?:\s+\S+)*/);
	return str ? str[0] : '';
}

function updateURLParameter(url, param, paramVal){
	var newAdditionalURL = "";
	var tempArray = url.split("?");
	var baseURL = tempArray[0];
	var additionalURL = tempArray[1];
	var temp = "";
	if (additionalURL) {
		tempArray = additionalURL.split("&");
		for (i=0; i<tempArray.length; i++){
			if(tempArray[i].split('=')[0] != param){
				newAdditionalURL += temp + tempArray[i];
				temp = "&";
			}
		}
	}
	
	var rows_txt = temp + "" + param + "=" + paramVal;
	return baseURL + "?" + newAdditionalURL + rows_txt;
}

function changeLanguage(lang) {
	location.href = updateURLParameter(location.href, "nls", lang);;
}
