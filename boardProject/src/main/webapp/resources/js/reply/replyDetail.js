/**
 * replyDetail.js
 */
(function($) {
	
	$(document).ready(function() {
		
		
		/* 댓글 관련 */
		
//		user_number information
		var user_number = $('#user_number').val(); // loginfo와 비교
		
		
		// 부모 댓글 작성 submit
		
		$('#replyForm').submit(function(){
			if(!$('#reply_content').val()) {
				alert('댓글 내용을 입력해주시기 바랍니다.');
				$('#reply_content').focus();
				return false;
			}
		});
		
		// 시작하자마자 댓글 리스트 불러오기
		getReplyList();
		
		// 댓글 내용 가져오기
		function getReplyList() {
			
			$('#reply-attach-object').empty(); // 내용 초기화
			
			var board_number = $('#board_number').val();
			var url ='/reply/list/'+board_number ;
			
			$.ajax({
				url: url,
				type: "GET",
				data: {"board_number":board_number},
				contextData: "application/json; charset=UTF-8",
				dataType: "json",
//				cache: false, // 데이터 갱신 방지
				success: function (data){
					
//					console.log('success');
//					console.log(data);					
					
					for (var i = 0 ; i < data.length ; i++) {
//						console.log(i);
						
						var reply_number = data[i].reply_number ;
						var user_name = data[i].user_name ;
						var reply_content = data[i].reply_content ;
						var reply_inputdate = data[i].reply_inputdate;
						var reply_depth = data[i].reply_depth;
						
//						console.log(reply_number);
//						console.log(user_name);
//						console.log(reply_content);
//						console.log(reply_inputdate);
//						console.log(reply_depth);
						
						// 댓글 뼈대에 넣기
					appendReplys(reply_number, user_name, reply_content, reply_inputdate, reply_depth);
					
					} // end for

				},
				error: function(data, status, error){
//					alert('댓글 내용 가져오기 문제 발생');
					console.log('댓글 내용 가져오기 문제 발생');
					console.log(data);
					console.log(status);
					console.log(error);
//					location.href = "/board/list/"+board_number ;
				}
			}); /* end ajax */
	
		}; /* end funtion replyList() */
		

		
		/* 댓글 뼈대 */
		function appendReplys(reply_number, user_name, reply_content, reply_inputdate, reply_depth){
			
			/* glyphicon 추가할 것 */
			var span_gly_tag = $('<span>');
			span_gly_tag.addClass('glyphicon glyphicon-share-alt');
			
			/* 댓글 1개 묶으려는 태그 */ 
			var li_tag = $('<li>');  
			li_tag.addClass('each-reply') ;
			
			/* padding-left 값 부여 */
			var padding_left = 30 * reply_depth;
			span_gly_tag.css({"padding-left":padding_left});
			li_tag.css({"padding-left":padding_left});
			
			// 작성자 name
			var span_name = $('<span>') ;
			span_name.addClass('span-replied') ;
			span_name.text(user_name) ;
			
			// 작성 일자
			var span_date = $('<span>') ;
			span_date.addClass('span-replied') ;
			span_date.html(reply_inputdate) ;
			
			if( $('#loginfo').val() == user_number ) {
				
				var button_update = "<button type='button' class='widen_content' name='widen_update' value='"+reply_number+"'>수정</button>" ; 
				var button_delete = "<button type='button' name='button_second_delete' class='reply_delete' value='"+reply_number+"'>삭제</button>" ;
				
			} else {
				var button_update = "<button type='button' class='widen_content' value='"+reply_number+" disabled'>수정</button>" ; 
				var button_delete = "<button type='button' value='"+reply_number+" disabled'>삭제</button>" ;
			}

			// 댓글 내용
			var p_content = $('<p>') ;
			p_content.addClass('p-replied') ;
			p_content.html(reply_content) ;

			// 기본으로 보이는 댓글 버튼, 클릭하면 display 효과줄 버튼
			var button_reply = "<button type='button' class='widen_content' name='widen_insert'>답글</button>" ;
			
			// 답글 insert button, input, 취소 관련
			var reply_second_input = "<input type='text' class='reply_second_input' name='reply_second_input' style='width:250px ; display:none;'>";
			var button_second_cancel = "<button type='reset' name='button_second_cancel' style='display:none;'>취소</button>";
			var button_second_reply = "<button type='button' name='button_second_reply' class='reply_reInsert'  style='display:none;' value='"+reply_number+"'>답글</button>" ;
			
			// 수정 update button, input, 취소, 수정 관련
			var reply_second_update_input = "<input type='text' class='reply_second_update_input' name='reply_second_update_input' style='width:250px ; display:none;'>";
			var button_second_update_cancel = "<button type='reset' name='button_second_update_cancel' style='display:none;'>취소</button>";
			var button_second_update = "<button type='submit' name='button_second_update' class='reply_reUpdate'  style='display:none;' value='"+reply_number+"'>수정</button>" ;
	
			// html tag append
			li_tag.append(span_name).append(span_date).append(button_update)
			.append(reply_second_update_input).append(button_second_update_cancel).append(button_second_update) // 수정 button
			.append(button_delete).append(p_content).append(button_reply)
			.append(reply_second_input).append(button_second_cancel).append(button_second_reply); // 답글 button
			
			if(reply_depth > 0){
				$('#reply-attach-object').append(span_gly_tag).append(li_tag).append('<hr/>') ;
			} else if(reply_depth == 0){
				$('#reply-attach-object').append(li_tag).append('<hr/>') ;
			}
			
			
		}
	

	/* display none or on */
		// 기본 display none 값으로 할당
//		$('.wide-class').off();
//		$('.wide-class').css('display','none') ;	
	
	/* 답글 눌렀을 시 display none or on */
		$(document).on('click', "button[name='widen_insert']", function(e){
			e.preventDefault();
			
			if ($(this).next().css('display') == 'block') {				
				$(this).nextAll().css('display', 'none');
	        } else {
				$(this).nextAll().css('display', 'block');
	        };
			
		});

	/* 수정 눌렀을 때 wide */
		$(document).on('click', "button[name='widen_update']", function(e){
			e.preventDefault();
			
			if ($(this).next().css('display') == 'block') {

				$(this).next("input[name='reply_second_update_input']").css('display', 'none');
				$(this).siblings("button[name='button_second_update_cancel']").css('display', 'none');
				$(this).siblings("button[name='button_second_update']").css('display', 'none');
	        
			} else {
	        	
	        	$(this).next("input[name='reply_second_update_input']").css('display', 'block');
				$(this).siblings("button[name='button_second_update_cancel']").css('display', 'block');
				$(this).siblings("button[name='button_second_update']").css('display', 'block');
	        };
			
		});
		
		
	/* 답글의 취소 눌렀을 시 display none or on */
		$(document).on('click', "button[name='button_second_cancel']", function(e){
			e.preventDefault();
//			alert('clicked');
			
			if ($(this).next().css('display') == 'block') {				
				$(this).siblings("input[name='reply_second_input']").css('display', 'none');
				$(this).css('display', 'none');
				$(this).next().css('display', 'none');
	        };
			
		});
		
		
	/* input에 있는 값 찾기 */
		/* submit button click */
		$(document).on('click', "button[name='button_second_reply']", function(e){
			e.preventDefault();
			
			if ($(this).css('display') == 'block') {				
//				alert($(this).attr('name'));
//				alert($(this).siblings().length);

			// input에 담긴 값, 보낼 내용
				var reply_second_content = $(this).siblings("input[name='reply_second_input']").val();
				console.log(reply_second_content);
				
			// button에 reply_number 담긴 값
				var button_second_number = $(this).val() ;
				console.log(button_second_number);
				
				// 작성자 number
				var user_number = $('#user_number').val();
				// 게시물 number
				var board_number = $('#board_number').val();
				
				if(
						reply_second_content == undefined ||
						!reply_second_content.length ||
						reply_second_content == ""
						) {
					alert('답글을 작성해 주시기 바랍니다.');
//					reply_second_content.focus();
					
				} else {
					// 댓글 대댓글 내용 
					$("input[name='reply_second_content']").attr('value', reply_second_content);
					// 댓글 고유 번호
					$("input[name='reply_second_reply_number']").attr('value', button_second_number);
					// 작성자 고유번호
					$("input[name='reply_second_user_number']").attr('value', user_number);
					// 작성할 댓글의 게시물 고유번호
					$("input[name='reply_second_board_number']").attr('value', board_number);
					
					var replySecondForm = $('#replySecondForm');
					
					
					replySecondForm.submit();
				}; /* end if ($(this).css('display') == 'block') */
				
				
				

				
	        };
			
		}); /* end input  */

		/* update button 클릭했을 때 submit form*/
		$(document).on('click', "button[name='button_second_update']", function(e){
			e.preventDefault();
			
			if ($(this).css('display') == 'block') {				
//				alert($(this).attr('name'));
//				alert($(this).siblings().length);

			// input에 담긴 값, 보낼 내용
				var reply_second_update_content = $(this).siblings("input[name='reply_second_update_input']").val();
//				console.log(reply_second_update_content);
				
			// button에 reply_number 담긴 값
				var button_second_number = $(this).val() ;
//				console.log(button_second_number);
				
				// 게시물 number
				var board_number = $('#board_number').val();
				
				if(
						reply_second_update_content == undefined ||
						!reply_second_update_content.length ||
						reply_second_update_content == ""
						) 
				{
					alert('답글을 작성해 주시기 바랍니다.');
					return false;
//					reply_second_content.focus();
					
				} else {
					// 댓글 고유 번호
					$("input[name='reply_second_update_reply_number']").attr( 'value', button_second_number );
					
					// 작성할 댓글의 게시물 고유번호
					$("input[name='reply_second_update_board_number']").attr( 'value', board_number );
					
					// 댓글 대댓글 수정 내용 
					$("input[name='reply_second_update_content']").attr( 'value', reply_second_update_content );

					
					
					var replySecondUpdateForm = $('#replySecondUpdateForm'); 
					
					replySecondUpdateForm.submit();
					

				}; /* end if ($(this).css('display') == 'block') */
				
				
				

				
	        };
			
		});
		
		
	/* REPLY DELETE */
		
		$(document).on('click', "button[name='button_second_delete']", function(e){
			e.preventDefault();

			// 게시물 number
			var board_number = $('#board_number').val();
			
			var message = '댓글을 삭제하시면 복구하기 어렵습니다. 그래도 삭제하시겠습니까?'
				message += '\n\n삭제를 원하신다면 확인을 눌러주세요.';
			
				if (confirm(message)) {
					
					$("input[name='reply_second_delete_reply_number']").attr( 'value', $(this).val() );
					$("input[name='reply_second_delete_board_number']").attr( 'value', board_number );
					var replySecondDeleteForm = $('#replySecondDeleteForm'); 
					replySecondDeleteForm.submit();
					
				} else {
					alert('삭제하지 않아주셔서 감사합니다');
				}
			

		});	/* end REPLY DELETE */
		

		
		
	/* event 댓글 내용 없으면 댓글 등록 버튼 못 누르게 */
		var contentFlag = false ;
		contentLengthCheck();
		
		$('#reply_content').keyup(function(){
			contentLengthCheck();
		});
		
		function contentLengthCheck() {
			reply_content = $('#reply_content').val();
			if(
					reply_content == undefined ||
					!reply_content.length ||
					reply_content == ""
					) 
			{
				contentFlag = false ;
				$('#replySubmitButton').attr('disabled', true);
			} else {
				contentFlag = true ;
				$('#replySubmitButton').attr('disabled', false);
			}
		}
		


	});/* end document.ready() */

})( jQuery );


