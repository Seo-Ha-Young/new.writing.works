$loginButton = document.getElementById("login");
$idInput = document.getElementById("id");
$idInputBox = document.querySelector(".idInput");
$pwInput = document.getElementById("password");
$pwInputBox = document.querySelector(".pwInput");
$loginForm = document.getElementById("loginForm");

const idmessage = document.createElement("div");
const pwmessage = document.createElement("div");
const idWarnning = document.createTextNode("아이디를 입력해 주세요!");
const pwWarnning = document.createTextNode("비밀번호를 입력해 주세요!");

function loginChk() {
  console.log("클릭");
  idmessage.innerHTML = "";
  pwmessage.innerHTML = "";
  if (!$idInput.value) {
    console.log("아이디비었다");
    idmessage.appendChild(idWarnning);
    $idInputBox.appendChild(idmessage);
    $idInputBox.style.color = "red";
    $idInput.style.border = "2px solid red";
    $idInput.focus();
  } else {
    console.log("id는있음");
    $idInput.style.border = "2px solid green";
  }
  if (!$pwInput.value) {
    console.log("비번이비었다");
    pwmessage.appendChild(pwWarnning);
    $pwInputBox.appendChild(pwmessage);
    $pwInputBox.style.color = "red";
    $pwInput.style.border = "2px solid red";
    $pwInput.focus();
  } else {
    $pwInput.style.border = "2px solid green";
  }
}

document
  .getElementById("loginForm")
  .addEventListener("submit", function (event) {
    event.preventDefault();

    loginChk();
  });
