  var post_no =  document.getElementById("dto_no").textContent;

async function showAvatar() {
  // JSON 읽기
  let response = await fetch("/like/"+post_no+"/all");
  let user = await response.json();

  await new Promise((resolve, reject) => setTimeout(resolve, 3000));

  return user;
}