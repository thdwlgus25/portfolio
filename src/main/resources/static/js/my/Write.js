document.addEventListener('DOMContentLoaded', function () {
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content; // CSRF 헤더 이름
    const csrfToken = document.querySelector('meta[name="_csrf"]').content; // CSRF 토큰 값

    $('#summernote').summernote({
        lang: 'ko-KR',
        height: 300, // 에디터 높이
        placeholder: '내용을 입력하세요...',
        callbacks: {
            onImageUpload: function (files) {
                if (files.length > 0) {
                    uploadImage(files[0], csrfHeader, csrfToken); // 첫 번째 파일 업로드
                }
            }
        }
    });
});

function uploadImage(file, csrfHeader, csrfToken) {
    const formData = new FormData();
    formData.append('file', file);

    fetch('/uploadImage', {
        method: 'POST',
        headers: {
            [csrfHeader]: csrfToken, // CSRF 토큰 추가
        },
        body: formData,
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('서버에서 에러 발생');
            }
            return response.text(); // 서버에서 반환된 이미지 URL
        })
        .then(url => {
            // Summernote에 이미지 삽입
            $('#summernote').summernote('insertImage', url);

            // 숨겨진 필드(imagePath)에 이미지 URL 설정 (첫 번째 이미지 URL 저장)
            const imagePathField = document.getElementById('imagePath');
            if (imagePathField.value) {
                imagePathField.value += `,${url}`; // 여러 이미지인 경우 쉼표로 구분
            } else {
                imagePathField.value = url; // 첫 번째 이미지 저장
            }
        })
        .catch(error => {
            console.error('이미지 업로드 실패:', error);
            alert('이미지 업로드에 실패했습니다.');
        });
}