# API-Trasporte


La documentacion de Swagger se encuentra en: http://localhost:8080/swagger-ui/index.htm

Se incluye el archivo APITransporte.postman_collection.json con la coleccion de Postman para poder probar los servicios.

De igual forma en este archivo se especifica la forma de consumir los servicios mediante portman:

EL proyecto esta estructurado en:

->Autenticacion
	Tipo POST
	http://localhost:8080/api/login

	Se requiere enviar el siguiente JSON en el cuerpo de la peticion:
	//cualquier otro usuario o password no permitira el acceso
	{
		"email": "admin@example.com",
		"password": "password123"
	}


->Driver
	-Crea un conductor
		Tipo POST
		Cabecera: Authorization Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBleGFtcGxlLmNvbSIsImlhdCI6MTc5MzM3Njg3MiwiZXhwIjoxNzkzNDEyODcyfQ.f28hT6HdYIa1lGeoJZyujVdv6uePL1T2D20vKVf_cGF6HtAlIbSpfY-mvZmkack2rp7qiXIhqE1CF0KD462Bcg
		http://localhost:8080/creaConductor

		Se requiere enviar el siguiente JSON en el cuerpo de la peticion:
		{
			 "name":"Saul Ortiz",
			 "licenseNumber":"45455",
			 "active":true
		}

	-Lista conductores
		Tipo GET
		Cabecera: Authorization Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBleGFtcGxlLmNvbSIsImlhdCI6MTc5MzM3Njg3MiwiZXhwIjoxNzkzNDEyODcyfQ.f28hT6HdYIa1lGeoJZyujVdv6uePL1T2D20vKVf_cGF6HtAlIbSpfY-mvZmkack2rp7qiXIhqE1CF0KD462Bcg
		http://localhost:8080/listaDeConductores



->Order
	-Crea nueva Orden
		Tipo POST
		Cabecera: Authorization Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBleGFtcGxlLmNvbSIsImlhdCI6MTc5MzM3Njg3MiwiZXhwIjoxNzkzNDEyODcyfQ.f28hT6HdYIa1lGeoJZyujVdv6uePL1T2D20vKVf_cGF6HtAlIbSpfY-mvZmkack2rp7qiXIhqE1CF0KD462Bcg
		http://localhost:8080/creaOrden

		Se requiere enviar el siguiente JSON en el cuerpo de la peticion:
		{
			 "status":"CREATED",
			 "origin":"CDMX",
			 "destination":"Oaxaca",
			 "createdAt":"2026-10-29T15:30:00",
			 "updatedAt":"2026-10-29T15:30:00"
		}

	-Cambiar el estado de la orden
		Tipo PUT
		Cabecera: Authorization Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBleGFtcGxlLmNvbSIsImlhdCI6MTc5MzM3Njg3MiwiZXhwIjoxNzkzNDEyODcyfQ.f28hT6HdYIa1lGeoJZyujVdv6uePL1T2D20vKVf_cGF6HtAlIbSpfY-mvZmkack2rp7qiXIhqE1CF0KD462Bcg
		http://localhost:8080/cambiaEstadoOrden

		Se requiere enviar el siguiente JSON en el cuerpo de la peticion:
		{
			"id":"a2028751-e047-452b-85bf-650cb0c618e4",
			 "status":"IN_TRANSIT"
		}
		
	-Consultar orden por ID
		Tipo GET
		Cabecera: Authorization Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBleGFtcGxlLmNvbSIsImlhdCI6MTc5MzM3Njg3MiwiZXhwIjoxNzkzNDEyODcyfQ.f28hT6HdYIa1lGeoJZyujVdv6uePL1T2D20vKVf_cGF6HtAlIbSpfY-mvZmkack2rp7qiXIhqE1CF0KD462Bcg
		http://localhost:8080/consultaOrdenId/71bc1083-9c7f-43ed-8c79-e91a4a03be6b
		
	-Filtrar orden
		Tipo GET
		Cabecera: Authorization Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBleGFtcGxlLmNvbSIsImlhdCI6MTc5MzM3Njg3MiwiZXhwIjoxNzkzNDEyODcyfQ.f28hT6HdYIa1lGeoJZyujVdv6uePL1T2D20vKVf_cGF6HtAlIbSpfY-mvZmkack2rp7qiXIhqE1CF0KD462Bcg
		http://localhost:8080/filtraOrden?status=IN_TRANSIT

->Asignacion Ordenes
	
	-Asignacion		
		Tipo POST
		Cabecera: Authorization Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBleGFtcGxlLmNvbSIsImlhdCI6MTc5MzM3Njg3MiwiZXhwIjoxNzkzNDEyODcyfQ.f28hT6HdYIa1lGeoJZyujVdv6uePL1T2D20vKVf_cGF6HtAlIbSpfY-mvZmkack2rp7qiXIhqE1CF0KD462Bcg
		http://localhost:8080/asignacion

		Se requiere enviar el siguiente JSON en el cuerpo de la peticion:
		{
			 "idOrder":"50054adc-f583-49b4-a7a9-c4604bcc13cb",
			 "idDriver":"556f6b71-36e0-4857-a38a-ec1af52b329e"
		}

	-Subir Imagen		
		Tipo POST
		Cabecera: Authorization Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBleGFtcGxlLmNvbSIsImlhdCI6MTc5MzM3Njg3MiwiZXhwIjoxNzkzNDEyODcyfQ.f28hT6HdYIa1lGeoJZyujVdv6uePL1T2D20vKVf_cGF6HtAlIbSpfY-mvZmkack2rp7qiXIhqE1CF0KD462Bcg
		http://localhost:8080/archivoImg?idDriver=556f6b71-36e0-4857-a38a-ec1af52b329e&idOrder=50054adc-f583-49b4-a7a9-c4604bcc13cb

		Se requiere enviar la siguiente informacion en form-data:
		key: Imagen
		tipo: File
		Value: (seleccionar la imagen a subir)
		
		
	-Subir Pdf		
		Tipo POST
		Cabecera: Authorization Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBleGFtcGxlLmNvbSIsImlhdCI6MTc5MzM3Njg3MiwiZXhwIjoxNzkzNDEyODcyfQ.f28hT6HdYIa1lGeoJZyujVdv6uePL1T2D20vKVf_cGF6HtAlIbSpfY-mvZmkack2rp7qiXIhqE1CF0KD462Bcg
		http://localhost:8080/archivoPdf?idDriver=517c7b56-4f6d-414e-909d-e056a07bc648&idOrder=2d5e4a07-69e5-45b0-8dce-523cf405f32a

		Se requiere enviar la siguiente informacion en form-data:
		key: pdf
		tipo: File
		Value: (seleccionar el pdf a subir)