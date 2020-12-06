FROM golang:latest AS builder
WORKDIR /go/src/CalculadorFactorRiesgo
COPY ./CalculadorFactorRiesgo .
RUN GOOS=linux go build -o calcularfactor ./cmd/calcularfactor.go



FROM ubuntu 
RUN apt-get update && apt-get upgrade 
RUN apt-get install -y git python3-pip python3 wget

COPY --from=builder /go/src/CalculadorFactorRiesgo/calcularfactor .
COPY requirements.txt .
COPY calculo.sh .
RUN pip3 install --no-cache-dir -r requirements.txt
RUN git clone https://github.com/fuster92/trendsScrapper
RUN chmod +x /calculo.sh 
RUN chmod +x /calcularfactor 



CMD /calculo.sh
