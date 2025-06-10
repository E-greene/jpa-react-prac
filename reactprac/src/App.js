import logo from './logo.svg';
import './App.css';
import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Home from './components/Home';
import Signin from './components/Signin';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/api/home" element={<Home />} />
        <Route path="/api/signin" element={<Signin />} />
      </Routes>
    </Router>
  );
}

export default App;
