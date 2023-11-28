/* Input값 */
const $inputId = document.getElementById("id");
const $inputPw = document.getElementById("password");
const $inputPw2 = document.getElementById("password2");
const $inputName = document.getElementById("name");
const $inputNickName = document.getElementById("nickname");
const $inputEmail = document.getElementById("email");
const $inputDate = document.getElementById("date_of_birth");
const $inputAddr = document.getElementById("main_address");
const $inputSubAddr = document.getElementById("sub_address");
const int_area = document.querySelector(".int-area");

const elSuccessMessage = document.querySelector(".success-message"); // div.success-message.hide
// 3. 실패 메시지 정보 가져오기 (글자수 제한 4~12글자)
const elFailureMessage = document.querySelector(".failure-message"); // div.failure-message.hide
const elFailureMessageName = document.querySelector(".failure-message-name"); // div.failure-message-name.hide
const elFailureMessageEmail = document.querySelector(".failure-message-Email"); // div.failure-message-name.hide
const elFailureMessageNickName = document.querySelector(
  ".failure-message-nickname"
); // div.failure-message-nickname.hide
// 4. 실패 메시지2 정보 가져오기 (영어 또는 숫자)
let elFailureMessageTwo = document.querySelector(".failure-message2"); // div.failure-message2.hide
// 3. 실패 메시지 정보 가져오기 (비밀번호 불일치)
let elMismatchMessage = document.querySelector(".mismatch-message"); // div.mismatch-message.hide
// 4. 실패 메시지 정보 가져오기 (8글자 이상, 영문, 숫자, 특수문자 미사용)
let elStrongPasswordMessage = document.querySelector(".strongPassword-message"); // div.strongPassword-message.hide

/* Input이 들어있는 DIV */
const inputId = document.getElementById("idBox");
const inputPw = document.getElementById("pwBox");
const inputPw2 = document.getElementById("pw2Box");
const inputName = document.getElementById("nameBox");
const inputNickName = document.getElementById("nickNameBox");
const inputEmail = document.getElementById("emailBox");
const inputDate = document.getElementById("dateBox");
const inputAddr = document.getElementById("addrBox");

// var email_rule =
//   /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
// var email_id = $("#email_id").val();
// var email_domain = $("#email_domain").val();
// var mail = "";

function createMessageElement() {
  const messageElement = document.createElement("div");
  messageElement.style.color = "red";
  return messageElement;
}

const idMessage = createMessageElement();
const idWarnning = document.createTextNode("아이디를 입력해 주세요!");
const pwMessage = createMessageElement();
const pwWarnning = document.createTextNode("비밀번호를 입력해 주세요!");
const pw2Message = createMessageElement();
const pw2Warnning = document.createTextNode("비밀번호가 일치하지 않습니다.!");
const nameMessage = createMessageElement();
const nameWarnning = document.createTextNode("이름을 입력해 주세요!");
const nicknameMessage = createMessageElement();
const nicknameWarnning = document.createTextNode("닉네임을 입력해 주세요!");
const emailMessage = createMessageElement();
const emailWarnning = document.createTextNode("이메일을 입력해 주세요!");
const dateMessage = createMessageElement();
const dateWarnning = document.createTextNode("생년월일을 입력해 주세요!");
const addrMessage = createMessageElement();
const addrWarnning = document.createTextNode("주소를 입력해 주세요!");
const subaddrMessage = createMessageElement();
const subaddrWarnning = document.createTextNode("상세주소를 입력해 주세요!");

function idLength(value) {
  return value.length >= 4 && value.length <= 12;
}
function onlyNumberAndEnglish(str) {
  return /^[A-Za-z0-9][A-Za-z0-9]*$/.test(str);
}
function strongPassword(str) {
  return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(
    str
  );
}
function isMatch(password1, password2) {
  return password1 === password2;
}
function regChk() {
  onkeyEmail();
  if (!$inputId.value) {
    $inputId.style.border = "2px solid red";
    elFailureMessage.classList.remove("hide");
    $inputId.focus();
  }
  if (!$inputPw.value) {
    $inputPw.style.border = "2px solid red";
    elStrongPasswordMessage.classList.remove("hide");
    $inputPw.focus();
  }
  if (!$inputPw2.value) {
    $inputPw2.style.border = "2px solid red";
    elMismatchMessage.classList.remove("hide");
    $inputPw2.focus();
  }
  onkeyName();

  onkeyNickName();
  if (!$inputAddr.value) {
    $inputAddr.style.border = "2px solid red";
    $inputAddr.focus();
  }
  if (!$inputSubAddr.value) {
    $inputSubAddr.style.border = "2px solid red";
    $inputSubAddr.focus();
  }
}
//여기부터 해라
document
  .getElementById("reg_form")
  .addEventListener("submit", function (event) {
    event.preventDefault();

    regChk;
  });
$inputId.onkeyup = function () {
  // 값을 입력한 경우
  if ($inputId.value.length !== 0) {
    // 영어 또는 숫자 외의 값을 입력했을 경우
    if (onlyNumberAndEnglish($inputId.value) === false) {
      elSuccessMessage.classList.add("hide");
      elFailureMessage.classList.add("hide");
      elFailureMessageTwo.classList.remove("hide"); // 영어 또는 숫자만 가능합니다
      $inputId.style.border = "2px solid red";
    }
    // 글자 수가 4~12글자가 아닐 경우
    else if (idLength($inputId.value) === false) {
      elSuccessMessage.classList.add("hide"); // 성공 메시지가 가려져야 함
      elFailureMessage.classList.remove("hide"); // 아이디는 4~12글자이어야 합니다
      elFailureMessageTwo.classList.add("hide"); // 실패 메시지2가 가려져야 함
      $inputId.style.border = "2px solid red";
    }
    // 조건을 모두 만족할 경우
    else if (idLength($inputId.value) || onlyNumberAndEnglish(inputId.value)) {
      elSuccessMessage.classList.remove("hide"); // 사용할 수 있는 아이디입니다
      elFailureMessage.classList.add("hide"); // 실패 메시지가 가려져야 함
      elFailureMessageTwo.classList.add("hide"); // 실패 메시지2가 가려져야 함
      $inputId.style.border = "2px solid green";
    }
  }
  // 값을 입력하지 않은 경우 (지웠을 때)
  // 모든 메시지를 가린다.
  else {
    $inputId.style.border = "";
    elSuccessMessage.classList.add("hide");
    elFailureMessage.classList.add("hide");
    elFailureMessageTwo.classList.add("hide");
  }
};
$inputPw.onkeyup = function () {
  // console.log(elInputPassword.value);
  // 값을 입력한 경우
  if ($inputPw.value.length !== 0) {
    if (strongPassword($inputPw.value)) {
      elStrongPasswordMessage.classList.add("hide"); // 실패 메시지가 가려져야 함
      $inputPw.style.border = "2px solid green";
    } else {
      elStrongPasswordMessage.classList.remove("hide"); // 실패 메시지가 보여야 함
      $inputPw.style.border = "2px solid RED";
    }
  }
  // 값을 입력하지 않은 경우 (지웠을 때)
  // 모든 메시지를 가린다.
  else {
    elStrongPasswordMessage.classList.add("hide");
    $inputPw.style.border = "";
  }
};
$inputPw2.onkeyup = function () {
  // console.log(elInputPasswordRetype.value);
  if ($inputPw2.value.length !== 0) {
    if (isMatch($inputPw.value, $inputPw2.value)) {
      elMismatchMessage.classList.add("hide"); // 실패 메시지가 가려져야 함
      $inputPw2.style.border = "2px solid green";
    } else {
      elMismatchMessage.classList.remove("hide"); // 실패 메시지가 보여야 함
      $inputPw2.style.border = "2px solid red";
    }
  } else {
    elMismatchMessage.classList.add("hide"); // 실패 메시지가 가려져야 함
    $inputPw2.style.border = "";
  }
};

const onkeyName = ($inputName.onkeyup = function () {
  if (!$inputName.value) {
    elFailureMessageName.classList.remove("hide"); // 실패 메시지가 보여야 함
    $inputName.style.border = "2px solid red";
  } else if ($inputName.value.length === 0) {
    $inputName.style.border = "";
    elFailureMessageName.classList.add("hide"); // 실패 메시지가 가려져야 함
  } else {
    elFailureMessageName.classList.add("hide"); // 실패 메시지가 가려져야 함
    $inputName.style.border = "2px solid green";
  }
});
const onkeyNickName = ($inputNickName.onkeyup = function () {
  if (!$inputNickName.value) {
    elFailureMessageNickName.classList.remove("hide"); // 실패 메시지가 보여야 함
    $inputNickName.style.border = "2px solid red";
  } else if ($inputNickName.value.length === 0) {
    $inputNickName.style.border = "";
    elFailureMessageNickName.classList.add("hide"); // 실패 메시지가 가려져야 함
  } else {
    elFailureMessageNickName.classList.add("hide"); // 실패 메시지가 가려져야 함
    $inputNickName.style.border = "2px solid green";
  }
});

const onkeyEmail = ($inputEmail.onkeyup = function () {
  var email = $inputEmail.value;
  var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
  if (!$inputEmail.value) {
    $inputEmail.style.border = "2px solid red";
    elFailureMessageEmail.classList.remove("hide");
    $inputEmail.focus();
  } else if (exptext.test(email) == false) {
    //이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우
    elFailureMessageEmail.classList.remove("hide");
    $inputEmail.style.border = "2px solid red";
    $inputEmail.focus();
  } else if ($inputEmail.value.length === 0) {
    $inputEmail.style.border = "";
    elFailureMessageEmail.classList.add("hide");
  } else {
    elFailureMessageEmail.classList.add("hide");
    $inputEmail.style.border = "2px solid green";
  }
});

// if (!email_id) {
//   alert("이메일을 입력해주세요");
//   $("#email_id").focus();
//   return false;
// }
// if (!email_domain) {
//   alert("도메인을 입력해주세요");
//   $("#email_domain").focus();
//   return false;
// }
// mail = email_id + "@" + email_domain;
// $("#mail").val(mail);

// if (!email_rule.test(mail)) {
//   alert("이메일을 형식에 맞게 입력해주세요.");
//   return false;
// }

// function setEmailDomain(domain) {
//   $("#email_domain").val(domain);
// }
