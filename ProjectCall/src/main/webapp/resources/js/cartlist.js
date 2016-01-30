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
	document.location.href = "index"
}

function buysubmit(){
	var payresult = $("#payresult").val();
	var point = $("#point").val()
	if(point>=payresult){
	var check = confirm("정말 구매 하시겠습니까?");
	if(check){
	document.form1.submit();
	}
	}else{
		alert("포인트가 충분하지 않습니다.")
	}
}
function cartsubmit(){
	document.form1.submit();
}
