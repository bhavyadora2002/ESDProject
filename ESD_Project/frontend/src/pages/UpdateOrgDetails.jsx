import React, { useState, useEffect } from "react";
import NavBar from "../components/NavBar";
import { deleteOrg, getOrgInfo } from "../services/alumniService";
import { useNavigate } from 'react-router-dom'; 
const UpdateOrgDetails = () => {
    const [orgData, setOrgData] = useState([]);
    const [message, setMessage] = useState("");
    const userId = localStorage.getItem("id");
    const navigate = useNavigate(); 

    useEffect(() => {
        const fetchOrgData = async () => {
            try {
                const data = await getOrgInfo(userId);
                setOrgData(data);
                console.log(data);
            } catch (error) {
                console.error("Error fetching organization data:", error);
                setMessage("Failed to load organization data.");
            }
        };

        fetchOrgData();
    }, [userId]);

    const handleDelete = async (orgId) => {
        console.log("Id is");
        console.log(orgId);
        try {
            await deleteOrg(orgId);
            setOrgData(orgData.filter((org) => org.workId !== orgId));
            setMessage("Organization deleted successfully.");
        } catch (error) {
            console.error("Error deleting organization:", error);
            setMessage("Failed to delete organization.");
        }
    };

    const handleUpdate = (orgId) => {
        // localStorage.setItem('ordId',orgId);
        // console.log("setting",orgId,orgId.dtype);
            navigate(`/edit-organisation/${orgId}`);
        };
        const handleAdd = () => {
            // localStorage.setItem('ordId',orgId);
            // console.log("setting",orgId,orgId.dtype);
                navigate(`/add-organisation`);
            };
    
    
    return (
        <div>
            <NavBar />
            <div className="container mt-5">
                <h3>Update Organization Details</h3>
                <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
            <button 
                className="btn btn-primary" 
                onClick={() => handleAdd()}
                style={{ width: '100px' }}
            >
                Add
            </button>
        </div>
                {/* <button className="btn btn-primary me-2"  onClick={() => handleAdd()}>Add</button> */}

                {message && <p className="text-danger">{message}</p>}
                <table className="table table-bordered">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Organization Name</th>
                            <th>Position</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {orgData.map((org, index) => (
                            <tr key={org.workId}>
                                <td>{index + 1}</td>
                                <td>{org.organisation?.name || "N/A"}</td>
                                <td>{org.position}</td>
                                <td>{org.joiningDate}</td>
                                <td>{org.leavingDate || "Present"}</td>
                                <td>
                                    <button className="btn btn-primary me-2"  onClick={() => handleUpdate(org.workId)}>Update</button>
                                    <button className="btn btn-danger" onClick={() => handleDelete(org.workId)}>
                                    Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default UpdateOrgDetails;
