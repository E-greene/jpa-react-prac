import React from "react";

const UserMenu = () => {
    const user = JSON.parse(localStorage.getItem("user"));

    const handleLogout = () => {
        
        window.location.href = "/login";
    };

    return (
        <>
            
        <button onClick={handleLogout}>로그아웃</button>
        </>
    );
};

export default UserMenu;