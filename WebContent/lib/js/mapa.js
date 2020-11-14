const tilesProvider = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';

let myMap = L.map('mapid').setView([51.505, -0.09], 13);

let grupoCirculos = L.layerGroup();

L.tileLayer(tilesProvider,{
	maxZoom: 18,
}).addTo(myMap);

let bandera = false;
function anadirCirculos(data) {
	if(bandera == true){
		grupoCirculos.clearLayers();
	}
	for (var i = 0; i < data.regiones.length; i++) {
		let circulo = L.circle([data.regiones[i].latitud,data.regiones[i].longitud], 5000, {
		color: 'red',
		fillColor: '#f03',
		fillOpacity: 0.5,
		bindPopup: data.regiones[i].nombre
		});
		grupoCirculos.addLayer(circulo);
		//.addTo(myMap).bindPopup(data.regiones[i].nombre);
	}
	
	grupoCirculos.addTo(myMap);
	bandera = true;
	
}	

