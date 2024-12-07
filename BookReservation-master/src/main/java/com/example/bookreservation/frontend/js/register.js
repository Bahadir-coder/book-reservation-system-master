// Qeydiyyat funksiyası
function registerUser() {
    // Form məlumatlarını alırıq
    const userName = document.getElementById('userName').value;
    const userSurname = document.getElementById('userSurname').value;
    const userFinCode = document.getElementById('userFinCode').value;
    const userTel = document.getElementById('userTel').value;
    const userEmail = document.getElementById('userEmail').value;

    const userData = {
        userName: userName,
        userSurname: userSurname,
        userFinCode: userFinCode,
        userTel: userTel,
        userEmail: userEmail
    };

    fetch('/api/user/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(err => {
                    throw new Error(err.message); // Əgər xətalı cavab alırıqsa, səhv mesajını göstəririk
                });
            }
            return response.json();
        })
        .then(data => {
            // Qeydiyyat uğurlu olduqda mesaj göstəririk
            alert("Qeydiyyat uğurla tamamlandı!");
            window.location.href = "login.html"; // Login səhifəsinə yönləndiririk
        })
        .catch(error => {
            // Xəta mesajını istifadəçiyə göstəririk
            const errorMessage = document.getElementById('errorMessage');
            errorMessage.textContent = error.message;
            errorMessage.style.display = 'block'; // Xəta mesajını göstəririk
        });
}
