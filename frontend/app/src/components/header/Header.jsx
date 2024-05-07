import React, { useEffect, useState } from "react";
import './Styles.css';
import Lupa from "../../images/lupa.svg";

const Header = ({registerVisible}) => {
    const [searchTerm, setSearchTerm] = useState(""); 

    useEffect(() => {
        const intervalId = setInterval(() => {
            if(searchTerm === ""){
                localStorage.setItem('searchTerm', '');
            }
        }, 1000);
        return () => clearInterval(intervalId);
    }, [searchTerm]);

    const changeTerm = (e) => {
        setSearchTerm(e.target.value);
    }

    const handleKeyDown = (e) => {
        if (e.key === 'Enter') storeTerm(e);
    }

    const storeTerm = (e) => {
        e.preventDefault();
        setSearchTerm(prevSearchTerm => {
          localStorage.setItem('searchTerm', prevSearchTerm);
          return prevSearchTerm;
        });
      };

    const showFormInsert = (e) => {
        e.preventDefault();
        registerVisible(true);
    }

    return(
        <header>
            <div className="logo-container">
                <span className="logo"></span>
            </div>
            <div className="search-area">
                <input type="text" 
                className="search-bar"
                onKeyDown={handleKeyDown}
                onChange={changeTerm}
                value={searchTerm} />
                <button className="search-btn"><img alt="Lupa" src={Lupa} onClick={storeTerm}/></button>
            </div>
            <button className="add-vehicle" title="Agregar vehiculo" onClick={showFormInsert}>+</button>
        </header>
    )
}

export default Header;