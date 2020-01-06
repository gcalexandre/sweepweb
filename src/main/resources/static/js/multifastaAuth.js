'use strict';

var str = sessionStorage.getItem("email");
conteudo.innerHTML = str;

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document
		.querySelector('#singleFileUploadSuccess');
var progressBar = document.getElementById("progress"), loadBtn = document
		.getElementById("bnt-send"), display = document
		.getElementById("display");


function logout() {
	
	sessionStorage.setItem("token", null);
	sessionStorage.setItem("email", null);
	sessionStorage.clear();
	location.href = "index.html";
	
	
}

function uploadSingleFile(file) {

	var formData = new FormData();
	formData.append("file", file);

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/uploadFileMultifasta", true);
	
	xhr.upload.onprogress = function (e) {
	    if (e.lengthComputable) {
	        progressBar.max = e.total;
	        progressBar.value = e.loaded;
	    }
	}
	xhr.upload.onloadstart = function (e) {
	    progressBar.value = 0;
	}
	xhr.upload.onloadend = function (e) {
	    progressBar.value = e.loaded;
	}
	
	xhr.onload = function() {
		console.log(xhr.responseText);
		var response = JSON.parse(xhr.responseText);
		if (xhr.status == 200) {
			singleFileUploadError.style.display = "none";
			singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a id=\"filehref\" href='"
					+ response.fileDownloadUri
					+ "' target='_blank'>"
					+ response.fileDownloadUri + "</a></p>";
			singleFileUploadSuccess.style.display = "block";
		} else {
			singleFileUploadSuccess.style.display = "none";
			singleFileUploadError.innerHTML = (response && response.message)
					|| "Some Error Occurred";
		}
	}

	xhr.send(formData);
}

singleUploadForm.addEventListener('submit', function(event) {
	var files = singleFileUploadInput.files;
	if (files.length === 0) {
		singleFileUploadError.innerHTML = "Please select a file";
		singleFileUploadError.style.display = "block";
	}
	uploadSingleFile(files[0]);
	event.preventDefault();
}, true);

window.onload = function() {
	var count = 0;
	$("#processing").hide();
	$("#btncreatemultifasta")
			.click(
					function() {
						var idfile = $("#filehref").attr("href").split("/")[4];
						var param1 = $('#typelist').val();
						var jobName = $('#jobName').val();

						var paramrequest = "processMultifasta?" + "fileId="
								+ idfile + "&" + "jobName=" + jobName + "&"
								+ "param1=" + param1;

						$("#processing").show();
						$.get(paramrequest, function(data, status) {
							
							$("#processing").hide();
							var responseData = JSON.parse(data);
							count++;
							var html = $('#resultProcessMultifasta').html();
							$("#resultProcessMultifasta").html(
									html + '<br>' + "Result " + count + " => "
											+ '<a  href="'
											+ responseData.fileDownloadURI
											+ '"> SWeeP_Multifasta </a><br>');

						});
					});

}
