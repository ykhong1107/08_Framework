// 스크롤 가장위로 올라가는 효과
const topBtn = document.querySelector("#topBtn");

topBtn.onclick = function(){
  window.scrollTo({
  top: 0,
  behavior: 'smooth' // 부드러운 스크롤 효과
  });
};

