

  var post_no = document.getElementById("dto_no").textContent;
  var id = document.getElementById("member_info").textContent;
  console.log("id=", id);
          //  var id = "닉네임"+(Math.floor(Math.random()*5+1));
            var txt;
            var good = "이미 추천을 했습니다."
            var bad = "이미 비추천을 했습니다.";
            console.log("id ="+id);
            //현재 계정 추천 유무 확인
            var _data = { post_no:post_no, id:id };
                function getLatLng() {
                    return new Promise(function (resolve, reject) {
                        $.ajax({
                            url: '/like/"+post_no+"/id',
                            type: 'post',
                            data:JSON.stringify(_data),
                            contentType:'application/json; charset=utf-8',
                            dataType: 'text',
                            success: resolve,
                            error: reject
                        });
                    });
                }
            const promise1 = getLatLng();
            console.log("word", promise1);

            //누르면 추천 기능
            $(".goodBtn").on("click", function() {
            getLatLng();
                var _data = { post_no:post_no, good:1, id:id };
               promise1.then( word => {
                console.log(word);
                if ( word == bad) {
                 console.log(word);
                 alert(word);
                } else {
                     $.ajax({
                          url:"/like/"+post_no+"/good",
                          type:'POST',
                          data:JSON.stringify(_data),
                          contentType:'application/json; charset=utf-8',
                          dataType: 'text',
                          success: function(result){
                              console.log("result :"+result);
                              alert(result);
                          promise1.then(data => {
                            data = result;
                          });
                              $(".good_Cnt").load(location.href+" .good_Cnt a");
                          }
                     });
                }
                });
            });
            //누르면 비추 가능
          $(".badBtn").on("click", function() {
          getLatLng();
                var _data = { post_no:post_no, bad:1, id:id };
                promise1.then( word => {
                console.log(word);
                if ( word == good) {
                 console.log(word);
                 alert(word);
                } else {
                    $.ajax({
                      url:"/like/"+post_no+"/bad",
                      type:'POST',
                      data:JSON.stringify(_data),
                      contentType:'application/json; charset=utf-8',
                      dataType: 'text',
                      success: function(result){
                          console.log("result :"+result);
                          alert(result);
                          promise1.then(data => {
                            data = result;
                          });
                          $(".like").load(window.location.href + " .like");
                      }
                    });
                }
                });
          });


