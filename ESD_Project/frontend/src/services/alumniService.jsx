import axios from "axios";

const API_URL = "/api/v1/alumni";

export async function login(email, password) {
    const response = await axios.post(`${API_URL}/login`, { email, password });
    return response.data;
}

export async function getContactInfo(userId) {
    const token =  localStorage.getItem("token");
    const response = await axios.get(`${API_URL}/${userId}`,{headers: { Authorization: `Bearer ${token}` },    });
    return response.data;
}

export async function updateContactInfo(userId, newContactInfo) {
    const token =  localStorage.getItem("token");
    console.log(token);

    const { newContactNumber, newEmail } = newContactInfo;

    const response = await axios.put(
        `${API_URL}/${userId}`,
        new URLSearchParams({
            newContactNumber,
            newEmail,
        }),
        {
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                Authorization: `Bearer ${token}`, 
            },
        }
    );

    return response.data; 
}

export async function getEduInfo(userId) {
    const token = localStorage.getItem("token");
    const response = await axios.get(`${API_URL}/education/${userId}`, {
        headers: { Authorization: `Bearer ${token}` },
    });
    return response.data;
}


export async function getOrgInfo(userId){
        const token = localStorage.getItem("token");
        // userId = localStorage.getItem('orgId');
        // console.log("In get",userId);
        const response = await axios.get(`${API_URL}/org/${userId}`, {
                headers: { Authorization: `Bearer ${token}` },
            });
        return response.data;
}

export async function deleteOrg(workId) {
    const token = localStorage.getItem("token");
    const response = await axios.delete(`${API_URL}/org/${workId}`, {
        headers: { Authorization: `Bearer ${token}` },
    });
    return response.data;
}

export const updateOrgInfo = async (orgId, updatedData) => {
    const token = localStorage.getItem("token");
    console.log("on servs",orgId);
    const response = await axios.put(
        `${API_URL}/org/${orgId}`, 
        updatedData, 
        {
            headers: {
                Authorization: `Bearer ${token}`, 
            },
        }
    );
    return response.data;
};

export const createOrganization = async (updatedData) => {
    const token = localStorage.getItem("token");
    const response = await axios.post(
        `${API_URL}/org`,  
        updatedData, 
        {
            headers: {
                Authorization: `Bearer ${token}`, 
            },
        }
    );
    return response.data;
};

export async function getOrganization(orgId){
    const token = localStorage.getItem("token");
    const response = await axios.get(`${API_URL}/orgbyorg/${orgId}`, {
            headers: { Authorization: `Bearer ${token}` },
        });
    return response.data;
}

export async function getAllOrganizations(){
    const token = localStorage.getItem("token");
    const response = await axios.get(`${API_URL}/org`, {
            headers: { Authorization: `Bearer ${token}` },
        });
    return response.data;
}





export async function updateEducationInfo(userId, updatedEducationInfo) {
    const token = localStorage.getItem("token");
    console.log("Token:", token);

    const { newDegree, newPassingYear, newJoiningYear, newCollegeName, newAddress } = updatedEducationInfo;

    try {
        const response = await axios.put(
            `${API_URL}/education/${userId}`, 
            {
                degree: newDegree,
                passing_year: newPassingYear,
                joining_year: newJoiningYear,
                college_name: newCollegeName,
                address: newAddress,
            },
            {
                headers: {
                    "Content-Type": "application/json",  
                    Authorization: `Bearer ${token}`,    
                },
            }
        );

        return response.data;  
    } catch (error) {
        console.error('Error updating education info:', error);
        throw error;  
    }
}
