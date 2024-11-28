import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import NavBar from '../components/NavBar';
import { getAllOrganizations, createOrganization } from '../services/alumniService';

const AddOrganization = () => {
    const [allOrganizations, setAllOrganizations] = useState([]); // State for all organizations
    const [newOrganisationName, setNewOrganisationName] = useState('');
    const [selectedOrganisationId, setSelectedOrganisationId] = useState(''); 
    const [newPosition, setNewPosition] = useState('');
    const [newJoiningDate, setNewJoiningDate] = useState('');
    const [newLeavingDate, setNewLeavingDate] = useState('');
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchAllOrganizations = async () => {
            try {
                const orgList = await getAllOrganizations();
                setAllOrganizations(orgList);
            } catch (error) {
                console.error('Failed to fetch data:', error);
                setMessage('Failed to load all organizations. Please try again.');
            }
        };

        fetchAllOrganizations();
    }, []);

    const handleSave = async (e) => {
        e.preventDefault();

        const newOrgData = {
            alumni_id: localStorage.getItem('id'), 
            organisation_id: selectedOrganisationId, 
            position: newPosition,
            joining_date: newJoiningDate,
            leaving_date: newLeavingDate || null, 
        };

        try {
             await createOrganization(newOrgData);
            setMessage('New organization added successfully.');
            navigate('/update'); 
        } catch (error) {
            console.error('Failed to add organization:', error);
            setMessage('Failed to add organization. Please try again.');
        }
    };

    return (
        <div>
            <NavBar />
            <div className="container mt-5">
                <h3>Add New Organization</h3>
                {message && <p className="text-danger">{message}</p>}
                <form onSubmit={handleSave}>
                    <div className="mb-3">
                        <label className="form-label">Organization Name</label>
                        <select
                            className="form-control"
                            value={newOrganisationName}
                            onChange={(e) => {
                                const selectedOrg = allOrganizations.find(org => org.name === e.target.value);
                                if (selectedOrg) {
                                    setNewOrganisationName(e.target.value);  
                                    setSelectedOrganisationId(selectedOrg.id); 
                                }
                            }}
                        >
                            <option value="" disabled>Select an organization</option>
                            {allOrganizations.map((org) => (
                                <option key={org.id} value={org.name}>
                                    {org.name}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="mb-3">
                        <label className="form-label">Position</label>
                        <input
                            type="text"
                            className="form-control"
                            value={newPosition}
                            onChange={(e) => setNewPosition(e.target.value)}
                            placeholder="Enter Position"
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">Joining Date</label>
                        <input
                            type="date"
                            className="form-control"
                            value={newJoiningDate}
                            onChange={(e) => setNewJoiningDate(e.target.value)}
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">Leaving Date (optional)</label>
                        <input
                            type="date"
                            className="form-control"
                            value={newLeavingDate}
                            onChange={(e) => setNewLeavingDate(e.target.value)}
                            placeholder="Enter Leaving Date (or leave blank)"
                        />
                    </div>
                    <button className="btn btn-success" type="submit">Add Organization</button>
                    <button
                        className="btn btn-secondary ms-2"
                        onClick={(e) => {
                            e.preventDefault();
                            navigate('/update'); 
                        }}
                    >
                        Cancel
                    </button>
                </form>
            </div>
        </div>
    );
};

export default AddOrganization;
