import React, { useState } from "react";
import CoachService from "../connection/services/CoachService";
import RouteCoach from "./RouteCoach";

const AddCoach = () => {
    const initialCoachState = {
        firstName: "",
        lastName: "",
        yearOfBirth: false
    };
    const [coach, setCoach] = useState(initialCoachState);
    const [submitted, setSubmitted] = useState(false);
    const [errorMsg, setErrorMsg] = useState('');
    const handleInputChange = event => {
        const { name, value } = event.target;
        setCoach({ ...coach, [name]: value });
    };
    const saveCoach = () => {
        CoachService.create(coach)
            .then(() => {
                setSubmitted(true);
                setErrorMsg('');
            })
            .catch(e => {
                console.log(e);
                setErrorMsg(e.response.data);
            });
    };

    const newCoach = () => {
        setCoach(initialCoachState);
        setSubmitted(false);
    };
    
    return (
        <div>
            <RouteCoach activePage="add" />
            <div className="submit-form">
                {submitted ? (
                    <div>
                        <h4>You submitted successfully!</h4>
                        <button className="btn btn-success" onClick={newCoach}>
                            Create another one.
                        </button>
                    </div>
                ) : (
                    <div>
                        <div className="form-group">
                            <label htmlFor="firstName">First name</label>
                            <input
                                type="text"
                                className="form-control"
                                id="firstName"
                                required
                                value={coach.firstName}
                                onChange={handleInputChange}
                                name="firstName"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="lastName">Last name</label>
                            <input
                                type="text"
                                className="form-control"
                                id="lastName"
                                required
                                value={coach.lastName}
                                onChange={handleInputChange}
                                name="lastName"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="yearOfBirth">Year of birth</label>
                            <input
                                type="number"
                                className="form-control"
                                id="yearOfBirth"
                                required
                                value={coach.yearOfBirth}
                                onChange={handleInputChange}
                                name="yearOfBirth"
                            />
                        </div>
                        <button onClick={saveCoach} className="btn btn-success">
                            Submit coach
                        </button>
                    </div>
                )}
            </div>
            <p>{(typeof errorMsg === 'string' || errorMsg instanceof String) ? errorMsg : 'An error occured.'}</p>
        </div>
    );
};
export default AddCoach;