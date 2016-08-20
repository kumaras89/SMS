function checkitOut() {
  // body...

    alert("Welcome22222")
     var apnumber = $('#apnumber').val();
	 var exam = $('#examPassed').val();
	 var inst = $('#instituteName').val();
     var group = $('#group').val();
     var passing = $('#YEAR').val();
     
     var per2 = $('#percentage').val();
     var per = parseInt(per2);
     var remark = $('#remark').val();
     
      var gender = $('#myRadio').val();
      var marital = $('#marital').val();
 	  var annual = $('#annual').val();
 	  
      var old2 = $('#old').val();
      var old = parseInt(old2);
      var branch = $('#branch').val();
      var branchcode = $('#branchcode').val();
      var makemp = $('#makemp').val();
      var stuname = $('#stuname').val();
      var emailid = $('#emailid').val();
      
      var address1 = $('#address1').val();
      var address2 = $('#address2').val();
      var address3 = $('#address3').val();
      var postalCode2 = $('#postal').val();
      var postalCode = parseInt(postalCode2);
      var taluk = $('#taluk').val();
      var district = $('#district').val();
      
      var fatherOrMotherName = $('#fmname').val();
      var dateOfBirth = $('#dob').val();
  
      var nationality = $('#nation').val();
      var religion = $('#rel2').val();
      var caste = $('#commu').val();
      var casteDescription = $('#casted').val();
      var studentPhoneNumber2 = $('#stunum').val();
      var studentPhoneNumber = parseInt(studentPhoneNumber2);
      var parentPhoneNumber2 = $('#parnum').val();
      var parentPhoneNumber = parseInt(parentPhoneNumber2);
      
      

     var dataV = {
     "educationDetails":[{"examPassed":exam,"instituteName":inst,"groupName":group, "passingYear":passing, "percentageObtained":per, "remark":remark}],  
            "gender":gender,
    		"maritalStatus":marital,
    		"annualIncome":annual,
    		"age":old,
    		"applicationNumber":apnumber,
    		"branch":branch,
    		"marketingEmployee":makemp,
    		"name":stuname,
    		"emailId":emailid, 
    		"address":
    {
        "address1":address1,
        "postalCode":postalCode,
        "address2":address2,
        "address3":address3,
        "taluk":taluk,
        "district":district
        
    },
    "fatherOrMotherName":fatherOrMotherName,
    "dateOfBirth":dateOfBirth,
    "nationality":nationality,
    "religion":religion,
    "caste":caste,
    "casteDescription":casteDescription,
    "studentPhoneNumber":studentPhoneNumber,
    "parentPhoneNumber":parentPhoneNumber,
    "status":"INSERTED",
    "branchCode":branchcode,
    "marketingEmployeeCode":"MT001"
    		
    		};
     alert(dataV);
     var V = JSON.stringify(dataV);
     alert(V);

    	 
$.ajax({


headers: { 
       'Accept': 'application/json',
       'Content-Type': 'application/json',
       'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
   },
 
  method: "POST",
  url: "http://192.168.0.8:8082/scholarshipenrollment",
  data: V,
  dataType: 'json',
  DataServiceVersion: 2.0,
  crossDomain: true,
  async: false,
  
   success: function (data) {
        alert("success")
 
var x = JSON.stringify(data); 
  alert(x);
   
    
      },
      error : function(jqXHR, textStatus, errorThrown) {
      alert("fail")
      } 

  
});
     
}