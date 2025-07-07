import React, {useEffect, useState} from 'react';
import {useParams, useNavigate} from 'react-router-dom';
import axios from 'axios';
import '../CreateBoard.css';
import useAuthCheck from './hook/useAuthCheck';

const BoardDetail = () => {
    const user = useAuthCheck();
    const {boardId} = useParams();
    const navigate = useNavigate();
    const [board, setBoard] = useState(null);

    useEffect(() => {
        if(!user) return;

        axios.get(`http://localhost:8080/boards/${boardId}`, { withCredentials: true })
            .then(res => setBoard(res.data.data))
            .catch(err => {
                // 백엔드에서 401, 403이 온 경우 로그인 페이지로 이동
                if (err.response && (err.response.status === 401 || err.response.status === 403)) {
                    alert("로그인이 필요합니다");
                    navigate("/login");
                } else {
                    alert("게시글을 불러올 수 없습니다.");
                    navigate("/boardList");
                }         
            });
    }, [boardId, navigate, user]);

    if(!user || !board) return null;

    const isMine = String(user.id) === String(board.userId);

    return (
        <div className="create-board-container">
            <h2>게시글 상세보기</h2>
            <form>
                <div>
                    <input type="text" value={board.title} readOnly/>
                </div>
                <div>
                    <textarea value={board.content} readOnly />
                </div>
                {isMine ? <button type="button" onClick={() => navigate(`/editBoard/${board.id}`)} style={{background: '#2e7d32'}}>수정</button>
                        : null                
                }
            </form>
        </div>
    );

}; //BoardDetail

export default BoardDetail;