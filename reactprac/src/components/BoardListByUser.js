import React, {useEffect, useState } from 'react';
import axios from 'axios';

const BoardListByUser = () => {
    const [boards, setBoards] = useState([]);
    const userId = localStorage.getItem("userId");

    useEffect(() => {
        if(userId) {
            axios.get(`http://localhost:8080/boards/${userId}`)
                .then((res) => {
                    setBoards(res.data);
                })
                .catch((err) => {
                    console.error("실패", err);
                });
        }
    }, [userId]);

    return (
        <div class="board-container">
            <h2>게시물 목록</h2>
            <table border="1" cellPadding="10" cellSpacing="0">
                <thead>
                <tr>
                    <th>제목</th>
                    <th>내용</th>
                    <th>작성시간</th>
                </tr>
                </thead>
                <tbody>
                {boards.map((board) => (
                    <tr key={board.id}>
                    <td>{board.title}</td>
                    <td>{board.content}</td>
                    <td>{new Date(board.createdAt).toLocaleString()}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
};

export default BoardListByUser;