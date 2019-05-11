﻿//modify by cwx341547 国际化修改
var countryCodeList = countryCodeListJson;
function isDerctionRTL(language){ 
	return ("ar_SA"==language||"fa_IR"==language||"ar"==language||"fa"==language);
}
function buildOption(countryCode,language) {
	var codeAndCountry = countryCode.country + " (" + countryCode.code + ")";
	var codeAndCountryr = countryCode.country + " (" + countryCode.code + ")";
	if(isDerctionRTL(language)){
		var code = countryCode.code.substring(1)+"+" ;
		codeAndCountryr = countryCode.country + " (" + code + ")";
	}
	else{
		codeAndCountryr = codeAndCountry;
	}
	return new Option(codeAndCountryr,codeAndCountry);;
}

function buildOptions(element,language) {
	for(var i = 0; i < countryCodeList.length; ++i) {
		element.options[element.options.length] = buildOption(countryCodeList[i],language);
	}
	var countryOther = ssoPro.message("js.pro.country.code.option.other")
	element.options[element.options.length] = new Option(countryOther,countryOther);
}

function indexCountryCode(code, name) {
	var i = 0;
	for(;i < countryCodeList.length;++i) {
		if(countryCodeList[i].code == code) {
			if(name != '' && countryCodeList[i].resourceKey == name){
				break;
			}else if(name == ''){
                break;
            }
		}
	}
	return i+1;
}

function countryCodeOfCountry(country,language) {
	var countryName = country.split(" (+")[0];
		for(var i = 0;i < countryCodeList.length;++i) {
			if(countryCodeList[i].country == countryName) {
				return countryCodeList[i].code;
			}
		}
	return "";
}
function cleanCountryCodeMsgForPC(){
	var countryCode =document.getElementById("registerVO.countryCode").value;
	if(countryCode == '') {
		document.getElementById("spancountrycode").innerHTML = "";
	} else {
		document.getElementById("spancountrycode").innerHTML = 
			'<img id="correctips" src="images/icons_right.png" border="0" align="absmiddle"/>';
    }
}
function cleanCountryCodeMsgForM() {
	var countryCode =document.getElementById("countryCode").value;
	var tip = document.getElementById("country-code-info");
	tip.style.display = 'none';
	tip.innerHTML = "";
}