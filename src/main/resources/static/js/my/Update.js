document.addEventListener('DOMContentLoaded', function () {
    $('#summernote').summernote({
        height: 300, // 에디터 높이
        lang: 'ko-KR', // 한글 설정
        focus: true, // 포커스
        toolbar: [
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['font', ['strikethrough', 'superscript', 'subscript']],
            ['fontsize', ['fontsize']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['insert', ['picture', 'link']],
            ['view', ['fullscreen', 'codeview', 'help']]
        ]
    });

    // Summernote에 기존 content 삽입
    const existingContent = $('#summernote').val();
    $('#summernote').summernote('code', existingContent); // 기존 content 로드
});