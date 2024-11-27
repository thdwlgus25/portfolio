$(document).ready(function () {
    $('#summernote').summernote({
        height: 300,          // 에디터 높이
        lang: "ko-KR",        // 한글 설정
        focus: true,          // 에디터에 포커스
        toolbar: [
            ['fontname', ['fontname']],
            ['fontsize', ['fontsize']],
            ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
            ['color', ['forecolor', 'color']],
            ['table', ['table']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['view', ['codeview', 'fullscreen', 'help']],
            ['insert', ['picture']] // 이미지 추가 버튼
        ],
        fontNames: ['Arial', '맑은 고딕', '궁서', '굴림체', '바탕체'],
        fontSizes: ['8', '10', '12', '14', '16', '18', '24', '36'],

        // 이미지 업로드 콜백
        callbacks: {
            onImageUpload: function (files) {
                for (let i = 0; i < files.length; i++) {
                    let reader = new FileReader();
                    reader.onload = function (e) {
                        // Base64 형식으로 변환된 이미지 삽입
                        $('#summernote').summernote('insertImage', e.target.result, 'Uploaded Image');
                    };
                    reader.readAsDataURL(files[i]);
                }
            }
        }
    });
});