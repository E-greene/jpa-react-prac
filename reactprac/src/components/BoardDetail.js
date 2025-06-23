import React, {useEffect, useState} from 'react';
import {useParams, useNavigate} from 'react-router-dom';
import axios from 'axios';
import '../CreateBoard.css';

const BoardDetail = () => {
    const {boardId} = useParams();
    const navigate = useNavigate();
    const [board, setBoard] = useState(null);
    const [myUserId, setMyUserId] = useState(null);

    useEffect(() => {
        setMyUserId(localStorage.getItem("userId"));
        axios.get(`http://localhost:8080/boards/${boardId}`)
            .then(res => setBoard(res.data))
            .catch(err => {
                alert("게시글을 불러올 수 없습니다.");
                navigate("/boardList");
            });

            fetchComments();

    }, [boardId, navigate]);

    const fetchComments = async () => {
        const res = await axios.get(`http://localhost:8080/comments/board/${boardId}`);
        setComments(res.data);
    };

    if(!board) return null;

    const isMine = String(myUserId) === String(board.userId);

    //댓글 작성(일반/대댓글)
    const handleCommentSubmit = async (e) => {
        e.preventDefault();
        if(!commentInput.trim()) { return; }

        await axios.post('http://localhost:8080/comments', {
            boardId: board.id,
            userId: myUserId,
            content: commentInput,
            parentId: commentParentId,
        });

        setCommentInput('');
        setCommentParentId(null);
        await fetchComments();
    }

    //soft delete
    const handleDelete = async (commentId) => {
        if(!window.confirm('댓글을 삭제할까요?')) {return;}
        await axios.delete(`http://localhost:8080/comments/${commentId}`);
        await fetchComments();
    }

    //댓글/대댓글 UI 재귀 랜더링
    const renderComments = (commentList, depth = 0) => {
        return commentList.map(comment => {
            const isMyComment = String(myUserId) === String(comment.userId);

            //삭제된 댓글은 "삭제된 댓글입니다" 표시, children은 보임
            const isDeleted = comment.deleteStatus === 'Y' || comment.deleted === true;

            return (
                <div key={comment.Id} style={{
                    marginLeft: depth * 28,
                    borderLeft: depth > 0 ? '2px solid #eee' : 'none',
                    paddingLeft: 8,
                    marginTop: 12,
                }}>
                    <div style={{color:isDeleted ? '#aaa' : 'inherit'}}>
                        <b>{isDeleted ? '' : comment.userName || '익명'}</b>
                        <span style={{fontSize:12, marginLeft: 8, color:'#888'}}>
                            {isDeleted ? '' : (comment.createdDate ? comment.createdDate.substring(0,16).replace('T',' '): '')}
                        </span>
                    </div>
                    {/* --- 댓글 본문/수정 입력창 --- */}
                    {editingCommentId === comment.id ? (
                        <form onSubmit={handleEditSubmit} style={{marginTop:4, display: 'flex', gap:4}}>
                            <input value={editingContent}
                                    onChange={e => setEditingContent(e.target.value)}
                                    style={{flex:1}}
                                    maxLength={500}
                                    autoFocus
                            />
                            <button type="submit">저장</button>
                            <button type="button" onClick={() => setEditingCommentId(null)}></button>
                        </form>
                    ) : (
                        <div style={{ margin: '6px 0' }}>
                            {isDeleted ? <span style={{ color: '#aaa' }}>삭제된 댓글입니다.</span> : comment.content}
                        </div>
                    )}
                    {/* --- 버튼 --- */}
                    {!isDeleted && (
                        <div style={{fontSize: 13, color: '#666', marginBottom: 3}}>
                            <button type="button"
                                    style={{marginRight: 8}}
                                    onClick={() => {
                                        setCommentParentId(comment.id);
                                        setCommentInput(`@${comment.userName || '익명'}`);
                                    }}        
                            >답글</button>
                            {isMyComment && editingCommentId !== comment.id && (
                                <>
                                    <button type="button"
                                            style={{marginRight: 8}}
                                            onClick={() => {
                                                setEditingCommentId(comment.id);
                                                setEditingContent(comment.content);
                                            }}
                                    >수정</button>
                                    <button type="button"
                                            onClick={() => handleDelete(comment.id)}
                                    >삭제</button>
                                </>
                            )}
                        </div>
                    )}
                    {/* --- 대댓글 재귀 --- */}
                    {comment.children && comment.children.length > 0 &&
                        renderComments(comment.children, depth + 1)}
                </div>
            )
        })
    }

    return (
        <div className="create-board-container">
            <h2>게시글 상세보기</h2>
            <form>
                <div>
                    작성자 : {board.userName}
                </div>
                <div>
                    <input type="text" value={board.title} readOnly/>
                </div>
                <div>
                    <textarea value={board.content} readOnly />
                </div>
                {isMine ? <button type="button" onClick={() => navigate(`/editBoard/${board.id}`)} style={{background: '#2e7d32'}}>수정</button>
                        : null                
                }
            </form>

            {/* --- 댓글 영역 --- */}
            <div style={{marginTop: 40}}>
                <h3>댓글</h3>
                <form onSubmit={handleCommentSubmit} style={{ display: 'flex', gap: 8, marginBottom: 20 }}>
                    <input type="text"
                            value={commentInput}
                            onChange={e => setCommentInput(e.target.value)}
                            placeholder={"댓글을 입력하세요"}
                            style={{flex:1}}
                            maxLength={500}
                    />
                    {
                        commentParentId && (
                            <button type="button" onClick={() => {setCommentParentId(null); setCommentInput('');}}>취소</button>
                    )}
                    <button type="submit">등록</button>
                </form>
                <div>
                    {comments.length === 0 && <div style={{color: '#aaa'}}>아직 댓글이 없습니다.</div>}
                    {renderComments(comments)}
                </div>
            </div>
        </div>
    );

}; //BoardDetail

export default BoardDetail;