// İstifadəçi məlumatlarını API-dən almaq
document.addEventListener("DOMContentLoaded", function () {
    fetchUserProfile();
});

function fetchUserProfile() {
    // Burada istifadəçinin fin kodunu dinamik olaraq götürmək lazımdır
    const userFinCode = "USER_FIN_CODE"; // Bu hissəni dinamik olaraq dəyişdirmək lazımdır

    fetch(`http://localhost:5000/api/user/find/by/finCode/for/user?finCode=${userFinCode}`)
        .then(response => response.json())
        .then(data => {
            // İstifadəçi məlumatlarını səhifədə göstəririk
            document.getElementById("userName").textContent = data.userName;
            document.getElementById("userSurname").textContent = data.userSurname;
            document.getElementById("userTel").textContent = data.userTel;
            document.getElementById("userEmail").textContent = data.userEmail;
            document.getElementById("userFinCode").textContent = data.userFinCode;
        })
        .catch(error => {
            console.error('Error fetching user profile:', error);
        });
}

// Çıxış etmək
function logout() {
    // İstifadəçi çıxışı üçün logic əlavə edə bilərsiniz
    localStorage.removeItem("userToken");  // İstifadəçi məlumatlarını silirik
    window.location.href = "login.html";    // Çıxışdan sonra login səhifəsinə yönləndiririk
}
