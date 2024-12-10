document.getElementById("login-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const finCode = document.getElementById("finCode").value;
    const password = document.getElementById("password").value;

    // API çağırışı: İstifadəçi məlumatını tapmaq üçün FİN koduna görə sorğu göndəririk
    fetch('http://localhost:5000/api/user/find/by/' + finCode, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('İstifadəçi tapılmadı');
            }
            return response.json();
        })
        .then(data => {
            // İstifadəçi tapıldıqdan sonra şifrəni yoxlayırıq
            if (data && data.userFinCode === finCode && data.userPassword === password) {
                alert('Daxil oldunuz!');
                localStorage.setItem('userToken', data.token); // İstifadəçi tokenini saxlaya bilərik
                window.location.href = "dashboard.html";  // Kitablar səhifəsinə yönləndiririk
            } else {
                alert('FİN kod və ya şifrə səhvdir!');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Xəta baş verdi: ' + error.message);
        });
});
