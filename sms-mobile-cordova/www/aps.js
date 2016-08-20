$(function() {
    
    $('#submit').click(function(){
     alert("Welcome");
	 var userName = $('#username').val();
     var password = $('#password').val();
     var dataV = JSON.stringify({ "username": userName, "password" : password }) ;
     alert(dataV);
$.ajax({
  method: "POST",
  url: "http://sms.rgmodern.org/authenticate",
   crossOrigin: true,
  data: dataV,
  dataType: "json",
  //cache :false,
  
  async: false,
   success: function (data) {
        alert("success")
 
var x = JSON.stringify(data); 
  alert(x);
   window.location="intextemp.html"
    
      },
      error : function(jqXHR, textStatus, errorThrown) {
      alert("fail")
      } 

  
});
     
});
 

});