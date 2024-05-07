import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import LoginForm from './LoginForm';
import Dashboard from './Dashboard';

const PrivateRoute = ({ path, element: Element }) => {
  const isLoggedIn = true; 

  if (!isLoggedIn) {
    return <Navigate to="/login" replace />;
  }
  return <Element />;
};

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LoginForm />} />
        <Route path="/dashboard" element={<PrivateRoute element={Dashboard} />} />
        <Route path="/" element={<Navigate to="/dashboard" />} />
      </Routes>
    </Router>
  );
};

export default App;
