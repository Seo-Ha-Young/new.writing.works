window.onload = function () {
  var post_no = document.getElementById("dto_no").textContent;
  console.log(post_no);
  var member_no = document.getElementById("member_info_no").textContent;
  var nickname = document.getElementById("member_info_nickname").textContent;
  console.log("member_no=", member_no);
  console.log("nickname=", nickname);

      $(".delete_post").on("click", function () {
        let delete_no = confirm("이 글을 삭제하시겠습니까?")
        if(delete_no == true) {
        console.log(post_no);
        $.ajax({
          url: "/html/delete_post/" + post_no,
          type: "delete",
          contentType: "application/json; charset:utf-8",
          datatype: "text",
          success: function (result) {
            alert(result);
            if(result == post_no+"번이 삭제되었습니다")
            window.location.replace("/html/board")
          }
        });
        } else {
        return;
        }
      });


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
        str +=
          '<a class="nickname" style = "font-weight: bold;">' +
          review.nickname +
          "</a><br><br>";
        str += '<a class="content">' + review.review_content + "</a><br><br>";
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
    var data = {
      post_no: post_no,
      review_content: content.val(),
      member_no: member_no,
    };
    console.log(data);
    if (member_no != "") {
      $.ajax({
        url: "/reviews/" + post_no,
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success: function (result) {
          console.log("result :" + result);
          content.val("");
          getPostReviews();
        },
      });
    } else {
      alert("로그인 후 이용하세요");
    }
  });

  var modal = document.querySelector(".modal");
  var review_no;
  var inputText = $('input[name="text"]');
  var inputNickname = $('input[name="nickname"]');

  //리뷰 수정창 띄우기
  $(".reviewList").on("click", ".post_no", function () {
    var targetReview = $(this);
    if (member_no != "") {
      review_no = targetReview.data("no");
      console.log("review_no:" + review_no);
      inputNickname.val(targetReview.find(".nickname").text());
      inputText.val(
        targetReview.find(".content").clone().children().remove().end().text()
      );
      console.log("review : " + inputNickname.val() + " " + inputText.val());
      if (inputNickname.val() == nickname) {
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
      nickname: nickname,
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
    var th = document.getElementsByTagName("head")[0];
    var s = document.createElement("script");
    s.setAttribute("type", "text/javascript");
    s.setAttribute("src", jsname);
    th.appendChild(s);
  }

  addJavascript("/js/like.js");
};
