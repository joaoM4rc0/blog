document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({ email, senha })
    })
    .then(res => res.json())
    .then(data => {
    console.log(data)
        if(data.token || data.role) {
            localStorage.setItem('token', data.token);
            if(data.role === 'ADMIN') {
                window.location.href = '/admin/telaAdmin';
            }
            if(data.role === 'USER') {
                window.location.href = '/blog/home';
            }
        }else {
            alert('Credenciais inválidas ou dados incompletos');
        }
    })
    .catch(err => {
        console.error('erro ao logar', err);
        alert('falha no login');
    });
});