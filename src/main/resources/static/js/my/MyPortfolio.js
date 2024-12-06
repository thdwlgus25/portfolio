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
function editPortfolio(event, seq) {
    event.preventDefault(); // 기본 동작 막기
    event.stopPropagation(); // 부모 태그로 이벤트 전파 막기
    console.log("수정 버튼 클릭: ", seq); // 디버깅 로그
    window.location.href = `/update?seq=${seq}`; // 수정 페이지로 이동
}

// 삭제 버튼 이벤트
function deletePortfolio(event, seq) {
    event.preventDefault(); // 기본 동작 막기
    event.stopPropagation(); // 부모 태그로 이벤트 전파 막기

    if (confirm('이 항목을 삭제하시겠습니까?')) {
        console.log("삭제 버튼 클릭: ", seq); // 디버깅 로그
        // 서버로 삭제 요청 보내기 (필요시 추가)
        alert(`포트폴리오 ID ${seq}가 삭제되었습니다.`);
    }
}

// 작성하기 버튼 이벤트
const createButton = document.querySelector('.create-button');
createButton.addEventListener('click', () => {
    alert('작성하기 버튼이 클릭되었습니다.');
    // 작성 페이지로 이동 또는 팝업 열기 등 원하는 동작 추가
});