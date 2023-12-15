function savePost() {
  var postContent = document.getElementById('write_word2').value;
  var image_no = null;
  console.log(postContent);
    if(postContent == "") {
      postContent = document.getElementById('write_word').value;
      console.log(postContent);
      image_no = emoticons.indexOf(todayEmoticon)+1;
    }

 console.log("image_no"+image_no)
  const data = {
    post_content: postContent, image_no:image_no
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
    console.log('글이 성공적으로 저장되었습니다:', data);/**/

    window.location.replace('/html/board');
  })
  .catch(error => {
    console.error('글을 저장하는 중 오류가 발생했습니다:', error);
  });
}