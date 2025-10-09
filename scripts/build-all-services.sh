#!/bin/bash

set -e

ROOT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )/.." && pwd )"

services=("coreCRM" "contentLoaderAdapter" "contentProcess" "priceHistory" "notificationSender")

for service in "${services[@]}"; do
    echo "Сборка $service..."
    cd "$ROOT_DIR/$service" || exit
    ./gradlew clean build -x test
done

echo "Сборка всех сервисов завершена."
