window.onload = function () {
  var post_no = document.getElementById("dto_no").textContent;
  console.log(post_no);
  var id = document.getElementById("member_info").textContent;
  console.log("id=", id);

  //전체 리뷰 보여주기 기능
  function getPostReviews() {
    function formatTime(str) {
      var date = new Date(str);
      return (
        date.getFullYear() +
        "/" +
        (date.getMonth() + 1) +
        "/" +
        date.getDate() +
        " " +
        date.getHours() +
        ":" +
        date.getMinutes()
      );
    }
    $.getJSON("/reviews/" + post_no + "/all", function (arr) {
      var str = "";
      $.each(arr, function (idx, review) {
        str += '<div class="post_no" data-no=' + review.no + " ><br>";
        str += '<a class="id">' + review.id + "</a><br>";
        str += '<a class="content">' + review.review_content + "</a><br>";
        str += '<a class="text">' + formatTime(review.regDate) + "</a>";
        str += "</div><br>";
      });

      $("#reviewList").html(str);
    });
  }
  getPostReviews();

  var content = $('textarea[name="reply"]');
  //리뷰 등록 기능
  $(".btn_reply").click(function () {
    var data = { post_no: post_no, review_content: content.val(), id: id };
    console.log(data);
    if(id != "") {
    $.ajax({
      url: "/reviews/" + post_no,
      type: "POST",
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "text",
      success: function (result) {
        console.log("result :" + result);
        getPostReviews();
      }
    });
     } else {
      alert("로그인 후 이용하세요");
      }

  });

  var modal = document.querySelector(".modal");
  var review_no;
  var inputText = $('input[name="text"]');
  var inputId = $('input[name="id"]');

  //리뷰 수정창 띄우기
  $(".reviewList").on("click", ".post_no", function () {
    var targetReview = $(this);
   if(id != "") {
    review_no = targetReview.data("no");
    console.log("review_no:" + review_no);
    inputId.val(targetReview.find(".id").text());
    inputText.val(
      targetReview.find(".content").clone().children().remove().end().text()
    );
    console.log("review : " + inputId.val() + " " + inputText.val());
    if( inputId.val() == id) {
    modal.classList.toggle("show");
    } else {
        alert("다른 사람의 댓글을 수정할 수 없습니다.");
    }
         } else {
          alert("로그인 후 수정 가능합니다.");
          }
  });

  //리뷰 수정기능
  $(".modifyBtn").on("click", function () {
    var data = {
      no: review_no,
      id: id,
      post_no: post_no,
      review_content: inputText.val(),
    };
    console.log(data);

    $.ajax({
      url: "/reviews",
      type: "PUT",
      data: JSON.stringify(data),
      contentType: "application/json; charset:utf-8",
      dataType: "text",
      success: function (result) {
        console.log("result : " + result);
        getPostReviews();
        close();
      },
    });
  });

  //리뷰 삭제기능
  $(".removeBtn").on("click", function () {
    console.log(review_no);
    $.ajax({
      url: "/reviews/" + review_no,
      type: "delete",
      contentType: "application/json; charset:utf-8",
      datatype: "text",
      success: function (result) {
        console.log("삭제된 리뷰 번호:" + result);
        getPostReviews();
        close();
      },
    });
  });

      //리뷰 수정창 닫기 기능
      function close() {
        modal.classList.remove("show");
      }
      $(".close").on("click", close);

      function addJavascript(jsname) {
      	var th = document.getElementsByTagName('head')[0];
      	var s = document.createElement('script');
      	s.setAttribute('type','text/javascript');
      	s.setAttribute('src',jsname);
      	th.appendChild(s);
      }

      addJavascript('/js/like.js');

  };


