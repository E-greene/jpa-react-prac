import React, {useEffect, useState } from 'react';
import {useNavigate} from 'react-router-dom';
import axios from 'axios';
import useAuthCheck from './hook/useAuthCheck';

const Home = () => {
    const user = useAuthCheck();
    const navigate = useNavigate();
    const [boards, setBoards] = useState([]);
    
    useEffect(() => {
        console.log("Home component render!");

        if(!user) {
            return;
        }

        axios.get(`/boards`, { withCredentials: true })
            .then((res) => {
                setBoards(Array.isArray(res.data.data) ? res.data.data : []);
            })
            .catch((err) => {
                // 백엔드에서 401, 403이 온 경우 로그인 페이지로 이동
                if (err.response && (err.response.status === 401 || err.response.status === 403)) {
                    alert("로그인이 필요합니다");
                    navigate("/login");
                } else {
                    console.error("실패", err);
                }
            });
    }, []);

    const handleCreatePost = () => {
        navigate("/createBoard");
    };

    return (
        <div class="board-container">
            <h2>전체 게시물</h2>
            <table border="1" cellPadding="10" cellSpacing="0">
                <thead>
                <tr>
                    <th>제목</th>
                    <th>내용</th>
                    <th>작성자</th>
                    <th>작성시간</th>
                </tr>
                </thead>
                <tbody>
                {boards.map((board) => (
                    <tr key={board.id} onClick={() => navigate(`/board/${board.id}`)} style={{cursor: "pointer"}}>
                    <td>{board.title}</td>
                    <td>{board.content}</td>
                    <td>{board.userName}</td>
                    <td>{new Date(board.createdDate).toLocaleString()}</td>
                    </tr>
                ))}
                </tbody>
            </table>

            <button
                className="create-post-button"
                onClick={handleCreatePost}
            >
                게시물 작성
            </button>
        </div>
    )
};

export default Home;