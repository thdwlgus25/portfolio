document.addEventListener('DOMContentLoaded', () => {
    const editLinks = document.querySelectorAll('.edit-link');
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    editLinks.forEach((link) => {
        link.addEventListener('click', (event) => {
            event.preventDefault();

            const infoItem = link.closest('.info-item');
            const infoValue = infoItem.querySelector('.info-value');

            // 수정 중이면 확인 버튼으로 변환
            if (link.textContent === '확인') {
                const inputField = infoItem.querySelector('.edit-field');
                const updatedValue = inputField.value || '제공되지 않음';

                // 서버로 데이터 전송
                const type = link.dataset.type; // github, blog, notion 중 하나
                fetch('/myProfile', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        [csrfHeader]: csrfToken, // CSRF 토큰 추가
                    },
                    body: JSON.stringify({
                        [type]: updatedValue,
                    }),
                })
                    .then((response) => {
                        if (!response.ok) {
                            throw new Error('서버와의 통신에 실패했습니다.');
                        }
                        return response.json();
                    })
                    .then((data) => {
                        infoValue.textContent = data[type] || '제공되지 않음';
                        inputField.remove();
                        link.textContent = type === '추가' ? '추가' : '수정';
                    })
                    .catch((error) => {
                        alert(error.message);
                    });
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