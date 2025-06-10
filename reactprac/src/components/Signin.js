import React, { useState } from 'react';
import {useNavigate} from 'react-router-dom';
import axios from 'axios';

function Singin() {
    const navigate = useNavigate();

    const [loginId, setLoginId] = useState('');
    const [loginPwd, setLoginPwd] = useState('');
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');

    const handleSignup = (e) => {
        e.preventDefault(); // 폼 기본제출 방지
        axios.post("http://localhost:8080/user/signUp", {
            loginId,
            loginPwd,
            name,
            email
        }).then((res) => {
            alert(' 회원가입 완료');
            navigate('/api/home');
        }).catch((err) => {
            console.error(err);
            alert('회원가입 실패');
        });
    }

    const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === "loginId") setLoginId(value);
    else if (name === "loginPwd") setLoginPwd(value);
    else if (name === "name") setName(value);
    else if (name === "email") setEmail(value);
    };

    // const handleSignup = async () => {
    //     try {
    //         const response = await axios.post('/user/signUp', {
    //             loginId,
    //             loginPwd,
    //             name,
    //             email
    //         });

    //         alert('회원가입 완료');
    //         navigate('/api/home');
    //     } catch (error) {
    //         alert(error.response?.data?.message || '회원가입 실패');
    //     }
    // }


    return (
        <form onSubmit={handleSignup}>
            <div className="container">
                <h1>회원가입</h1>
                <div className="input-box">
                    <input type="text" placeholder="아이디를 입력하세요" name="loginId" value={loginId} onChange={handleChange} />
                </div>
                <div className="input-box">
                    <input type="password" placeholder="비밀번호를 입력하세요" name="loginPwd" value={loginPwd} onChange={handleChange} />
                </div>
                <div className="input-box">
                    <input type="text" placeholder="이름을 입력하세요" name="name" value={name} onChange={handleChange} />
                </div>
                <div className="input-box">
                    <input type="text" placeholder="이메일을 입력하세요" name="email" value={email} onChange={handleChange} />
                </div>
                <button className="button">가입</button>
            </div>
        </form>
    );
}

export default Singin;