'use strict';

function login() {
	
	var userElement = document.getElementById('email');
	var passwordElement = document.getElementById('senha');
	var email = userElement.value;
	var senha = passwordElement.value;

$.ajax({
    type: "POST",
    url: "/login",
    processData: false, 
    contentType: 'application/json',
    data: JSON.stringify({email: email, senha: senha}),
   

    success: function (data, textStatus, request, response) {
    	var tok = request.getResponseHeader('Authorization').substring(7);
    	sessionStorage.setItem("token", tok);
    	sessionStorage.setItem("email", email);
       	sessionStorage.setItem("pagecount", 0);
        location.href = "indexAuth.html";
    
        
    },
    error: function (request, textStatus, errorThrown, response) {
        alert('Incorrect password or email: Try again.');
    }
  
});

}

function recover() {
	var userElement = document.getElementById('email');
	var email = userElement.value;
	
	$.ajax({
	    type: "POST",
	    url: "/auth/forgot",
	    processData: false, 
	    contentType: 'application/json',
	    data: JSON.stringify({email: email}),
	
});}



$("#btn-login").click(function (){
	login();
});

$("#btn-recover").click(function (){
	recover();
	alert('Recovery password sent. Check email of sweep.bioinfo.ufpr @ gmail.com');
	location.href = "login.html";
	
});