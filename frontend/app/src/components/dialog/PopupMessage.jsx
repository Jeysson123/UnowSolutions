import React from 'react';
import './Styles.css'

const PopupMessage = ({ message, onClose }) => {
	return (
		<div className="popup-message" style={{backgroundColor : message.includes('actualizado') || message.includes('exitoso') ? '#a9fa98' : 'crimson' }}>
			<div className="popup-content">
				<span>{message}</span>
				<span onClick={onClose}className='popupClose'></span>
			</div>
		</div>
	);
};

export default PopupMessage;
