document.addEventListener('DOMContentLoaded', () => {
    const editLinks = document.querySelectorAll('.edit-link');

    editLinks.forEach((link) => {
        link.addEventListener('click', (event) => {
            event.preventDefault();

            const infoItem = link.closest('.info-item');
            const infoValue = infoItem.querySelector('.info-value');

            // 수정 중이면 확인 버튼으로 변환
            if (link.textContent === '확인') {
                const inputField = infoItem.querySelector('.edit-field');
                infoValue.textContent = inputField.value || '제공되지 않음';
                inputField.remove();
                link.textContent = link.dataset.type === '추가' ? '추가' : '수정';
            } else {
                // 기존 값을 입력 필드로 전환
                const currentValue = infoValue.textContent.trim() === '제공되지 않음' ? '' : infoValue.textContent.trim();
                const inputField = document.createElement('input');
                inputField.type = 'text';
                inputField.value = currentValue;
                inputField.classList.add('edit-field');
                infoValue.textContent = '';
                infoValue.appendChild(inputField);

                // 버튼 텍스트 변경
                link.textContent = '확인';
            }
        });
    });
});