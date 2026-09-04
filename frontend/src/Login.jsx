import { useState } from 'react'
import './Login.css'

function Login() {
    const [pin, setPin] = useState('')
    const [message, setMessage] = useState('')

    async function handleSubmit(e) {
        e.preventDefault()
        const response = await fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            credentials: 'include',
            body: JSON.stringify({ pin }),
        })
        if (response.ok) {
            setMessage('ACCESS GRANTED')
        } else {
            setMessage('ACCESS DENIED')
        }
    }

    return (
        <div className="login-screen">
            <h1>Aaron Life</h1>
            <form onSubmit={handleSubmit} className="terminal-box">
                <div className="terminal-label">TERMINAL_INPUT</div>
                <input
                    type="password"
                    value={pin}
                    onChange={(e) => setPin(e.target.value)}
                    placeholder="Enter command..."
                />
            </form>
            {message && <p className="status">{message}</p>}
        </div>
    )
}

export default Login