const tilesProvider = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';

let myMap = L.map('mapid').setView([51.505, -0.09], 13);

let grupoCirculos = L.layerGroup();

L.tileLayer(tilesProvider,{
	maxZoom: 18,
}).addTo(myMap);

function onLocationFound(e) {
		var radius = e.accuracy / 2;

		L.marker(e.latlng).addTo(myMap)
			.bindPopup("You are within " + radius + " meters from this point").openPopup();

		L.circle(e.latlng, radius).addTo(myMap);
	}

	function onLocationError(e) {
		alert(e.message);
	}

	myMap.on('locationfound', onLocationFound);
	myMap.on('locationerror', onLocationError);

	myMap.locate({setView: true, maxZoom: 16});

let bandera = false;
function anadirCirculos(data) {
	myMap.setView([data.latitud,data.longitud],8);
	if(bandera == true){
		grupoCirculos.clearLayers();
	}
	let circulo;
	for (var i = 0; i < data.regiones.length; i++) {
		circulo = L.circle([data.regiones[i].latitud,data.regiones[i].longitud], 5000, {
		color: 'red',
		fillColor: '#f03',
		fillOpacity: 0.5
		});
		grupoCirculos.addLayer(circulo);
		//.addTo(myMap).bindPopup(data.regiones[i].nombre);
	}
	var i = 0;
	grupoCirculos.eachLayer(function (layer) {
    	layer.bindPopup(data.regiones[i].nombre);
    	i++;
	});
	grupoCirculos.addTo(myMap);
	bandera = true;
	
}	

