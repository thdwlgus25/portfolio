document.addEventListener('DOMContentLoaded', () => {
    const buttons = document.querySelectorAll('.view-details');

    buttons.forEach(button => {
        button.addEventListener('click', () => {
            alert('상세 정보를 표시합니다.');
        });
    });
});