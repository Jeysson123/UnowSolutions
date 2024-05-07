import React, { useState } from "react";
import './Styles.css';
import ImgUtils from "../../utils/ImgUtils";

const Detail = (props) => {
  const [showForm, setShowForm] = useState(true);
  return (
    <>
    {showForm && <div className="details">
    <p className="detailsBrand">{props.vehicle.brand}</p>
    <p className="detailsModel">{props.vehicle.model}</p>
    <p className="detailsPlate">{props.vehicle.licensePlate}</p>
    <p className="detailsColor">{props.vehicle.color}</p>
    <p className="detailsMake">{props.vehicle.year}</p>
    <span className="detailsClose" title="Cerrar detalles" onClick={() => setShowForm(false)}></span>
    <span className="maskContainer" style={{maskImage : `url(${require(`../../images/${ImgUtils.CustomImage(props.vehicle.brand)}`)})`}}></span>

  </div>
  }  
  </>
  );
};

export default Detail;
