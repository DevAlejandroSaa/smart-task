#!/bin/bash

set -e

PROJECT_DIR="$(cd "$(dirname "$0")/../app/smart-task" && pwd)"

cd "$PROJECT_DIR"

echo "==> Ejecutando pruebas y generando cobertura..."
mvn test

echo "==> Generando Javadoc..."
mvn javadoc:javadoc

echo "==> Generando JAR..."
mvn package

echo
echo "==> Proceso completado."