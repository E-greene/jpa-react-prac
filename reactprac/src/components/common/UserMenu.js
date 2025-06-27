import React from "react";

const UserMenu = () => {
    const user = JSON.parse(localStorage.getItem("user"));

    const handleLogout = () => {
        localStorage.removeItem("user");
        localStorage.removeItem("userId");
        window.location.href = "/login";
    };

    return (
        <>
            <span>{user.name}</span>
        <button onClick={handleLogout}>로그아웃</button>
        </>
    );
};

export default UserMenu;