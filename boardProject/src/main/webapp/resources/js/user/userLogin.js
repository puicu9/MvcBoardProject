/**
 * userLogin.js 및 회원가입 시, 유효성 검사 js
 */
(function($) {
	
	$(document).ready(function() {
		
		
		
	/* 클릭 이벤트 */

	/* form 제출 submit */	
		$('#submitButton').click(function(){
			submitForm();
		});
		
		function submitForm(){
			
			//console.log('submit 버튼 클릭');
			$('#submitForm').submit(); // form 제출		
		}
			
		
		$('#goToButton').click(function(){
			goToPage();
		})
			
		function goToPage() {
			event.preventDefault();
			location.href='/user/insert';
		}
	

			

			
			
		
		
		
		

		
		
		
	

	});/* end document.ready() */

})( jQuery );
