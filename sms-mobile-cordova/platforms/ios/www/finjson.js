function finjson(){
alert("finejson")
var accounting = [];
var educationDetails = {};

for(var i in someData) {

    var item = someData[i];

   accounting.push({ 
        "examPassed" : item.examPassed,
        "instituteName"  : item.instituteName,
        "groupName"       : item.groupName 
    });
}

alert(educationDetails.accounting = accounting);
}