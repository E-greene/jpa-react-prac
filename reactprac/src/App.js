import logo from './logo.svg';
import './App.css';
import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Login from './components/Login';
import Signin from './components/Signin';
import BoardListByUser from './components/BoardListByUser';
import CreateBoard from './components/CreateBoard';
import BoardDetail from './components/BoardDetail';
import EditBoard from './components/EditBoard';
import Home from './components/Home';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signin" element={<Signin />} />
        <Route path="/boardListByUser" element={<BoardListByUser />} />
        <Route path="/createBoard" element={<CreateBoard />}/>
        <Route path="/board/:boardId" element={<BoardDetail />} />
        <Route path="/editBoard/:boardId" element={<EditBoard />} />
        <Route path="/home" element={<Home />} />
      </Routes>
    </Router>
  );
}

export default App;
