proj_root=$(pwd)
echo "current directory is $proj_root"
order_generation_service_version=0.0.1
order_persistence_service_version=0.0.1

# compile order generation service
cd $proj_root/order-generation-service
./gradlew clean jar
cp "build/libs/order-generation-service-"$order_generation_service_version"-SNAPSHOT.jar" build/libs/order-generation-service.jar

# build order generation service image
docker build -t order-generation-service . --no-cache

# compile order persistence service
cd $proj_root/order-persistence-service
./gradlew clean jar
cp "build/libs/order-persistence-service-"$order_persistence_service_version"-SNAPSHOT.jar" build/libs/order-persistence-service.jar

# build order persistence service image
docker build -t order-persistence-service . --no-cache

# start service
cd $proj_root
docker-compose up -d