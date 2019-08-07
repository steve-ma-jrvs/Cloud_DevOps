# Cloud_DevOps

## Overview

* [Docker containerize trading-app](#Docker-Containerize-Trading-App)
* [Migrating trading_app to the cloud](#Migrating-trading_app-to-the-cloud)

## Docker Containerize Trading-App

1. Start docker
```posh
#auto start
sudo systemctl enable docker
#start service
sudo systemctl start docker
sudo docker -v
```

2. Creating a network bridge

```bash
sudo docker network create --driver bridge trading-net
```

3. Building PSQL image

```
git clone git@github.com:steve-ma-jrvs/Cloud_DevOps.git
cd trading_app/psql

#ls image
docker image ls

#check Docker file
cat Dockerfile

#build docker image
docker build -t jrvs-psql .

#run psql
#attach this container to the network bridge
sudo docker run --rm --name jrvs-psql \
--restart unless-stopped \
-e POSTGRES_PASSWORD=password \
-e POSTGRES_DB=jrvstrading \
-e POSTGRES_USER=postgres \
--network trading-net \
-d -p 5432:5432 -t jrvs-psql

#list runing containers
docker container ls
```

4. Building trading-app image

```bash
cd trading_app/

#check Docker file
cat Dockerfile

#build trading app
docker build -t trading-app .

IEX_TOKEN='your_token'

sudo docker run --rm --name trading-app-v1 \
--restart unless-stopped \
-e 'PSQL_URL=jdbc:postgresql://jrvs-psql:5432/jrvstrading' \
-e 'PSQL_USER=postgres' \
-e 'PSQL_PASSWORD=password' \
-e "IEX_PUB_TOKEN=${IEX_TOKEN}" \
--network trading-net \
-p 8080:8080 -t trading-app

#list running containers
docker container ls
```

5. Verify the trading-app

```bash
#verify health
curl localhost:8080/health
#verify Swagger UI from your browser
localhost:8080/swagger-ui.html
```

## Migrating trading_app to the cloud

| Service         | Local                     | Cloud/AWS                               |
| --------------- | ------------------------- | --------------------------------------- |
| Host            | Laptop                    | EC2                                     |
| DB(PSQL)        | Docker                    | RDS                                     |
| Security        | N/A                       | EC2-Security Group                      |
| Load Balancer   | N/A                       | EC2-ALB<br />Application Load Balancer  |
| Scaling         | Verticaling <br />Scaling | Horizontal Scaling<br />EC2-Autoscaling |
| Fault-tolerance | N/A                       | EC2-AutoScaling<br />VPC, etc.          |

### Detailed Architecture Diagram
![image1](https://github.com/steve-ma-jrvs/Cloud_DevOps/blob/master/images/Trading-App-AWS.png)
