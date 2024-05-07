import React, { useState } from 'react';

const Pagination = (props) => {
  const { size, indexHome, perPageHome } = props;
  const [perPage, setPerPage] = useState(4);
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
  

  return (
    <div>
      <button value="anterior" onClick={handlePreviousClick}>anterior</button>
      <p>{indexPage}/{Math.ceil(size/perPage)}</p>
      <button value="siguiente" onClick={handleNextClick}>siguiente</button>
    </div>
  );
};

export default Pagination;
