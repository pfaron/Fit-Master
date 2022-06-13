import React from "react";
import { Routes, Route } from "react-router-dom";
import AddCoach from '../coach/AddCoach';
import ListCoaches from "../coach/ListCoaches";
import UpdateCoach from "../coach/UpdateCoach";
import RouteCoach from "../coach/RouteCoach";
import MainPage from "../main/MainPage";
import RouteClub from "../club/RouteClub";
import AddClub from "../club/AddClub";
import ListClubs from "../club/ListClubs";

const AllRoutes = () => {
    return (
        <div className="container mt-3">
            <Routes>
                <Route path="/" element={<MainPage />} />
                <Route path="/coaches" element={<RouteCoach />} />
                <Route path="/coaches/list" element={<ListCoaches />} />
                <Route path="/coaches/add" element={<AddCoach />} />
                <Route path="/coaches/:id" element={<UpdateCoach />} />
                <Route path="/clubs" element={<RouteClub />} />
                <Route path="/clubs/list" element={<ListClubs />} />
                <Route path="/clubs/add" element={<AddClub />} />
            </Routes>
        </div>
    )
}

export default AllRoutes;