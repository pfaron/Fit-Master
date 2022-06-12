import React, { useState } from "react";
import CoachService from "../connection/services/CoachService";

const AddCoach = () => {
    const initialCoachState = {
        firstName: "",
        lastName: "",
        yearOfBirth: false
    };
    const [coach, setCoach] = useState(initialCoachState);
    const [submitted, setSubmitted] = useState(false);
    const handleInputChange = event => {
        const { name, value } = event.target;
        setCoach({ ...coach, [name]: value });
    };
    const saveCoach = () => {
        var data = {
            firstName: coach.firstName,
            lastName: coach.lastName,
            yearOfBirth: coach.yearOfBirth
        };
        CoachService.create(data)
            .then(() => setSubmitted(true))
            .catch(e => {
                console.log(e);
            });
    };
    const newCoach = () => {
        setCoach(initialCoachState);
        setSubmitted(false);
    };
    return (
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
    );
};
export default AddCoach;