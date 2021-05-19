docker stop ecom-runner
docker stop ecom-db

docker rm ecom-db
docker rm ecom-runner

docker image rm ecom-app
docker image rm ecom-db
