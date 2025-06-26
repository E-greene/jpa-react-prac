import React, {useEffect, useState } from 'react';
import {useNavigate} from 'react-router-dom';
import axios from 'axios';

const Home = () => {
    const navigate = useNavigate();
    const [boards, setBoards] = useState([]);
    
    useEffect(() => {
        console.log("Home component render!");
        const userId = localStorage.getItem("userId");
        console.log("userId : "+userId);

        axios.get(`http://localhost:8080/boards`)
            .then((res) => {
                setBoards(Array.isArray(res.data.data) ? res.data.data : []);
            })
            .catch((err) => {
                console.error("실패", err);
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