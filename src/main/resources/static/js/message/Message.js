// 닫기 버튼 이벤트 핸들러
document.querySelectorAll('.close-button').forEach(button => {
    button.addEventListener('click', () => {
        const messageItem = button.closest('.message-item');
        messageItem.style.transition = 'opacity 0.3s ease, transform 0.3s ease';
        messageItem.style.opacity = '0';
        messageItem.style.transform = 'translateY(-10px)';
        setTimeout(() => {
            messageItem.remove();
        }, 300); // 애니메이션 이후 DOM에서 제거
    });
});