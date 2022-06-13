import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useParams, useNavigate } from 'react-router-dom';
import CoachService from "../connection/services/CoachService";

const UpdateCoach = props => {
    const { id } = useParams();
    let navigate = useNavigate();

    const initialCoachState = {
        id: null,
        firstName: "",
        lastName: "",
        yearOfBirth: false
    };

    const [currentCoach, setCurrentCoach] = useState(initialCoachState);
    const [message, setMessage] = useState("");

    const getCoach = id => {
        CoachService.get(id)
            .then(response => {
                setCurrentCoach(response.data);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    useEffect(() => {
        if (id)
            getCoach(id);
    }, [id]);

    const handleInputChange = event => {
        const { name, value } = event.target;
        setCurrentCoach({ ...currentCoach, [name]: value });
    };

    const updateCoach = () => {
        CoachService.update(currentCoach.id, currentCoach)
            .then(response => {
                console.log(response.data);
                setMessage("The coach was updated successfully!");
            })
            .catch(e => {
                console.log(e);
            });
    };

    const deleteCoach = () => {
        CoachService.remove(currentCoach.id)
            .then(response => {
                console.log(response.data);
                navigate("/coaches/list");
            })
            .catch(e => {
                console.log(e);
            });
    };
    return (
        <div>
            {currentCoach ? (
                <div className="edit-form">
                    <h4>Coach</h4>
                    <form>
                        <div className="form-group">
                            <label htmlFor="firstName">First Name</label>
                            <input
                                type="text"
                                className="form-control"
                                id="firstName"
                                name="firstName"
                                value={currentCoach.firstName}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="lastName">Last Name</label>
                            <input
                                type="text"
                                className="form-control"
                                id="lastName"
                                name="lastName"
                                value={currentCoach.lastName}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="yearOfBirth">Year of birth</label>
                            <input
                                type="number"
                                className="form-control"
                                id="yearOfBirth"
                                name="yearOfBirth"
                                value={currentCoach.yearOfBirth}
                                onChange={handleInputChange}
                            />
                        </div>
                    </form>
                    <button
                        className="btn btn-primary m-2"
                        onClick={deleteCoach}
                    >
                        Delete
                    </button>
                    <button
                        type="submit"
                        className="btn btn-primary m-2"
                        onClick={updateCoach}
                    >
                        Update
                    </button>
                    <Link
                        to={"/coaches/list"}
                        className="btn btn-primary m-2"
                    >
                        All coaches
                    </Link>
                    <p>{message}</p>
                </div>
            ) : (
                <div>
                    <br />
                    <p>Please click on a coach...</p>
                </div>
            )}
        </div>
    );
};
export default UpdateCoach;
