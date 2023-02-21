/**
 * userInsert.js 및 회원가입 시, 유효성 검사 js
 */
(function($) {
	
	$(document).ready(function() {
		
		$('#name_check').html('※ 이름 : 한글 조합(2~10글자)');
		$('#id_check').html('※ 아이디 : 영문, 숫자 조합(4~10글자)');
		$('#password_check').html('※ 비밀번호 : 영문/숫자/특수문자 조합(8~10글자)');
		$('#password_doubleCheck').html('※ 확인을 위해 위 비밀번호를 다시 입력해주세요');
		$('#email_check').html('※ 이메일 : example@example.com 형식(100글자 이내)');
		$('#contact_check').html('※ 연락처 : [3글자] - [3글자 or 4글자] - [4글자] 형식');
		
	/*	이름 유효성 검사	*/
		var nameFlag = false ;
		// 이름 null check
		var user_name = $('#user_name').val();
		if(
				user_name == undefined  ||
				!user_name.length  ||
				user_name == ""
		){
			nameFlag = false ;
		};
		
		
		// 입력 글자 확인
		$('#user_name').keyup(function() {
			user_name = $('#user_name').val();
			
			const validName = /^[가-힣]{2,10}$/ ;
			if (!validName.test(user_name)){
				/* error 시 */
				$('#name_check').removeClass('notError');
				$('#name_check').addClass('error');
				$('#name_check').html('※ 이름 : 한글 조합(2~10글자)');
				nameFlag = false ;
			} else {
				/* not error 시 */
				$('#name_check').removeClass('error');
				$('#name_check').addClass('notError');
				$('#name_check').html('사용 가능한 이름입니다.');
				nameFlag = true;
			} 
			
		}); /* end $(user_name).keyup */

		

		
	/* 아이디 유효성 검사, 아이디 글자 수 체크 및 id 중복 체크 */
		var idFlag = false;

		var user_id = $('#user_id').val();
		// 아이디 null check
		if(
				user_id == undefined  ||
				!user_id.length  ||
				user_id == ""
					
		){
			idFlag = false ;
		};
		
		
		$('#user_id').keyup(function() {
			user_id = $('#user_id').val();
			
			const validId = /^[a-zA-Z0-9]{4,10}$/ ;
			if(!validId.test(user_id)){
				idHasError();
				$('#id_check').html('※ 아이디 : 영문, 숫자 조합(4~10글자)');
				idFlag = false ;
			} else {
				idFlag = true ;
			};
			

			
			// 아이디 유효성 검사 통과한 경우
			if(idFlag){
				idHasError(); // 아이디 유효성 검사 Error 없는 경우
				$('#id_check').html('아이디 중복 검사를 클릭해주시기 바랍니다.');
			};
			
			
		}); /* end $(user_id).keyup */
		
		
		
		
	/*	비밀번호 유효성 검사	*/
		var passwordFlag = false ;
		var user_password = $('#user_password').val();
		if(
				user_password == undefined ||
				!user_password.length ||
				user_password == ""
		){
			passwordFlag = false ;
		}
		

		
		// 비밀번호 정규식 표현 적용
		$('#user_password').keyup(function() {
			 user_password = $('#user_password').val();
			 
			// * ※ 비밀번호 : 영문/숫자/특수문자 조합(8~10글자)
			// 조건1 : 특수문자 !, @, #, $, %, & 포함
			// 조건2 : 대문자, 소문자 포함 
			// 조건3 : 숫자 포함
			// 조건4 : 8글자 이상 10글자 이하 ? true : false
			const validPasswordCondition1 = /[\!\@\#\$\%\&]/ ;
			const validPasswordCondition2 = /[a-zA-Z]/ ;
			const validPasswordCondition3 = /[0-9]/ ;
			const validPasswordCondition4 = ( user_password.length >= 8 && user_password.length <= 10 ) ? true : false ;
			
			if(validPasswordCondition1.test(user_password) &&
					validPasswordCondition2.test(user_password) &&
					validPasswordCondition3.test(user_password) &&
					validPasswordCondition4
			){
				// 비밀번호 유효성 검사 통과
				$('#password_check').removeClass('error');
				$('#password_check').addClass('notError');
				$('#password_check').html('사용 가능한 비밀번호입니다.');
				
				passwordFlag = true ;
			} else {
				// 비밀번호 유효성 검사 통과 실패
				$('#password_check').removeClass('notError');
				$('#password_check').addClass('error');
				$('#password_check').html('※ 비밀번호 : 영문/숫자/특수문자 조합(8~10글자)');
				
				passwordFlag = false ;
			}
			 
		}); /* end $(user_password).keyup */

		
		// 비밀번호 재확인 검사
		var passwordSecondFlag = false;

		$('#user_doubleCheck').keyup(function() {
			// 원 비밀번호, input tag
			user_password = $('#user_password').val();
			
			// 확인 비밀번호, input tag
			var user_doubleCheck = $('#user_doubleCheck').val();
			
			if(user_password === user_doubleCheck){
				$('#password_doubleCheck').removeClass('error');
				$('#password_doubleCheck').addClass('notError');
				$('#password_doubleCheck').html('위의 비밀번호와 일치합니다.');
				
				passwordSecondFlag = true ;
			} else {
				$('#password_doubleCheck').removeClass('notError');
				$('#password_doubleCheck').addClass('error');
				$('#password_doubleCheck').html('위의 비밀번호와 일치하지 않습니다.');
				
				passwordSecondFlag = false ;
			}
			
			 
		}); /* end $('#user_doubleCheck').keyup(function() */
		
		
		// 이메일 정규 표현식
		var emailFlag = false;
		$('#user_email').keyup(function() {
			user_email = $('#user_email').val();
			 
			// * 영문/숫자/특수문자 조합(8~10글자)
			// 조건1 : 불라불라@불라불라.불라불라
			// 조건2 : 글자수 100 이하
			const validEmailCondition1 = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/ ;
			const validEmailCondition2 = (user_email.length <= 100) ? true : false;
			
			if(
					validEmailCondition1.test(user_email) &&
					validEmailCondition2
			){
				// 유효성 검사 통과
				$('#email_check').removeClass('error');
				$('#email_check').addClass('notError');
				$('#email_check').html('사용 가능한 이메일입니다.');
				
				emailFlag = true ;
			} else {
				// 유효성 검사 통과 실패
				$('#email_check').removeClass('notError');
				$('#email_check').addClass('error');
				$('#email_check').html('※ 이메일 : example@example.com 형식(100글자 이내)');
				
				emailFlag = false ;
			}
			 
		}); /* $('#user_email').keyup(function() */
		
		
		// 연락처 정규 표현식
		var contactFlag = false;
		$('#user_contact').keyup(function() {
			user_contact = $('#user_contact').val();
			 
			// * 연락처 : 영문/숫자/특수문자 조합(8~10글자)
			// 조건1 : 3글자 - 3글자 or 4글자 - 4글자 형태
			const validContactCondition = /\d{3}-\d{3,4}-\d{4}/ ;
			
			if(
					validContactCondition.test(user_contact)
			){
				// 유효성 검사 통과
				$('#contact_check').removeClass('error');
				$('#contact_check').addClass('notError');
				$('#contact_check').html('사용 가능한 연락처입니다.');
				
				contactFlag = true ;
				
			} else {
				// 유효성 검사 통과 실패
				$('#contact_check').removeClass('notError');
				$('#contact_check').addClass('error');
				$('#contact_check').html('※ 연락처 : [3글자] - [3글자 or 4글자] - [4글자] 형식');
				
				contactFlag = false ;
			}
			 
		}); /* $('#user_contact').keyup(function() */
		
		
		/*	이메일 null check	*/
		var emailFlag = false ;
		var user_email = $('#user_email').val();
		if(
				user_email == undefined  ||
				!user_email.length  ||
				user_email == ""
		){
			emailFlag = false ;
		};
		
		/*	연락처 null check	*/
		var contactFlag = false ;
		var user_contact = $('#user_contact').val();
		if(
				user_contact == undefined  ||
				!user_contact.length  ||
				user_contact == ""
		){
			contactFlag = false ;
		};
		
		/*	우편번호 null check	*/
		var zipcodeFlag = false ;
		var user_zipcode = $('#user_zipcode').val();
		if(
				user_zipcode == undefined  ||
				!user_zipcode.length  ||
				user_zipcode == "" ||
				user_zipcode == null
		){
			zipcodeFlag = false ;
		} else {
			zipcodeFlag = true;
		};
		
		/*	도로명주소 null check	*/
		var roadnameFlag = false ;
		var user_roadname = $('#user_roadname').val();
		if(
				user_roadname == undefined  ||
				!user_roadname.length  ||
				user_roadname == ""
		){
			roadnameFlag = false ;
		} else {
			roadnameFlag = true;
		};
		

		

		
		
		
		
		
	/* 클릭 이벤트 */
		
		// 아이디 중복 검사 클릭 이벤트
		$('#idValidCheck').on('click', function(event){
			// event.preventDefault();
			var user_id = $('#user_id').val();
			if(
					user_id == undefined ||
					!user_id.length  ||
					user_id === "" 
			){
				$('#id_check').html('아이디를 입력해주시기 바랍니다.');
				
				idFlag = false ; 
			} else {
			
				var url = '/user/idValidCheck';
				
				$.ajax({
					url: url,
					type: "POST",
					async: false,
					data: {"user_id": user_id},
					contextData: "application/json",
					dataType: "json",
					cache: false, // 데이터 갱신 방지
					success: function (data){
						if(data.state === 1){ // 반환값이 1이면 가입한 대상이 있음

							idHasError();
							$('#id_check').html('이미 사용 중인 아이디입니다.');
							
							idFlag = false;
						} else if(data.state === 0) { // 반환값이 0이면 가입한 대상이 없음
							
							idHasNotError();
							$('#id_check').html('사용 가능한 아이디입니다.');

							idFlag = true ;
						}

					},
					error: function(data, status, error){
						alert('문제 발생');
						console.log('문제 발생');
						console.log(data);
						console.log(status);
						console.log(error);
						location.href = "/user/insert";
					}
					
				});
			}
			
		}); /* end $('#idValidCheck').on('click', function() */


	/* form 제출 submit */	
		$('#submitButton').click(function(){
			submitForm();
		});
		
		function submitForm(){
			event.preventDefault();
			if(
					nameFlag == true &&  
					idFlag == true && 
					passwordFlag == true && 
					passwordSecondFlag == true &&
					emailFlag == true &&
					contactFlag == true &&
					zipcodeFlag == true &&
					roadnameFlag == true
			){
				$('#submitForm').submit(); // form 제출
				
			} else { // 회원가입 클릭 시 한 번 더 확인

				if(nameFlag == false){
					document.getElementById("user_name").focus();
				
					
					$('#name_check').removeClass('notError');
					$('#name_check').addClass('error');
					$('#name_check').html('이름을 입력해주시기 바랍니다.');
				}
				
				if(idFlag == false){
					document.getElementById("user_id").focus();
					
					$('#id_check').removeClass('notError');
					$('#id_check').addClass('error');
					$('#id_check').html('아이디를 입력해주시기 바랍니다.');
				}

				if(passwordFlag == false){
					document.getElementById("user_password").focus();
					
					
					$('#password_check').removeClass('notError');
					$('#password_check').addClass('error');
					$('#password_check').html('비밀번호를 입력해주시기 바랍니다.');
				}
				
				if(passwordSecondFlag == false){
					document.getElementById("password_doubleCheck").focus();
					
					
					$('#password_doubleCheck').removeClass('notError');
					$('#password_doubleCheck').addClass('error');
					$('#password_doubleCheck').html('비밀번호 확인을 위해 위 번호를 다시 입력해주시기 바랍니다');
				}
				
				if(emailFlag == false){
					$('#email_check').focus();
					
					
					$('#email_check').removeClass('notError');
					$('#email_check').addClass('error');
					$('#email_check').html('※ 이메일 : example@example.com 형식(100글자 이내)');
				}
				
				if(contactFlag == false){
					$('#contact_check').focus();
					
					
					$('#contact_check').removeClass('notError');
					$('#contact_check').addClass('error');
					$('#contact_check').html('※ 연락처 : [3글자] - [3글자 or 4글자] - [4글자] 형식');
				}
				
				if(zipcodeFlag == false){
					
					$('#zipcode_check').focus();
					
					$('#zipcode_check').removeClass('notError');
					$('#zipcode_check').addClass('error');
					$('#zipcode_check').html('주소를 검색하여 입력해주시기 바랍니다.');
				}
				
				
				
				
			}
			
		}
			
		/* form submit function */
//		$('#submitForm').on('submit', function(event){
//		$('#submitForm').submit(function(event){

		/*
		
		// 버튼 click으로 변경
		$('#submitButton').click(function(){
			event.preventDefault();
			
			if(
					nameFlag == true && 
					idFlag == true && 
					passwordFlag == true && 
					passwordSecondFlag == true &&
					emailFlag == true &&
					contactFlag == true &&
					zipcodeFlag == true &&
					roadnameFlag == true
			){
				$("form").submit(); // form 제출 
				
			} else { // 회원가입 클릭 시 한 번 더 확인

				if(nameFlag == false){
					$("#user_name").focus();
					
					$('#name_check').removeClass('notError');
					$('#name_check').addClass('error');
					$('#name_check').html('이름을 입력해주시기 바랍니다.');
				}
				
				if(idFlag == false){
					$("#user_id").focus();
					
					$('#id_check').removeClass('notError');
					$('#id_check').addClass('error');
					$('#id_check').html('아이디를 입력해주시기 바랍니다.');
				}

				if(passwordFlag == false){
					$("#user_password").focus();
					
					$('#password_check').removeClass('notError');
					$('#password_check').addClass('error');
					$('#password_check').html('비밀번호를 입력해주시기 바랍니다.');
				}
				
				if(passwordSecondFlag == false){
					$("#password_doubleCheck").focus();
					
					$('#password_doubleCheck').removeClass('notError');
					$('#password_doubleCheck').addClass('error');
					$('#password_doubleCheck').html('비밀번호 확인을 위해 위 번호를 다시 입력해주시기 바랍니다');
				}
				
				if(emailFlag == false){
					$('#email_check').focus();
					
					$('#email_check').removeClass('notError');
					$('#email_check').addClass('error');
					$('#email_check').html('※ 이메일 : example@example.com 형식(100글자 이내)');
				}
				
				if(contactFlag == false){
					$('#contact_check').focus();
					
					$('#contact_check').removeClass('notError');
					$('#contact_check').addClass('error');
					$('#contact_check').html('※ 연락처 : [3글자] - [3글자 or 4글자] - [4글자] 형식');
				}
				
				if(zipcodeFlag == false){
					
					$('#zipcode_check').focus();
					
					$('#zipcode_check').removeClass('notError');
					$('#zipcode_check').addClass('error');
					$('#zipcode_check').html('주소를 검색하여 입력해주시기 바랍니다.');
				}
				
				
				
				
			}
			
		
		});
		
		*/

			

			
			
		
		
		// 아이디 유효성 검사 Error Function
		function idHasError(){
			$('#id_check').removeClass('notError');
			$('#id_check').addClass('error');
		}
		
		// 아이디 유효성 검사 Error 없는 경우
		function idHasNotError(){
			$('#id_check').removeClass('error');
			$('#id_check').addClass('notError');
		}
		
		
		
		
	/* 도로주소명 관련 js */
		$('#DaumPostcode').click(function(){
			DaumPostcode();
		});
	
		function DaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 참고 항목 변수

	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname ;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('user_zipcode').value = data.zonecode;
	                document.getElementById("user_roadname").value = roadAddr;

	                zipcodeFlag = true;
					roadnameFlag = true;
					
	                // onsole.log(zipcodeFlag);
	                // console.log(roadnameFlag);
	                
	                //document.getElementById("user_detailadress").focus();
	                //document.getElementById("user_detailadress").value = data.jibunAddress;
	                
	                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
	                /*if(roadAddr !== ''){
	                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
	                } else {
	                    document.getElementById("sample4_extraAddress").value = '';
	                }*/

	                /*var guideTextBox = document.getElementById("guide");
	                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
	                if(data.autoRoadAddress) {
	                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
	                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
	                    guideTextBox.style.display = 'block';

	                } else if(data.autoJibunAddress) {
	                    var expJibunAddr = data.autoJibunAddress;
	                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
	                    guideTextBox.style.display = 'block';
	                } else {
	                    guideTextBox.innerHTML = '';
	                    guideTextBox.style.display = 'none';
	                }*/
	                
	            }
	        }).open();
	    }
		

		
		
		
	

	});/* end document.ready() */

})( jQuery );

