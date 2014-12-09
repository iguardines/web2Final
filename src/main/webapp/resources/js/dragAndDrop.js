$(document).ready(function(){
	
	function validate(state, action){
		
		if(state == 'ACCEPTED' && action == 'deliver'){
			return true;
		}
		if(state == 'PENDING' && action == 'accept'){
			return true;
		}
		if(state == 'PENDING' && action == 'reject'){
			return true;
		}
		return false;
	}
	
	$('.drag-me').draggable({
	   cursor: 'move',
	   revert : true
	});
	
	$(".droppable").droppable({
		   tolerance: "fit",
		   drop: function( event, ui ) {
			  var loanId = ui.draggable.data('loanid'),
			   	  state = ui.draggable.data('state'),
			      action = $(this).data('action');
			  
			  if(validate(state, action)){
				  $.ajax({
						url : '/booksmov/app/loan/' + action + '/' + loanId,
						type : "GET",
						success : function(data){
							var result = $.parseJSON(data);
							$('#state-' + loanId).text(result.message);
							$('#loan-' + loanId).css('background-color', result.color);
							if(result.name == 'DELIVERED'){
								$('#delivery-date-' + loanId).text(result.deliveryDate);
							}
							if(result.name == 'ACCEPTED' || result.name == 'REJECTED'){
								$('#response-date-' + loanId).text(result.responseDate);
							}
							ui.draggable.data('state', result.name);
						},
						error : function(jqXHR, textStatus, errorThrown) {
							var errorHtml = "An error ocurred <br/>";
							errorHtml += "Status: " + textStatus + "<br/>";
							errorHtml += "Reason: <pre>" + errorThrown + "</pre> <br/>";
							alert(errorHtml);
						}
				});
			  }
		   }
		});
});