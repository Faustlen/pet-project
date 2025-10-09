# Запуск локально через Docker Compose

1. Собрать артефакты для сервисов при помощи скрипта /scripts/build-all-services.sh или вручную:
    ``` bash
    cd coreCRM
    ./gradlew clean build -x test

    cd ../contentLoaderAdapter
    ./gradlew clean build -x test

    cd ../contentProcess
    ./gradlew clean build -x test
    
    cd ../priceHistory
    ./gradlew clean build -x test
    
    cd ../notificationSender
    ./gradlew clean build -x test
    ```

2. Создать Docker-образы при помощи скрипта /scripts/build-all-docker.sh или вручную:
    ``` bash
    cd coreCRM
    docker build -t core-crm .
    
    cd ../contentLoaderAdapter
    docker build -t content-loader .
    
    cd ../contentProcess
    docker build -t content-processor .
    
    cd ../priceHistory
    docker build -t price-history .
    
    cd ../notificationSender
    docker build -t notification-sender .
    ```

3. Поднять инфраструктуру и сервисы:
    ``` bash
    docker-compose up
    ```
    
4. Доступные сервисы:
    
    - **Postgres**: `localhost:5432` (логин/пароль: admin/123)
    - **Kafka UI**: `localhost:8081`
    - **CoreCRM**: `localhost:8080`


# Запуск в Kubernetes

1. Собрать артефакты для сервисов при помощи скрипта /scripts/build-all-services.sh или вручную:
    ``` bash
    cd coreCRM
    ./gradlew clean build -x test

    cd ../contentLoaderAdapter
    ./gradlew clean build -x test

    cd ../contentProcess
    ./gradlew clean build -x test
    
    cd ../priceHistory
    ./gradlew clean build -x test
    
    cd ../notificationSender
    ./gradlew clean build -x test
    ```

2. Создать Docker-образы при помощи скрипта /scripts/build-all-docker.sh или вручную:
    ``` bash
    cd coreCRM
    docker build -t core-crm .
    
    cd ../contentLoaderAdapter
    docker build -t content-loader .
    
    cd ../contentProcess
    docker build -t content-processor .
    
    cd ../priceHistory
    docker build -t price-history .
    
    cd ../notificationSender
    docker build -t notification-sender .
    ```

3. Создать `ConfigMap` и `Secrets`:
    ``` bash
    kubectl apply -f k8s/config-maps.yaml
    kubectl apply -f k8s/secrets.yaml
    ```
4. Создать PVC:
    ``` bash
    kubectl apply -f k8s/pvcs.yaml
    ```
    
5. Поднять инфраструктуру:
    ``` bash
    kubectl apply -f k8s/postgres-deployment.yaml
    kubectl apply -f k8s/kafka-deployment.yaml
    kubectl apply -f k8s/kafka-ui-deployment.yaml
    ```
    
6. Запустить сервисы:
    ``` bash
    kubectl apply -f k8s/content-loader-deployment.yaml
    kubectl apply -f k8s/content-processor-deployment.yaml
    kubectl apply -f k8s/core-crm-deployment.yaml
    kubectl apply -f k8s/notification-sender-deployment.yaml
    kubectl apply -f k8s/price-history-deployment.yaml
    ```

7. Доступные сервисы:
    
    - **Postgres**: `localhost:30032` (логин/пароль: admin/123)
    - **Kafka UI**: `localhost:30081`
    - **CoreCRM**: `localhost:30080`