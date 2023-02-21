/**
 * userUpdate.jsp 관
 */
(function($) {

	$(document).ready(function() {

		
		
						$('#id_check').html('※ 아이디는 변경이 불가합니다.');
						$('#name_check').html('※ 변경할 이름 : 한글 조합(2~10글자)');
						$('#email_check').html('※ 변경할 이메일 : example@example.com 형식(100글자 이내)');
						$('#contact_check').html('※ 변경할 연락처 : [3글자] - [3글자 or 4글자] - [4글자] 형식');
						$('#password_check').html('※ 회원정보 수정을 위해 비밀번호를 입력해 주세요');

				/*	이름 유효성 검사 */
						
						nameFlag = false;
						
						$('#user_name').keyup(function() {
									var user_name = $('#user_name').val();

									const validName = /^[가-힣]{2,10}$/;
									if (!validName.test(user_name)) {
										/* error 시 */
										$('#name_check')
												.removeClass('notError');
										$('#name_check').addClass('error');
										$('#name_check').html(
												'※ 변경할 이름 : 한글 조합(2~10글자)');
										nameFlag = false;
									} else {
										/* not error 시 */
										$('#name_check').removeClass('error');
										$('#name_check').addClass('notError');
										$('#name_check').html('사용 가능한 이름입니다.');
										nameFlag = true;
									}

								}); /* end $(user_name).keyup */

						/*	비밀번호 유효성 검사	*/
						var passwordFlag = false;
						var user_password = $('#user_password').val();
						if (user_password == undefined || 
							!user_password.length ||
							 user_password == "") {
								passwordFlag = false;
							};

						// 비밀번호 정규식 표현 적용
						$('#user_password').keyup(
										function() {
											user_password = $('#user_password').val();

											// * ※ 비밀번호 : 영문/숫자/특수문자 조합(8~10글자)
											// 조건1 : 특수문자 !, @, #, $, %, & 포함
											// 조건2 : 대문자, 소문자 포함 
											// 조건3 : 숫자 포함
											// 조건4 : 8글자 이상 10글자 이하 ? true : false
											const validPasswordCondition1 = /[\!\@\#\$\%\&]/;
											const validPasswordCondition2 = /[a-zA-Z]/;
											const validPasswordCondition3 = /[0-9]/;
											const validPasswordCondition4 = (user_password.length >= 8 && user_password.length <= 10) ? true : false;

											if (validPasswordCondition1.test(user_password)
													&& validPasswordCondition2.test(user_password)
													&& validPasswordCondition3.test(user_password)
													&& validPasswordCondition4) {
												// 비밀번호 유효성 검사 통과
												$('#password_check').removeClass('error');
												$('#password_check').addClass('notError');
												$('#password_check').html('확인을 위한 비밀번호가 입력되었습니다.');

												passwordFlag = true;
											} else {
												// 비밀번호 유효성 검사 통과 실패
												$('#password_check').removeClass('notError');
												$('#password_check').addClass('error');
												$('#password_check').html('※ 비밀번호 : 영문/숫자/특수문자 조합(8~10글자)');

												passwordFlag = false;
											}

										}); /* end $(user_password).keyup */

					

						// 이메일 정규 표현식
						var emailFlag = false;
						$('#user_email').keyup(function() {
											user_email = $('#user_email').val();

											// * 영문/숫자/특수문자 조합(8~10글자)
											// 조건1 : 불라불라@불라불라.불라불라
											// 조건2 : 글자수 100 이하
											const validEmailCondition1 = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
											const validEmailCondition2 = (user_email.length <= 100) ? true : false;

											if (validEmailCondition1.test(user_email)
													&& validEmailCondition2) { // 유효성 검사 통과
												$('#email_check').removeClass('error');
												$('#email_check').addClass('notError');
												$('#email_check').html('사용 가능한 이메일입니다.');

												emailFlag = true;
											} else {
												// 유효성 검사 통과 실패
												$('#email_check').removeClass('notError');
												$('#email_check').addClass('error');
												$('#email_check').html('※ 이메일 : example@example.com 형식(100글자 이내)');

												emailFlag = false;
											}

										}); /* $('#user_email').keyup(function() */

						// 연락처 정규 표현식
						var contactFlag = false;
						$('#user_contact').keyup(function() {
											user_contact = $('#user_contact').val();

											// * 연락처 : 영문/숫자/특수문자 조합(8~10글자)
											// 조건1 : 3글자 - 3글자 or 4글자 - 4글자 형태
											const validContactCondition = /\d{3}-\d{3,4}-\d{4}/;

											if (validContactCondition.test(user_contact)) { // 유효성 검사 통과
												$('#contact_check').removeClass('error');
												$('#contact_check').addClass('notError');
												$('#contact_check').html('사용 가능한 연락처입니다.');

												contactFlag = true;

											} else {
												// 유효성 검사 통과 실패
												$('#contact_check').removeClass('notError');
												$('#contact_check').addClass('error');
												$('#contact_check').html('※ 연락처 : [3글자] - [3글자 or 4글자] - [4글자] 형식');

												contactFlag = false;
											}

										}); /* $('#user_contact').keyup(function() */

						/* 이름 null check */
						var nameFlag = false;
						var user_name = $('#user_name').val();
						if (user_name == undefined || 
							!user_name.length ||
							 user_name == "") {
								nameFlag = false;
						};
						
						
						/*	이메일 null check	*/
						var emailFlag = false;
						var user_email = $('#user_email').val();
						if (user_email == undefined || 
							!user_email.length ||
							 user_email == "") {
							emailFlag = false;
						};

						/*	연락처 null check	*/
						var contactFlag = false;
						var user_contact = $('#user_contact').val();
						if (user_contact == undefined ||
							 !user_contact.length ||
							  user_contact == "") {
							contactFlag = false;
						};

						/*	우편번호 null check	*/
						var zipcodeFlag = false;
						var user_zipcode = $('#user_zipcode').val();
						if (user_zipcode == undefined || 
							!user_zipcode.length||
							 user_zipcode == "") {
							zipcodeFlag = false;
						} else {
							zipcodeFlag = true;
						};

						/*	도로명주소 null check	*/
						var roadnameFlag = false;
						var user_roadname = $('#user_roadname').val();
						if (user_roadname == undefined || 
							!user_roadname.length || 
							user_roadname == "") {
							roadnameFlag = false;
						} else {
							roadnameFlag = true;
						};


						/* 클릭 이벤트 */

						/* form 제출 function function */
						$('#submitButton').click(function() {
							submitForm();
						});

						function submitForm() {

							event.preventDefault();

							if (nameFlag == true && 
								passwordFlag == true && 
								emailFlag == true && 
								contactFlag == true &&
								 zipcodeFlag == true &&
								  roadnameFlag == true) {

								$('#submitForm').submit(); // form 제출

							} else { // 클릭 시 한 번 더 확인

								if (nameFlag == false) {
									document.getElementById("user_name").focus();

									$('#name_check').removeClass('notError');
									$('#name_check').addClass('error');
									$('#name_check').html('이름을 입력해주시기 바랍니다.');
								}

								if (passwordFlag == false) {
									document.getElementById("user_password").focus();

									$('#password_check').removeClass('notError');
									$('#password_check').addClass('error');
									$('#password_check').html('비밀번호를 입력해주시기 바랍니다.');
								}

								if (emailFlag == false) {
									$('#email_check').focus();

									$('#email_check').removeClass('notError');
									$('#email_check').addClass('error');
									$('#email_check').html('※ 이메일 : example@example.com 형식(100글자 이내)');
								}

								if (contactFlag == false) {
									$('#contact_check').focus();

									$('#contact_check').removeClass('notError');
									$('#contact_check').addClass('error');
									$('#contact_check').html('※ 연락처 : [3글자] - [3글자 or 4글자] - [4글자] 형식');
								}

								if (zipcodeFlag == false) {
									$('#zipcode_check').focus();

									$('#zipcode_check').removeClass('notError');
									$('#zipcode_check').addClass('error');
									$('#zipcode_check').html(
											'주소를 검색하여 입력해주시기 바랍니다.');
								}

							}

						}

						/* 탈퇴 버튼 function */
						$('#userDelete').click(function() {
							userDelete();
						});

						function userDelete() {
							event.preventDefault();
							var message = '계정을 삭제하시면 복구하기 어렵습니다. 그래도 삭제하시겠습니까?'
							message += '\n\n삭제를 원하신다면 확인을 눌러주세요.';
							if (confirm(message)) {
								location.href = '/user/delete'
							} else {
								alert('삭제하지 않아주셔서 감사합니다');
							}
						}
						
						
						

						/* 도로주소명 관련 js */
						$('#DaumPostcode').click(function() {
							DaumPostcode();
						});

						function DaumPostcode() {
							new daum.Postcode(
									{
										oncomplete : function(data) {
											// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

											// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
											// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
											var roadAddr = data.roadAddress; // 도로명 주소 변수
											var extraRoadAddr = ''; // 참고 항목 변수

											// 법정동명이 있을 경우 추가한다. (법정리는 제외)
											// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
											if (data.bname !== ''
													&& /[동|로|가]$/g
															.test(data.bname)) {
												extraRoadAddr += data.bname;
											}
											// 건물명이 있고, 공동주택일 경우 추가한다.
											if (data.buildingName !== ''
													&& data.apartment === 'Y') {
												extraRoadAddr += (extraRoadAddr !== ''
														? ', '
																+ data.buildingName
														: data.buildingName);
											}
											// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
											if (extraRoadAddr !== '') {
												extraRoadAddr = ' ('
														+ extraRoadAddr + ')';
											}

											// 우편번호와 주소 정보를 해당 필드에 넣는다.
											document
													.getElementById('user_zipcode').value = data.zonecode;
											document
													.getElementById("user_roadname").value = roadAddr;
											document.getElementById(
													"user_detailadress")
													.focus();

										}
									}).open();
						}

					});/* end document.ready() */

})(jQuery);
