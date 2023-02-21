/**
 * boardUpdate.js 
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
		
		
		
		
	/* list 목록으로 이동 */	
		$('#listButton').click(function(e){
			e.preventDefault();
			location.href='/board/list'
		});
		
		
	/* 파일 추가 & 삭제 하기 */
		
	/* 파일 추가하기 */
		$('#plusFile').click(function(){
//			console.log('clicked');
			event.preventDefault();
			var divTag = "<div class='file-new-group' id='file-new-group'><input type='file' name='file'><button name='deleteFile'>삭제</button></div>";
			$('#file-new-group').append(divTag);
		});
		
	/* 파일 삭제하기 */
		// 태그에 append 하는 경우에는 미리 태그가 생성되어 있는 경우가 아니라, 동적으로 생성된 경우이다.
		// $(선택자).on('click', function(event){}) 안 먹히는 경우가 있는데, 이는 document 객체에서 잡아와서 이벤트를 걸어줘야 한다.
		$(document).on('click', "button[name='deleteFile']", function(event){
			event.preventDefault();
			$(this).parent().remove();
		});
		
	/* 지운 정보 배열로 보내기 */
		
		var file_delete_number_array = new Array() ;
		var file_delete_name_array = new Array() ;
		
		$('.file_delete').each(function(index){
			
			$(this).click(function(idx){
				
//				console.log("index : " + index);
//				console.log("idx : " + idx);
				
				var FILE_NUMBER = $(this).val();
				
				file_delete(FILE_NUMBER, 'FILE_NUMBER_'+index);
				
				event.preventDefault();
				$(this).parent().remove();
			})
		});
		
		function file_delete(number, name) {
			console.log('number : ' + number);
			console.log('name : ' + name);
			
		//  push() : 배열 끝에 여러 값을 추가
			file_delete_number_array.push(number) ;
			file_delete_name_array.push(name) ;
			
			$('#file_delete_number').attr('value', file_delete_number_array) ;
			$('#file_delete_name').attr('value', file_delete_name_array) ;
		}
		
		
		/* 글 삭제 버튼 function */
		$('#deleteButton').click(function() {
			boardDelete();
		});

		function boardDelete() {
			event.preventDefault();
			var message = '게시글을 삭제하시면 복구하기 어렵습니다. 그래도 삭제하시겠습니까?'
			message += '\n\n삭제를 원하신다면 확인을 눌러주세요.';
			
			var board_number = $('#board_number').val();
			
			if (confirm(message)) {
				location.href = '/board/delete/' + board_number;
			} else {
				alert('삭제하지 않아주셔서 감사합니다');
			}
		}

			


		
		
	

	});/* end document.ready() */

})( jQuery );

