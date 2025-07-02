import './App.css';
import React from 'react';
import {BrowserRouter as Router, Route, Routes, useLocation} from 'react-router-dom';
import Login from './components/Login';
import Signin from './components/Signin';
import BoardListByUser from './components/BoardListByUser';
import CreateBoard from './components/CreateBoard';
import BoardDetail from './components/BoardDetail';
import EditBoard from './components/EditBoard';
import Home from './components/Home';
import Header from './components/common/Header';
import BiddingRoom from './components/BiddingRoom';

// Header 조건부 렌더링을 위해 LayoutWithHeader 컴포넌트 추가
function LayoutWithHeader() {
  const location = useLocation();
  const hideHeader = ["/","/login", "/signin"].includes(location.pathname);

  return (
    <>
      {!hideHeader && <Header />}
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signin" element={<Signin />} />
        <Route path="/boardListByUser" element={<BoardListByUser />} />
        <Route path="/createBoard" element={<CreateBoard />} />
        <Route path="/board/:boardId" element={<BoardDetail />} />
        <Route path="/editBoard/:boardId" element={<EditBoard />} />
        <Route path="/home" element={<Home />} />
        <Route path="/bidding" element={<BiddingRoom />} />
        <Route path="*" element={<Login />} />
      </Routes>
    </>
  );
}

function App() {
  return (
    <Router>
      <LayoutWithHeader />
    </Router>
  );
}

export default App;
