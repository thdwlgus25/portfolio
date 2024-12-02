document.addEventListener('DOMContentLoaded', function () {
    const fileInput = document.getElementById('file');
    const contentDiv = document.getElementById('content');

    if (fileInput) {
        fileInput.addEventListener('change', function (event) {
            const file = event.target.files[0];

            // 이미지 파일인지 확인
            if (file && file.type.startsWith('image/')) {
                const reader = new FileReader();

                // 파일이 로드되면 이미지 태그를 contenteditable 영역에 추가
                reader.onload = function (e) {
                    const img = document.createElement('img');
                    img.src = e.target.result;
                    img.alt = 'Uploaded Image';
                    img.style.maxWidth = '100%';
                    img.style.margin = '10px 0';
                    contentDiv.appendChild(img);
                };

                reader.readAsDataURL(file);
            } else {
                alert('이미지 파일만 업로드할 수 있습니다.');
            }
        });
    }



});