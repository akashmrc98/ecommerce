docker container run --name ecom-db --network ecom-net -p 3307:3307 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=ecommerce -e MYSQL_USER=user -e MYSQL_PASSWORD=user -d mysql:latest
docker build -t ecom-app .
docker container run --network ecom-net --name ecom-runner -p 8081:8081 -d ecom-app
