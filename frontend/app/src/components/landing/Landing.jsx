import React, { useEffect, useState } from "react";
import axios from "axios";
import './Styles.css';
import Card from '../card/Card';

const Landing = (props) => {
    const {throwAlert, finalMsg, listSize, index, qtyPage } = props;
    const [listVehicles, setListVehicles] = useState([]);
    const [searchTerm, setSearchTerm] = useState("");  
    useEffect(() => {
        const intervalId = setInterval(() => {
        setSearchTerm(localStorage.getItem('searchTerm'));
        }, 1000);
    
        return () => clearInterval(intervalId);
    }, [searchTerm]);

    useEffect(() => {
        setSearchTerm("");
        localStorage.setItem('searchTerm', '');
    }, []);

    //Get vehicles
    useEffect(() => {
        const getVehicles = async () => {
          const headers = {
            "Content-Type": "application/json"
          };
          try {
            const response = await axios.get("http://localhost:8080/api/unowsoltions/vehicles/get", { headers });
            setListVehicles(response.data.data);
          } catch (error) {
            console.log("Error fetching list vehicles:", error);
          }
        };
    
        getVehicles();
    
        const intervalId = setInterval(() => {
            getVehicles();
        }, 3000);
    
        return () => {
          clearInterval(intervalId);
        };
      }, [searchTerm]);

      const renderVehicles = () => {
        if(!listVehicles) return "No hay vehiculos disponbles";
        listSize(listVehicles.length);
        let vehiclesToShow = [];
        if(searchTerm !== "") {
          // Filter the list of vehicles based on the search term
          const vehiclesFiltered = listVehicles.filter(item => 
            item.brand.toLowerCase().startsWith(searchTerm.toLowerCase())
            || item.model.toLowerCase().startsWith(searchTerm.toLowerCase())
            || item.licensePlate.toLowerCase().startsWith(searchTerm.toLowerCase())
          );
    
          vehiclesToShow = vehiclesFiltered.map((item, index) => (
            <div key={index}>
              <Card vehicle={item} throwAlert={throwAlert} finalMsg={finalMsg}/>
            </div>
          ));
        } else {
          vehiclesToShow = listVehicles.map((item, index) => (
            <div key={index}>
              <Card vehicle={item} throwAlert={throwAlert} finalMsg={finalMsg}/>
            </div>
          ));
        }
        const startIndex = (index - 1) * qtyPage;
        const endIndex = startIndex + qtyPage;
        return vehiclesToShow.slice(startIndex, endIndex);;
      }

    return (
        <div className="landing">
            {renderVehicles()}
        </div>
    )
}

export default Landing;