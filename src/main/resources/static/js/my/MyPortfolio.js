// 카테고리 필터링
const categoryButtons = document.querySelectorAll('.category-button');
const portfolioCards = document.querySelectorAll('.portfolio-card');

categoryButtons.forEach(button => {
    button.addEventListener('click', () => {
        // 버튼 활성화
        categoryButtons.forEach(btn => btn.classList.remove('active'));
        button.classList.add('active');

        // 카드 필터링
        const category = button.getAttribute('data-category');
        portfolioCards.forEach(card => {
            if (category === 'all' || card.getAttribute('data-category') === category) {
                card.style.display = 'block';
            } else {
                card.style.display = 'none';
            }
        });
    });
});

// 검색 기능
const searchInput = document.querySelector('.search-input');
searchInput.addEventListener('input', () => {
    const searchTerm = searchInput.value.toLowerCase();
    portfolioCards.forEach(card => {
        const title = card.querySelector('.project-title').textContent.toLowerCase();
        if (title.includes(searchTerm)) {
            card.style.display = 'block';
        } else {
            card.style.display = 'none';
        }
    });
});

// 수정 버튼 이벤트
const editButtons = document.querySelectorAll('.edit-button');
editButtons.forEach(button => {
    button.addEventListener('click', () => {
        alert('수정 버튼이 클릭되었습니다.');
        // 수정 로직 추가
    });
});

// 삭제 버튼 이벤트
const deleteButtons = document.querySelectorAll('.delete-button');
deleteButtons.forEach(button => {
    button.addEventListener('click', () => {
        if (confirm('이 항목을 삭제하시겠습니까?')) {
            const card = button.closest('.portfolio-card');
            card.remove();
        }
    });
});

// 작성하기 버튼 이벤트
const createButton = document.querySelector('.create-button');
createButton.addEventListener('click', () => {
    alert('작성하기 버튼이 클릭되었습니다.');
    // 작성 페이지로 이동 또는 팝업 열기 등 원하는 동작 추가
});