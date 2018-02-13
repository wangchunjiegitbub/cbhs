(function($) { 
//自定义validator.js
//扩展easyui表单的验证
$.extend($.fn.validatebox.defaults.rules, {
 //验证汉字
 CHS: {
  validator: function (value) {
   return /^[\u0391-\uFFE5]+$/.test(value);
  },
  message: '只能输入汉字'
 },

 //移动手机号码验证
 mobile: {//value值为文本框中的值
  validator: function (value) {
   var reg = /^1[3|4|5|8|9]\d{9}$/;
   return reg.test(value);
  },
  message: '输入手机号码格式不准确.'
 },
 //国内邮编验证
 zipcode: {
  validator: function (value) {
   var reg = /^[1-9]\d{5}$/;
   return reg.test(value);
  },
  message: '邮编必须是非0开始的6位数字.'
 },
 //验证数字位数 
 intcount: {//param的值为[]中值
	 validator: function (value, param) {
		   var reg =new RegExp("^\\d{0," + param + "}$"); // reg为/^\d{5}$/gim
		   //alert(reg);
		   //var reg = /^\d{5}$/;
		   if (!reg.test(value)) {
			   $.fn.validatebox.defaults.rules.intcount.message = '必须输入' + param + '位以内的数字.';
			   return false;   
		   }else {
			     return true;
		   }
		   
		  },
		  message: ''
 },
 //验证小数 
 validateFloat: {//param的值为[]中值
	 validator: function (value) {
		var patten = /^-?\d+\.?\d{0,2}$/;  
		return patten.test(value);
		},
		message: '请输入整数或二位小数'
 },
 //日期验证yyyy-MM-dd
 datevalue: {
  validator: function (value) {
 	  var date = value;
      var result = date.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
      
      if (result == null)
          return false;
      var d = new Date(result[1], result[3] - 1, result[4]);
      return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[4]);
	  
  },
  message: '日期格式不正确!'
 },
//日期时间验证yyyy-MM-dd HH:mm
 datetimevalue: {
   validator: function (value) {
 	  
 	  var date = value;
       var result = date.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})(\s{1})(\d{1,2})(:{1})(\d{1,2})$/);
       if (result == null)
           return false;
       var d = new Date(result[1], result[3] - 1, result[4], result[6],result[8]);
       return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[4] && d.getHours() == result[6] && d.getMinutes() == result[8] ) ;
 	  
   },
   message: '日期格式不正确!'
  },
  
//身份证验证
  IdentityCodeValid: {
    validator: function (value) {
    	var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
        var tip = "";
        var pass= true;
        
        if(!value || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(value)){
            tip = "身份证号格式错误";
            pass = false;
        }
        
       else if(!city[value.substr(0,2)]){
            tip = "地址编码错误";
            pass = false;
        }
        else{
            //18位身份证需要验证最后一位校验位
            if(value.length == 18){
                value = value.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                //校验位
                var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++)
                {
                    ai = value[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];
                if(parity[sum % 11] != value[17]){
                    tip = "校验位错误";
                    pass =false;
                }
            }
        }
        //if(!pass) alert(tip);
        return pass;
    	  
    },
    message: '身份证格式不正确!'
   },
   zeroTest: {
	   validator: function (value) {
		   var reg = /^0+$|^0+\d*$|^0.0*$/;
		   return !reg.test(value);
	   },
	   message: '数字不能用0开头'
	  },
  
 //车次验证(只能包括 _ 数字 字母) 
 account: {//param的值为[]中值
  validator: function (value, param) {
   if (value.length < param[0] || value.length > param[1]) {
    $.fn.validatebox.defaults.rules.account.message = '长度必须在' + param[0] + '至' + param[1] + '范围';
    return false;
   } else {
    if (!/^[0-9a-zA-Z]*$/.test(value)) {
    	
     $.fn.validatebox.defaults.rules.account.message = '只能输入数字、字母组成.';
     return false;
    } else {
     return true;
    }
   }
  }, message: ''
 }
});
})(jQuery);
