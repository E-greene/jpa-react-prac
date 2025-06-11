import logo from './logo.svg';
import './App.css';
import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Home from './components/Home';
import Signin from './components/Signin';
import BoardListByUser from './components/BoardListByUser';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/api/home" element={<Home />} />
        <Route path="/api/signin" element={<Signin />} />
        <Route path="/api/boardList" element={<BoardListByUser />} />
      </Routes>
    </Router>
  );
}

export default App;
