import axios from "axios";

export default axios.create({
    baseURL: `${process.env.REACT_APP_BACKEND_URL}/api/v1`,
    headers: {
        "Content-type": "application/json"
    }
});
