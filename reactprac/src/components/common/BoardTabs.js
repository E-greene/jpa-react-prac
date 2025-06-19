import React from "react";
import { useNavigate } from "react-router-dom";

const BoardTabs = () => {
  const navigate = useNavigate();

  return (
    <>
      <button onClick={() => navigate("/boardListByUser")}>내가 쓴 글목록</button>
      {/* 필요한 추가 탭 */}
    </>
  );
};

export default BoardTabs;
