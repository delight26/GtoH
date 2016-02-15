function priceupdateplus(count) {
	var quentity = $('#quentity' + count).val();
	var price = $('#price' + count).val();
	var result = Number(price) * Number(quentity);
	$('#result' + count).val(result);
	payresultplus(count);
}

function priceupdateminus(count) {
	var quentity = $('#quentity' + count).val();
	var price = $('#price' + count).val();
	var result = Number(price) * Number(quentity);
	$('#result' + count).val(result);
	payresultminus(count);
}

function plus(count) {
	var quentity = $('#quentity' + count).val();
	quentity = Number(quentity) + 1;
	$('#quentity' + count).val(Number(quentity));
	priceupdateplus(count);
}
function minus(count) {
	var quentity = $('#quentity' + count).val();
	if (quentity <= 1) {
		alert('최소 한개 이상은 구매 하셔야 합니다.');
	} else {
		quentity = Number(quentity) - 1;
		$('#quentity' + count).val(Number(quentity));
		priceupdateminus(count);
	}

}

function payresultplus(count) {
	var result = Number($('#payresult').val());
	var i = count;
	var checked = $('#check' + i).is(":checked");
	if (checked == true) {
		result += Number($('#price' + i).val());
		$('#payresult').val(result);
	}

}

function payresultminus(count) {
	var result = Number($('#payresult').val());
	var i = count;
	var checked = $('#check' + i).is(":checked");
	if (checked == true) {
		result -= Number($('#price' + i).val());
		$('#payresult').val(result);
	}
}

$(document).ready(function() {
	var result = Number($('#payresult').val());
	var i = 1;
	$(".table input:checked").each(function() {
		var checked = $('#check' + i).is(":checked");
		if (checked == true) {
			if (isNaN($('#result' + i).val())) {
				i++;
			} else {
				result += Number($('#result' + i).val());
				i++;
			}
		}
	});
	$('#payresult').val(result);
});

function changef(count) {
	var result = Number($('#payresult').val());
	var i = count;
	var checked = $('#check' + i).is(":checked");
	if (checked == true) {
		if (isNaN($('#result' + i).val())) {
		} else {
			result += Number($('#result' + i).val());
			$('#payresult').val(result);
		}
	} else {
		if (isNaN($('#result' + i).val())) {
		} else {
			result -= Number($('#result' + i).val());
			$('#payresult').val(result);
		}
	}
}

function back() {
	history.back();
}

function buysubmit(email){
	if(email==""){
		document.location.href="loginform";
	}else{
		var payresult = Number($("#payresult").val());
/*		alert(payresult);
		alert(typeof payresult);
*/		var point = Number($("#point").val());
/*		alert(point);
		alert(typeof payresult);
*/		if(point >= payresult){
			var check = confirm("정말 구매 하시겠습니까?");
			if(check){
				document.form1.submit();
			}else{
				alert("포인트가 충분하지 않습니다.");
			}	
		}
	}	
}
function cartsubmit(email, page){
	if(email==""){
		$('#myModal').modal({
    		remote : "loginform?page="+page
    		});
	}else{
	document.form1.submit();
	}
}

function del(){
	$('.product').remove();
	$('#payresult').val(0);
	$.ajax({
        url: "cartlistremoveall",
        type:"GET",
        dataType: "text",
        success: function(responseData, statusText, xhr){
        	var result = responseData;
        	if(result==0){
        		alert('장바구니를 모두 비웠습니다.');
        	}else{
        		alert('요청을 처지하지 못하였습니다.');
        	}
        },
        error : function(xhr, statusText, responseData){
           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
        }
     });
} 

function checkdel(){
$(".product input:checked").each(function(){
	var checked = $(this).is(":checked");
	if(checked==true){
	$(this).parents("tr").remove();
	$.ajax({
        url: "cartlistremove",
        type:"GET",
        data:{"pProductCode" : $(this).val()},
        dataType: "text",
        success: function(responseData, statusText, xhr){
        	var result = responseData;
        	if(result==0){
        	}else{
        	}
        },
        error : function(xhr, statusText, responseData){
           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
        }
     });
	}
	});
$('#payresult').val(0);	
}