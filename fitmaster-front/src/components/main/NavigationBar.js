import { Link } from "react-router-dom";
import './NavigationBar.css';

const NavigationBar = () => {
    return (
        <div>
            <nav className="navbar navbar-expand navbar-dark bg-dark">
                <a href="/" className="navbar-brand">
                    <img src="./fit-master-logo.png" width="auto" height="40" alt="logo" />
                </a>
                <div className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <Link to={"/coaches"} className="nav-link">
                            coaches
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to={"/clubs"} className="nav-link">
                            clubs
                        </Link>
                    </li>
                </div>

            </nav>
        </div>
    )
}

export default NavigationBar;