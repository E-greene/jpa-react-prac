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
        <div>
            <h1>{data}</h1>
            <button onClick={() => navigate('/api/signin')}>회원가입</button>
            <button>로그인</button>
        </div>
    );
}

export default Home;