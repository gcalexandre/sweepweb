window.onload = function() {

	$("#btnsignup").click(function() {
		var name = $('#name').val();
		var email = $('#email').val();
		var pass = $('#pass').val();
		var passr = $('#passr').val();

		if (pass != passr) {

			alert("Password does not mach.");
			return;

		}
		
		if (name == null){
			alert("Entry 'name' is required.");
			return;
			
		}
		
		if (email == null){
			alert("Entry 'email' is required.");
			return;
			
		}
		
		if (pass == null){
			alert("Entry 'password' is required.");
			return;
			
		}

		$.ajax({
			contentType : "application/json;charset=UTF-8",
			url : "/clientes",
			dataType : "json",
			cache : true,
			type : "POST",
			processData : false,
			data : JSON.stringify({
				nome : name,
				email : email,
				senha : pass,
				tipo : -1
			}),
			statusCode : {
				201 : function(response) {
					alert("Registration success.");
					location.href = "login.html";
				},

				422 : function(response) {
					alert("Email already exits.");
				}
			},
			

		});

	});
}
