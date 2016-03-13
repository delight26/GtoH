 $(document).ready(function(){
		$('#image').on('change', function() {
			
			ext = $(this).val().split('.').pop().toLowerCase();
			
			if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
				resetFormElement($(this)); 
				window.alert('이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)');
			} else {
				file = $('#image').prop("files")[0];
				blobURL = window.URL.createObjectURL(file);
				$('#image_preview img').attr('src', blobURL);
				$('#image_preview').slideDown();
				$(this).slideUp();
			}
		});
		$('#image_preview a').bind('click', function() {
			resetFormElement($('#image'));
			$('#image').slideDown();
			$(this).parent().slideUp();
			return false;
		});
		function resetFormElement(e) {
			e.wrap('<form>').closest('form').get(0).reset(); 
			e.unwrap();
		}
 
		$(document).on('change', '.btn-file :file', function() {
		  var input = $(this);
		      numFiles = input.get(0).files ? input.get(0).files.length : 1;
		      label = input.val();
		      $("#filePath").val(label);
		});

		$(document).ready( function() {
		    $('.btn-file :file').on('fileselect', function(event, numFiles, label) {
		        
		        var input = $(this).parents('.input-group').find(':text'),
		            log = numFiles > 1 ? numFiles + ' files selected' : label;
		        
		        if( input.length ) {
		            input.val(log);
		        } else {
		            if( log ) alert(log);
		        }
		        
		    });
		});
		
		$('#removePhoto').on('click', function() {
			$("#filePath").val("");
		});
 
 
 
 
 
 });