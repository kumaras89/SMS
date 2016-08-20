
$(function() {
    
    $('#submit').click(function(){
    var response = "";
	var base_url = 'http://sms.rgmodern.org/authenticate';
	 var userName = $('#username').val();
     var password = $('#password').val();
    var dataV = JSON.stringify({ "username": userName, "password" : password }) ;
    $.ajax({
        type: "POST", 
        url: base_url, 
        data: dataV,
        success: function(response)
        {

             //alert(response);
			 var x = JSON.parse(JSON.stringify(response)); 
			 
			 var fname = x.user.firstName ;
			  var lname = x.user.lastName ;
			var x = document.cookie;
               
			localStorage.lastname = fname ;
			localStorage.firstname = lname ;
            window.location = "intextemp.html"
        },
        error : function(jqXHR, textStatus, errorThrown) {
      alert("Please Enter Username & Password corrtectly..");
      },
        dataType: "json"//set to JSON    
    })    
});

});
