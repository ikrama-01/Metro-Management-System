import { useEffect, useState } from "react";
import axios from "../api/axiosConfig";
import { useNavigate } from "react-router-dom";

function AdminDashboard() {
  const [adminData, setAdminData] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    const fetchAdminData = async () => {
      const token = localStorage.getItem("token");
      try {
        const response = await axios.get("/api/users/profile", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setAdminData(response.data);
      } catch (err) {
        console.log("Error fetching admin data", err);
        alert("Session expired! Please login again.");
        localStorage.removeItem("token");
        localStorage.removeItem("role");
        navigate("/");
      }
    };

    fetchAdminData();
  }, [navigate]);

  const handleLogout = () => {
    localStorage.clear();
    navigate("/");
  };

  return (
    <div className="dashboard-container" style={{ padding: "2rem" }}>
      <h2>🛠️ Admin Dashboard</h2>
      {adminData ? (
            <>
                <p><strong>Name:</strong> {adminData.name}</p>
                <p><strong>Email:</strong> {adminData.email}</p>
                <p><strong>Role:</strong> {adminData.role}</p>
                <p><strong>Wallet:</strong> ₹ {adminData.walletBalance}</p>
                <button onClick={handleLogout}>Logout</button>
            </>
        ) : (
            <p>Loading user data...</p>
        )}
    </div>
  );
}

export default AdminDashboard;
