// import React, { useState, useEffect } from 'react';
// import { useNavigate } from 'react-router-dom';
// import NavBar from '../components/NavBar';
// import { getContactInfo, updateContactInfo } from '../services/alumniService'; // Import service layer functions

// const Dashboard = () => {
//     const [contactInfo, setContactInfo] = useState({ email: '', contactNumber: '' });
//     const [isEditing, setIsEditing] = useState(false);
//     const [newEmail, setNewEmail] = useState('');
//     const [newContactNumber, setNewContactNumber] = useState('');
//     const [message, setMessage] = useState('');
//     const userId = localStorage.getItem('id');


//     useEffect(() => {
//         const fetchContactInfo = async () => {
//             try {
//                 const data = await getContactInfo(userId);
//                 setContactInfo(data);
//             } catch (error) {
//                 console.error('Failed to fetch contact info:', error);
//                 setMessage('Failed to load contact info. Please try again.');
//             }
//         };

//         fetchContactInfo();
//     }, [userId]);

//     const handleUpdate = async () => {
//         try {
//             const updatedInfo = await updateContactInfo(userId, {
//                 newEmail: newEmail || contactInfo.email, // Use the existing email if the field is empty
//                 newContactNumber: newContactNumber || contactInfo.contactNumber, // Use the existing contact number if the field is empty
//             });
//             setContactInfo(updatedInfo);
//             setIsEditing(false);
//             setMessage('Contact information updated successfully.');
//         } catch (error) {
//             console.error('Failed to update contact info:', error);
//             setMessage('Failed to update contact info. Please try again.');
//         }
//     };

//     return (
//         <div>
//             <NavBar />
//             <div className="container mt-5">
//                 {/* <h3>Personal Info</h3>
//                 {message && <p className="text-danger">{message}</p>} */}

//                 {!isEditing ? (
//                     <div>
//                     <div>
//                     <h3>Personal Info</h3>
//                         {message && <p className="text-danger">{message}</p>}

//                         <p><strong>Email:</strong> {contactInfo.email}</p>
//                         <p><strong>Contact Number:</strong> {contactInfo.contactNumber}</p>
//                         <button className="btn btn-primary" onClick={() => setIsEditing(true)}>
//                             Edit
//                         </button>
//                     </div>
//                     <br></br>
//                     <br></br>
//                     <div>
//                     <h3>Education Info</h3>
//                     {message && <p className="text-danger">{message}</p>}

//                     <p><strong>Email:</strong> {contactInfo.email}</p>
//                     <p><strong>Contact Number:</strong> {contactInfo.contactNumber}</p>
//                     <button className="btn btn-primary" onClick={() => setIsEditing(true)}>
//                         Edit
//                     </button>
//                     </div>
//                     </div>
//                 ) : (
//                     <div>
//                         <form onSubmit={(e) => e.preventDefault()}>
//                             <div className="mb-3">
//                                 <label className="form-label">New Email</label>
//                                 <input
//                                     type="email"
//                                     className="form-control"
//                                     // value={newEmail}
//                                     onChange={(e) => setNewEmail(e.target.value)}
//                                     placeholder={contactInfo.email}
                                    
//                                 />
//                             </div>
//                             <div className="mb-3">
//                                 <label className="form-label">New Contact Number</label>
//                                 <input
//                                     type="tel"
//                                     className="form-control"
//                                     // value={newContactNumber}
//                                     onChange={(e) => setNewContactNumber(e.target.value)}
//                                     placeholder={contactInfo.contactNumber}
                                    
//                                 />
//                             </div>
//                             <button className="btn btn-success" onClick={handleUpdate}>
//                                 Save
//                             </button>
//                             <button
//                                 className="btn btn-secondary ms-2"
//                                 onClick={() => setIsEditing(false)}
//                             >
//                                 Cancel
//                             </button>
//                         </form>
//                     </div>
//                 )}
//             </div>
//         </div>
//     );
// };

// export default Dashboard;

import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import NavBar from '../components/NavBar';
import { getContactInfo } from '../services/alumniService'; 
import { getEduInfo } from '../services/alumniService'; 

const Dashboard = () => {
    const [contactInfo, setContactInfo] = useState({ email: '', contactNumber: '' });
    const [eduInfo, setEduInfo] = useState({ degree: '', passing_year: '', joining_year: '', college_name: '', address: '' });
    const [message, setMessage] = useState('');
    const userId =  localStorage.getItem("id"); 
    const navigate = useNavigate();

    useEffect(() => {
        const fetchContactInfo = async () => {
            try {
                const data = await getContactInfo(userId);
                console.log(data);
                setContactInfo(data);
            } catch (error) {
                console.error('Failed to fetch contact info:', error);
                setMessage('Failed to load contact info. Please try again.');
            }
        };

        const fetchEduInfo = async () => {
            try {
                const data = await getEduInfo(userId);
                console.log(data);
                setEduInfo(data);
            } catch (error) {
                console.error('Failed to fetch education info:', error);
                setMessage('Failed to load education info. Please try again.');
            }
        };

        fetchContactInfo();
        fetchEduInfo();
    }, [userId]);

    const handleEditClick = (section) => {
        if (section === 'personal') {
            navigate('/edit-personal');
        } else if (section === 'education') {
            navigate('/edit-education');
        }
    };

    return (
        <div>
            <NavBar />
            <div className="container mt-5">
                <h3>My Info</h3>
                {message && <p className="text-danger">{message}</p>}

                <div className="card mb-3">
                    <div className="card-body">
                        <h4 className="card-title">Personal Info</h4>
                        <p><strong>Email:</strong> {contactInfo.email}</p>
                        <p><strong>Contact Number:</strong> {contactInfo.contactNumber}</p>
                        {/* <button 
                            className="btn btn-primary" 
                            onClick={() => handleEditClick('personal')}
                            style={{ marginLeft: 'auto',
                                width:'100px'
                             }}
                        >
                            Edit
                        </button> */}
                        <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
            <button 
                className="btn btn-primary" 
                onClick={() => handleEditClick('personal')}
                style={{ width: '100px' }}
            >
                Edit
            </button>
        </div>
                    </div>
                </div>

                <div className="card mb-3">
                    <div className="card-body">
                        <h4 className="card-title">Education Info</h4>
                        <p><strong>Degree:</strong> {eduInfo.degree}</p>
                        <p><strong>Passing Year:</strong> {eduInfo.passing_year}</p>
                        <p><strong>Joining Year:</strong> {eduInfo.joining_year}</p>
                        <p><strong>College Name:</strong> {eduInfo.college_name}</p>
                        <p><strong>Address:</strong> {eduInfo.address}</p>
                        <div style={{ display: 'flex' }}>
                        <button 
                            className="btn btn-primary" 
                            onClick={() => handleEditClick('education')}
                            style={{ marginLeft: 'auto',
                                width:'100px'
                             }} 
                        >
                            Edit
                        </button>
</div>

                    </div>
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
