import React from 'react';
import {useNavigate} from 'react-router-dom';

function Singin() {
    const navigate = useNavigate();

    const handleSignup = () => {
        alert('회원가입 완료');
        navigate('/api/home');
    };

    return (
        <div>
            <h1>회원가입</h1>
            <input type="text" placeholder="아이디를 입력하세요"/>
            <input type="password" placeholder="비밀번호를 입력하세요"/>
            <button onClick={handleSignup}>가입</button>
        </div>
    );
}

export default Singin;