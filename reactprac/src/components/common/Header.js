import React from "react";
import {useNavigate} from "react-router-dom";
import BoardTabs from "./BoardTabs";
import UserMenu from "./UserMenu";

const Header = () => {

    const navigate = useNavigate();

    return (
        <header style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
            padding: "10px 20px",
            borderBottom: "1px solid #ddd",
            background: "#fff"
        }}>
            <div>
                <button onClick={() => navigate("/home")}>home</button>
            </div>
            <div>
                <BoardTabs/>
            </div>
            <div>
                <UserMenu/>
            </div>
        </header>
    );
};

export default Header;