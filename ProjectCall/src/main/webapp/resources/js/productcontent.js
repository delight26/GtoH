function addcart(prod) {
	var quentity = $('input:text[name=quentity]').val();
	if (quentity == "") {
		alert('구매 수량을 작성하셔야 합니다.');
	} else {
		var url = "addcart?quentity=" + quentity + "&pNo=" + prod;
		var popOption = "width=500, height=500, resizable=no, scrollbars=no, status=no";
		window.open(url, "장바구니", popOption);
	}
}

function plus() {
	var quentity = $('#quentity').val();
	quentity = Number(quentity) + 1;
	$('#quentity').val(Number(quentity));
}

function minus() {
	var quentity = $('#quentity').val();
	if (quentity <= 1) {
		alert('최소 한개 이상은 구매 하셔야 합니다.');
	} else {
		quentity = Number(quentity) - 1;
		$('#quentity').val(Number(quentity));
	}
}

$(document).ready(function() {
	$('#quentity').val(1);
});
