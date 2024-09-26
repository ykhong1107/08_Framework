// 쿠키에 저장된 여러 값 중 key가 일치하는 value 반환
function getCookie(key){

  // 1. cookie 전부 얻어오기(string)
  const cookies = document.cookie; // "K=V;K=V;..." 
  // console.log(cookies);

  // 2.";" 을 구분자로 삼아서 배열 형태로 쪼개기(split)
  const arr = cookies.split(";"); // ["K=V", "K=V"]
  // console.log(arr);

  // 3. 쪼개진 배열 요소를 하나씩 꺼내서
  //  JS 객체에 K:V 형태로 추가

  const cookieObj = {}; // 빈 객체 생성

  for(let entry of arr){
    // entry == "K=V"
    // -> "=" 기준으로 쪼개기(split)
    const temp = entry.split("="); // ["K", "V"]

    cookieObj[temp[0]] = temp[1];
  }

  // 매개변수로 전달 받은 key와 일치하는 value 반환
  return cookieObj[key];
}

// HTML 로딩(랜더링)이 끝난 후 수행
document.addEventListener("DOMContentLoaded", () => {

  const saveEmail = getCookie("saveEmail"); // 쿠키에 저장된 Email 얻어오기

  // 저장된 이메일이 없을 경우
  if(saveEmail == undefined) return; 

  const memberEmail 
    = document.querySelector("#loginForm input[name=memberEmail]");

  const checkbox 
    = document.querySelector("#loginForm input[name=saveEmail]");

  // 로그인 상태인 경우 함수 종료
  if(memberEmail == null) return;

  // 이메일 입력란에 저장된 이메일 출력
  memberEmail.value = saveEmail;

  // 이메일 저장 체크박스를 체크 상태로 바꾸기
  checkbox.checked = true;
});


// --------------------------------------------------------------

// tbody 태그
const memberList = document.querySelector("#memberList");

/* 메인 페이지 회원 목록 비동기 조회 함수 */
const selectMemberList = () => {

  // 1) 비동기로 모든 회원의 
  //  회원번호, 이메일, 탈퇴상태 조회하기
  fetch("/selectMemberList")
  .then(response => {
    // 응답 성공 시 JSON형태의 응답 데이터를 JS 객체로 변경 
    if(response.ok) return response.json();
    throw new Error("조회 오류");
  })
  .then(list => {
    // console.log(list);

    // 1) #memberList 기존 내용 없애기
    memberList.innerHTML = "";

    // 2) 조회 결과인 list를 반복 접근해서 
    //   #memberList에 조회된 내용으로 tr,td,th 만들어 넣기
    list.forEach(member => {
      // 매개변수 member == 조회된 list에서 하나씩 꺼낸 요소

      // tr 요소 만들기
      const tr = document.createElement("tr");

      // th 만들어서 회원 번호 세팅
      const th1 = document.createElement("th");
      th1.innerText = member.memberNo;

      // td 만들어 회원 이메일 세팅
      const td2 = document.createElement("td");
      td2.innerText = member.memberEmail;

      // th 만들어서 탈퇴 여부 세팅
      const th3 = document.createElement("th");
      th3.innerText = member.memberDelFl;

      // th > button 만들어서 "로그인" 글자 세팅
      const th4 = document.createElement("th");
      const loginBtn = document.createElement("button");
      loginBtn.innerText = "로그인";
      th4.append(loginBtn);

      // 만약, 탈퇴상태인 경우 로그인버튼 비활성화
      if(member.memberDelFl === 'Y'){
        loginBtn.disabled = true;
      }else{

        // 탈퇴 상태가 아닌경우
        // 만들어진 로그인 버튼에 클릭이벤트 추가
        loginBtn.addEventListener("click", () => {

          // body 태그 제일 마지막에 form 태그를 추가해
          // 제출하는 형식으로 코드작성
          // 왜? POST 방식 요청을 하고싶기 때문에

          const form = document.createElement("form");
          form.action = "/directLogin";
          form.method = "POST";

          const input = document.createElement("input");
          input.type = "hidden";
          input.name = "memberNo";
          input.value = member.memberNo;
  
          form.append(input); // form 자식으로 input 추가

          // body 태그 자식으로 form 추가
          document.querySelector("body").append(form);

          form.submit(); // 제출

        })

      }

      // th > button 만들어서 "비밀번호 초기화" 글자 세팅
      const th5 = document.createElement("th");
      const initBtn = document.createElement("button");
      initBtn.innerText = "비밀번호 초기화";
      th5.append(initBtn);

      initBtn.addEventListener("click", () => {
        
        fetch("/resetPw", {
          method : "POST",
          headers : {"Content-Type" : "application/json"},
          body : member.memberNo
        })
        .then(response => {
          if(response.ok) return response.text();
          throw new Error("비밀번호 초기화 오류");
        })
        .then(result => {
          if(result > 0) alert("비밀번호가 초기화 되었습니다.");
          else alert("실패!");
        })
        .catch(err => console.error(err));
      })

      // th > button 만들어서 "탈퇴 상태 변경" 글자 세팅
      const th6 = document.createElement("th");
      const changeBtn = document.createElement("button");
      changeBtn.innerText = "탈퇴 상태 변경";
      th6.append(changeBtn);

      changeBtn.addEventListener("click", () => {

        fetch("/changeStatus", {
          method : "PUT",
          headers : {"Content-Type" : "application/json"},
          body : member.memberNo
        })
        .then(response => {
          if(response.ok) return response.text();
          throw new Error("탈퇴상태변경 오류")
        })
        .then(result => {
          if(result > 0)
          selectMemberList();
        })
        .catch(err => console.error(err));



      })

      // tr에 만든 th,td 모두 추가
      tr.append(th1, td2, th3, th4, th5, th6)

      // #memberList에 tr 추가
      memberList.append(tr);
    })


  })
  .catch(err => console.error(err));

}



/* 페이지 로딩(렌더링) 끝난 후 수행 */
document.addEventListener("DOMContentLoaded", () => {
  selectMemberList();
})

