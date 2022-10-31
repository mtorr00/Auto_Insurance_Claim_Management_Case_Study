import React, { useState, useEffect } from "react";
import axios from 'axios'


const ClaimCard = () => {
    const [content, setContent] = useState<string>("");
    
    useEffect(() => {
        const API_GET_URL = "http://localhost:9080/api/claims/admin/" + + "/claims"

        axios.get(API_GET_URL).then(
        (response) => {
          setContent(response.data);
        },
        (error) => {
          const _content =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
  
          setContent(_content);
  
        }
      );
    }, []);
  
  
  
  
    return (
    <div className='bordered'>{content}</div>
  )
}

export default ClaimCard