import React from 'react';
import './Styles.css'; 

const PopupMessage = ({ message, onClose }) => {
  return (
    <div className="popup-message">
      <div className="popup-content">
        <span>{message}</span>
        <button onClick={onClose}>Close</button>
      </div>
    </div>
  );
};

export default PopupMessage;
