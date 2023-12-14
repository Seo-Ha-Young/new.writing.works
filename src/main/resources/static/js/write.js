//function openTitlePopup() {
// // 새로운 팝업을 현재 창의 중앙에 위치시키고 크기를 지정합니다.
//  const popupWidth = 600;
//  const popupHeight = 400;
//  const left = (window.screen.width - popupWidth) / 2;
//  const top = (window.screen.height - popupHeight) / 2;
//  const popupURL = 'popup.html'; // 팝업으로 열 HTML 파일 경로
//
//  // 팝업 창 열기
//  const popupWindow = window.open(popupURL, 'TitlePopup', `width=${popupWidth}, height=${popupHeight}, left=${left}, top=${top}`);
//
//  // 팝업 차단을 우회하기 위해 팝업이 정상적으로 열렸는지 확인합니다.
//  if (!popupWindow || popupWindow.closed || typeof popupWindow.closed === 'undefined') {
//    alert('팝업이 차단되었습니다. 팝업 차단을 해제해주세요.');
//  }
//
//}
function savePost() {

  const postContent = document.getElementById('write_word').value;
  console.log(postContent);
  const data = {
    post_content: postContent
  };
  console.log(data);
  fetch('/savePost', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return response.json();
  })
  .then(data => {
    console.log('글이 성공적으로 저장되었습니다:', data);

    window.location.href = '/html/board';
  })
  .catch(error => {
    console.error('글을 저장하는 중 오류가 발생했습니다:', error);
  });
}