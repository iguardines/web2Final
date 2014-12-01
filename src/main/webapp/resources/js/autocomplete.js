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
							$('#user-results').append('<div class="display_box"><span>' + result[i] + '</span></div>');
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
		
		$('#title').on('keyup', function(){
			++count;
			if(count >= 2){
				
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
						for(var i = 0; i < result.length; i++){
							$('#product-results').append('<div class="display_box"><span>' + result[i] + '</span></div>');
						}
						$('#product-results').show();
					},
					error : function(jqXHR, textStatus, errorThrown) {
						var errorHtml = "An error ocurred <br/>";
						errorHtml += "Status: " + textStatus + "<br/>";
						errorHtml += "Reason: <pre>" + errorThrown + "</pre> <br/>";
						alert(errorHtml);
					}
				});
				
				count = 0;
			}
		});
	});