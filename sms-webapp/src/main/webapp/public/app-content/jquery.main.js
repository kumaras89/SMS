$(function(){

    //reset progress bar
    $('#progress').css('width','0');
    $('#progress_text').html('0% Complete');

    //first_step
    $('form').submit(function(){ return false; });
    $('#submit_first').click(function(){
        //remove classes
        $('#first_step input').removeClass('error').removeClass('valid');

        //ckeck if inputs aren't empty
        var fields = $('#first_step input[type=text]');
        var error = 0;
        fields.each(function(){
            var value = $(this).val();
            if( value.length<0) {
                $(this).addClass('error');
                $(this).effect("shake", { times:3 }, 50);
                
                error++;
            } else {
                $(this).addClass('valid');
            }
        });        
        
        if(!error) {
            if( $('#password').val() != $('#cpassword').val() ) {
                    $('#first_step input[type=password]').each(function(){
                        $(this).removeClass('valid').addClass('error');
                        $(this).effect("shake", { times:3 }, 50);
                    });
                    
                    return false;
            } else {   
                //update progress bar
                $('#progress_text').html('20% Complete');
                $('#progress').css('width','80px');
                
                //slide steps
                $('#first_step').slideUp();
                $('#second_step').slideDown();     
            }               
        } else return false;
    });

    $('#submit_back_first').click(function(){
        $('#second_step').slideUp();
        $('#first_step').slideDown();
    });

    $('#submit_back_second').click(function(){
        $('#third_step').slideUp();
        $('#second_step').slideDown();
    });

    $('#submit_back_third').click(function(){
        $('#fourth_step').slideUp();
        $('#third_step').slideDown();
    });

    $('#submit_back_fourth').click(function(){
        $('#fifth_step').slideUp();
        $('#fourth_step').slideDown();
    });

    $('#submit_back_fifth').click(function(){
        $('#sixth_step').slideUp();
        $('#fifth_step').slideDown();
    });

    $('#submit_second').click(function(){
        //remove classes
        $('#second_step input').removeClass('error').removeClass('valid');

        var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;  
        var fields = $('#second_step input[type=text]');
        var error = 0;
        fields.each(function(){
            var value = $(this).val();
            if( value.length<0 || ( $(this).attr('id')=='email' && !emailPattern.test(value) ) ) {
                $(this).addClass('error');
                $(this).effect("shake", { times:3 }, 50);
                
                error++;
            } else {
                $(this).addClass('valid');
            }
        });

        if(!error) {
                //update progress bar
                $('#progress_text').html('40% Complete');
                $('#progress').css('width','160px');
                
                //slide steps
                $('#second_step').slideUp();
                $('#third_step').slideDown();     
        } else return false;

    });


    $('#submit_third').click(function(){
        //update progress bar
        $('#progress_text').html('60% Complete');
        $('#progress').css('width','240px');

        //slide steps
        $('#third_step').slideUp();
        $('#fourth_step').slideDown();            
    });

    $('#submit_fourth').click(function(){
        //update progress bar
        $('#progress_text').html('80% Complete');
        $('#progress').css('width','320px');

        //slide steps
        $('#fourth_step').slideUp();
        $('#fifth_step').slideDown();
    });

    $('#submit_fifth').click(function(){
        //update progress bar
        $('#progress_text').html('100% Complete');
        $('#progress').css('width','400px');

        //slide steps
        $('#fifth_step').slideUp();
        $('#sixth_step').slideDown();
    });
});