import { useState } from "react";
import axios from "../api/axiosConfig";
import { useNavigate } from "react-router-dom";

function RegisterPage() {
    const [formData, setFormData] = useState({
        username : "",
        email : "",
        password : "",
        confirmPassword : "",
    });

    const[error,setError] = useState("");
    const navigate = useNavigate();
    
    const handleChange = (e) => {
        setFormData((prev) => ({...prev, [e.target.name]: e.target.value}));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if(formData.password != formData.confirmPassword){
            setError("Passwords do not match!");
            return;
        }

        try{
            await axios.post('api/users/register',{
                name : formData.username,
                email : formData.email,
                password : formData.password,   
            });

            alert("Registration successful! Please login.");
            navigate("/");
        } catch (err) {
            setError("Registration failed! Please try again.");
            console.log("Registration Error:", err);
        }
    };

    return(
        <div className="auth-container" style={{ display:"flex", justifyContent:"center" }} >
            <form onSubmit={handleSubmit} style={{ display:"flex", flexDirection:"column", width:"300px" }} >
                <h2>
                    Register
                </h2>
                {error && <p style={{color:"red"}} > {error} </p>}
                <input
                    type="text"
                    placeholder="Username"
                    name="username"
                    value={formData.username}
                    onChange={handleChange}
                    required
                    style={{ padding:"10px", marginBottom:"10px" }}
                />
                <input
                    type="email"
                    placeholder="Email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                    style={{ padding:"10px", marginBottom:"10px" }}
                />
                <input
                    type="password"
                    placeholder="Password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                    required
                    style={{ padding:"10px", marginBottom:"10px" }}
                />
                <input
                    type="password"
                    placeholder="Confirm Password"
                    name="confirmPassword"
                    value={formData.confirmPassword}
                    onChange={handleChange}
                    required
                    style={{ padding:"10px", marginBottom:"10px" }}
                />
                <button type="submit" style={{ padding:"10px" }} >
                    Register
                </button>
                <p style={{ marginTop:"10px" }} >
                    Already have an account? <a href="/">Login</a>
                </p>
            </form>
        </div>
    );
}

export default RegisterPage;