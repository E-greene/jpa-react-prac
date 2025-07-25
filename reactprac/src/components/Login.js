import React, { useState, useEffect } from 'react';
import {useNavigate} from 'react-router-dom';
import axios from 'axios';

function Login() {
    const navigate = useNavigate();
    const [data, setData] = useState('');
    const [loginId, setLoginId] = useState('');
    const [loginPwd, setLoginPwd] = useState('');

    const handleLogin = (e) => {
        e.preventDefault();
        //로그인요청 보내기
        console.log("로그인시 보낼 값:", loginId, loginPwd);
        axios.post("/api/auth/login", {
            loginId,
            loginPwd
        },
        {
            withCredentials: true
        })
        .then((res) => {
            alert('로그인하셨습니다');
            navigate("/home");
        })
        .catch((err) => {
            console.error(err);
            alert("아이디/비밀번호를 확인하세요");
        });
    }

    useEffect(() => {
        axios.get('/home')
        .then(res => setData(res.data.data))
        .catch(err => console.error(err));
    }, []);
    
    return (
        <div className="container">
            <h1>{data}</h1>
            <div className="button-group">
            <form onSubmit={handleLogin}>
                <div className="input-box">
                    <input type="text" placeholder="아이디를 입력하세요" name="loginId" value={loginId} onChange={(e) => setLoginId(e.target.value)}/>
                </div>
                <div className="input-box">
                    <input type="password" placeholder="비밀번호를 입력하세요" name="loginPwd" value={loginPwd} onChange={(e) => setLoginPwd(e.target.value)}/>
                </div>
                <button className="button">로그인</button>           
            </form>
                <div>
                    <button className="button" onClick={() => navigate('/signin')}>회원가입</button>
                </div>
            </div>
        </div>
    );
}

export default Login;