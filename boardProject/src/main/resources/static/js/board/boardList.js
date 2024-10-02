// 페이지이동을 위한 버튼 모두 얻어오기
const pageNoList = document.querySelectorAll(".pagination a");

/*
  [Array 또는 NodeList에서 제공하는 forEach() 메서드]

  배열.forEach( (item, index) => {} )

  - 배열 내 요소 개수만큼 반복
  - 매 반복마다 {} 내부 코드가 수행
  - item : 0번 인덱스부터 차례대로 하나씩 요소를 얻어와 저장하는 변수
  - index : 현재 반복 접근중인 index
 */
// 페이지 이동 버튼이 클릭 되었을 때
pageNoList?.forEach( (item, index) => {

  // 클릭 되었을 때
  item.addEventListener("click", e => {
    e.preventDefault();

    // 만약 클릭된 a태그에 "current" 클래스가 있을경우
    // == 현재 페이지 숫자를 클릭한 경우
    if(item.classList.contains("current")){
      return;
    }


    const pathname = location.pathname; // 현재 게시판 조회 요청 주소

    // 클릭된 버튼이 <<, <, >, >>인 경우
    // console.log(item.innerText);
    switch(item.innerText){
      case '<<' : // 1페이지로 무조건 이동
        location.href = pathname + "?cp=1";
        break;

      case '<'  : // 이전페이지
        location.href = pathname + "?cp=" + pagination.prevPage;
        break;

      case '>'  : 
        location.href = pathname + "?cp=" + pagination.nextPage;
        break;
        
      case '>>' : 
        location.href = pathname + "?cp=" + pagination.maxPage;
        break;

      default : // 클릭한 숫자 페이지로 이동
        location.href = pathname + "?cp=" + item.innerText;
    }


  });





})