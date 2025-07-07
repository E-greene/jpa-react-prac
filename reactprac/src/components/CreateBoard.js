import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import axios from 'axios';
import '../CreateBoard.css';
import useAuthCheck from './hook/useAuthCheck';

const CreateBoard = () => {
    useAuthCheck();
    const navigate = useNavigate();
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!title.trim()) {
            alert("제목을 입력해주세요");
            return;
        }
        if(!content.trim()) {
            alert("내용을 입력해주세요");
            return;
        }

        try {
            const response = await axios.post("http://localhost:8080/boards", {
                title,
                content
            },
            {
                withCredentials: true
            });
            
            alert("게시글이 성공적으로 등록되었습니다.");

            navigate(`/home`);

            
        } catch(error) {
            // 백엔드에서 401, 403이 온 경우 로그인 페이지로 이동
            if (error.response && (error.response.status === 401 || error.response.status === 403)) {
                alert("로그인이 필요합니다");
                navigate("/login");
            } else {
                console.error("게시글 등록 실패 : ", error);
                alert("게시글 등록 중 오류가 발생했습니다.");
            }       
        }
    };

    return (
        <div className="create-board-container">
            <h2>게시글 작성</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <input
                        type="text"
                        placeholder="제목"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                    />
                </div>
                <div>
                    <textarea
                        placeholder="내용"
                        value={content}
                        onChange={(e) => setContent(e.target.value)}
                    />
                </div>
                <button type="submit">등록</button>
            </form>
        </div>
    );
};




export default CreateBoard;