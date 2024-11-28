import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import NavBar from '../components/NavBar';
import { updateOrgInfo, getAllOrganizations, getOrganization } from '../services/alumniService';

const EditOrganization = () => {
    const [organizationInfo, setOrganizationInfo] = useState({
        alumniId:'',
        organisationName: '',
        position: '',
        joiningDate: '',
        leavingDate: '',
    });
    const [allOrganizations, setAllOrganizations] = useState([]); 
    const [newOrganisationName, setNewOrganisationName] = useState('');
    const [selectedOrganisationId, setSelectedOrganisationId] = useState(''); 
    const [newPosition, setNewPosition] = useState('');
    const [newJoiningDate, setNewJoiningDate] = useState('');
    const [newLeavingDate, setNewLeavingDate] = useState('');
    const [message, setMessage] = useState('');
    const orgId  = useParams(); 
    const navigate = useNavigate();

    useEffect(() => {
        const fetchOrgData = async () => {
            try {
                const orgData = await getOrganization(orgId.orgId);
                setOrganizationInfo({
                    // alumniId:localStorage.getItem('id'),
                    // organisationId:orgId.orgId,
                    organisationName: orgData.organisation.name,
                    position: orgData.position,
                    joiningDate: orgData.joiningDate,
                    leavingDate: orgData.leavingDate || '', 
                });
                setSelectedOrganisationId(orgData.organisation.organisationId);

                setNewOrganisationName(orgData.organisation.name);
                setNewPosition(orgData.position);
                setNewJoiningDate(orgData.joiningDate);
                setNewLeavingDate(orgData.leavingDate || '');
                console.log("name",newOrganisationName);
                console.log("pos",newPosition)
            } catch (error) {
                console.error('Error in getOrgInfo:', error);
                setMessage('Failed to load organization info. Please try again.');
            }
        };

        const fetchAllOrganizations = async () => {
            try {
                const orgList = await getAllOrganizations();
                setAllOrganizations(orgList);
            } catch (error) {
                console.error('Failed to fetch data:', error);
                setMessage('Failed to load all organizations. Please try again.');
            }
        };

        fetchOrgData();
        fetchAllOrganizations();
    });


    const handleUpdate = async (e) => {
        e.preventDefault();

        console.log("id is",selectedOrganisationId);
        const updatedData = {
            alumni_id: localStorage.getItem('id'),
            organisation_id: selectedOrganisationId, 
            position: newPosition || organizationInfo.position,
            joining_date: newJoiningDate || organizationInfo.joiningDate,
            leaving_date: newLeavingDate || organizationInfo.leavingDate,
        };

        console.log(updatedData);

        try {
            console.log("In ed", orgId.orgId, orgId.dtype);
            const updatedInfo = await updateOrgInfo(orgId.orgId, updatedData);
            setOrganizationInfo(updatedInfo); 
            setMessage('Organization information updated successfully.');
            navigate('/update'); 
        } catch (error) {
            console.error('Failed to update organization info:', error);
            setMessage('Failed to update organization info. Please try again.');
        }
    };

    return (
        <div>
            <NavBar />
            <div className="container mt-5">
                <h3>Edit Organization Info</h3>
                {message && <p className="text-danger">{message}</p>}
                <form onSubmit={handleUpdate}>
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
                            <option key={selectedOrganisationId} value={newOrganisationName} selected >{newOrganisationName}</option>
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
                            placeholder={organizationInfo.position}
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">Joining Date</label>
                        <input
                            type="date"
                            className="form-control"
                            value={newJoiningDate}
                            onChange={(e) => setNewJoiningDate(e.target.value)}
                            placeholder={organizationInfo.joiningDate}
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">Leaving Date</label>
                        <input
                            type="date"
                            className="form-control"
                            value={newLeavingDate}
                            onChange={(e) => setNewLeavingDate(e.target.value)}
                            placeholder={organizationInfo.leavingDate || 'Enter Leaving Date (or leave blank for present)'}
                        />
                    </div>
                    <button className="btn btn-success" type="submit">Save</button>
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

export default EditOrganization;

