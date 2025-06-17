import logo from './logo.svg';
import './App.css';
import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Home from './components/Home';
import Signin from './components/Signin';
import BoardListByUser from './components/BoardListByUser';
import CreateBoard from './components/CreateBoard';
import BoardDetail from './components/BoardDetail';
import EditBoard from './components/EditBoard';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/signin" element={<Signin />} />
        <Route path="/boardList" element={<BoardListByUser />} />
        <Route path="/createBoard" element={<CreateBoard />}/>
        <Route path="/board/:boardId" element={<BoardDetail />} />
        <Route path="/editBoard/:boardId" element={<EditBoard />} />
      </Routes>
    </Router>
  );
}

export default App;
