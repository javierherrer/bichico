	const tilesProvider = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';

	let myMap = L.map('mapid').setView([40.463667, -3.74922], 6);

	let grupoCirculos = L.layerGroup();

	L.tileLayer(tilesProvider,{
		maxZoom: 18,
	}).addTo(myMap);

//marca de agua
L.Control.Watermark = L.Control.extend({
	onAdd: function(map) {
		var img = L.DomUtil.create('img');

		img.src = './img/gallery/covid.png';
		img.style.width = '60px';

		return img;
	},

	onRemove: function(map) {
        // Nothing to do here
    }
});

L.control.watermark = function(opts) {
	return new L.Control.Watermark(opts);
}

L.control.watermark({ position: 'bottomleft' }).addTo(myMap);


//leyenda mapa
var legend = L.control({position: 'bottomright'});

legend.onAdd = function (map) {

	var div = L.DomUtil.create('div', 'info legend'),
	grades = [0, 20, 50, 70, 100],
	labels = [],
	from, to;

	for (var i = 0; i < grades.length -1; i++) {
		from = grades[i];
		to = grades[i + 1];
		if(from == 0){
			labels.push(
				'<i style="background:white;">Na</i> ' +
				from + (to ? '&ndash;' + to : '+'));
		}else{
			labels.push(
				'<i style="background:' + getColor(from + 1) + '"></i> ' +
				from + (to ? '&ndash;' + to : '+'));
		}
		
	}

	div.innerHTML = labels.join('<br>');
	return div;
};

legend.addTo(myMap);

//localizacion de la posicion
function onLocationFound(e) {
	var radius = e.accuracy / 2;

	L.marker(e.latlng).addTo(myMap)
	.bindPopup("Tu estas entre  " + radius + " metros de este punto").openPopup();

	L.circle(e.latlng, radius).addTo(myMap);
}

function onLocationError(e) {
	alert(e.message);
}

myMap.on('locationfound', onLocationFound);
myMap.on('locationerror', onLocationError);

myMap.locate({setView: true, maxZoom: 16});


//añadir y eliminar circulos mapa
let bandera = false;
function anadirCirculos(data) {
	myMap.setView([data.latitud,data.longitud],8);
	if(bandera == true){
		grupoCirculos.clearLayers();
	}
	let circulo;
	for (var i = 0; i < data.regiones.length; i++) {
		var tamanioCirculo = calcularTamanio(data.regiones[i].habitantes);
		if(data.regiones[i].factor > 20 && data.regiones[i].factor < 50){
			circulo = L.circle([data.regiones[i].latitud,data.regiones[i].longitud], tamanioCirculo, {//5000
				color: 'yellow',
				fillColor: '#f7f85f',
				fillOpacity: 0.5
			});
			grupoCirculos.addLayer(circulo);
		}else if(data.regiones[i].factor >= 50 && data.regiones[i].factor < 70){
			circulo = L.circle([data.regiones[i].latitud,data.regiones[i].longitud], tamanioCirculo, {
				color: 'orange',
				fillColor: '#ffa500',
				fillOpacity: 0.5
			});
			grupoCirculos.addLayer(circulo);
		}else if(data.regiones[i].factor >= 70){
			circulo = L.circle([data.regiones[i].latitud,data.regiones[i].longitud], tamanioCirculo, {
				color: 'red',
				fillColor: '#f03',
				fillOpacity: 0.5
			});
			grupoCirculos.addLayer(circulo);
		}
		//.addTo(myMap).bindPopup(data.regiones[i].nombre);
	}
	var i = 0;
	grupoCirculos.eachLayer(function (layer) {
		layer.bindPopup(data.regiones[i].nombre + "n" + data.regiones[i].factor);
		i++;
	});
	grupoCirculos.addTo(myMap);
	bandera = true;
	
}

// color de la leyenda
function getColor(d) {

	if(d>70){
		return '#f03';
	}else if(d >= 50){
		return '#ffa500';
	}else if(d >= 20){
		return '#f7f85f';
	}
	return 'green';
}

//tamaño circulos diferentes sitios
function calcularTamanio(tamanio){

	if(tamanio<1500){
		return tamanio;
	}else{
		return 6000;
	}
	
	//(data.regiones[i].habitantes/100)

}

