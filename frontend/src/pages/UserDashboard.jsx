import { useEffect, useState } from "react";
import axiosInstance from "../api/axiosConfig";
import { useNavigate } from "react-router-dom";

function UserDashboard() {
    const [userData, setUserData] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchUserData = async () => {
            const token = localStorage.getItem("token");
            console.log("Token:", token);
            try{
                const response = await axiosInstance.get("/api/users/profile",{
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                setUserData(response.data);
            } catch (err) {
                console.error("Error fetching user data:", err);
                navigate("/");
            }
        };

        fetchUserData();
    }, [navigate]);

    useEffect(() => {
        if (userData) {
            console.log("Fetched user data:", userData);
        }
    }, [userData]);

    const handleLogout = () => {
        localStorage.clear();
        navigate("/");
    };

    return(
        <div className="dashboard-container" style={{ padding: "2rem" }}>
            <h2>👤 User Dashboard</h2>
            {userData ? (
            <>
                <p><strong>Name:</strong> {userData.name}</p>
                <p><strong>Email:</strong> {userData.email}</p>
                <p><strong>Role:</strong> {userData.role}</p>
                <p><strong>Wallet:</strong> ₹ {userData.walletBalance}</p>
                <button onClick={handleLogout}>Logout</button>
            </>
        ) : (
            <p>Loading user data...</p>
        )}
        </div>
    );
}

export default UserDashboard;