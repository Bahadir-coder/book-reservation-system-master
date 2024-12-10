// İstifadəçinin daxil olması üçün funksiya
function loginUser() {
    // Form məlumatlarını alırıq
    const userFinCode = document.getElementById('userFinCode').value;
    const userPassword = document.getElementById('userPassword').value;

    const loginData = {
        userFinCode: userFinCode,
        userPassword: userPassword
    };

    fetch('/api/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginData),
    })
        .then(response => {
            // Xətalı cavab alarsaq, xəta mesajını göstəririk
            if (!response.ok) {
                return response.json().then(err => {
                    throw new Error(err.message || 'Xəta baş verdi');
                });
            }
            return response.json();
        })
        .then(data => {
            // Uğurlu login
            if (data && data.token) {
                localStorage.setItem('userToken', data.token); // Token saxlanılır
                window.location.href = "dashboard.html"; // Ana səhifəyə yönləndirilir
            } else {
                throw new Error('Serverdən düzgün cavab alınmadı');
            }
        })
        .catch(error => {
            // Xəta baş veribsə mesajı göstəririk
            const errorMessage = document.getElementById('errorMessage');
            errorMessage.textContent = error.message;
            errorMessage.style.display = 'block'; // Xəta mesajını göstəririk
        });
}
