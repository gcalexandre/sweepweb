'use strict';

var str = sessionStorage.getItem("email");
conteudo.innerHTML = str;
var jwt = sessionStorage.getItem("token");

function logout() {
		
		sessionStorage.setItem("token", null);
		sessionStorage.setItem("email", null);
		location.href = "index.html";
		
		
}	


