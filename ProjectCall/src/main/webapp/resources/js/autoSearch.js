$(document).ready(function() {
	$("#listBox").hide();
	$("#toId").on("keyup", function(e) {
		var keyword = $("#toId").val();
		var params = "keyword=" + keyword;

		$.ajax({
			type: "POST",
			url: "searchNickName",
			data: params,
			success: function(data, textStatus, xhr) {
				$("#listBox").show()			
					.css("border", "1px solid #a8a8a8")
					.css("border-top", "1px solid #1EB501")
					.css("z-index", "9999");
				
				$("#resultList").html(data);
				$("#resultList li").hover(function() {
					$(this).addClass("hover").css("cursor", "pointer");					
				}, function() {
					$(this).removeClass("hover");
				});
			},
			error: function(xhr, textStatus) {
			}
		});		 
	}).on("blur", function(e) {
		setTimeout(function() {
			$("#listBox").hide();
		}, 150);	
	}).on("focus", function(e) {
		$("#listBox").show();
	});
	
	$("#resultList").on("click", "li.searchList", function(e) {
		$("#toId").val($(this).text());
		$("#listBox").hide();
	});
});