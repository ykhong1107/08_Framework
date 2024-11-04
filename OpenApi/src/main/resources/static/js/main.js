/** 비동기로 공공데이터 - 시도별 미세먼지 정보조회 */
const getAirQuality = async (cityName) => {

  // 서비스키를 서버에 비동기 POST 방식으로 요청해서 가져오기(숨기기)
  /* const serviceKey = '9xWjAl6ZtoasPt9fAysmrRZHmbZqqw0%2F3w4jcvzRTBcwlYvZIqrvo0wE5ksX1fgYwIO%2FrYMHyGpbqkvTDf1OZQ%3D%3D' */
  // -> 비동기는 코드수행을 기다리지 않고 다음코드를 수행
  // -> 서비스키를 얻어오지 못한 상태에서
  //    공공데이터를 요청하는 문제가 발생!!

  // * 해결방법 : 비동기 -> 동기식 요청으로 변경(async/await)

  /*
    async : 비동기 요청코드가 포함된 함수에 작성하는 키워드
      - 이 키워드가 붙은 함수는 항상 Promise 객체를 반환함
      (Promise : 비동기 데이터가 돌아올것을 약속하는 객체)

      - async가 포함된 함수에서
        명시적으로 Promise 객체 반환 구문을 작성하지 않을경우
        자동으로 Promise.resolve() 형태로 반환된다.
        (약속했던 결과가 돌아옴 == Promise.resolve())
        (fetch API에서 첫 번째 then에 사용되는 매개변수 response)


    await : 비동기 요청을 동기식 요청으로 변환
      - async가 작성된 함수 내에서만 사용가능

      - await 키워드가 붙은 함수가 종료될 때 까지 코드해석을 일시중지
        (동기식)
  */

  // /getServiceKey 요청에 대한 응답이 돌아올 때 까지 대기(await)
  // - 응답이 돌아오면 response1 변수에
  //   응답결과(첫 번째 then의 response)가 저장됨
  const response1 = await fetch("/getServiceKey", { method : "POST" });
  
   // 응답 데이터를 text 형태로 파싱될 때 까지 대기(await)
  // -> serviceKey에 서버에서 조회한 값이 저장됨
  const serviceKey = await response1.text();

  console.log(serviceKey);



  // 요청주소조합(주소 + 쿼리스트링)
  // get방식은 쿼리스트링 ? 으로 보내야함!!!
  let url = `http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty`;

  url += `?serviceKey=${serviceKey}`;
  url += `&sidoName=${cityName}`;
  url += `&returnType=json`;
  url += `&numOfRows=1`;
  url += `&pageNo=1`;
  url += `&ver=1.0`;

  // fetch API(AJAX)를 이용해서 공공데이터 조회 -> 동기식으로 변경

  try{

    const response2 = await fetch(url);
  
    if(!response2.ok) throw new Error("공공데이터 조회실패");
  
    const data = await response2.json();

    // 필요한 데이터만 추출
    const item = data.response.body.items[0];

    // console.log(data);
    console.log(`미세먼지 농도 : ${item['pm10Value']} ㎍/㎥`);
    console.log(`미세먼지 등급 : ${item['pm10Grade']}`);
    
    console.log(`초미세먼지 농도 : ${item['pm25Value']} ㎍/㎥`);
    console.log(`초미세먼지 등급 : ${item['pm25Grade']}`);

    // 화면에 출력

    const pm10Grade = document.querySelector("#pm10Grade");
    const pm10GradeText = document.querySelector("#pm10GradeText");
    const pm10Value = document.querySelector("#pm10Value");

    const pm25Grade = document.querySelector("#pm25Grade");
    const pm25GradeText = document.querySelector("#pm25GradeText");
    const pm25Value = document.querySelector("#pm25Value");

    // 이모지/등급 배열
    const gradeEmoji = ['😄', '🙂', '😷', '🤢'];
    const gradeText = ['좋음', '보통', '나쁨', '매우나쁨'];

    pm10Grade.innerText = gradeEmoji[item.pm10Grade - 1];
    pm10GradeText.innerText = gradeText[item.pm10Grade - 1];
    pm10Value.innerText     = `미세먼지 농도 : ${item['pm10Value']} ㎍/㎥`;

    pm25Grade.innerText = gradeEmoji[item.pm25Grade - 1];
    pm25GradeText.innerText = gradeText[item.pm25Grade - 1];
    pm25Value.innerText     = `초미세먼지 농도 : ${item['pm25Value']} ㎍/㎥`;



  }catch(err){
    console.error(err);
  }
}

// 조회버튼 클릭 시
document.querySelector("#selectBtn").addEventListener("click", () => {
  const cityName =document.querySelector("#cityName").value;
  getAirQuality(cityName);

});

// 페이지 로딩 완료 시
document.addEventListener("DOMContentLoaded", () => {
  getAirQuality("서울");
});

//   ★★★★★★★★★★★★★★★★★★ 얘네는 이제 필요가 없음 ★★★★★★★★★★★★★★★★★★
//   fetch(url)
//   .then(response => {
//     if(!response.ok) throw new Error("공공데이터 조회실패");
//     return response.json();
//   })

//   .then(data => {
 

//    // 필요한 데이터만 추출

   
//    console.log(item);

//   })
//   .catch(err => console.error(err));
// }

