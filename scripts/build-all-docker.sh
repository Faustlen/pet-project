#!/bin/bash

set -e

ROOT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )/.." && pwd )"

declare -A images
images=(
    ["coreCRM"]="core-crm"
    ["contentLoaderAdapter"]="content-loader"
    ["contentProcess"]="content-processor"
    ["priceHistory"]="price-history"
    ["notificationSender"]="notification-sender"
)

for service in "${!images[@]}"; do
    echo "Сборка Docker-образа для $service..."
    cd "$ROOT_DIR/$service" || exit
    docker build -t "${images[$service]}" .
done

echo "Все Docker-образы созданы."
