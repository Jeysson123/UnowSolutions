import React, {  useCallback, useState } from "react";
import './Styles.css';
import axios from "axios";
import Update from "../update/Update";
import Detail from "../detail/Detail";
import ImgUtils from "../../utils/ImgUtils";

const Card = (props) => {
    const {throwAlert, finalMsg } = props;
    const [showForm, setShowForm] = useState(false);
    const [showDetail, setShowDetail] = useState(false);

    const showAlert = (e) => {
        const result = window.confirm("Quieres eliminar este vehiculo?");
        if (result) {
          DeleteVehicle(e);
        } 
      };

    const DeleteVehicle = (e) => {
        e.preventDefault();
        const headers = {
          "Content-Type": "application/json"
        };
        const removeEndpoint = `http://localhost:8080/api/unowsoltions/vehicles/remove/${props.vehicle.id}`;
        axios
          .delete(removeEndpoint, { headers })
          .then((response) => {
            localStorage.setItem('popupMsg', !Array.isArray(response.data.data) ? response.data.data : '');
            localStorage.setItem('showPopup', false);
          })
          .catch((error) => console.log(error));
      };
    return (
        <div>
         {showForm && <Update typeRequest="update" id={props.vehicle.id} throwAlert={throwAlert} finalMsg={finalMsg} />}
         {showDetail && <Detail vehicle={props.vehicle}/>}
        <div className="card">
            <div className="img-container" onClick={() => setShowDetail(true)}>
                <img src={require(`../../images/${ImgUtils.CustomImage(props.vehicle.brand)}`)} alt="Imagen de vehiculo" />
            </div>
            <div className="info-container">
                <h1 onClick={() => setShowDetail(true)}>{props.vehicle.model}</h1>
                <h4 onClick={() => setShowDetail(true)}>{props.vehicle.year} </h4>
                <div className="btn-container">
                    <button className="info-btn" onClick={() => setShowForm(true)}>Actualizar</button>
                    <button className="info-btn" onClick={e => showAlert(e)}>Eliminar</button>
                </div>
            </div>
        </div>
        </div>
    )
}

export default Card;