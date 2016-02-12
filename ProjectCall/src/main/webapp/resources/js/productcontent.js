function addcart(prod) {
	var quantity = $('#quantity').val();
	if (quantity == "") {
		alert('구매 수량을 작성하셔야 합니다.');
	} else {
		var url = "addcart?quantity=" + quantity + "&pNo=" + prod;
		var popOption = "width=500, height=500, resizable=no, scrollbars=no, status=no";
		window.open("addcart?quantity=" + quantity + "&pNo=" + prod, "장바구니", popOption);
	}
}

function plus() {
	var quantity = $('#quantity').val();
	quantity = Number(quantity) + 1;
	$('#quantity').val(Number(quantity));
}

function minus() {
	var quantity = $('#quantity').val();
	if (quantity <= 1) {
		alert('최소 한개 이상은 구매 하셔야 합니다.');
	} else {
		quantity = Number(quantity) - 1;
		$('#quantity').val(Number(quantity));
	}
}

$(document).ready(function() {
	$('#quantity').val(1);
});

function buysubmit(email, pcode ,page){
	var quantity = $('#quantity').val();
	if(email==""){
		document.location.href="loginform?pProductCode="+pcode+"&page="+ page + "&quantity="+quantity;
	}else{
	document.contentform.submit();
	}
}