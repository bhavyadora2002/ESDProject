import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './pages/Login'; // Import your Login component
import Dashboard from './pages/DashBoard'; 
import EditPersonal from './pages/EditPersonal';// Import your Dashboard component
import EditEducation from './pages/EditEducation';
import UpdateOrgDetails from './pages/UpdateOrgDetails';
import AddOrganization from './pages/AddOrganisation';
import EditOrganization from './pages/EditOrganisation';
const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/edit-personal" element={<EditPersonal />} />
                <Route path="/edit-education" element={<EditEducation />} />
                <Route path="/update" element={<UpdateOrgDetails />} />
                <Route path="/edit-organisation/:orgId" element={<EditOrganization />} />
                <Route path="/add-organisation" element={<AddOrganization />} />

            </Routes>
        </Router>
    );
};

export default App;
