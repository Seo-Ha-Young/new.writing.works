 function savePost() {
    const postContent1 = document.getElementById('write_word2').value;
    let data = {};
    let post_name = prompt("게시글 제목");

    if (postContent1.trim() !== '') {
      data = {
        post_content: postContent1, post_name: post_name
      };
    } else {
      const postContent2 = document.getElementById('write_word').value;
      if (postContent2.trim() !== '') {
        var image_no = emoticons.indexOf(todayEmoticon)+1;
        data = {
          post_content: postContent2, image_no:image_no, post_name: post_name
        };
      } else {
        console.error('모든 필드가 비어있습니다.');
        return;
      }
    }
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
    console.log('글이 성공적으로 저장되었습니다:', data);/**/

    window.location.replace('/html/board');
  })
  .catch(error => {
    console.error('글을 저장하는 중 오류가 발생했습니다:', error);
  });
}