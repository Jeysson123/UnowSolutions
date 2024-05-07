import React, { useState } from 'react';
import './Styles.css'

const Pagination = (props) => {
	const { size, indexHome, perPageHome } = props;
	const [perPage, setPerPage] = useState(3);
	const [indexPage, setIndexPage] = useState(1);

	const handlePreviousClick = () => {
		const newIndex = indexPage === 1 ? indexPage : indexPage - 1;
		setIndexPage(newIndex);
		indexHome(newIndex);
		perPageHome(perPage);
	};

	const handleNextClick = () => {
		const newIndex = indexPage === Math.ceil(size / perPage) ? Math.ceil(size / perPage) : indexPage + 1;
		setIndexPage(newIndex);
		indexHome(newIndex);
		perPageHome(perPage);
	};

	
    const handlePerPage = (e) => {
		const newPerPage = parseInt(e.target.value);
		setPerPage(newPerPage); // Update perPage state
		setIndexPage(1); // Reset indexPage when changing perPage
		indexHome(1); // Also update the index in HomePage to 1
		perPageHome(newPerPage); // Pass the new perPage value back to HomePage
	};
	

	return (
	<div className='paginationContainer'>
		<button value="anterior" onClick={handlePreviousClick}>«</button>
		<p>{indexPage}/{Math.ceil(size/perPage)}</p>
		<button value="siguiente" onClick={handleNextClick}>»</button>
		<input type="text" className='pageAmount' value={perPage} onChange={handlePerPage} />
	</div>
	);
};

export default Pagination;