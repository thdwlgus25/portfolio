document.addEventListener('DOMContentLoaded', () => {
    const itemsPerPage = 8; // 한 페이지당 보여줄 게시물 수
    const portfolioCards = document.querySelectorAll('.portfolio-card');
    const pagination = document.querySelector('.pagination');

    let currentPage = 1;
    const totalPages = Math.ceil(portfolioCards.length / itemsPerPage);

    // 페이지에 맞는 게시물만 표시
    function showPage(page) {
        portfolioCards.forEach((card, index) => {
            if (index >= (page - 1) * itemsPerPage && index < page * itemsPerPage) {
                card.style.display = 'block';
            } else {
                card.style.display = 'none';
            }
        });
    }

    // 페이지네이션 버튼 생성
    function createPagination() {
        pagination.innerHTML = '';

        // 이전 버튼
        const prevButton = document.createElement('a');
        prevButton.href = '#';
        prevButton.classList.add('page-link');
        prevButton.textContent = '«';
        prevButton.addEventListener('click', (e) => {
            e.preventDefault();
            if (currentPage > 1) {
                currentPage--;
                updatePagination();
            }
        });
        pagination.appendChild(prevButton);

        // 페이지 번호 버튼
        for (let i = 1; i <= totalPages; i++) {
            const pageButton = document.createElement('a');
            pageButton.href = '#';
            pageButton.classList.add('page-link');
            pageButton.textContent = i;
            if (i === currentPage) {
                pageButton.classList.add('active');
            }
            pageButton.addEventListener('click', (e) => {
                e.preventDefault();
                currentPage = i;
                updatePagination();
            });
            pagination.appendChild(pageButton);
        }

        // 다음 버튼
        const nextButton = document.createElement('a');
        nextButton.href = '#';
        nextButton.classList.add('page-link');
        nextButton.textContent = '»';
        nextButton.addEventListener('click', (e) => {
            e.preventDefault();
            if (currentPage < totalPages) {
                currentPage++;
                updatePagination();
            }
        });
        pagination.appendChild(nextButton);
    }

    // 페이지 업데이트
    function updatePagination() {
        showPage(currentPage);
        createPagination();
    }

    // 초기 실행
    updatePagination();
});