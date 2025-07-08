
export interface User {
  id: number;
  name: string;
  email: string;
  loginId: string;
  role: {
    roleName: string; // Role 객체의 구조에 따라 수정
  };
}
