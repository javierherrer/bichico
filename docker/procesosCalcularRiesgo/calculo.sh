#/bin/sh

while true 
do

	python3 /trendsScrapper/main.py >> scrapper.log
	/calcularfactor
	sleep 600
done
