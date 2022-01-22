proj_root=$(pwd)
echo "current directory is $proj_root"

# compile order generation service
cd $proj_root/order-generation-service
./gradlew clean bootJar

# build order generation service image
docker build -t order-generation-service . --no-cache

# compile order persistence service
cd $proj_root/order-persistence-service
./gradlew clean bootJar

# build order persistence service image
docker build -t order-persistence-service . --no-cache

# start service
cd $proj_root
docker-compose up -d