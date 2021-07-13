# Environment Variables

- URL_CFG_SERVER:  url de servidor configuraciones para el microservicio product

# How to run in docker playground

docker build --tag product .
URL_CFG_SERVER="http://ip172-18-0-26-c3mulmvqf8u00084qm1g-9999.direct.labs.play-with-docker.com/" && export URL_CFG_SERVER
docker run product