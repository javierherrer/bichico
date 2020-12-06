#/bin/sh

while true 
do

	python3 /trendsScrapper/main.py >> scrapper.log
	/calcularfactor > /dev/null
	sleep 14400
done
