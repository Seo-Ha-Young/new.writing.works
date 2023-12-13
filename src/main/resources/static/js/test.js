let selectedValue = "1";
let selectMode = "";
let selectWord = "";
let uploadBtn = "";
const $write_warp = document.getElementById("write_warp");
const $write_mode1 = document.getElementById("write_mode1");
const $write_mode2 = document.getElementById("write_mode2");
const $upload = document.getElementById("upload");
const $upload2 = document.getElementById("upload2");
const $mod = document.querySelector("#mod");
const $timer = document.querySelector("#timer");
const $write_word = document.getElementById("write_word");
const $write_word2 = document.getElementById("write_word2");
let $boarder_wrap = document.getElementById("#board_wrap");
let write_mod1 = document.querySelector("#timers");
let write_mod2 = document.querySelector("#timers2");

let timer = $timer.value; //기준시간 작성

const sentences = [
  "비가 구슬프게 쏟아지고 있었다.",
  "그의 눈동자는 깊고 어두웠다.",
  "어디선가 잔잔한 음악소리가 들려왔다.",
  "우울해서, 시골로 향했다.",
  "바다를 보고싶다는 생각에, 나는.",
  "끝없이 긴 동굴 속을 걷는 느낌이었다.",
  "아침이 밝아오자, 그는 일어나 눈을 비비었다.",
  "그곳에선 무엇이든 가능했다.",
  "어두워지자, 그들은 움직이기 시작했다.",
  "그녀의 목소리는 나를 언제나 안정시켰다.",
  "평화로운 시골마을에는 늘 위험이 도사리고 있다.",
  "쓸쓸한 바다 위에서 나는 노래를 부르기 시작했다.",
  "그 날, 바람이 나를 울렸다.",
  "아직도 그녀를 잊을 수 없다.",
  "어디선가 영웅을 맞이하는 소리가 들려왔다.",
  "그들은 강을 건너고 숲을 지나 여기까지 왔다.",
  "햇살이 가득한 날, 나는 나홀로 바닷가를 걷고 있었다.",
  "어릴 적 나는, 대단히 성실했다고 생각했다.",
  "내가 이 글을 쓰는 지금, 나는 누군가의 꿈에서 깨어난 듯한 기분이 든다.",
  "이 세상에는 태어나면서부터 이기고 싶은 게 있었다.",
  "내가 어떤 사람이 될지는, 어떤 책을 읽느냐에 달렸다.",
  "매일 밤 나는 아무도 모르게 자고 일어나서, 책상 위에 있는 펜으로 무언가를 적는다.",
  "나는 이제부터 나 자신에게, 그리고 세상에게 맞서 싸울 용기를 갖추기로 했다.",
  "내가 살아온 이유를 찾고 싶어, 새로운 도시로 떠나기로 했다.",
  "내가 좋아하는 음악을 듣고 있으면, 어느새 내 삶도 노래처럼 흘러간다.",
  "어릴 적부터 나는, 어디서든 책을 읽기 시작하면 시간 가는 줄도 모른 채 집중했다.",
  "내가 인생에서 가장 중요하게 생각하는 것은, 시간이다.",
  "이제 내가 쓰는 글은, 나를 지나 친구에게 전해지고 친구의 친구에게 전해져서, 어느 날 세상을 변화시킬지도 모른다.",
  "내 인생은 책과 함께했다. 그래서 나는 어떤 일이든, 책과 연관시키려고 한다.",
  "어떤 도전도, 이제부터는 단 한 번도 실패하지 않을 것이다.",
  "나는 언제나, 인생에서 한 가지 일에 집중하고 싶었다.",
  "이 세상에는 정말로 일어날 수 없는 일이라고 생각했던 것이, 언제나 내 인생에서 일어났다.",
  "내가 꿈꾸는 것을 이룰 수 있다는 것을, 이제는 알게 되었다.",
  "나는 삶에서 언제나, 새로운 도전을 찾고자 노력했다.",
];
const emoticons = [
  "img01.jpg",
  "img02.jpg",
  "img03.jpg",
  "img04.jpg",
  "img05.jpg",
  "img06.jpg",
  "img07.jpg",
  "img08.jpg",
  "img09.jpg",
  "img10.jpg",
  "img11.jpg",
  "img12.jpg",
  "img13.jpg",
  "img14.jpg",
  "img15.jpg",
  "img16.jpg",
  "img17.jpg",
  "img18.jpg",
  "img19.jpg",
  "img20.jpg",
  "img21.jpg",
  "img22.jpg",
  "img23.jpg",
  "img24.jpg",
  "img25.jpg",
  "img26.jpg",
  "img27.jpg",
  "img28.jpg",
  "img29.jpg",
  "img30.jpg",
  "img31.jpg",
  "img32.jpg",
  "img33.jpg",
  "img34.jpg",
  "img35.jpg",
  "img36.jpg",
  "img37.jpg",
  "img38.jpg",
  "img39.jpg",
  "img40.jpg",
  "img41.jpg",
  "img42.jpg",
  "img43.jpg",
  "img44.jpg",
  "img45.jpg",
  "img46.jpg",
];

const todayEmoticon = emoticons[Math.floor(Math.random() * emoticons.length)];

const etImg = document.createElement("img"); //랜덤으로 돌려서 나온 이미지
etImg.src = `../img/${todayEmoticon}`;
etImg.width = 300;

const homePage = document.getElementById("img");
homePage.appendChild(etImg);

function getRandomSentence() {
  const index = Math.floor(Math.random() * sentences.length);
  return sentences[index];
}

function getSelectedValue() {
  selectedValue = $mod.value;
}

function fadeInElement(element) {
  $(element).fadeIn(1000);
}

function fadeOutElement(element) {
  $(element).remove();
}

function startTimer(totalTime) {
  let elapsedSeconds = 0;
  let runningTime = totalTime;
  const message = document.createElement("div");
  message.classList.add("timer-message");
  const existingMessage = selectMode.querySelector(".timer-message");

  intervalId = setInterval(function () {
    if (existingMessage) {
      existingMessage.remove();
    }

    if (elapsedSeconds >= totalTime) {
      clearInterval(intervalId);
      console.log("타이머 종료!");
      message.textContent = `타이머 종료`;
      selectWord.readOnly = true;
      selectMode.appendChild(message);
      $(uploadBtn).fadeIn(1000);
    } else {
      let min = Math.floor(runningTime / 60);
      let sec = runningTime % 60;
      console.log("남은 시간: " + min + "분" + sec + "초");
      message.textContent = `남은 시간: ${min}분 ${sec}초`;
      selectMode.appendChild(message);

      elapsedSeconds++;
      runningTime--;
    }
  }, 1000);
}

function buttonClick() {
  console.log("Selected Value:", selectedValue);
  console.log($timer.value);

  if (selectedValue === "1") {
    selectMode = write_mod1;
    selectWord = $write_word;
    uploadBtn = $upload;
  } else if (selectedValue === "2") {
    selectMode = write_mod2;
    selectWord = $write_word2;
    uploadBtn = $upload2;
  }

  if (!$timer.value) {
    alert("목표 시간을 입력해 주세요");
    $timer.focus();
  } else if (selectedValue === "1") {
    startTimer($timer.value);
    fadeOutElement("#board_wrap");
    fadeInElement("#write_mode1");
    fadeOutElement("#write_warp");
  } else if (selectedValue === "2") {
    startTimer($timer.value);
    fadeOutElement("#board_wrap");
    fadeInElement("#write_mode2");
    fadeOutElement("#write_warp");
    $write_word2.value = getRandomSentence();
  }
}

