# Environment Variables

- URL_CFG_SERVER:  url de servidor configuraciones para el microservicio product

# How to run in docker playground

docker build --tag product . <br/>
URL_SERVER="<URL>" && export URL_SERVER <br/>
docker run --env URL_CFG_SERVER="$URL_SERVER" --env PORT="8080" -p 8080:8080 product <br/>