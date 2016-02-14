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

function prodcontent(pNo){
	$.ajax({
        url: "productcontent",
        type:"GET",
        data: {"pNo" : pNo},
        dataType: "text",
        success: function(responseData, statusText, xhr){
        	var result = responseData;
        	$('.modal-content').html(result);
        },
        error : function(xhr, statusText, responseData){
           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
        }
     });
	$('#myModal').modal('show');
}

function buysubmit(email, pcode ,page){
	$('#myModal').modal('hide');
	var quantity = $('#quantity').val();
	if(email==''){
		alert('로그인 하세요');
		$('#myModal').modal({
    		remote : "loginform?pProductCode="+pcode+"&page="+ page + "&quantity="+quantity
    		});
	}else{
	document.contentform.submit();
	}
}

function updateproduct(pProductCode){
	document.location.href="productupdate?pProductCode="+pProductCode;
}
function deleteproduct(pProductCode){
	document.location.href="productdelete?pProductCode="+pProductCode;
}