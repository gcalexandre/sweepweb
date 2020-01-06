'use strict';


var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document
		.querySelector('#singleFileUploadSuccess');
var progressBar = document.getElementById("progress"), loadBtn = document
.getElementById("bnt-send"), display = document
.getElementById("display");



function uploadSingleFile(file) {
	var formData = new FormData();
	formData.append("file", file);

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/uploadFile");
	
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
			singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p>"
				   +"<p>DownloadUrl : <a id=\"filehref\" href='"
					+ response.fileDownloadUri
					+ "' target='_blank'>"
					+ response.fileDownloadUri +  
					+"</a></p>";
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
	$("#btnprocess").click(
			function() {
				var idfile = $("#filehref").attr("href").split("/")[4];
				var mask = $('#maskList').val();
				var projection = $('#projectionList').val();
				var type = $('#typelist').val();
				var jobName = $('#jobName').val();
				var generateTree = $('#generateTree').val();
				var distanceTree = $('#distanceList').val();
			

				var paramrequest = "process?" + "fileId=" + idfile + "&"
						+ "mask=" + mask + "&" + "projection=" + projection
						+ "&" + "type=" + type + "&" + "jobName=" + jobName
						+ "&" + "generateTree=" + generateTree + "&"
						+ "distanceTree=" + distanceTree;
				
				$("#processing").show();
				$.get(paramrequest, function(data, status) {
					
					$("#processing").hide();
					var responseData = JSON.parse(data);
					count++;

					var html = $('#resultProcess').html();

					if (responseData.fileDownloadURI3 === undefined
							&& responseData.fileDownloadURI4 === undefined) {

						$("#resultProcess").html(
								html + '<br>' +'<br>'
										+ "Job Name: "
										+ responseData.jobName + "   "
										+ '<br>'
										+ "Result " + count + " => " + "Mask: "
										+ responseData.mask + "   "
										+ "Projection: "
										+ responseData.projection + "   "
										+ "Type: " + responseData.type + '<br>'
										+ '<a  href="'
										+ responseData.fileDownloadURI
										+ '"> SWeeP-LHDV</a>'
										+ '&nbsp;&nbsp;<a  href="'
										+ responseData.fileDownloadURI2
										+ '"> SWeeP-Headers </a>');

					}

					
					if (responseData.fileDownloadURI3 === undefined
							&& responseData.fileDownloadURI4 !== undefined) {

						$("#resultProcess").html(
								html + '<br>' + '<br>'
										+ "Job Name: "
										+ responseData.jobName + "   "
										+ '<br>'
										+ "Result " + count + " => " + "Mask: "
										+ responseData.mask + "   "
										+ "Projection: "
										+ responseData.projection + "   "
										+ "Type: " + responseData.type + '<br>'
										+ '<a  href="'
										+ responseData.fileDownloadURI
										+ '"> SWeeP-LHDV</a>'
										+ '&nbsp;&nbsp;<a  href="'
										+ responseData.fileDownloadURI2
										+ '"> SWeeP-Headers </a>'
										+ '&nbsp;&nbsp;<a  href="'
										+ responseData.fileDownloadURI4
										+ '"> SWeeP-Phylomat </a>');

					}

					
				});
			});

}
$(document).ready(function() {

	$("#parameters-content-btn").click(function() {

		$("#parameters-content-div").slideToggle();
		valor = $("#parameters-content-btn").text();

		if (valor == "+") {
			valor = "-";
		} else {
			valor = "+"
		}
		$("#parameters-content-btn").text(valor);

	});
});