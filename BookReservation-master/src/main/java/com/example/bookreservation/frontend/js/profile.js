// İstifadəçi məlumatlarını API-dən almaq
document.addEventListener("DOMContentLoaded", function () {
    fetchUserProfile();
});

function fetchUserProfile() {
    // Burada istifadəçinin fin kodunu dinamik olaraq götürmək lazımdır
    const userFinCode = localStorage.getItem("userFinCode"); // İstifadəçinin fin kodunu localStorage-dən alırıq

    if (!userFinCode) {
        // Əgər fin kodu tapılmırsa, istifadəçini login səhifəsinə yönləndiririk
        alert("İstifadəçi qeydiyyatdan keçməmişdir. Lütfən daxil olun.");
        window.location.href = "login.html";  // Login səhifəsinə yönləndiririk
        return;
    }

    // API çağırışı edirik
    fetch(`http://localhost:5000/api/user/find/by/finCode/for/user?finCode=${userFinCode}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('İstifadəçi məlumatları alınarkən xəta baş verdi.');
            }
            return response.json();
        })
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
            // Xəta baş verərsə istifadəçiyə xəbərdarlıq mesajı göstəririk
            const errorMessage = document.getElementById('errorMessage');
            errorMessage.textContent = error.message;
            errorMessage.style.display = 'block';
        });
}

// Çıxış etmək
function logout() {
    // Tokeni və fin kodu silirik
    localStorage.removeItem("userToken");  // İstifadəçinin tokenini silirik
    localStorage.removeItem("userFinCode"); // İstifadəçinin fin kodunu silirik

    // İstifadəçini login səhifəsinə yönləndiririk
    window.location.href = "login.html";
}
