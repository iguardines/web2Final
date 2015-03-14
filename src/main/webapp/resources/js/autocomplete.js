$(document).ready(function(){
				
		count = 0;
		
		$('#user-results').on('click', '.display_box', function(){
			$('input[name=userName]').val($(this).text());
			$('#user-results').hide(500);
		});
		
		$('#product-results').on('click', '.display_box', function(){
			$('input[name=title]').val($(this).text());
			$('#product-results').hide(500);
			count = 0;
		});
		
		$('#userName').on('keyup', function(){
			var name = $('#userName').val();
			
			if(name != ""){
				$.ajax({
					url : '/booksmov/app/search/autocomplete-users?name=' +name,
					type : "GET",
					success : function(data, status, xhr){
						var result = $.parseJSON(data);
						$('#user-results').children().empty().remove();
						for(var i = 0; i < result.length; i++){
							$('#user-results').append('<div class="display_box display-users-ml"><span>' + result[i] + '</span></div>');
						}
						$('#user-results').show();
						
					},
					error : function(jqXHR, textStatus, errorThrown) {
						var errorHtml = "An error ocurred <br/>";
						errorHtml += "Status: " + textStatus + "<br/>";
						errorHtml += "Reason: <pre>" + errorThrown + "</pre> <br/>";
						alert(errorHtml);
					}
				});
			}
		});
		
		$(this).on("click", function(e) { 
			  var $clicked = $(e.target);
			  if (! $clicked.hasClass("search")){
				$('.div-result').hide(500);
			  }
		});
		
		function evaluateKey(key){
			var inputSize = $('#title').val().length;
			
			if(inputSize == 0){
				count = 0;
			}
			// si es una letra, numero o espacio, suma contador
			if( (key >= 48 && key <= 90) || key == 32){
				++count;
			}
			else if( (key == 8 || key == 46) && count > 0 ){
				--count;
			}
		}
		
		$('#title').on('keyup', function(event){
			
			evaluateKey(event.keyCode, count);
			
			if(count < 2){
				$('#product-results').hide('slow');
			}else{
				
				var type = $('.type:checked').val(),
				userName = $('#userName').val(),
				   title = $('#title').val();
				
				$.ajax({
					url : '/booksmov/app/search/autocomplete-elements',
					type : "GET",
					data : {type: type, userName: userName, title: title},
					success : function(data, status, xhr){
						var result = $.parseJSON(data);
						$('#product-results').children().empty().remove();
						
						if(result.length == 0){
							$('#product-results').hide('slow');
						}else{
							for(var i = 0; i < result.length; i++){
								$('#product-results').append('<div class="display_box display-items-ml"><span>' + result[i] + '</span></div>');
							}
							$('#product-results').show();
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						var errorHtml = "An error ocurred <br/>";
						errorHtml += "Status: " + textStatus + "<br/>";
						errorHtml += "Reason: <pre>" + errorThrown + "</pre> <br/>";
						alert(errorHtml);
					}
				});
			}
		});
	});