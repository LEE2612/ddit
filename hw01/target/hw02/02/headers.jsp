<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>HTTP Headers</title>
    <style>
        dl {
            border: 1px solid #ccc;
            padding: 10px;
            width: 600px;
        }
        dt {
            font-weight: bold;
            margin-top: 10px;
        }
        dd {
            margin-left: 20px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<h1>HTTP 표준 헤더</h1>
<dl id="headerList"></dl>

<script>
const dl = document.getElementById('headerList');

async function loadHeaders() {
    try {
        const res = await fetch('<%= request.getContextPath() %>/proxy/http-headers');
        if (!res.ok) {
            throw new Error(`서버 오류: ${res.status}`);
        }
        const data = await res.json();

        if (!Array.isArray(data) || data.length === 0) {
            dl.textContent = '헤더 데이터가 없습니다.';
            return;
        }

        data.forEach(item => {
            const dt = document.createElement('dt');
            dt.textContent = item.name || '이름 없음';

            const dd = document.createElement('dd');
            dd.textContent = item.desc || '설명 없음';

            dl.appendChild(dt);
            dl.appendChild(dd);
        });
    } catch (err) {
        console.error('헤더 데이터를 불러오지 못했습니다.', err);
        dl.textContent = '데이터를 불러올 수 없습니다.';
    }
}

// 페이지 로드 시 호출
loadHeaders();
</script>
</body>
</html>
