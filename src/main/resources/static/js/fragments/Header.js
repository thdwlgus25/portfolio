document.addEventListener("DOMContentLoaded", () => {
    const menuButton = document.getElementById("menu-button");
    const dropdown = document.getElementById("dropdown");

    // 메뉴 버튼 클릭 시 드롭다운 토글
    menuButton.addEventListener("click", (event) => {
        event.stopPropagation(); // 클릭 이벤트가 부모로 전파되지 않도록 방지
        dropdown.classList.toggle("show");
    });

    // 드롭다운 바깥을 클릭하면 닫힘
    document.addEventListener("click", () => {
        dropdown.classList.remove("show");
    });
});