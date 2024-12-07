// Kitabları gətirmək və göstərmək
document.addEventListener("DOMContentLoaded", function () {
    fetchBooks();
});

function fetchBooks() {
    fetch('http://localhost:5000/api/book/get/all')
        .then(response => response.json())
        .then(data => {
            const booksContainer = document.getElementById('books-container');
            booksContainer.innerHTML = ''; // Əvvəlki kitabları təmizləyirik

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

    const reservationData = {
        userFinCode: "USER_FIN_CODE",  // Burada istifadəçinin FİN kodunu dinamik olaraq götürmək olar
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
