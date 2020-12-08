#/bin/sh

while true 
do

	python3 /main.py >> scrapper.log
	/calcularfactor >> factor.log
	sleep 14400
done
