import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import { login } from '../services/alumniService';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const [loginInfo, setLoginInfo] = useState(null); 
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault(); 
        setMessage(''); 

        try {
            const data = await login(email, password); 
            const { id, token } = data; // Destructure token and id
            localStorage.setItem('token', token); // Store token in localStorage
            localStorage.setItem('id', id);       // Store id in localStorage
            setLoginInfo(data);                   // Update state with login info
            setMessage('Login Successful!');
            console.log('Token:', token); 
            console.log('User ID:', id);
            navigate('/dashboard'); // Navigate to dashboard
        } catch (error) {
            if (error.response && error.response.status === 401) {
                setMessage('Invalid Email or Password');
            } else {
                setMessage('An error occurred. Please try again.');
            }
            console.error('Login error:', error);
        }
    };

    return (
        <div className="d-flex align-items-center justify-content-center vh-100 bg-light">
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
                <div className="container-fluid">
                    <Link className="navbar-brand" to="/">Alumni Portal</Link>
                    <button 
                        className="navbar-toggler" 
                        type="button" 
                        data-bs-toggle="collapse" 
                        data-bs-target="#navbarNav" 
                        aria-controls="navbarNav" 
                        aria-expanded="false" 
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon"></span>
                    </button>
                </div>
            </nav>
            <div className="card p-4 shadow-lg" style={{ width: '400px' }}>
                <h3 className="card-title text-center mb-4">Alumni Portal Login</h3>
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label htmlFor="email" className="form-label">Email</label>
                        <input
                            type="email"
                            id="email"
                            className="form-control"
                            placeholder="Enter your email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label htmlFor="password" className="form-label">Password</label>
                        <input
                            type="password"
                            id="password"
                            className="form-control"
                            placeholder="Enter your password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>

                    <button type="submit" className="btn btn-primary w-100">Login</button>
                </form>
                {message && <p className="mt-3 text-center text-danger">{message}</p>}
            </div>
        </div>
    );
};

export default Login;
