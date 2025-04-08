import { Navigate, Route } from "react-router-dom";
import React from "react";


// eslint-disable-next-line no-unused-vars
const ProtectedRoute = ({ element: Component, roleRequired, ...rest}) => {
    const token = localStorage.getItem("token");
    const role = localStorage.getItem("role");
    const isAuthenticated = token && role;

    if(!isAuthenticated) {
        return <Navigate to="/" />;
    }
    if(roleRequired && role !== roleRequired) {
        return <Navigate to="/" />;
    }
    return <Route {...rest} element={<Component />} />;
};

export default ProtectedRoute;