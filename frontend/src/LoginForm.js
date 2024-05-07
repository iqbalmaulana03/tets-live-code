import React, { useState } from 'react';
import axios from 'axios';
import {useNavigate} from 'react-router-dom';
import './styles.css';

const LoginForm = () => {
  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
  e.preventDefault();
  if (!formData.username || !formData.password) {
    alert('Please enter both username and password');
    return;
  }
  try {
    const response = await axios.post('http://localhost:8080/api/auth/login', formData);
    const token = response.data.token;
    localStorage.setItem('token', token);
    navigate('/dashboard');
  } catch (error) {
    alert('Login failed. Please check your credentials.');
  }
};

return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleSubmit} className="login-form">
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            name="username"
            value={formData.username}
            onChange={handleChange}
            className="form-control"
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            className="form-control"
          />
        </div>
        <button type="submit" className="btn btn-primary">Login</button>
      </form>
    </div>
  );
};

export default LoginForm;