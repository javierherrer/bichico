FROM ubuntu 
RUN apt-get update && apt-get upgrade 
RUN apt-get install -y git python3-pip python3 wget

COPY requirements.txt .
COPY calcularfactor .
COPY calculo.sh .
RUN pip3 install --no-cache-dir -r requirements.txt
RUN git clone https://github.com/fuster92/trendsScrapper
RUN chmod +x /calculo.sh 
RUN chmod +x /calcularfactor 
CMD /calculo.sh
