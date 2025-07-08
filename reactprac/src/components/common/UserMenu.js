import React from "react";
import axios from "axios";
import useAuthCheck from '../hook/useAuthCheck';

const UserMenu = () => {
    const user = useAuthCheck();
    
    const handleLogout = async () => {
        
        try {
            await axios.post("http://localhost:8080/auths/logout", {}, {
                withCredentials: true,
            });

            window.location.href = "/login";
        } catch (err) {
            console.error("로그아웃 실패", err);
            alert("로그아웃 중 오류가 발생했습니다");
        }
    
    };

    return (
            <>
            <span>{user.name}</span>
            <button onClick={handleLogout}>로그아웃</button>
        </>
    );
};

export default UserMenu;