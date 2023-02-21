/**
 * boardList.js 및 회원가입 시, 유효성 검사 js
 */
(function($) {
	
	$(document).ready(function() {
		
	
		/* null check */
		// 글제목
		var titleFlag = false ;
		var board_title = $('#board_title').val();
		if(
				board_title == undefined ||
				!board_title.length ||
				board_title == ""
				) {
			titleFlag = false ;
		} else {
			titleFlag = true ;
		}
		
		
		// 글내용
		var contentFlag = false ;
		var board_content = $('#board_content').val();
		if(
				board_content == undefined ||
				!board_content.length ||
				board_content == ""
				) {
			contentFlag = false ;
		}	else {
			contentFlag = true ;
		}
		
//		keyup을 통해 제목 remove class
		$('#board_title').keyup(function(){
			board_title = $('#board_title').val();
			if(
					board_title == undefined ||
					!board_title.length ||
					board_title == ""
					) {
				titleFlag = false ;
			} else {
				titleFlag = true ;
				$('#title_check').removeClass('error');
				$('#title_check').html('');
			}
		});
		
		// keyup을 통해 내용 remove class
		$('#board_content').keyup(function(){
			board_content = $('#board_content').val();
			if(
					board_content == undefined ||
					!board_content.length ||
					board_content == ""
					) {
				contentFlag = false ;
			} else {
				contentFlag = true ;
				$('#content_check').removeClass('error');
				$('#content_check').html('');
			}
		});
		
		
		
		
	/* 클릭 이벤트 */
		
	// form submit 제출
		$('#submitButton').click(function(){
			console.log('clicked');
			submitForm();
		});
		
		function submitForm() {
			event.preventDefault();
			
			if(
					titleFlag == true &&
					contentFlag == true
					) {
				$('#submitForm').submit();
			} else {
				// 한 번 더 체크
				
				if(titleFlag == false) {
					$('#board_title').focus();
					
					$('#title_check').removeClass('notError');
					$('#title_check').addClass('error');
					$('#title_check').html('제목을 입력해주시기 바랍니다.');
				}

				
				if(contentFlag == false) {
					$('#board_content').focus();
					
					$('#content_check').removeClass('notError');
					$('#content_check').addClass('error');
					$('#content_check').html('내용을 입력해주시기 바랍니다.');
				}
								
			}

			
		}
		
		
		
		
	/* 게시물 등록으로 가기 goToPage() */	
		$('#writeButton').click(function(){
			event.preventDefault();
			location.href='/board/insert'
		});
		
		
	/* 파일 추가하기 */
		
		$('#plusFile').click(function(){
//			console.log('clicked');
			event.preventDefault();
			var divTag = "<div class='file-new-group' id='file-new-group'><input type='file' name='file'><button name='deleteFile'>삭제</button></div>";
			$('#file-new-group').append(divTag);
		});
	
	/* 파일 삭제하기*/
		// 태그에 append 하는 경우에는 미리 태그가 생성되어 있는 경우가 아니라, 동적으로 생성된 경우이다.
		// $(선택자).on('click', function(event){}) 안 먹히는 경우가 있는데, 이는 document 객체에서 잡아와서 이벤트를 걸어줘야 한다.
		$(document).on('click', "button[name='deleteFile']", function(event){
			event.preventDefault();
			$(this).parent().remove();
		});
		

		

			


		
		
	

	});/* end document.ready() */

})( jQuery );

