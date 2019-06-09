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
})(jQuery);