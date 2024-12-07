document.addEventListener('DOMContentLoaded', function () {
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;

    $('#summernote').summernote({
        lang: 'ko-KR',
        height: 300, // 에디터 높이
        placeholder: '내용을 입력하세요...',
        callbacks: {
            onImageUpload: function (files) {
                uploadImages(files, csrfHeader, csrfToken);
            }
        }
    });
});

function uploadImages(files, csrfHeader, csrfToken) {
    const formData = new FormData();

    // 여러 이미지 추가
    Array.from(files).forEach(file => formData.append('files', file));

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
            return response.json(); // 서버에서 반환된 이미지 URL 배열
        })
        .then(urls => {
            const imagePathsField = document.getElementById('imagePaths');

            urls.forEach(url => {
                // Summernote에 이미지 삽입
                $('#summernote').summernote('insertImage', url);

                // 숨겨진 필드(imagePaths)에 이미지 URL 추가
                if (imagePathsField.value) {
                    imagePathsField.value += `,${url}`;
                } else {
                    imagePathsField.value = url;
                }
            });
        })
        .catch(error => {
            console.error('이미지 업로드 실패:', error);
            alert('이미지 업로드에 실패했습니다.');
        });
}