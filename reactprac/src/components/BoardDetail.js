import React, {useEffect, useState} from 'react';
import {useParams, useNavigate} from 'react-router-dom';
import axios from 'axios';
import '../CreateBoard.css';

const BoardDetail = () => {
    const {boardId} = useParams();
    const navigate = useNavigate();
    const [board, setBoard] = useState(null);
    const [myUserId, setMyUserId] = useState(null);

    useEffect(() => {
        setMyUserId(localStorage.getItem("userId"));
        axios.get(`http://localhost:8080/boards/${boardId}`)
            .then(res => setBoard(res.data))
            .catch(err => {
                alert("게시글을 불러올 수 없습니다.");
                navigate("/boardList");
            });
    }, [boardId, navigate]);

    if(!board) return null;

    const isMine = String(myUserId) === String(board.userId);

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