FROM openjdk:8
EXPOSE 8888
ADD target/PetPalooza-1.0.jar PetPalooza-1.0.jar
ENTRYPOINT ["java", "-jar", "/PetPalooza-1.0.jar"]