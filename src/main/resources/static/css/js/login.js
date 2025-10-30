document.getElementById('loginForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const loginText = document.getElementById('loginText');
    const loginSpinner = document.getElementById('loginSpinner');
    const errorMessage = document.getElementById('errorMessage');

    // Mostrar loading
    loginText.classList.add('d-none');
    loginSpinner.classList.remove('d-none');
    errorMessage.classList.add('d-none');

    try {
        const response = await fetch('/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, password })
        });

        if (response.ok) {
            const data = await response.json();
            // Salvar token no localStorage
            localStorage.setItem('token', data.token);
            localStorage.setItem('username', data.username);

            // Redirecionar para dashboard
            window.location.href = '/dashboard';
        } else {
            const error = await response.text();
            showError('Usuário ou senha inválidos!');
        }
    } catch (error) {
        showError('Erro de conexão. Tente novamente.');
    } finally {
        // Esconder loading
        loginText.classList.remove('d-none');
        loginSpinner.classList.add('d-none');
    }
});

function showError(message) {
    const errorMessage = document.getElementById('errorMessage');
    errorMessage.textContent = message;
    errorMessage.classList.remove('d-none');
}

// Enter para submit
document.getElementById('password').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        document.getElementById('loginForm').dispatchEvent(new Event('submit'));
    }
});