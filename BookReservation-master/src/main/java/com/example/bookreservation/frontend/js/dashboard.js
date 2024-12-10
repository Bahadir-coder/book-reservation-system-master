// Kitabları gətirmək və göstərmək
document.addEventListener("DOMContentLoaded", function () {
    fetchBooks();
});

function fetchBooks() {
    const booksContainer = document.getElementById('books-container');
    booksContainer.innerHTML = '<p>Yüklənir...</p>'; // Yüklənmə mesajı

    fetch('http://localhost:5000/api/book/get/all')
        .then(response => {
            if (!response.ok) {
                throw new Error('Kitabları gətirmək mümkün olmadı.');
            }
            return response.json();
        })
        .then(data => {
            booksContainer.innerHTML = ''; // Əvvəlki kitabları təmizləyirik
            if (data.length === 0) {
                booksContainer.innerHTML = '<p>Kitab tapılmadı.</p>'; // Kitab tapılmadıqda xəbərdarlıq
            }
            data.forEach(book => {
                const bookCard = document.createElement('div');
                bookCard.classList.add('book-card');
                bookCard.innerHTML = `
                    <h3>${book.bookName}</h3>
                    <p><strong>Kateqoriya:</strong> ${book.bookGenre}</p>
                    <p><strong>Yazar:</strong> ${book.authorNames.join(', ')}</p>
                    <button onclick="reserveBook('${book.bookCode}')">Rezervasiya et</button>
                `;
                booksContainer.appendChild(bookCard);
            });
        })
        .catch(error => {
            console.error('Error fetching books:', error);
            booksContainer.innerHTML = '<p>Xəta baş verdi, yenidən cəhd edin.</p>'; // Xəta mesajı
        });
}

// Kitab rezervasiya etmək
function reserveBook(bookCode) {
    document.getElementById('bookCode').value = bookCode; // Kitabın kodunu formda göstəririk
}

// Rezervasiya formunu göndərmək
document.getElementById("reservation-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const bookCode = document.getElementById("bookCode").value;
    const reservationType = document.getElementById("reservationType").value;
    const userFinCode = "USER_FIN_CODE";  // Bu hissəni dinamik olaraq dəyişdirmək lazımdır

    const reservationData = {
        userFinCode: userFinCode,
        bookCode: bookCode,
        reservationType: reservationType,
        reservationCode: generateReservationCode(),
        createdDate: new Date().toISOString(),
        expiryDate: new Date(new Date().setDate(new Date().getDate() + 7)).toISOString()  // 7 gün sonra
    };

    fetch('http://localhost:5000/api/reservation/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(reservationData)
    })
        .then(response => {
            if (response.ok) {
                alert('Kitab uğurla rezervasiya edildi!');
            } else {
                alert('Xəta baş verdi!');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Xəta baş verdi!');
        });
});

// Rezervasiya kodu yaratmaq
function generateReservationCode() {
    return 'RES-' + Math.random().toString(36).substr(2, 9).toUpperCase();
}

// İstifadəçinin daxil olması üçün funksiya
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
