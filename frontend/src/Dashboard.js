import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Dashboard = () => {
  const [fullName, setFullName] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token) {
      navigate('/login');
    } else {
      axios.get('http://localhost:8080/api/users', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      .then(response => {
        const userData = response.data[0];
        setFullName(userData.fullName);
      })
      .catch(error => {
        console.error('Error:', error);
        navigate('/login');
      });
    }
  }, [navigate]);

  return (
    <div>
      <h2>Dashboard</h2>
      <p>Welcome, {fullName}!</p>
    </div>
  );
};

export default Dashboard;