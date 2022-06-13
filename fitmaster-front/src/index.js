import "bootstrap/dist/css/bootstrap.min.css";
import React from "react";
import { createRoot } from 'react-dom/client';
import { BrowserRouter } from "react-router-dom";
import NavigationBar from "./components/main/NavigationBar";
import AllRoutes from "./components/main/AllRoutes";

const root = createRoot(document.getElementById('root'));

root.render(
  <BrowserRouter>
    <NavigationBar />
    <AllRoutes />
  </BrowserRouter>
);