import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import NavBar from '../components/NavBar';
import { getEduInfo, updateEducationInfo } from '../services/alumniService';

const EditEducation = () => {
    const [educationInfo, setEducationInfo] = useState({
        degree: '',
        passing_year: '',
        joining_year: '',
        college_name: '',
        address: ''
    });
    const [newDegree, setNewDegree] = useState('');
    const [newPassingYear, setNewPassingYear] = useState('');
    const [newJoiningYear, setNewJoiningYear] = useState('');
    const [newCollegeName, setNewCollegeName] = useState('');
    const [newAddress, setNewAddress] = useState('');
    const [message, setMessage] = useState('');
    const userId = localStorage.getItem('id');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchEducationInfo = async () => {
            try {
                const data = await getEduInfo(userId);
                setEducationInfo(data);
                setNewDegree(data.degree);
                setNewPassingYear(data.passing_year);
                setNewJoiningYear(data.joining_year);
                setNewCollegeName(data.college_name);
                setNewAddress(data.address);
            } catch (error) {
                console.error('Failed to fetch education info:', error);
                setMessage('Failed to load education info. Please try again.');
            }
        };

        fetchEducationInfo();
    }, [userId]);

    const handleUpdate = async (e) => {
        e.preventDefault(); // Prevent page refresh on form submission

        try {
            const updatedInfo = await updateEducationInfo(userId, {
                newDegree: newDegree || educationInfo.degree,
                newPassingYear: newPassingYear || educationInfo.passing_year,
                newJoiningYear: newJoiningYear || educationInfo.joining_year,
                newCollegeName: newCollegeName || educationInfo.college_name,
                newAddress: newAddress || educationInfo.address,
            });

            setEducationInfo(updatedInfo);
            setMessage('Education information updated successfully.');
            navigate('/dashboard'); 
        } catch (error) {
            console.error('Failed to update education info:', error);
            setMessage('Failed to update education info. Please try again.');
        }
    };

    return (
        <div>
            <NavBar />
            <div className="container mt-5">
                <h3>Edit Education Info</h3>
                {message && <p className="text-danger">{message}</p>}
                <form onSubmit={handleUpdate}>
                    <div className="mb-3">
                        <label className="form-label">Degree</label>
                        <input
                            type="text"
                            className="form-control"
                            value={newDegree}
                            onChange={(e) => setNewDegree(e.target.value)}
                            placeholder={educationInfo.degree}
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">Passing Year</label>
                        <input
                            type="number"
                            className="form-control"
                            value={newPassingYear}
                            onChange={(e) => setNewPassingYear(e.target.value)}
                            placeholder={educationInfo.passing_year}
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">Joining Year</label>
                        <input
                            type="number"
                            className="form-control"
                            value={newJoiningYear}
                            onChange={(e) => setNewJoiningYear(e.target.value)}
                            placeholder={educationInfo.joining_year}
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">College Name</label>
                        <input
                            type="text"
                            className="form-control"
                            value={newCollegeName}
                            onChange={(e) => setNewCollegeName(e.target.value)}
                            placeholder={educationInfo.college_name}
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">Address</label>
                        <input
                            type="text"
                            className="form-control"
                            value={newAddress}
                            onChange={(e) => setNewAddress(e.target.value)}
                            placeholder={educationInfo.address}
                        />
                    </div>
                    <button className="btn btn-success" type="submit">
                        Save
                    </button>
                    <button
                        className="btn btn-secondary ms-2"
                        onClick={(e) => {
                            e.preventDefault(); 
                            navigate('/dashboard');
                        }}
                    >
                        Cancel
                    </button>
                </form>
            </div>
        </div>
    );
};

export default EditEducation;
