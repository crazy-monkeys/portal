typeof Aura === "undefined" && (Aura = {});
Aura.StaticResourceMap = {"dropdowncss":{"":1541769924000},"jspdf":{"":1541769924000},"proxy_setup":{"":1551090977000},"lodash":{"":1542371265000},"JMF_Approval_Mapping_Import_Template":{"":1552713676000},"CPRI_PDFJS":{"":1552739728000},"bootstrapcss":{"":1541769924000},"CPRI_Program_Pic":{"":1547905872000},"jqueryuifull":{"":1542132003000},"MBO_Resource":{"":1527265579000},"Util":{"":1542132003000},"jsonFormaterjs":{"":1541769924000},"CPRI_PA_ActiveMember_Style":{"":1552739727000},"jsonFormatercss":{"":1541769924000},"CPRIPolicyCustomerValidator":{"":1543666960000},"SiteSamples":{"":1527235646000},"jquery224":{"":1537534693000},"Clusterize":{"":1552713676000},"jqueryMin":{"":1552739729000},"fileSaver":{"":1542371265000},"globalStyle":{"":1547922290000},"Step_Resource":{"":1542132003000},"dropdownjs":{"":1541769924000},"jspdfCustomfonts":{"":1542132003000},"svgjs":{"":1542371265000},"checkboxIcon":{"":1542132003000},"bootstrapsf":{"":1542132003000},"CPRI_Jquery":{"":1543317493000},"pdfDebug":{"":1541769924000},"Table2Excel":{"":1552739728000},"CPRI_Pic":{"":1542487837000},"XLSX":{"":1547890085000},"hwa":{"":1542132003000},"ExcelJs":{"":1552739728000},"excelCore":{"":1552739729000},"AOStaticResource":{"":1546932315000},"html2canvas":{"":1541769924000},"html2pdf":{"":1542132003000},"CPRIUtil":{"":1543666960000},"PromiseJS":{"":1541769924000},"Validator":{"":1552635570000}};

(function() { 
	function initResourceGVP() {
		 if (!$A.getContext() || !$A.get('$Resource')) { 
			 $A.addValueProvider('$Resource', 
			 { 
				 merge : function() {}, 
				 isStorable : function() { return false; }, 
				 get : function(resource) { 
					 var modStamp, rel, abs, name, ns;
					 var nsDelim = resource.indexOf('__');
					 if (nsDelim >= 0) { ns = resource.substring(0, nsDelim); name = resource.substring(nsDelim + 2); } else { name = resource; }
					 var srMap = Aura.StaticResourceMap[name];
					 modStamp = srMap && srMap[ns = ns || Object.keys(srMap)[0]];
					 if (!modStamp) { return; }
					 rel = $A.get('$SfdcSite.pathPrefix');
					 abs = $A.get('$Absolute.url');
					 return [abs || rel || '', '/resource/', modStamp, '/', ns === '' ? name : ns + '__' + name].join('');
				 } 
			 }); 
		 } 
	 }
if(Aura.frameworkJsReady)initResourceGVP();else{Aura.beforeFrameworkInit=Aura.beforeFrameworkInit||[],Aura.beforeFrameworkInit.push(initResourceGVP)}
})();