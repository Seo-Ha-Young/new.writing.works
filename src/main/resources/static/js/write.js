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
    console.log('글이 성공적으로 저장되었습니다:', data);/**/

    window.location.href = '/html/board';
  })
  .catch(error => {
    console.error('글을 저장하는 중 오류가 발생했습니다:', error);
  });
}