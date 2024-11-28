import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import NavBar from '../components/NavBar';
import { getContactInfo, updateContactInfo } from '../services/alumniService'; 

const EditPersonalDetails = () => {
    const [contactInfo, setContactInfo] = useState({ email: '', contactNumber: '' });
    const [newEmail, setNewEmail] = useState('');
    const [newContactNumber, setNewContactNumber] = useState('');
    const [message, setMessage] = useState('');
    const userId = localStorage.getItem('id'); 
    const navigate = useNavigate();

    useEffect(() => {
        const fetchContactInfo = async () => {
            try {
                const data = await getContactInfo(userId);
                setContactInfo(data);
                setNewEmail(data.email);
                setNewContactNumber(data.contactNumber);
            } catch (error) {
                console.error('Failed to fetch contact info:', error);
                setMessage('Failed to load contact info. Please try again.');
            }
        };

        fetchContactInfo();
    }, [userId]);

    const handleUpdate = async (e) => {
        e.preventDefault(); 

        try {
            const updatedInfo = await updateContactInfo(userId, {
                newEmail: newEmail || contactInfo.email,
                newContactNumber: newContactNumber || contactInfo.contactNumber,
            });

            setContactInfo(updatedInfo);
            setMessage('Contact information updated successfully.');
            navigate('/dashboard'); 
        } catch (error) {
            console.error('Failed to update contact info:', error);
            setMessage('Failed to update contact info. Please try again.');
        }
    };

    return (
        <div>
            <NavBar />
            <div className="container mt-5">
                <h3>Edit Personal Info</h3>
                {message && <p className="text-danger">{message}</p>}
                <form onSubmit={handleUpdate}>
                    <div className="mb-3">
                        <label className="form-label">New Email</label>
                        <input
                            type="email"
                            className="form-control"
                            value={newEmail}
                            onChange={(e) => setNewEmail(e.target.value)}
                            placeholder={contactInfo.email}
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">New Contact Number</label>
                        <input
                            type="tel"
                            className="form-control"
                            value={newContactNumber}
                            onChange={(e) => setNewContactNumber(e.target.value)}
                            placeholder={contactInfo.contactNumber}
                        />
                    </div>
                    <button className="btn btn-success" type="submit">
                        Save
                    </button>
                    <button
                        className="btn btn-secondary ms-2"
                        onClick={() => navigate('/dashboard')} 
                    >
                        Cancel
                    </button>
                </form>
            </div>
        </div>
    );
};

export default EditPersonalDetails;
