
// 1.서버 시간 저장 (GET 요청으로): SaveTimeServlet 호출
function saveServerTime() { 
    const displayElement = document.getElementById("serverTimeDisplay");
    
    // SaveTimeServlet (GET) 요청으로 보냅니다...
    fetch("../01/server-now", {
        method: 'GET',
    }) 
    .then(response => {
        if (!response.ok) {
            throw new Error('서버 응답 오류: ' + response.status);
        }
        return response.json(); 
    })
    .then(data => {
        // SaveTimeServlet의 응답 키 = 'serverTime'
        const time = data.serverTime;
        displayElement.textContent = time;
    })
    // 예외처리 오류처리
    .catch(error => {
        console.error('저장실패:', error);
        displayElement.textContent = "저장실패";
    });
        
    return false; 
}


document.addEventListener("DOMContentLoaded", ()=>{
    
    // 2. 시간 업데이트 함수
    function updateClock() {
        // 현재 시간 정보 가져오기
        const now = new Date(); 
        const timeString = now.toString();
        const timeElement = document.getElementById("clock");
        if (timeElement) {
            timeElement.textContent = timeString;
        }
    }

    // 페이지를 로드 할때 함수를 한번 실행해서 초기 시간을 표시하기
    updateClock();

    // 3. 1초마다 페이지 자동 새로고침 함수
    setInterval(updateClock, 1000); 
});