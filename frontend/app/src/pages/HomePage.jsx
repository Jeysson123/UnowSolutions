import React, { useEffect, useState } from "react";
import Header from '../components/header/Header';
import Landing from "../components/landing/Landing";
import Update from "../components/update/Update";
import PopupMessage from "../components/dialog/PopupMessage";
import Pagination from "../components/pagination/Pagination";

const HomePage = () => {
    const [showInsert, setShowInsert] = useState(false); 
    const [showPopup, setShowPopup] = useState(false);
    const [popupMsg, setPopupMsg] = useState("");
    const [totalVehicles, setTotalVehicles] = useState(0);
    const [indexPage, setIndexPage] = useState(1);
    const [perPage, setPerPage] = useState(4);

    const handleInsert = (showFormInsert) => {
        setShowInsert(showFormInsert);
    }

    const handlePopup = (canShowPopup) => {
        setShowPopup(canShowPopup);
    }

    const handlePopupMsg = (msg) => {
        setPopupMsg(msg);
    }

    const handleSizeVehicles = (size) => {
        setTotalVehicles(size);
    }

    const handleIndexPage = (index) => {
        setIndexPage(index);
    }

    const handlePerPage = (perPage) => {
        setPerPage(perPage);
    }

    return (
        <>
        <Header registerVisible={handleInsert}/>
        {showInsert && <Update typeRequest="insert" throwAlert={handlePopup} finalMsg={handlePopupMsg}/>}
        {showPopup && <PopupMessage message={popupMsg} onClose={() => setShowPopup(false)} />}
        <Landing listSize={handleSizeVehicles} index={indexPage} qtyPage={perPage} throwAlert={handlePopup} finalMsg={handlePopupMsg}/>
        <Pagination size={totalVehicles} indexHome={handleIndexPage} perPageHome={handlePerPage}/>
        </>
    )
}

export default HomePage;