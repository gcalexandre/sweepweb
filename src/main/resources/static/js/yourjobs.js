'use strict';

var jwt = sessionStorage.getItem("token");

var str2 = sessionStorage.getItem("email");
conteudo.innerHTML = str2;

var pageCount = sessionStorage.getItem("pagecount");

function logout() {

	sessionStorage.setItem("token", null);
	sessionStorage.setItem("email", null);
	sessionStorage.clear();
	location.href = "index.html";

}

function navpage(page) {
	sessionStorage.setItem("pagecount", page);
	location.reload();

}

$(document)
		.ready(
				function() {

					$
							.ajax(
									{
										type : "GET",
										url : '/requisitionlist?page='
												+ pageCount,
										ContentType : 'application/json',
										beforeSend : function(xhr) { // Include
											// the
											// bearer
											// token
											// in
											// header
											xhr.setRequestHeader(
													"Authorization", 'Bearer '
															+ jwt);
										}

									})
							.done(
									function(data, textStatus, request,
											response) {
										console.log(data);
										for (var i = 0; i < data["content"].length; i++) {
											var line = data["content"][i];
											var idArq = line["id"];
											var fasta = line["dbfile"]["id"];
											var mask = line["maskListParameter"];
											var instant = line["instante"];
											var jobName = line["jobName"];
											var projec = line["projectionListParameter"];
											var fileName = line["fileName"];
											var typeList = line["typeList"];
											var totalElements = line["totalElements"]

											if (fileName == "SWeeP-Phylomat.tree") {
												var htmlOutPhylomat = '<a href="downloadFileProcess/'
														+ idArq
														+ '">'
														+ fileName
														+ '</a>'
														+ '&nbsp'
														+ '&nbsp'
														+ "||" + '&nbsp';

												$("#content").append(
														htmlOutPhylomat);

											}

											else if (fileName == "SWeeP-LHDV.csv") {
												var htmlOutLHDV = '<a href="downloadFileProcess/'
														+ idArq
														+ '">'
														+ fileName
														+ '</a>'
														+ '&nbsp'
														+ '&nbsp'
														+ "||" + '&nbsp';
												$("#content").append(
														htmlOutLHDV);

											}

											else {

												var htmlOutHeaders = '<a  href="downloadFileProcess/'
														+ idArq
														+ '">'
														+ fileName
														+ '</a>'
														+ '&nbsp'
														+ '&nbsp'
														+ "||" + '&nbsp';
												$("#content").append(
														htmlOutHeaders);

												var htmlOutFasta = '<a href="downloadFile/'
														+ fasta
														+ '">'
														+ "MultiFasta"
														+ '</a>';
													

												$("#content").append(
														htmlOutFasta);

												var deleteAll = '<a href="reqdeletefasta/'
														+ fasta
														+ '"onclick="javascript:return confirm('
														+ "'Attention!! All jobs related to the same MULTIFASTA ID Will be deleted.'"
														+ ");"
														+ '"'
														+ '>'
														+ "[DEL]" + '</a>';

												var htmlOutInformation = '<br>'
														+ '<strong>' + "Mask: "
														+ '</strong>'
														+ mask
														+ "   "
														+ '<strong>'
														+ "Projection: "
														+ '</strong>'
														+ projec
														+ "   "
														+ '<strong>'
														+ "Type: "
														+ '</strong>'
														+ typeList
														+ "   "
														+ '<br>'
														+ '<strong>'
														+ "Job Name: "
														+ '</strong>'
														+ jobName
														+ "   "
														+ '<br>'
														+ '<strong>'
														+ "Date: "
														+ '</strong>'
														+ instant
														+ '<br>'
														+ '<strong>'
														+ "MultiFasta id: "
														+ '</strong>'
														+ fasta
														+ '&nbsp' + '&nbsp';

												$("#content").append(
														htmlOutInformation);
												
												$("#content").append(deleteAll);


												var htmlfinal = '<br>' + '<br>';

												$("#content").append(htmlfinal);

											}
										}

										var pagesView = Math
												.ceil(data["content"].length / 30);

										$("#content").append(htmlfinal);

										for (var x = 0; x < pagesView; x++) {
											var pageRequest = '<a id="pagenumber"  href="#void" onclick="navpage('
													+ x
													+ ");"
													+ '"'
													+ '>'
													+ x
													+ '</a>'
													+ '&nbsp'
													+ "|"
													+ '&nbsp';
											$("#content").append(pageRequest);

										}

									}).fail(function(err) {
								alert('request failed');
							});

				});
