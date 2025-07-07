import React, {useEffect, useState} from 'react';
import {useParams, useNavigate} from 'react-router-dom';
import axios from 'axios';
import '../CreateBoard.css';
import useAuthCheck from './hook/useAuthCheck';

const EditBoard = () => {
    useAuthCheck();
    const {boardId} = useParams();
    const navigate = useNavigate();
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    useEffect(() => {

        axios.get(`http://localhost:8080/boards/${boardId}`, { withCredentials: true })
            .then(res => {
                setTitle(res.data.data.title);
                setContent(res.data.data.content);
            })
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
    }, [boardId, navigate]);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!title.trim()) {
            alert("제목을 입력해주세요");
            return;
        }
        if (!content.trim()) {
            alert("내용을 입력해주세요");
            return;
        }

        try {
            await axios.put(`http://localhost:8080/boards/${boardId}`, {
                title,
                content
            },{
                withCredentials: true
            });
            alert('게시글이 수정되었습니다');
            navigate("/home");
        } catch(error) {
            alert("게시글 수정에 실패했습니다.");
        }
    };

    //삭제핸들러
    const handleDelete = async () => {
        if(window.confirm("정말로 삭제하시겠습니까?")) {
            try {
                await axios.delete(`http://localhost:8080/boards/${boardId}`, {
                    withCredentials: true
                });
                alert("게시글이 삭제되었습니다.");
                navigate("/home");
            } catch (error) {
                alert("게시글 삭제에 실패했습니다.");
            }
        }
    };

    return (
        <div className="create-board-container">
            <h2>게시글 수정</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
                </div>
                <div>
                    <textarea value={content} onChange={(e) => setContent(e.target.value)} />
                </div>
                <div className="edit-btn-group">
                    <button type="submit" className="update-btn">수정 완료</button>
                    <button type="button" className="delete-btn" onClick={handleDelete}>삭제</button>
                </div>
            </form>
        </div>
    )

} // EditBoard

export default EditBoard;