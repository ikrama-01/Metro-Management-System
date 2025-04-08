import axios from "../api/axiosConfig";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function LoginPage() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try{
            const response = await axios.post('api/users/login',{
                email,
                password
            });

            const token = response.data.token;
            const role = response.data.role;
            localStorage.setItem('token', token);
            localStorage.setItem('role', role);

            if(role === 'ADMIN'){
                navigate("/admin-dashboard");
            } else{
                navigate("/user-dashboard");
            }
        } catch (err) {
            console.log('Login Error:', err);
            alert('Invalid Credentials');
        }
    };

    return(
        <div className="auth-container" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <form onSubmit={handleLogin} style={{ display: 'flex', flexDirection: 'column', width: '300px' }}>
                <h2>Login</h2>
                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                    style={{padding:'10px', marginBottom: '10px'}}
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                    style={{padding:'10px', marginBottom: '10px'}}
                />
                <button type="submit" style={{ padding:'10px' }}>Login</button>
                <p style={{ marginTop: '10px' }}>
                    Don't have an account? <a href="/register">Register</a></p>
                    </form>
                    </div>       
    );
}

export default LoginPage;