(function($){
    $.fn.serializeJson=function(){
        var serializeObj={};
        var array=this.serializeArray();
        var str=this.serialize();
        var fieldName = "";
        $(array).each(function(){
            if(!this.name || !this.value){
                return true;
            }
            if(serializeObj[this.name]){
                if($.isArray(serializeObj[this.name])){
                    serializeObj[this.name].push(this.value);
                }else{
                    serializeObj[this.name]=[serializeObj[this.name],this.value];
                }
            }else if(this.name.indexOf('[') == -1){
                serializeObj[this.name]=this.value;
            }else{
                var arrName = this.name.substr(0, this.name.indexOf('['));
                var index = this.name.substring(this.name.indexOf('[') + 1, this.name.indexOf('.') - 1);
                fieldName = this.name.substr(this.name.indexOf('.') + 1, this.name.length);
                if(serializeObj[arrName]){
                    if(serializeObj[arrName].length > index){
                        var obj = serializeObj[arrName][index];
                        obj[fieldName] = this.value;
                        serializeObj[arrName][index] = obj;
                    }else{
                        var obj = {};
                        obj[fieldName] = this.value;
                        serializeObj[arrName].push(obj);
                    }
                }else{
                    var obj = {};
                    obj[fieldName] = this.value;
                    serializeObj[arrName] = [obj];
                }
            }
        });
        return serializeObj;
    };

    $.getJson = function(url, back){
        getAjax(url, {}, back);
    };
    $.getJsonParam = function(url, param, back){
        getAjax(url, param, back);
    };
    $.postForm = function(url, fromId, back){
        var json = $("#"+fromId).serializeJson();
        postAjax(url, json, back);
    }
    $.postJson = function(url, json, back){
        postAjax(url, json, back);
    }
    $.delJson = function(url, back){
        delAjax(url, {}, back);
    }

    function getAjax(url, param, back){
        $.ajax({
            url : url,
            type : "get",
            data : param,
            success : function(res) {
                if(!res || (res && res.code === 1)){
                    back(res.data);
                } else {
                    layer.msg(res.msg);
                }
            },
            error : function(xhr,textStatus,errorThrown){
                permissionInvalid(xhr);
            }
        });
    }
    function postAjax(url, json, back){
        $.ajax({
            url : url,
            type : "post",
            contentType:'application/json',
            data : JSON.stringify(json),
            success : function(res) {
                if(res.code === 1){
                    back(res.data);
                } else {
                    layer.msg(res.msg);
                }
            },
            error : function(xhr,textStatus,errorThrown){
                permissionInvalid(xhr);
            }
        });
    }
    function delAjax(url, param, back){
        $.ajax({
            url : url,
            type : "delete",
            data : param,
            success : function(res) {
                if(!res || (res && res.code === 1)){
                    back(res.data);
                } else {
                    layer.msg(res.msg);
                }
            },
            error : function(xhr,textStatus,errorThrown){
                permissionInvalid(xhr);
            }
        });
    }
    function permissionInvalid(xhr){
        if (xhr.status == 401) {
            if(self != top) {
                parent.window.location.href = "/";
            }else{
                window.location.href = "/";
            }
        }
    }

    //备份jquery的ajax方法
    var _ajax=$.ajax;

    //重写jquery的ajax方法
    $.ajax = function(opt){
        //备份opt中error和success方法
        var fn = {
            error:function(XMLHttpRequest, textStatus, errorThrown){},
            success:function(data, textStatus){}
        }
        if(opt.error){
            fn.error=opt.error;
        }
        if(opt.success){
            fn.success=opt.success;
        }
        var _opt = $.extend(opt,{
            beforeSend:function(xhr){
                xhr.setRequestHeader("Authorization", localStorage.getItem("authorization")) ;
            },
            error:function(response, textStatus, errorThrown){
                if(response.status == 401){
                    permissionInvalid(response);
                }else{
                    fn.error(XMLHttpRequest, textStatus, errorThrown);
                }
            },
            success:function(data, textStatus){
                fn.success(data, textStatus);
            }
        });
        _ajax(_opt);
    };

})(jQuery);