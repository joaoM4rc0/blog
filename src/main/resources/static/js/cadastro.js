document.getElementById('cadastro-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    fetch('/auth/cadastro', {
        method : 'POST',
        headers: {
        'content-type:' 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({nome, email, senha})
    })
    .then(res => res.json())
    .then(data => {
            window.location.href = '/blog/home'
    })
    .catch(err => {
    console.error('erro ao cadastrar', err)})
    alert('falha ao cadastrar')
})