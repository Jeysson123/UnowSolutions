import React, { useEffect, useState } from "react";
import './Styles.css'
import axios from "axios";
import Vehicle from "../../dao/Vehicle";


const Update = (props) => {
    const { typeRequest, throwAlert, finalMsg } = props;
    const [showForm, setShowForm] = useState(true);
    const [value, setValue] = useState('');
    const [validation, setValidation] = useState('');


    useEffect(() => {
        if(typeRequest === "update") {
            completeFields();
        }
    }, []);

    const changeValue = (e) => {
        const {name, value} = e.target
        setValue(v=>({...v, [name]: value}))
    }

    const completeFields = async () => {
        const  findUrl = `http://localhost:8080/api/unowsoltions/vehicles/find?id=${props.id}`;
        const headers = {
          'Content-Type': 'application/json'
        };
        const response = await axios.get(findUrl, { headers })
        const data = response.data.data;
        setValue({carBrand : data.brand, carModel : data.model, carPlate : data.licensePlate, carColor : data.color, carMake : data.year})

      }

    const update = (e) => {
        e.preventDefault();
        throwAlert(true);
        let updateUrl = typeRequest === "update" ? `http://localhost:8080/api/unowsoltions/vehicles/update/${props.id}`
                            : 'http://localhost:8080/api/unowsoltions/vehicles/insert';

        const vehicle = new Vehicle(value.carBrand, value.carModel, value.carPlate, value.carColor, value.carMake);

        const headers = {
            'Content-Type': 'application/json'
        };

        const body = {
            brand : vehicle.brand ? vehicle.brand : "",
            model : vehicle.model ? vehicle.model : "",
            licensePlate : vehicle.licensePlate ? vehicle.licensePlate : "",
            color : vehicle.color ? vehicle.color : "",
            year : vehicle.year ? vehicle.year : ""
        }
        if(updateUrl.includes('update')) {
            axios.put(updateUrl,  body, { headers })
            .then(response => {
            if(Array.isArray(response.data.data)){
                let updatedValidation = { ...validation }
                response.data.data.forEach(obj => {
                    updatedValidation.errorBrand = obj.msg.includes('Marca') ? obj.msg : updatedValidation.errorBrand;
                    updatedValidation.errorModel = obj.msg.includes('Modelo') ? obj.msg : updatedValidation.errorModel;
                    updatedValidation.errorPlate = obj.msg.includes('Matricula') ? obj.msg : updatedValidation.errorPlate;
                    updatedValidation.errorColor = obj.msg.includes('Color') ? obj.msg : updatedValidation.errorColor;
                    updatedValidation.errorYear = obj.msg.includes('Año') ? obj.msg : updatedValidation.errorYear;
                });
                setValidation(updatedValidation);
                finalMsg('Formulario con errores');

            }
            else{
                finalMsg(Array.isArray(response.data.data) ? response.data.data : 'Formulario con errores');
                if(response.data.data.includes('actualizado')) setShowForm(false);
            }
            });
        }
        else {
         axios.post(updateUrl,  body, { headers })
            .then(response => {
            if(Array.isArray(response.data.data)){
                let updatedValidation = { ...validation };
                response.data.data.forEach(obj => {
                    updatedValidation.errorBrand = obj.msg.includes('Marca') ? obj.msg : updatedValidation.errorBrand;
                    updatedValidation.errorModel = obj.msg.includes('Modelo') ? obj.msg : updatedValidation.errorModel;
                    updatedValidation.errorPlate = obj.msg.includes('Matricula') ? obj.msg : updatedValidation.errorPlate;
                    updatedValidation.errorColor = obj.msg.includes('Color') ? obj.msg : updatedValidation.errorColor;
                    updatedValidation.errorYear = obj.msg.includes('Año') ? obj.msg : updatedValidation.errorYear;
                });
                setValidation(updatedValidation);
                finalMsg('Formulario con errores');

            }
            else{
                finalMsg(!Array.isArray(response.data.data) ? response.data.data : 'Formulario con errores');
                if(response.data.data.includes('exitoso')) setShowForm(false);
            }
            });
        }
    }
    const clearFields = () => {
        setValue('');
        setValidation('');
    }
    return (
        <>
        {showForm &&  <form className="update" onSubmit={update}>
        <label for="car-brand"><span>Marca</span>
            <input onChange={changeValue} name="carBrand" value={value.carBrand || ''} id="car-brand" type="text" className={validation.errorBrand ? "colorError" : ""}/>
            <p className="validationError" name="errorBrand">{validation.errorBrand || ''}</p>
        </label>

        <label for="car-model"><span>Modelo</span>
            <input onChange={changeValue} name="carModel" value={value.carModel || ''} id="car-model" type="text" className={validation.errorModel ? "colorError" : ""}/>
            <p className="validationError" name="errorModel">{validation.errorModel || ''}</p>
        </label>

        <label for="car-plate"><span>Matricula</span>
            <input onChange={changeValue} name="carPlate" value={value.carPlate || ''} id="car-plate" type="text" className={validation.errorPlate ? "colorError" : ""}/>
            <p className="validationError" name="errorPlate">{validation.errorPlate || ''}</p>
        </label>

        <label for="car-color"><span>Color</span>
            <input onChange={changeValue} name="carColor" value={value.carColor || ''} id="car-color" type="text" className={validation.errorColor ? "colorError" : ""}/>
            <p className="validationError" name="errorColor">{validation.errorColor || ''}</p>
        </label>

        <label for="car-make"><span>Año</span>
            <input onChange={changeValue} name="carMake" value={value.carMake || ''} id="car-make" type="text" className={validation.errorYear ? "colorError" : ""}/>
            <p className="validationError" name="errorYear">{validation.errorYear || ''}</p>
        </label>

        <div className="update-btn-container">
            
            <input type="submit" value={typeRequest === "update" ? "Actualizar" : "Registar"} />

            <input type="reset" value="Limpiar" onClick={clearFields}/>
            
        </div>
    </form>}
       </>
    )
}

export default Update;