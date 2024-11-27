document.addEventListener('DOMContentLoaded', function () {
    // Summernote 초기화
    $('#summernote').summernote({
        height: 300, // 에디터 높이
        lang: 'ko-KR', // 한글 설정
        focus: true, // 포커스
        toolbar: [
            ['fontname', ['fontname']],
            ['fontsize', ['fontsize']],
            ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
            ['color', ['forecolor', 'color']],
            ['table', ['table']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['view', ['codeview', 'fullscreen', 'help']],
            ['insert', ['picture']]
        ],
        callbacks: {
            // 이미지 업로드
            onImageUpload: function (files) {
                for (let i = 0; i < files.length; i++) {
                    let reader = new FileReader();
                    reader.onload = function (e) {
                        $('#summernote').summernote('insertImage', e.target.result, 'Uploaded Image');
                    };
                    reader.readAsDataURL(files[i]);
                }
            }
        }
    });

    // 파일 업로드 이벤트
    const fileInput = document.getElementById('file');
    if (fileInput) {
        fileInput.addEventListener('change', function (event) {
            const file = event.target.files[0];

            // 이미지 파일인지 확인
            if (file && file.type.startsWith('image/')) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    $('#summernote').summernote('insertImage', e.target.result, 'Uploaded Image');
                };
                reader.readAsDataURL(file);
            } else {
                alert('이미지 파일만 업로드할 수 있습니다.');
            }
        });
    }
});