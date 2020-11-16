const tilesProvider = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';

let myMap = L.map('mapid').setView([51.505, -0.09], 13);

L.tileLayer(tilesProvider,{
	maxZoom: 18,
}).addTo(myMap);

function anadirCirculos(datos) {
	for (var i = 0; i < data.regiones.length; i++) {
		L.circle([data.regiones[i].latitud,data.regiones[i].longitud], 5000, {
		color: 'red',
		fillColor: '#f03',
		fillOpacity: 0.5
		}).addTo(mymap).bindPopup(data.regiones[i].nombre);
	}
}	

