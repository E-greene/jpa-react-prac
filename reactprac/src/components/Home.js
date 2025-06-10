import React, { useState, useEffect } from 'react';
import {useNavigate} from 'react-router-dom';

function Home() {
    const navigate = useNavigate();
    const [data, setData] = useState('');

    useEffect(() => {
        fetch('/api/home')
        .then(res => res.text())
        .then(text => setData(text));
    }, []);

    return (
        <div className="container">
            <h1>{data}</h1>
            <div className="button-group">
                <div className="input-box">
                    <input type="text" placeholder="아이디를 입력하세요"/>
                </div>
                <div className="input-box">
                    <input type="password" placeholder="비밀번호를 입력하세요"/>
                </div>
                <button className="button">로그인</button>
                <div>
                    <button className="button" onClick={() => navigate('/api/signin')}>회원가입</button>
                </div>
            </div>
        </div>
    );
}

export default Home;