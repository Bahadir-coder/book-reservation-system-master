document.getElementById("login-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const finCode = document.getElementById("finCode").value;
    const password = document.getElementById("password").value;

    // API çağırışı
    fetch('http://localhost:5000/api/user/find/by/' + finCode, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => response.json())
        .then(data => {
            if (data && data.userFinCode === finCode) {
                // Burada şifrəni doğrulama əlavə etmək olar (backend tərəfindən gələn cavabla)
                alert('Daxil oldunuz!');
                window.location.href = "dashboard.html";  // Kitablar səhifəsinə yönləndirmək
            } else {
                alert('FİN kod və ya şifrə səhvdir!');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Xəta baş verdi!');
        });
});
