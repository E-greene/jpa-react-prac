import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import type { User} from '../types/User';

export default function useAuthCheck() {
  const navigate = useNavigate();
  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    axios.get("http://localhost:8080/auths/me", { withCredentials: true })
        .then((res) => {
          console.log("✅ user info:", res.data.data);
          console.log("🔎 role field:", res.data.data.role);
          setUser(res.data.data)
        })
      .catch((err) => {
        if (err.response?.status === 401 || err.response?.status === 403) {
          alert("로그인이 필요합니다");
          navigate("/login");
        }
      });
  }, []);

  return user;
}
