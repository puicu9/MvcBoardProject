/**
 * boardList.js 
 */
(function($) {
	
	$(document).ready(function() {
		

		
	/* 클릭 이벤트 */
		
	/* 글쓰기로 가기 goToPage() */	
		$('#writeButton').click(function(){
			
			goToPage();
		});
		
		function goToPage(){
			event.preventDefault();
			//console.log('goToPage(); 실행');
			location.href='/board/insert'
				
			
			
		}


	});/* end document.ready() */

})( jQuery );

